package com.jimaio.vanir.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.jimaio.vanir.domain.Account;
import com.jimaio.vanir.domain.User;
import com.jimaio.vanir.service.AccountService;
import com.jimaio.vanir.service.UserService;

@RestController
public class AccountsController extends GenericController<Account> {

	private static final long serialVersionUID = -940552912755714006L;
	
	@Autowired
	private AccountService accountService;

	@Autowired
	private UserService userService;
	
	public AccountsController(AccountService accountService) {
		super(accountService);
	}
	
	@GetMapping("/account")
	public Account getAccount(@RequestHeader("userId") Integer id) {
		User user = userService.getItem(id);
		if (user == null)
			return null;
		
		Account account = accountService.getAccountOfUser(user);
		if (account == null)
			return null;
		
		return account;
	}

	@Override
	protected Account getEmptyItem() {
		return new Account();
	}

	@Override
	protected Boolean isNew(Account item) {
		return item.getId() < 1;
	}

}
