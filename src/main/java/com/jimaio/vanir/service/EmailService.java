package com.jimaio.vanir.service;

import com.jimaio.vanir.domain.Transaction;
import com.jimaio.vanir.domain.User;

public interface EmailService {

	public void sendWelcomeMail(User user);
	
	public void sendNotificationToSender(Transaction transaction, User sender);
	
	public void sendNotificationToRecipient(Transaction transaction, User recipient);
	
	public void sendTopupNotification(Transaction transaction);
	
}
