package com.jimaio.vanir.service;

import com.jimaio.vanir.domain.Currency;

public interface CurrencyService extends GenericService<Currency> {

	public Double exchange(Currency from, Currency to, Double amount);
	
}
