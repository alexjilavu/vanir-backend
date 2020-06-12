package com.jimaio.vanir.response;

import lombok.Data;

@Data
public class TransactionResponse {

	private Integer id;
	
	private String recipientName;
	
	private double amount;
	
	private String date;
	
	private String currency;
	
}
