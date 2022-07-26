package com.project.ms.bank.transactions.infrastructure.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.project.ms.bank.transactions.domain.entities.Transaction;

import reactor.core.publisher.Mono;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String>{
	Mono<Transaction> findTransactionAmountByAccount(String account);

}
