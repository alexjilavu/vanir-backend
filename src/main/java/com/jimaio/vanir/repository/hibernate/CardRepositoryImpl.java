package com.jimaio.vanir.repository.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jimaio.vanir.domain.Card;
import com.jimaio.vanir.repository.CardRepository;

@Repository
@Transactional
public class CardRepositoryImpl extends GenericRepositoryImpl<Card> implements CardRepository {

	public CardRepositoryImpl() {
		super.setClazz(Card.class);
	}
}
