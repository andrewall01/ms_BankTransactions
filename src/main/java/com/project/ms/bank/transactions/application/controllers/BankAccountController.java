package com.project.ms.bank.transactions.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.ms.bank.transactions.domain.dto.BankAccountDto;
import com.project.ms.bank.transactions.domain.entities.AccountBalance;
import com.project.ms.bank.transactions.domain.entities.ResumeProduct;
import com.project.ms.bank.transactions.domain.services.BankAccountService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
public class BankAccountController {
	
	@Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/save")
    public Mono<ResponseEntity<BankAccountDto>> saveBusinessAcountBank(@RequestBody BankAccountDto bankAccount){
        return bankAccountService.saveBusinessAccount(bankAccount)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @GetMapping("/all")
    public ResponseEntity<Flux<BankAccountDto>> findAll(){
        return new ResponseEntity<>(bankAccountService.finAlllBusinessAccount(), HttpStatus.OK);

    }

    @PutMapping("/save")
    public Mono<ResponseEntity<BankAccountDto>> updateBusinessAccountBank(@RequestBody BankAccountDto bankAccount){
        return bankAccountService.updateBankAccount(bankAccount)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
    @GetMapping("/{account}")
    public Mono<ResponseEntity<BankAccountDto>> findBankAccountByAccountNumber(@PathVariable("account") Integer accountNumber){
        return bankAccountService.findBankAccountByAccountNumber(accountNumber)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
    @GetMapping("/status/{number}")
    public Mono<Boolean> existAccount(@PathVariable("number") Integer accountNumber){
        return bankAccountService.exitAccount(accountNumber);
    }

    @GetMapping("/balance/{account}")
    public Mono<ResponseEntity<AccountBalance>> getBalanceOfAccount(@PathVariable("account") Integer accountNumber){
        return bankAccountService.getBalanceOfAccount(accountNumber)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @GetMapping("/resume/{dniNumber}")
    public Mono<ResumeProduct> consolidateProductOfUser(@PathVariable("dniNumber") Integer dniNumber){
        return bankAccountService.consolidateAccountByDni(dniNumber);
    }

}
