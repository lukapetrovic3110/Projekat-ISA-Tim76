package Team76.InternetSoftwareArchitecture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private JavaMailSender javaMailSender;

	private Environment env;

	@Autowired
	public EmailService(JavaMailSender javaMailSender, Environment env) {
		this.javaMailSender = javaMailSender;
		this.env = env;
	}

	public void sendNotificaitionAsync(String emailAddress, String subject, String text) {
		System.out.println(env.getProperty("spring.mail.username"));
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(emailAddress);
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject(subject);
		mail.setText(text);
		javaMailSender.send(mail);
		System.out.println("Email successfully sent!");
	}


}
