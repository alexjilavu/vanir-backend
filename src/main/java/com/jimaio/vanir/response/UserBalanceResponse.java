package com.jimaio.vanir.response;

import com.jimaio.vanir.Constants;
import com.jimaio.vanir.domain.Account;

import lombok.Data;

@Data
public class UserBalanceResponse {

	private double balance;
	
	private String currency;
	
	private String ronRate;
	
	private String usdRate;
	
	private String eurRate;
	
	public UserBalanceResponse(Account account) {
		this.balance = account.getBalance();
		this.currency = account.getCurrency() != null ? account.getCurrency().getName() : "RON";
		this.eurRate = Constants.EUR_RON_EXCHANGE_RATE.toString();
		this.usdRate = Constants.USD_RON_EXCHANGE_RATE.toString();
		this.ronRate = "1";
	}
	
	public UserBalanceResponse() {
		
	}
	
}
