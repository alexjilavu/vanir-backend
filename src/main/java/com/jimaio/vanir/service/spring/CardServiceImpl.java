package com.jimaio.vanir.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jimaio.vanir.domain.Card;
import com.jimaio.vanir.repository.CardRepository;
import com.jimaio.vanir.service.CardService;

@Service
public class CardServiceImpl extends GenericServiceImpl<Card> implements CardService{

	@Autowired
	CardRepository cardRepository; 

}
