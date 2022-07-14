package com.project.ms.bank.transactions.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ms.bank.transactions.domain.entities.Transaction;
import com.project.ms.bank.transactions.domain.services.TransactionService;
import com.project.ms.bank.transactions.infrastructure.repositories.TransactionRepository;

import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionDao;

	@Override
	public Mono<Transaction> save(Transaction transaction) {
		return transactionDao.save(transaction);
	}

}
