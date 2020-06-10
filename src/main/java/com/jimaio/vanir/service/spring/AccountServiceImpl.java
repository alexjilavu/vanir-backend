package com.jimaio.vanir.service.spring;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jimaio.vanir.domain.Account;
import com.jimaio.vanir.domain.User;
import com.jimaio.vanir.repository.AccountRepository;
import com.jimaio.vanir.service.AccountService;
import com.jimaio.vanir.service.CardService;
import com.jimaio.vanir.service.CurrencyService;

@Service
@Transactional
public class AccountServiceImpl extends GenericServiceImpl<Account> implements AccountService{

	AccountRepository accountRepository;
	
	@Autowired
	CurrencyService currencyService;
	
	@Autowired
	CardService cardService;

	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository) {
		super.setRepository(accountRepository);
		this.accountRepository = accountRepository;
	}
	
	@Override
	public void createAccount(User user) {
		Account account = new Account();
		account.setBalance(0);
		account.setCreationDate(new Date());
		account.setCurrency(currencyService.getItem(1));
		account.setUser(user);
		
		account = create(account);
		
		cardService.createCard(account);
	} 

	public Account getAccountOfUser(User user) {
		return accountRepository.getAccountByUser(user);
	}
}
