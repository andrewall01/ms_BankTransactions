package com.project.ms.bank.transactions.domain.dto;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.ms.bank.transactions.domain.entities.Account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "debitCard")
public class DebitCardDto {
	
	@Id
    private String idDebitCard;

    private String cardNumber;

    private List<Account> bankAccounts = new ArrayList<>();

    private Integer dniUser;

    private Boolean isActive;

    @JsonIgnore
    public void addBankAccount(Account bankAccount) {
        this.bankAccounts.add(bankAccount);
    }

}
