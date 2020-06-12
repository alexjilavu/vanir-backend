package com.jimaio.vanir.service;

import java.util.List;

import com.jimaio.vanir.domain.Transaction;
import com.jimaio.vanir.domain.User;

public interface TransactionService extends GenericService<Transaction> {

	public Transaction topUpBalance(Integer id, Double value);
	
	public Transaction send(Integer senderId, Integer recipientId, double value);
	
	public List<Transaction> getTransactionsOfUser(User user);
}
