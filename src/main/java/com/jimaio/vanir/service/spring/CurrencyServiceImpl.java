package com.jimaio.vanir.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jimaio.vanir.domain.Currency;
import com.jimaio.vanir.repository.CurrencyRepository;
import com.jimaio.vanir.service.CurrencyService;

@Service
@Transactional
public class CurrencyServiceImpl extends GenericServiceImpl<Currency> implements CurrencyService{

	@Autowired
	CurrencyRepository currencyRepository; 

}
