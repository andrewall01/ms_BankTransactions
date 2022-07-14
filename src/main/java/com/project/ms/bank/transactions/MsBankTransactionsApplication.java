package com.project.ms.bank.transactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

@SpringBootApplication
@EnableReactiveMongoAuditing
public class MsBankTransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBankTransactionsApplication.class, args);
	}

}
