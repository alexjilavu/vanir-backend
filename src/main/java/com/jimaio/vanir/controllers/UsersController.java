package com.jimaio.vanir.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jimaio.vanir.domain.User;
import com.jimaio.vanir.request.LoginForm;
import com.jimaio.vanir.request.RegisterForm;
import com.jimaio.vanir.service.UserService;

@RestController
@RequestMapping("/user")
public class UsersController extends GenericController<User>{

	private static final long serialVersionUID = 7773512516984596478L;
	
	@Autowired
	public UserService userService;

	public UsersController(UserService service) {
		super(service);
	}
	
	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean register(@RequestBody RegisterForm registerForm) {
		
		if (!registerForm.getPassword().equals(registerForm.getConfirmPassword()))
			return false;
			
		User user = getEmptyItem();
		user.setEmail(registerForm.getEmail());
		user.setName(registerForm.getFullName());
		user.setPassword(registerForm.getPassword());
		user.setPhoneNumber(registerForm.getPhoneNumber());
		user.setApiKey(UUID.randomUUID().toString());
		
		userService.createUser(user);
		
		return true;
	}
	
	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Long login(@RequestBody LoginForm loginForm) {
		
		User user = null;
		
		if (loginForm.getEmail() != null && loginForm.getPassword() != null)
			user = userService.checkCredentials(loginForm.getEmail(), loginForm.getPassword());
		else
			if (loginForm.getPhoneNumber() != null)
				user = userService.checkCredentials(loginForm.getPhoneNumber());
		
		return user.getId();
	}

	@GetMapping(value = "/getAll")
	@ResponseBody
	public List<User> list() {
		return userService.getItems();
	}
	
	@GetMapping(value = "/get")
	@ResponseBody
	public User getUser(@RequestParam(name = "id", required = true) Integer id) {
		return userService.getItem(id);
	}
	
	@PostMapping(value = "/delete")
	public void deleteUser(@RequestParam(name = "id", required = true) Integer id) {
		userService.deleteById(id);
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
