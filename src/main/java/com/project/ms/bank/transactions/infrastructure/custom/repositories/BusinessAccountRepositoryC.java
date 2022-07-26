package com.project.ms.bank.transactions.infrastructure.custom.repositories;

import com.project.ms.bank.transactions.domain.entities.AccountBalance;

import reactor.core.publisher.Mono;

public interface BusinessAccountRepositoryC {
	Mono<AccountBalance> findByAccountNumber(Integer getOneValor, String parameter);

}
