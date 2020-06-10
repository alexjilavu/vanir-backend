package com.jimaio.vanir.repository.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jimaio.vanir.domain.Currency;
import com.jimaio.vanir.repository.CurrencyRepository;

@Repository
@Transactional
public class CurrencyRepositoryImpl extends GenericRepositoryImpl<Currency> implements CurrencyRepository {

	public CurrencyRepositoryImpl() {
		super.setClazz(Currency.class);
	}
}
