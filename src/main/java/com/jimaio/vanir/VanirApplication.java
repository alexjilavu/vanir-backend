package com.jimaio.vanir;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import com.jimaio.vanir.domain.User;
import com.jimaio.vanir.repository.UserRepository;

@SpringBootApplication
public class VanirApplication {
	
	public static UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(VanirApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner run(UserRepository userRepository) throws Exception {
        return (String[] args) -> {
            User user = new User();
            
            user.setName("Alex");
            user.setFirebaseId("abc");
            user.setBirthDate(new Date());
            user.setAddress("lol");
            user.setPhoneNumber("0743");
            
            userRepository.create(user);
            
            userRepository.findAll().forEach(u -> System.out.println(u));
        };
    }
}
