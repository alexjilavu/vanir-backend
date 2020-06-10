package com.jimaio.vanir.request;

import lombok.Data;

@Data
public class RegisterForm {

	private String email;
	
	private String password;
	
	private String phoneNumber; 
}
