package com.jimaio.vanir.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jimaio.vanir.domain.User;
import com.jimaio.vanir.repository.UserRepository;
import com.jimaio.vanir.service.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService{

	@Autowired
	UserRepository userRepository;
	
}
