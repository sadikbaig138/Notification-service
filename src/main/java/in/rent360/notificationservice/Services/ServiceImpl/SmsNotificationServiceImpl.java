package in.rent360.notificationservice.Services.ServiceImpl;

import in.rent360.notificationservice.Notifictation.Notification;
import in.rent360.notificationservice.Notifictation.NotificationType;
import in.rent360.notificationservice.Notifictation.SmsAuthentication;
import in.rent360.notificationservice.Services.INotificationService;

public class SmsNotificationServiceImpl implements INotificationService{

	private final SmsAuthentication smsAuth;
	
	@Override
	public NotificationType getType() {
		return NotificationType.SMS;
	}

	public SmsNotificationServiceImpl(SmsAuthentication smsAuth) {
		this.smsAuth = smsAuth;
	}
	
	
	@Override
	public void sendNotification(Notification notification) throws Exception {
		try {
			/**
			Twilio.init(this.smsAuth.getAccountSid(), this.smsAuth.getAuthToken());
			Message message=Message.creator(new PhoneNumber(notification.getRecipient()), new PhoneNumber(this.smsAuth.getFromNumber()),notification.getMessage()).create();
			if(message.getErrorCode()!=null) {
				throw new Exception("SMS Failed:");
			}*/
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
