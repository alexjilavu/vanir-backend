package com.jimaio.vanir.test.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jimaio.vanir.VanirApplication;
import com.jimaio.vanir.request.RegisterForm;
import com.jimaio.vanir.response.AuthResponse;
import com.jimaio.vanir.service.UserService;

@SpringBootTest(classes = VanirApplication.class,
		webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsersControllerIntegrationTests {

	@LocalServerPort
    private int port;
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testRegisterUserValid() {
		RegisterForm form = new RegisterForm();
		form.setEmail("test@test.com");
		form.setFullName("Integration");
		form.setPassword("abcd");
		form.setConfirmPassword("abcd");
		form.setPhoneNumber("0723143123");
		
		ResponseEntity<AuthResponse> response = this.restTemplate
				.postForEntity("http://localhost:" + port + "/users/register", form, AuthResponse.class);
		
		assertEquals(200, response.getStatusCodeValue());
	}
	
	@Test
	public void testRegisterUserWrong() {
		RegisterForm form = new RegisterForm();
		form.setEmail("test@test.com");
		form.setFullName("Integration fail");
		form.setPassword("a");
		form.setConfirmPassword("abcd");
		form.setPhoneNumber("0723143123");
		
		ResponseEntity<AuthResponse> response = this.restTemplate
				.postForEntity("http://localhost:" + port + "/users/register", form, AuthResponse.class);
		
		assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}
	
//	@Test
//	public void testLoginSuccess() {
//		LoginForm form = new LoginForm();
//		form.setEmail("test@test.com");
//		form.setPassword("abcd");
//		
//		ResponseEntity<AuthResponse> response = this.restTemplate
//				.postForEntity("http://localhost:" + port + "/users/login", form, AuthResponse.class);
//		
//		assertNotEquals(null, response.getBody().getId());
//	}
}
