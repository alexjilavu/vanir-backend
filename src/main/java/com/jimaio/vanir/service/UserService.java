package com.jimaio.vanir.service;

import com.jimaio.vanir.domain.User;

public interface UserService extends GenericService<User>{

	public String checkCredentials(String email, String password);
	
	public String checkCredentials(String phoneNumber);
	
	public void createUser(User user);
	
	public User getByApiKey(String apiKey);
	
}
