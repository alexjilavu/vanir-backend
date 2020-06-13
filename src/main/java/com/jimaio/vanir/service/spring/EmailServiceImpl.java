package com.jimaio.vanir.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.jimaio.vanir.domain.User;
import com.jimaio.vanir.service.EmailService;
import com.jimaio.vanir.domain.Transaction;

@Service("emailService")
public class EmailServiceImpl implements EmailService {
	
	@Value("${spring.mail.username}")
	String senderUsername;
	
	@Autowired
    public JavaMailSender javaMailSender;
 
	@Override
	public void sendWelcomeMail(User user) {
		SimpleMailMessage mail = new SimpleMailMessage();
		 
	    mail.setFrom(senderUsername);
	    mail.setTo(user.getEmail());
	    mail.setSubject("Welcome to Vanir App!");
	    mail.setText("Hello " + user.getName() + ",\n" + "Your account has been successfully created.");
	    
	    System.out.println("Sending welcome email to " + user.getEmail());
	    
	    javaMailSender.send(mail);
	    
	    System.out.println("Welcome email sent to " + user.getEmail());
	
	}

	@Override
	public void sendNotificationToSender(Transaction transaction, User sender) {
		if (!transaction.getSenderAccount().getUser().getId().equals(sender.getId()))
			return;	
		
		User receiver = transaction.getRecipientAccount().getUser();
		SimpleMailMessage mail = new SimpleMailMessage();
		 
	    mail.setFrom(senderUsername);
	    mail.setTo(sender.getEmail());
	    mail.setSubject("Transaction confirmed");
	    mail.setText("Hello " + sender.getName() + ",\n" + "You sent " + transaction.getAmount() + " " + transaction.getCurrency().getName() + " to " + receiver.getName() + ".");
 
	    javaMailSender.send(mail);		
	}

	@Override
	public void sendNotificationToRecipient(Transaction transaction, User recipient) {
		if (!transaction.getRecipientAccount().getUser().getId().equals(recipient.getId()))
			return;	
		
		User sender = transaction.getSenderAccount().getUser();
		SimpleMailMessage mail = new SimpleMailMessage();
		 
	    mail.setFrom(senderUsername);
	    mail.setTo(recipient.getEmail());
	    mail.setSubject("Transaction confirmed");
	    mail.setText("Hello " + recipient.getName() + ",\n" + "You received " + transaction.getAmount() + " " + transaction.getCurrency().getName() + " from " + sender.getName());
 
	    javaMailSender.send(mail);
	}

	@Override
	public void sendTopupNotification(Transaction transaction) {
		User user = transaction.getRecipientAccount().getUser();
		
		SimpleMailMessage mail = new SimpleMailMessage();
		 
	    mail.setFrom(senderUsername);
	    mail.setTo(user.getEmail());
	    mail.setSubject("Transaction confirmed");
	    mail.setText("Hello " + user.getName() + ",\n" + "Your account has been toped-up with " + transaction.getAmount() + " " + transaction.getCurrency().getName() + ".");
 
	    javaMailSender.send(mail);
	}
}
