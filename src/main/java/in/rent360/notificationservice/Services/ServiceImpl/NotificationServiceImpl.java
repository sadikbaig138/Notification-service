package in.rent360.notificationservice.Services.ServiceImpl;

import in.rent360.notificationservice.Notifictation.Notification;
import in.rent360.notificationservice.Notifictation.NotificationRequest;
import in.rent360.notificationservice.Notifictation.NotificationStatus;
import in.rent360.notificationservice.Repository.NotificationRepository;
import in.rent360.notificationservice.Services.INotificationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;


@Service
public class NotificationServiceImpl  {
    
    private final NotificationRepository notificationRepository;

    private final Logger logger= LogManager.getLogger(NotificationServiceImpl.class);
    
    private final INotificationService emailSendService;

    public NotificationServiceImpl(NotificationRepository notificationRepository,EmailNotificationServiceImpl service) {
        this.notificationRepository = notificationRepository;
        this.emailSendService=service;
    }

    @Async
    public CompletableFuture<Notification> sendMailNotification(NotificationRequest request){
        logger.info("start.......");
        Notification notification = new Notification();
        notification.setStatus(NotificationStatus.PROCESSING);
        notification.setMessage(request.getMessage());
        notification.setRecipient(request.getRecipient());
        notification.setSubject(request.getSubject());
        notification.setSentTime(LocalDateTime.now());
        this.notificationRepository.save(notification);
        return sendMail(notification);
    }

    @Retryable(maxAttempts = 4, backoff = @Backoff(delay = 1000, multiplier = 2))
    private CompletableFuture<Notification> sendMail(Notification notification) {
        try{
            logger.info("notification created.......{}",notification.getId());
            emailSendService.sendNotification(notification);
            notification.setStatus(NotificationStatus.SUCCESS);
        }catch(Exception e) {
            notification.setStatus(NotificationStatus.FAILED);
        }
        return CompletableFuture.completedFuture(notificationRepository.save(notification));
    }
}
