package com.jimaio.vanir.repository.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jimaio.vanir.domain.Transaction;
import com.jimaio.vanir.domain.User;
import com.jimaio.vanir.repository.TransactionRepository;

@Repository
@Transactional
public class TransactionRepositoryImpl extends GenericRepositoryImpl<Transaction> implements TransactionRepository {

	public TransactionRepositoryImpl() {
		super.setClazz(Transaction.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> getTransactionsOfUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		Query<Transaction> query = session.createQuery("SELECT t FROM Transaction t "
				+ "WHERE t.recipientAccount.user.id = :userId OR t.senderAccount.user.id = :userId ORDER BY t.date DESC");
		query.setParameter("userId", user.getId());
		return query.list();
	}

}
