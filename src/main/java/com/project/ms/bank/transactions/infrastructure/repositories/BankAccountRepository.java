package com.project.ms.bank.transactions.infrastructure.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.project.ms.bank.transactions.domain.dto.BankAccountDto;
import com.project.ms.bank.transactions.infrastructure.custom.repositories.BusinessAccountRepositoryC;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@EnableReactiveMongoRepositories
public interface BankAccountRepository extends ReactiveMongoRepository<BankAccountDto, String>, BusinessAccountRepositoryC{
	
	Mono<BankAccountDto> findByAccountNumber(Integer accountNumber);
    Mono<Boolean> existsByAccountNumber(Integer accountNumber);
    Flux<BankAccountDto> findAllByDniUserAndAccountNumberIn(Integer dniUser, List<Integer> accountNumbers);
    Flux<BankAccountDto> findAllByDniUser(Integer dniUser);
    Mono<Boolean> existsByIdBankAccount(String idAccount);

}
