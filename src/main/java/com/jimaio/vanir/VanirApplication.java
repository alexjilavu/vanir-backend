package com.jimaio.vanir;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.jimaio.vanir.domain.Currency;
import com.jimaio.vanir.repository.CurrencyRepository;
import com.jimaio.vanir.repository.UserRepository;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class VanirApplication {
	
	public static CurrencyRepository currencyRepository;

	public static void main(String[] args) {
		SpringApplication.run(VanirApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner run(CurrencyRepository currencyRepository) throws Exception {
        return (String[] args) -> {
            Currency currency = new Currency();
            currency.setName("RON");
            
            currencyRepository.create(currency);
        };
    }
}
