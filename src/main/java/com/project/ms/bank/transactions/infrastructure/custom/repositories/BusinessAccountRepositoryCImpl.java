package com.project.ms.bank.transactions.infrastructure.custom.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.project.ms.bank.transactions.domain.entities.AccountBalance;

import reactor.core.publisher.Mono;

public class BusinessAccountRepositoryCImpl implements BusinessAccountRepositoryC {
	
	private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    public BusinessAccountRepositoryCImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<AccountBalance> findByAccountNumber(Integer getOneValor, String parameter) {
        Query query = new Query(Criteria.where("accountNumber").is(getOneValor));
        query.fields().include("balance");
        return reactiveMongoTemplate.findOne(query, AccountBalance.class);
    }

}
