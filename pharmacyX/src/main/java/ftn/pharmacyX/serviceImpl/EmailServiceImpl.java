package ftn.pharmacyX.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import ftn.pharmacyX.model.users.User;
import ftn.pharmacyX.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	private JavaMailSender javaMailSender;
	
	@Autowired
	public EmailServiceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	@Override
	public void sendMail(User user, String subject, String text) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom("bgkgsiit@gmail.com");
		mail.setSubject(subject);
		mail.setText(text);
		javaMailSender.send(mail);
		
	}

}
