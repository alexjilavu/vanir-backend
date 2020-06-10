package com.jimaio.vanir.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jimaio.vanir.domain.Transaction;
import com.jimaio.vanir.repository.TransactionRepository;
import com.jimaio.vanir.service.TransactionService;

@Service
@Transactional
public class TransactionServiceImpl extends GenericServiceImpl<Transaction> implements TransactionService{

	@Autowired
	TransactionRepository transactionRepository; 
 
}
