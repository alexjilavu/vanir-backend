package com.jimaio.vanir.test.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.jimaio.vanir.VanirApplication;
import com.jimaio.vanir.domain.Transaction;
import com.jimaio.vanir.request.SendTransaction;

@SpringBootTest(classes = VanirApplication.class,
webEnvironment = WebEnvironment.RANDOM_PORT)
public class TransactionControllerIntegrationTests {

	@LocalServerPort
    private int port;
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@Test
	public void sendTest() {
		
		SendTransaction form = new SendTransaction();
		form.setRecipientId("2");
		form.setValue(-12d);
		
		restTemplate.getRestTemplate().setInterceptors(
		        Collections.singletonList((request, body, execution) -> {
		            request.getHeaders()
		                    .add("userId", "1");
		            return execution.execute(request, body);
		        }));
		
		ResponseEntity<Transaction> response = this.restTemplate
				.postForEntity("http://localhost:" + port + "/transactions/send", form, Transaction.class);
		
		assertEquals(null, response.getBody());
		
	}
	
}
