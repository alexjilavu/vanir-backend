package com.jimaio.vanir.repository.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jimaio.vanir.domain.Transaction;
import com.jimaio.vanir.repository.TransactionRepository;

@Repository
@Transactional
public class TransactionRepositoryImpl extends GenericRepositoryImpl<Transaction> implements TransactionRepository {

	public TransactionRepositoryImpl() {
		super.setClazz(Transaction.class);
	}

}
