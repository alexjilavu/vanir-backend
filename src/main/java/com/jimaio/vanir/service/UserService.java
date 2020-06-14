package com.jimaio.vanir.service;

import com.jimaio.vanir.domain.User;

public interface UserService extends GenericService<User>{

	public User checkCredentials(String email, String password);
	
	public User checkCredentials(String phoneNumber);
	
	public void createUser(User user);
	
	public User getByApiKey(String apiKey);
	
}
