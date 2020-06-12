package com.jimaio.vanir.service.spring;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jimaio.vanir.domain.Account;
import com.jimaio.vanir.domain.Transaction;
import com.jimaio.vanir.domain.User;
import com.jimaio.vanir.repository.TransactionRepository;
import com.jimaio.vanir.service.AccountService;
import com.jimaio.vanir.service.TransactionService;
import com.jimaio.vanir.service.UserService;

@Service
@Transactional
public class TransactionServiceImpl extends GenericServiceImpl<Transaction> implements TransactionService{

	TransactionRepository transactionRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AccountService accountService;

	@Autowired
	public TransactionServiceImpl(TransactionRepository transactionRepository) {
		super.setRepository(transactionRepository);
		this.transactionRepository = transactionRepository;
	}
	
	@Override
	public Transaction topUpBalance(Integer id, Double value) {
		
		User user = userService.getItem(id);
		if (user == null)
			return null;
		
		Account account = accountService.getAccountOfUser(user);
		if (account == null)
			return null;
		
		Transaction transaction = new Transaction();
		transaction.setAmount(value);
		transaction.setCurrency(account.getCurrency());
		transaction.setDate(new Date());
		transaction.setRecipientAccount(account);
		transaction.setSenderAccount(account);
		
		Boolean ok = accountService.processTransaction(account, transaction);
		
		if (ok)
			return create(transaction);
		
		return null;
	}
	
	public Transaction send(Integer senderId, Integer recipientId, double value) {
		User sender = userService.getItem(senderId);
		User recipient = userService.getItem(recipientId);
		
		if (sender == null || recipient == null)
			return null;
		
		Account senderAccount = accountService.getAccountOfUser(sender);
		Account recipientAccount = accountService.getAccountOfUser(recipient);
		
		if (senderAccount == null || recipientAccount == null)
			return null;
		
		if (senderAccount.getBalance() < value)
			return null;
		
		Transaction transaction = new Transaction();
		transaction.setAmount(value);
		transaction.setCurrency(senderAccount.getCurrency());
		transaction.setDate(new Date());
		transaction.setRecipientAccount(recipientAccount);
		transaction.setSenderAccount(senderAccount);
		
		Boolean okSend = accountService.processTransaction(senderAccount, transaction);
		Boolean okReceived = accountService.processTransaction(recipientAccount, transaction);
		
		if (okSend && okReceived) {
			create(transaction);
			return transaction;
		}
		
		return null;
	}

	public List<Transaction> getTransactionsOfUser(User user) {
		return transactionRepository.getTransactionsOfUser(user);
	}
 
}
