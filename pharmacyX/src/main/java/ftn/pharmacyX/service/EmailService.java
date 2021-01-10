package ftn.pharmacyX.service;

import org.springframework.mail.MailException;

import ftn.pharmacyX.model.users.User;

public interface EmailService {

	public void sendMail(User user, String subject, String text) throws MailException;
}
