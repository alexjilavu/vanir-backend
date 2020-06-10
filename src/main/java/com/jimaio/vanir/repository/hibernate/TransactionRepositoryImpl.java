package com.jimaio.vanir.repository.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> getTransactions(Integer limit) {
		Session session = sessionFactory.getCurrentSession();
		Query<Transaction> query = session.createQuery("SELECT t FROM Transaction t ORDER BY t.date DESC");
		return query.setMaxResults(limit).list();
	}

}
