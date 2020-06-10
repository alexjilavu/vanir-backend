package com.jimaio.vanir.service.spring;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jimaio.vanir.domain.Account;
import com.jimaio.vanir.domain.Transaction;
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
		account.setBalance(0d);
		account.setCreationDate(new Date());
		account.setCurrency(currencyService.getItem(1));
		account.setUser(user);
		
		account = create(account);
		
		cardService.createCard(account);
	} 

	public Account getAccountOfUser(User user) {
		return accountRepository.getAccountByUser(user);
	}
	
	public boolean processTransaction(Account account, Transaction transaction) {
		Account recipient = transaction.getRecipientAccount();
		Account sender = transaction.getSenderAccount();
		
		// topup transaction
		if (recipient.equals(sender) && recipient.equals(account)) {
			account.setBalance(account.getBalance() + transaction.getAmount());
			accountRepository.update(account);
			return true;
		}
		
		// the given account is the sender
		if (sender.equals(account)) {
			if (account.getBalance() < transaction.getAmount() || transaction.getAmount() < 0)
				return false;
			
			account.setBalance(account.getBalance() - transaction.getAmount());
			accountRepository.update(account);
			
			return true;
		}
		
		// the given account is the recipient
		if (recipient.equals(account)) {
			Double amount = transaction.getAmount();
			
			if (recipient.getCurrency() != null && recipient.getCurrency() != null && !recipient.getCurrency().equals(sender.getCurrency()))
				amount = currencyService.exchange(sender.getCurrency(), recipient.getCurrency(), transaction.getAmount());
			
			account.setBalance(account.getBalance() + amount);
			accountRepository.update(account);
			
			return true;
		}
		
		return false;
	}
}
