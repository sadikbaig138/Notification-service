package in.rent360.notificationservice.RestImpl;


import in.rent360.notificationservice.Notifictation.Notification;
import in.rent360.notificationservice.Notifictation.NotificationRequest;
import in.rent360.notificationservice.Services.ServiceImpl.NotificationServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationRestImpl {

    private final NotificationServiceImpl notificationService;

    public NotificationRestImpl(NotificationServiceImpl notificationService) {
        this.notificationService=notificationService;
    }

    @PostMapping("/sendMail")
    public ResponseEntity<Map> sendMail(@RequestBody NotificationRequest request) {
    	CompletableFuture<Notification> future= notificationService.sendMailNotification(request);
    	Map map=new HashMap<>();
    	map.put("status", "Queue");
    	map.put("Message", "Notification will sent shortly!!!!!");
    	return ResponseEntity.accepted().body(map);
    }
}
