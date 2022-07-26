package com.project.ms.bank.transactions.domain.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
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
@Document(collection = "bankAccount")
public class BankAccountDto {
	
	@Id
    private String idBankAccount;

    //private List<String> idUser;
    private Integer dniUser;
    private Integer accountNumber;
    private Integer balance;
    private String typeAccount;
    private Integer maintenanceCharge;
    private Integer movementNumber;

    @Builder.Default
    private Boolean benefitStatus=false;

}
