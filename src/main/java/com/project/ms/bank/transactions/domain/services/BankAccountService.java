package com.project.ms.bank.transactions.domain.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import com.project.ms.bank.transactions.domain.dto.BankAccountDto;
import com.project.ms.bank.transactions.domain.entities.AccountBalance;
import com.project.ms.bank.transactions.domain.entities.ResumeProduct;
import com.project.ms.bank.transactions.domain.entities.UserCredit;
import com.project.ms.bank.transactions.domain.entities.VipBusiness;
import com.project.ms.bank.transactions.infrastructure.repositories.BankAccountRepository;
import com.project.ms.bank.transactions.util.WebClientTemplate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BankAccountService {
	
	@Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private WebClientTemplate webClientTemplate;

    public Mono<BankAccountDto> saveBusinessAccount(BankAccountDto bankAccount){
        return validateSaveWithCriteria(bankAccount);
    }


    public Mono<BankAccountDto> updateBankAccount(BankAccountDto bankAccount){
        /*if(bankAccount.getTypeAccount().equals("corriente")){
            return bankAccountRepository.existsByIdBankAccount(bankAccount.getIdBankAccount())
                    .flatMap(condition->{
                        if(condition){
                            return bankAccountRepository.save(bankAccount);
                        }
                        return Mono.empty();
                    });
        }
        return Mono.empty();*/
        return validateSaveWithCriteria(bankAccount);
    }

    public Flux<BankAccountDto> finAlllBusinessAccount(){
        return bankAccountRepository.findAll();
    }

    public Mono<BankAccountDto> findBankAccountByAccountNumber(Integer accountNumber){
        return bankAccountRepository.findByAccountNumber(accountNumber);
    }
    public Mono<Boolean> exitAccount(Integer accountNumber){
        return bankAccountRepository.existsByAccountNumber(accountNumber);
    }
    public Mono<AccountBalance> getBalanceOfAccount(Integer accountNumber){
        return bankAccountRepository.findByAccountNumber(accountNumber, "true");
    }

    private Mono<VipBusiness> isUserBusinessVip(Integer dniNumber){
        return webClientTemplate.templateWebClient("http://localhost:8092")
                .get()
                .uri("/user/business/"+dniNumber)
                .retrieve()
                .bodyToMono(VipBusiness.class);
    }

    private Mono<UserCredit> userBusinessVipHaveCredit(Integer dniNumber){
        return webClientTemplate.templateWebClient("http://localhost:8093")
                .get()
                .uri("/credit/status/"+dniNumber)
                .retrieve()
                .bodyToMono(UserCredit.class);
    }

    private Mono<BankAccountDto> validateSaveWithCriteria(BankAccountDto bankAccount){
        return isUserBusinessVip(bankAccount.getDniUser())
                .flatMap(condition-> userBusinessVipHaveCredit(bankAccount.getDniUser())
                        .flatMap(haveCredit->{
                            if(bankAccount.getBalance()>0 && typeAccount.test(bankAccount.getTypeAccount())){
                                if(condition.getStatus().equals(true) && haveCredit.getStatus().equals(true)){
                                    bankAccount.setBenefitStatus(true);
                                }
                                return bankAccountRepository.save(bankAccount);
                            }
                            return Mono.empty();
                        }));
    }

    Predicate<String> typeAccount = type->type.equals("corriente");

    public Mono<ResumeProduct> consolidateAccountByDni(Integer dniNumber){
        return bankAccountRepository.findAllByDniUser(dniNumber)
                .filter(account -> account.getDniUser()!=null)
                .switchIfEmpty(Mono.empty())
                .collectList()
                .flatMap(account -> {
                    var resume = new ResumeProduct();
                    Map<String, Object> lol = new HashMap<>();
                    List<Map<String,Object>> accounts = new ArrayList<>();
                    //account.forEach(element -> accounts.add(1,2));
                    account.forEach(element -> lol.put(element.getTypeAccount(),element.getAccountNumber()));
                    accounts.add(lol);
                    resume.setDniUser(dniNumber);
                    resume.setAccounts(accounts);
                    return  Mono.just(resume);
                });

    }

}
