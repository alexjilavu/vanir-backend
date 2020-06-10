package com.jimaio.vanir.service.spring;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jimaio.vanir.domain.Account;
import com.jimaio.vanir.domain.Card;
import com.jimaio.vanir.repository.AccountRepository;
import com.jimaio.vanir.repository.CardRepository;
import com.jimaio.vanir.service.CardService;
import com.jimaio.vanir.utils.Utils;

@Service
@Transactional
public class CardServiceImpl extends GenericServiceImpl<Card> implements CardService{

	@Autowired
	CardRepository cardRepository;
	
	@Autowired
	public CardServiceImpl(CardRepository cardRepository) {
		super.setRepository(cardRepository);
		this.cardRepository = cardRepository;
	}

	@Override
	public void createCard(Account account) {
		Card card = new Card();
		card.setCardNumber(Utils.generateRandomNumber(16));
		card.setCcv(Utils.generateRandomNumber(3));
		card.setCreationDate(new Date());
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, 5);
		
		card.setExpirationDate(calendar.getTime());
		card.setOwnerName(account.getUser().getName());
		card.setPinCode(Utils.generateRandomNumber(4));
		card.setAccount(account);
		
		create(card);
	} 

}
