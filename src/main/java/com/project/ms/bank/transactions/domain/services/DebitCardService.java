package com.project.ms.bank.transactions.domain.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.ms.bank.transactions.domain.dto.DebitCardDto;
import com.project.ms.bank.transactions.domain.entities.Account;
import com.project.ms.bank.transactions.domain.entities.AccountBalance;
import com.project.ms.bank.transactions.infrastructure.repositories.BankAccountRepository;
import com.project.ms.bank.transactions.infrastructure.repositories.DebitCardRepository;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class DebitCardService {
	
	@Autowired
    private DebitCardRepository debitCardRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public Mono<DebitCardDto> saveDebitCard(DebitCardDto debitCard) {
        List<Integer> accounts = new ArrayList<>();
        debitCard.getBankAccounts().forEach(x -> accounts.add(x.getAccountNumber()));

        return bankAccountRepository.findAllByDniUserAndAccountNumberIn(debitCard.getDniUser(), accounts)
                .collectList()
                .flatMap(x -> {

                    if(x.isEmpty()) {
                        return Mono.empty();
                    }
                    log.info("Initialization of save method bl...");

                    Account principalAccount = debitCard.getBankAccounts().stream().filter(acc -> acc.getFlagPrincipal() == true).findFirst().orElse(null);

                    if(principalAccount == null) {
                        return Mono.empty();
                    }

                    debitCard.setCardNumber(UUID.randomUUID().toString());

                    List<Account> banks = new ArrayList<>();

                    debitCard.getBankAccounts().forEach(bkAccount -> {
                        bkAccount.setAccountNumber(bkAccount.getAccountNumber());
                        bkAccount.setAssociateDebitCardDate(LocalDateTime.now());
                        bkAccount.setFlagPrincipal(bkAccount.getFlagPrincipal());

                        banks.add(bkAccount);
                    });

                    return debitCardRepository.save(debitCard);
                });
    }

    public Flux<DebitCardDto> findAll() {
        return debitCardRepository.findAll();
    }

    public Mono<DebitCardDto> findByCardNumber(String cardNumber) {
        return debitCardRepository.findByCardNumber(cardNumber);
    }

    public Mono<AccountBalance> getBalanceOfAccount(String cardNumber){
        return debitCardRepository.findByCardNumber(cardNumber)
                .flatMap(x -> {
                    Account account = x.getBankAccounts().stream().filter(acc -> acc.getFlagPrincipal() == true)
                            .findFirst().orElse(null);

                    if(account == null) {
                        return Mono.empty();
                    }

                    return bankAccountRepository.findByAccountNumber(account.getAccountNumber())
                            .flatMap(value-> Mono.just(new AccountBalance(value.getBalance())));
                });
    }

}
