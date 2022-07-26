package com.project.ms.bank.transactions.infrastructure.repositories;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.project.ms.bank.transactions.domain.dto.DebitCardDto;

import reactor.core.publisher.Mono;

@EnableReactiveMongoRepositories
public interface DebitCardRepository extends ReactiveMongoRepository<DebitCardDto, String>{
	
	Mono<DebitCardDto> findByCardNumber(String cardNumber);

}
