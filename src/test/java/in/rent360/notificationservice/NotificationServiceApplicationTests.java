package in.rent360.notificationservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class NotificationServiceApplicationTests {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void contextLoads() throws Exception {
		String request= """
				{
				"subject":"Welcome!!",
				"recipient":"sadikbaig190@gmail.com",
				"message":"Welcome!"
				}""";
		
		mockMvc.perform(post("/notification/sendMail")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request))
				//.andExpect(status().)
				.andExpect(jsonPath("$.status").value("Queue"));
	}

}
