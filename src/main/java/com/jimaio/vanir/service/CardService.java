package com.jimaio.vanir.service;

import com.jimaio.vanir.domain.Account;
import com.jimaio.vanir.domain.Card;

public interface CardService extends GenericService<Card> {

	public Card createCard(Account account);
	
}
