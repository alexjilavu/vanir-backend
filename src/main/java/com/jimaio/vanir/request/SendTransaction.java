package com.jimaio.vanir.request;

import lombok.Data;

@Data
public class SendTransaction {

	public String recipientId;
	public Double value;
	
}
