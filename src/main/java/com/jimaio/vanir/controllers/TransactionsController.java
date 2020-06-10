package com.jimaio.vanir.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jimaio.vanir.domain.Transaction;
import com.jimaio.vanir.domain.User;
import com.jimaio.vanir.request.SendTransaction;
import com.jimaio.vanir.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionsController extends GenericController<Transaction> {

	private static final long serialVersionUID = 8965089371782598521L;
	
	@Autowired
	private TransactionService transactionService;

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
	
	@GetMapping("/getTransactions")
	public List<Transaction> getTransactions(@RequestParam("limit") Integer limit) {
		return transactionService.getItems(limit);
	}
	
	@PostMapping(value = "/send", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Transaction send(@RequestHeader("userId") Integer id, 
							@RequestBody SendTransaction body) {
		double value = body.getValue();
		if (value <= 0)
			return null;
		
		Transaction transaction = transactionService.send(id, body.recipientId, body.value);
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
