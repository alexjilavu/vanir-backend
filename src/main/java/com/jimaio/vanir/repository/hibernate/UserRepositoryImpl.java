package com.jimaio.vanir.repository.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jimaio.vanir.domain.User;
import com.jimaio.vanir.repository.UserRepository;

@Repository
@Transactional
public class UserRepositoryImpl extends GenericRepositoryImpl<User> implements UserRepository {

	public UserRepositoryImpl() {
		super.setClazz(User.class);
	}
}
