package com.jimaio.vanir.request;

import lombok.Data;

@Data
public class SendTransaction {

	public Integer recipientId;
	public Double value;
	
}
