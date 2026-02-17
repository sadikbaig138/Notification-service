package in.rent360.notificationservice.Notifictation;

import org.springframework.boot.context.properties.ConfigurationProperties;

//@ConfigurationProperties(prefix = "twilio")
public class SmsAuthentication {
	private String accountSid;
	private String authToken;
	private String fromNumber;
	
	public String getAccountSid() {
		return accountSid;
	}
	
	public String getAuthToken() {
		return authToken;
	}

	public String getFromNumber() {
		return fromNumber;
	}
}
