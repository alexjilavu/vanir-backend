package com.jimaio.vanir.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jimaio.vanir.domain.Account;
import com.jimaio.vanir.repository.AccountRepository;
import com.jimaio.vanir.service.AccountService;

@Service
@Transactional
public class AccountServiceImpl extends GenericServiceImpl<Account> implements AccountService{

	@Autowired
	AccountRepository accountRepository; 

}
