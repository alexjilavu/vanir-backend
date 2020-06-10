package com.jimaio.vanir.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jimaio.vanir.domain.User;
import com.jimaio.vanir.repository.UserRepository;
import com.jimaio.vanir.service.AccountService;
import com.jimaio.vanir.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService{

	UserRepository userRepository;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super.setRepository(userRepository);
		this.userRepository = userRepository;
	}

	@Override
	public User checkCredentials(String email, String password) {
		User user = userRepository.getByCredentials(email, password);
		if (user != null)
			return user;
		return null;
	}

	@Override
	public User checkCredentials(String phoneNumber) {
		User user = userRepository.getByPhoneNumber(phoneNumber);
		if (user != null)
			return user;
		return null;
	}

	@Override
	public void createUser(User user) {
		user = create(user);
		
		accountService.createAccount(user);
	}
	
	public User getByApiKey(String apiKey) {
		return userRepository.getByApiKey(apiKey);
	}
	
}
