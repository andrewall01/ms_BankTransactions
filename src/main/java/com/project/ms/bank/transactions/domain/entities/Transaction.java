package com.project.ms.bank.transactions.domain.entities;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transaction")
public class Transaction {
	
	@Id
    private String id;
    
    private double amount;
    
    private String account;
    
    @Field("txn_type")
    private char txnType;
    
    @Field("created_at")
    @CreatedDate
    private Instant createdAt;

}
