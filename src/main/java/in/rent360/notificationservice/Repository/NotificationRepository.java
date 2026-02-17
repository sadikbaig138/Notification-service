package in.rent360.notificationservice.Repository;

import in.rent360.notificationservice.Notifictation.Notification;
import in.rent360.notificationservice.Services.INotificationService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {

}
