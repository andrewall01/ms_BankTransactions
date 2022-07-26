package com.project.ms.bank.transactions.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.ms.bank.transactions.domain.dto.DebitCardDto;
import com.project.ms.bank.transactions.domain.entities.AccountBalance;
import com.project.ms.bank.transactions.domain.services.DebitCardService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/debit_card")
public class DebitCardController {
	
	@Autowired
    private DebitCardService debitCardService;

    @PostMapping("/save")
    public Mono<ResponseEntity<DebitCardDto>> saveDebitCard(@RequestBody DebitCardDto debitCard){
        return debitCardService.saveDebitCard(debitCard)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @GetMapping("/all")
    public ResponseEntity<Flux<DebitCardDto>> findAll(){
        return new ResponseEntity<>(debitCardService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/card_number/{cardNumber}")
    public ResponseEntity<Mono<DebitCardDto>> findByCardNumber(@PathVariable String cardNumber) {
        return new ResponseEntity<>(debitCardService.findByCardNumber(cardNumber), HttpStatus.OK);
    }

    @GetMapping("/balance/{cardNumber}")
    public Mono<ResponseEntity<AccountBalance>> getAccountBalance(@PathVariable String cardNumber){
        return debitCardService.getBalanceOfAccount(cardNumber)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

}
