package com.jimaio.vanir.repository.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jimaio.vanir.domain.Account;
import com.jimaio.vanir.repository.AccountRepository;

@Repository
@Transactional
public class AccountRepositoryImpl extends GenericRepositoryImpl<Account> implements AccountRepository {

	public AccountRepositoryImpl() {
		super.setClazz(Account.class);
	}
}
