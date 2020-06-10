package com.jimaio.vanir.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jimaio.vanir.domain.Account;
import com.jimaio.vanir.domain.User;
import com.jimaio.vanir.repository.AccountRepository;

@Repository
@Transactional
public class AccountRepositoryImpl extends GenericRepositoryImpl<Account> implements AccountRepository {

	public AccountRepositoryImpl() {
		super.setClazz(Account.class);
	}
	
	@SuppressWarnings("unchecked")
	public Account getAccountByUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		Query<Account> query = session.createQuery("select a from Account a where a.user.id = :userId").setParameter("userId", user.getId());
		return query.getSingleResult();
	}
}
