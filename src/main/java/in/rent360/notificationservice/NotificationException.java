package in.rent360.notificationservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotificationException {

	@ExceptionHandler(exception = Exception.class)
	public ResponseEntity<String> handleNotificationException(Exception e){
		return ResponseEntity.status(HttpStatus.METHOD_FAILURE).body(e.getMessage());
	}
}
