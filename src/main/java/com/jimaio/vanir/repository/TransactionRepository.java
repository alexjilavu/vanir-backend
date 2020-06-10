package com.jimaio.vanir.repository;

import java.util.List;

import com.jimaio.vanir.domain.Transaction;

public interface TransactionRepository extends GenericRepository<Transaction>{

	List<Transaction> getTransactions(Integer limit);
	
}
