package com.jimaio.vanir.repository;

import com.jimaio.vanir.domain.User;

public interface UserRepository extends GenericRepository<User>{

	public User getByPhoneNumber(String phoneNumber);
	
	public User getByCredentials(String email, String password);
	
}
