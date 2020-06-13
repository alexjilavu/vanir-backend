package com.jimaio.vanir.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jimaio.vanir.domain.Account;
import com.jimaio.vanir.domain.Transaction;
import com.jimaio.vanir.domain.User;
import com.jimaio.vanir.request.LoginForm;
import com.jimaio.vanir.request.RegisterForm;
import com.jimaio.vanir.response.AuthResponse;
import com.jimaio.vanir.response.UserBalanceResponse;
import com.jimaio.vanir.service.EmailService;
import com.jimaio.vanir.service.TransactionService;
import com.jimaio.vanir.service.UserService;
import com.jimaio.vanir.utils.AvatarUtils;

@RestController
@RequestMapping("/users")
public class UsersController extends GenericController<User>{

	private static final long serialVersionUID = 7773512516984596478L;
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public TransactionService transactionService;
	
	@Autowired
	public AvatarUtils avatarUtils;
	
	@Autowired
	public EmailService emailService;

	public UsersController(UserService service) {
		super(service);
	}
	
	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterForm registerForm) {
		
		if (!registerForm.getPassword().equals(registerForm.getConfirmPassword()))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			
		User user = getEmptyItem();
		user.setEmail(registerForm.getEmail());
		user.setName(registerForm.getFullName());
		user.setPassword(registerForm.getPassword());
		user.setPhoneNumber(registerForm.getPhoneNumber());
		user.setApiKey(UUID.randomUUID().toString());
		user.setAvatarUrl(avatarUtils.generateAvatarUrl());
		
		userService.createUser(user);
		
		Thread t = new Thread() {
			public void run() {
				emailService.sendWelcomeMail(user);
			}
		};
		t.start();
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setId(user.getApiKey());
		
		return ResponseEntity.ok(authResponse);
	}
	
	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<AuthResponse> login(@RequestBody LoginForm loginForm) {
		
		User user = null;
		AuthResponse authResponse = new AuthResponse();
		
		try {
			if (loginForm.getEmail() != null && loginForm.getPassword() != null)
				user = userService.checkCredentials(loginForm.getEmail(), loginForm.getPassword());
			
			if (user != null) {
				authResponse.setId(user.getApiKey());
				return ResponseEntity.ok(authResponse);	
			}
			
			if (loginForm.getPhoneNumber() != null)
				user = userService.checkCredentials(loginForm.getPhoneNumber());
			
			if (user != null) {
				authResponse.setId(user.getApiKey());
				return ResponseEntity.ok(authResponse);	
			}
				
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}

	@GetMapping
	@ResponseBody
	public List<User> list() {
		return userService.getItems();
	}
	
	@GetMapping(value = "/{id}")
	@ResponseBody
	public User getUser(@PathVariable("id") String apiKey) {
		return userService.getByApiKey(apiKey);
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteUser(@PathVariable("id") String apiKey) {
		User user = userService.getByApiKey(apiKey);
		userService.delete(user);
	}
	
	@GetMapping(value = "/balance")
	public ResponseEntity<UserBalanceResponse> getBalance(@RequestHeader("id") String apiKey) {
		try {
			User user = userService.getByApiKey(apiKey);
			Account account = user.getAccount();
			
			UserBalanceResponse response = new UserBalanceResponse(account);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}
	
	@PostMapping(value = "/balance", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Double> topup(@RequestHeader("id") String apiKey, 
							 @RequestBody Double value) {
		
		User user = userService.getByApiKey(apiKey);
		if (user == null)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		
		Double balance = null;
		Transaction transaction = null;
		try {
			balance = user.getAccount().getBalance();
			transaction = transactionService.topUpBalance(user.getId().intValue(), value);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(balance);
		}
		
		if (transaction == null)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(balance);
		
		user = userService.getItem(user.getId());
		
		return ResponseEntity.ok(user.getAccount().getBalance());
	}
	
	@Override
	protected User getEmptyItem() {
		return new User();
	}

	@Override
	protected Boolean isNew(User item) {
		return item.getId() < 1;
	}

}
