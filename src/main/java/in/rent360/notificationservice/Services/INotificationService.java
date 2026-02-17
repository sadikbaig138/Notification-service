package in.rent360.notificationservice.Services;

import in.rent360.notificationservice.Notifictation.Notification;
import in.rent360.notificationservice.Notifictation.NotificationType;

public interface INotificationService {
    NotificationType getType();
    void sendNotification(Notification notification) throws Exception;
}
