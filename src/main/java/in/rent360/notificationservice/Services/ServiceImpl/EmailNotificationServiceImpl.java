package in.rent360.notificationservice.Services.ServiceImpl;

import in.rent360.notificationservice.NotificationException;
import in.rent360.notificationservice.Notifictation.Notification;
import in.rent360.notificationservice.Notifictation.NotificationType;
import in.rent360.notificationservice.Services.INotificationService;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailNotificationServiceImpl implements INotificationService {

	private final JavaMailSender mailSender;

    private Logger logger= LogManager.getLogger(NotificationServiceImpl.class);

    @Value("${spring.mail.username}")
    private String addressfrom;

    private TemplateEngine templateEngine;
    
    public EmailNotificationServiceImpl(JavaMailSender mailSender,TemplateEngine engine) {
        this.mailSender = mailSender;
        this.templateEngine=engine;
    }

    @Override
    public NotificationType getType() {
        return NotificationType.EMAIL;
    }

    @Override
    public void sendNotification(Notification notification) throws Exception {
    	try{
    		Context context=new Context();
    		context.setVariable("content", notification.getMessage());
        	String processedString = templateEngine.process("template", context);
        	logger.info("sending notification method arrived");
        
        	MimeMessage message=mailSender.createMimeMessage();
        	MimeMessageHelper helper=new MimeMessageHelper(message,true,"UTF-8");
        	helper.setFrom(addressfrom);
        	helper.setTo(notification.getRecipient());
        	helper.setSubject(notification.getSubject());
        	helper.setText(processedString,true);
        	mailSender.send(message);
    	}catch(Exception e) {
            throw new Exception("Failed To send Email {}",e);
    	}
    }
}
