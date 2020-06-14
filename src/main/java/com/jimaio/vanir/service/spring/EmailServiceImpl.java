package com.jimaio.vanir.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import com.jimaio.vanir.domain.User;
import com.jimaio.vanir.service.EmailService;
import com.jimaio.vanir.domain.Transaction;

@Service("emailService")
public class EmailServiceImpl implements EmailService {
	
	@Value("${spring.mail.username}")
	String senderUsername;
	
	@Autowired
    public JavaMailSender javaMailSender;
	
	@Value("${mailgun.api.key}")
	private String API_KEY;
	
	@Value("${mailgun.domain.name}")
	private String domainName;
	
 
	@Override
	public void sendWelcomeMail(User user) {
		
	    try {
	    	System.out.println("Sending welcome email to " + user.getEmail());
	    	
			sendSimpleMessage(user.getEmail(), "Welcome to Vanir App!", "Hello " + user.getName() + ",\n" + "Your account has been successfully created.");
			
			System.out.println("Welcome email sent to " + user.getEmail());
	    } catch (UnirestException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public void sendNotificationToSender(Transaction transaction, User sender) {
		if (!transaction.getSenderAccount().getUser().getId().equals(sender.getId()))
			return;	
		
		User receiver = transaction.getRecipientAccount().getUser();
 
	    try {
			sendSimpleMessage(sender.getEmail(), "Transaction confirmed", 
					"Hello " + sender.getName() + ",\n" + "You sent " + transaction.getAmount() + " " + transaction.getCurrency().getName() + " to " + receiver.getName() + ".");
		} catch (UnirestException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void sendNotificationToRecipient(Transaction transaction, User recipient) {
		if (!transaction.getRecipientAccount().getUser().getId().equals(recipient.getId()))
			return;	
		
		User sender = transaction.getSenderAccount().getUser();
	    
	    try {
			sendSimpleMessage(recipient.getEmail(), "Transaction confirmed", 
					"Hello " + recipient.getName() + ",\n" + "You received " + transaction.getAmount() + " " + transaction.getCurrency().getName() + " from " + sender.getName());
		} catch (UnirestException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void sendTopupNotification(Transaction transaction) {
		User user = transaction.getRecipientAccount().getUser();
	    
	    try {
			sendSimpleMessage(user.getEmail(), "Transaction confirmed", 
					"Hello " + user.getName() + ",\n" + "Your account has been toped-up with " + transaction.getAmount() + " " + transaction.getCurrency().getName() + ".");
		} catch (UnirestException e) {
			e.printStackTrace();
		}	
	}
	
	public JsonNode sendSimpleMessage(String to, String subject, String msg) throws UnirestException {
		HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + domainName + "/messages")
			.basicAuth("api", API_KEY)
			.queryString("from", "vanir@jimaio.com")
			.queryString("to", to)
			.queryString("subject", subject)
			.queryString("text", msg)
			.asJson();
		return request.getBody();
	}
	
}
