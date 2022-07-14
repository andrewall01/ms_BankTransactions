package com.project.ms.bank.transactions.infrastructure.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.project.ms.bank.transactions.domain.entities.Transaction;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String>{

}
