package com.jimaio.vanir.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jimaio.vanir.domain.Transaction;
import com.jimaio.vanir.domain.User;
import com.jimaio.vanir.request.SendTransaction;
import com.jimaio.vanir.response.TransactionResponse;
import com.jimaio.vanir.service.TransactionService;
import com.jimaio.vanir.service.UserService;

@RestController
@RequestMapping("/transactions")
public class TransactionsController extends GenericController<Transaction> {

	private static final long serialVersionUID = 8965089371782598521L;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	public UserService userService;

	public TransactionsController(TransactionService transactionService) {
		super(transactionService);
	}
	
	@PostMapping(value = "/topup", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Transaction topup(@RequestHeader("userId") Integer id, 
							 @RequestBody Double value) {
		Transaction transaction = transactionService.topUpBalance(id, value);
		return transaction;
	}
	
	@GetMapping
	public List<TransactionResponse> getTransactions(@RequestHeader("id") String apiKey) {
		User user = userService.getByApiKey(apiKey);
		List<Transaction> transactions = transactionService.getTransactionsOfUser(user);
		List<TransactionResponse> responses = new ArrayList<TransactionResponse>();
		
		for (Transaction transaction : transactions) {
			TransactionResponse response = new TransactionResponse();
			
			if (transaction.getSenderAccount().equals(transaction.getRecipientAccount())) {
				response.setRecipientName("Topup");
				response.setAmount(transaction.getAmount());
				response.setRecipientAvatar(user.getAvatarUrl());
			} else
				if (transaction.getSenderAccount().getUser().getId().equals(user.getId())) {
					User recipient = transaction.getRecipientAccount().getUser();
					response.setRecipientName(recipient.getName());
					response.setAmount(-transaction.getAmount());
					response.setRecipientAvatar(recipient.getAvatarUrl());
				} else
					if (transaction.getRecipientAccount().getUser().getId().equals(user.getId())) {
						User sender = transaction.getSenderAccount().getUser();
						response.setRecipientName(sender.getName());
						response.setAmount(transaction.getAmount());
						response.setRecipientAvatar(sender.getAvatarUrl());
					}

			Date date = transaction.getDate();
			LocalDate localDate = date.toInstant()
					.atZone(ZoneId.systemDefault())
					.toLocalDate();
		    DateTimeFormatter dateFormatter = DateTimeFormatter
		    		.ofPattern("d-MMM-u", Locale.ENGLISH);
		    
		    String formattedDate = localDate.format(dateFormatter);
			
			response.setCurrency(transaction.getCurrency() != null ? transaction.getCurrency().getName() : "RON");
			response.setDate(formattedDate);
			response.setId(transaction.getId().intValue());
			
			responses.add(response);
		}
		
		return responses;
	}
	
	@PostMapping(value = "/send", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Transaction send(@RequestHeader("userId") String id, 
							@RequestBody SendTransaction body) {
		double value = body.getValue();
		if (value <= 0)
			return null;
		User recipient = userService.getByApiKey(body.getRecipientId());
		User sender = userService.getByApiKey(id);
		
		Transaction transaction = transactionService.send(sender.getId().intValue(), recipient.getId().intValue(), body.getValue());
		return transaction;
	}

	@Override
	protected Transaction getEmptyItem() {
		return new Transaction();
	}

	@Override
	protected Boolean isNew(Transaction item) {
		return item.getId() < 1;
	}

}
