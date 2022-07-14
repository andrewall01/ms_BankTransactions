package com.project.ms.bank.transactions.domain.services;

import com.project.ms.bank.transactions.domain.entities.Transaction;

import reactor.core.publisher.Mono;

public interface TransactionService {

	public Mono<Transaction> save(Transaction transaction);

}
