package com.jimaio.vanir.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jimaio.vanir.Constants;
import com.jimaio.vanir.domain.Currency;
import com.jimaio.vanir.repository.CurrencyRepository;
import com.jimaio.vanir.service.CurrencyService;

@Service
@Transactional
public class CurrencyServiceImpl extends GenericServiceImpl<Currency> implements CurrencyService{

	CurrencyRepository currencyRepository; 

	@Autowired
	public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
		super.setRepository(currencyRepository);
		this.currencyRepository = currencyRepository;
	}
	
	public Double exchange(Currency from, Currency to, Double amount) {
		if (from.equals(to))
			return amount;
		
		if (from == null || to == null)
			return amount;
		
		Double exchangeRate = 1d;
		Double exchangedAmount = amount;
		if (from.getName().equals("RON")) {
			if (to.getName().equals("EUR")) {
				exchangeRate = Constants.EUR_RON_EXCHANGE_RATE;
				exchangedAmount = amount / exchangeRate;
			} else
				if (to.getName().equals("USD")) {
					exchangeRate = Constants.USD_RON_EXCHANGE_RATE;
					exchangedAmount = amount / exchangeRate;
				}
		} else
			if (from.getName().equals("EUR")) {
				if (to.getName().equals("RON")) {
					exchangeRate = Constants.EUR_RON_EXCHANGE_RATE;
					exchangedAmount = amount * exchangeRate;
				} else
					if (to.getName().equals("USD")) {
						exchangeRate = Constants.EUR_USD_EXCHANGE_RATE;
						exchangedAmount = amount * exchangeRate;
					}
			} else {
				if (from.getName().equals("USD")) {
					if (to.getName().equals("RON")) {
						exchangeRate = Constants.USD_RON_EXCHANGE_RATE;
						exchangedAmount = amount * exchangeRate;
					} else
						if (to.getName().equals("EUR")) {
							exchangeRate = Constants.EUR_USD_EXCHANGE_RATE;
							exchangedAmount = amount / exchangeRate;
						}
				}
			}
		
		return exchangedAmount;
	}
}
