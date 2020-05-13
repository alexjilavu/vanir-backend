package com.jimaio.vanir.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jimaio.vanir.domain.Transaction;
import com.jimaio.vanir.repository.TransactionRepository;
import com.jimaio.vanir.service.TransactionService;

@Service
public class TransactionServiceImpl extends GenericServiceImpl<Transaction> implements TransactionService{

	@Autowired
	TransactionRepository transactionRepository; 
 
}
