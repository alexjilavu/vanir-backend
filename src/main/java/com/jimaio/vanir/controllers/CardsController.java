package com.jimaio.vanir.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jimaio.vanir.domain.Account;
import com.jimaio.vanir.domain.Card;
import com.jimaio.vanir.domain.User;
import com.jimaio.vanir.response.CardResponse;
import com.jimaio.vanir.service.AccountService;
import com.jimaio.vanir.service.CardService;
import com.jimaio.vanir.service.UserService;

@RestController
@RequestMapping("/cards")
public class CardsController extends GenericController<Card> {

	private static final long serialVersionUID = -3554670093314264046L;
	
	@Autowired
	CardService cardService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AccountService accountService;
	
	public CardsController(CardService cardService) {
		super(cardService);
	}
	
	@GetMapping
	public ResponseEntity<List<CardResponse>> list(@RequestHeader("id") String apiKey) {
		User user = userService.getByApiKey(apiKey);
		
		List<CardResponse> response = null;
		try {
			List<Card> cards = listItems();
			cards = cards
					.stream()
					.filter(c -> c.getAccount().getUser().getId().equals(user.getId()))
					.collect(Collectors.toList());
			
			response = new ArrayList<CardResponse>();
			
			for (Card card : cards) 
				response.add(new CardResponse(card));
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<CardResponse> addCard(@RequestHeader("id") String apiKey) {
		
		CardResponse cardResponse = null;
		
		try {
			User user = userService.getByApiKey(apiKey);
			Account account = accountService.getAccountOfUser(user);
			
			Card newCard = cardService.createCard(account);
			
			cardResponse = new CardResponse(newCard);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		
		return ResponseEntity.ok(cardResponse); 
	}
	
	@DeleteMapping(value = "/{cardId}")
	public void deleteCard(@PathVariable("cardId") Integer id) {
		Card card = cardService.getItem(id);
		cardService.delete(card);
	}
	
	

	@Override
	protected Card getEmptyItem() {
		return new Card();
	}

	@Override
	protected Boolean isNew(Card item) {
		return item.getId() < 1;
	}

}
