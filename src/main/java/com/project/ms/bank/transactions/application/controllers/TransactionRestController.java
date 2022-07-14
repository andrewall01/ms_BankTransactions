package com.project.ms.bank.transactions.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ms.bank.transactions.domain.entities.Transaction;
import com.project.ms.bank.transactions.domain.services.TransactionService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transaction")
public class TransactionRestController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/save")
	public Mono<Transaction> saveTransaction(@RequestBody Transaction transaction) {
		return transactionService.save(transaction);
	}

}
