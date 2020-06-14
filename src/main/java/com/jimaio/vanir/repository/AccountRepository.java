package com.jimaio.vanir.repository;

import com.jimaio.vanir.domain.Account;
import com.jimaio.vanir.domain.User;

public interface AccountRepository extends GenericRepository<Account> {

	public Account getAccountByUser(User user);
	
}
