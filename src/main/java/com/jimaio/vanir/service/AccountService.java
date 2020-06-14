package com.jimaio.vanir.service;

import com.jimaio.vanir.domain.Account;
import com.jimaio.vanir.domain.Transaction;
import com.jimaio.vanir.domain.User;

public interface AccountService extends GenericService<Account> {

	public void createAccount(User user);
	
	public Account getAccountOfUser(User user);
	
	public boolean processTransaction(Account account, Transaction transaction);
}
