package com.jimaio.vanir.response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.jimaio.vanir.domain.Card;

import lombok.Data;

@Data
public class CardResponse {

	private Integer id;
	
	private String cardholderName;
	
	private String number;
	
	private String expirationDate;
	
	private String cvv;

	public CardResponse(Card card) {
		this.id = card.getId().intValue();
		this.cardholderName = card.getOwnerName();
		this.number = card.getCardNumber();
		this.cvv = card.getCcv();
		
		DateFormat df = new SimpleDateFormat("MM/yy");
		this.expirationDate = df.format(card.getExpirationDate());
	}
	
	
}
