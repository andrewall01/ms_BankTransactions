package com.project.ms.bank.transactions.domain.entities;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {
	
	private Integer accountNumber;
    private LocalDateTime associateDebitCardDate;
    private Boolean flagPrincipal;

}
