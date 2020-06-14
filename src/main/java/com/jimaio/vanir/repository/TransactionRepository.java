package com.jimaio.vanir.repository;

import java.util.List;

import com.jimaio.vanir.domain.Transaction;
import com.jimaio.vanir.domain.User;

public interface TransactionRepository extends GenericRepository<Transaction>{

	public List<Transaction> getTransactionsOfUser(User user);
	
}
