package com.jimaio.vanir.repository.hibernate;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jimaio.vanir.domain.User;
import com.jimaio.vanir.repository.UserRepository;

@Repository
@Transactional(readOnly = false)
public class UserRepositoryImpl extends GenericRepositoryImpl<User> implements UserRepository {
	
	public UserRepositoryImpl() {
		super.setClazz(User.class);
	}

	public User getByPhoneNumber(String phoneNumber) {
		if (cb == null)
			cb = sessionFactory.getCriteriaBuilder();
		CriteriaQuery<User> crit = cb.createQuery(clazz);
		Root<User> root = crit.from(clazz);
		crit.where(cb.equal(root.get("phoneNumber"), phoneNumber));
		TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(crit);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public User getByCredentials(String email, String password) {
		if (cb == null)
			cb = sessionFactory.getCriteriaBuilder();
		CriteriaQuery<User> crit = cb.createQuery(clazz);
		Root<User> root = crit.from(clazz);
		crit.where(cb.and(cb.equal(root.get("email"), email), cb.equal(root.get("password"), password)));
		TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(crit);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
