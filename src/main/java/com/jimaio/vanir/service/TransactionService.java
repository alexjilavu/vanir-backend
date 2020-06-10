package com.jimaio.vanir.service;

import java.util.List;

import com.jimaio.vanir.domain.Transaction;

public interface TransactionService extends GenericService<Transaction> {

	public Transaction topUpBalance(Integer id, Double value);
	
	public List<Transaction> getItems(Integer limit);
	
	public Transaction send(Integer senderId, Integer recipientId, double value);
}
