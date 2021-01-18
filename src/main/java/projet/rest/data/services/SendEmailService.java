package projet.rest.data.services;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;


@Service
@Component
public class SendEmailService {

	private JavaMailSender javaMailSender;
	@Autowired
	public SendEmailService(JavaMailSender javaMailSender) {
		this.javaMailSender= javaMailSender;
	}
/*	@Bean
    public JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }
	
	 @Autowired
	private JavaMailSender JavaMailSender = javaMailSender();
*/	
	public void sendEmail(String to,String body,String topic) {
		Properties authProps = new Properties();
		authProps.put("mail.smtp","587");
		SimpleMailMessage mail=new SimpleMailMessage();
		mail.setFrom("youradvancer@gmail.com");
		mail.setTo("youradvancerrequest@gmail.com");
		mail.setCc(to);
		mail.setSubject(topic);
		mail.setText(body);
		javaMailSender.send(mail);
	}
	
	public void welcomeMail(String to,String username) {
		String body,topic;
		Properties authProps = new Properties();
		authProps.put("mail.smtp","587");
		SimpleMailMessage mail=new SimpleMailMessage();
		mail.setFrom("youradvancer@gmail.com");
		mail.setTo(to);
		topic= " Welcome "+username+" to Your-Advancer Community";
		mail.setSubject(topic);
		body="Welcome to Your Advancer \r\n"
				+ "Access in-depth resources\r\n"
				+ "Take advantage of available resources to solve problems and advance your career."
				+ " Check out our developer"
				+ " how-tos and getting started guides, videos, cheat sheets, and more.";
		mail.setText(body);
		javaMailSender.send(mail);
	}
}
