package com.project.ms.bank.transactions.domain.entities;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeProduct {
	private Integer dniUser;
    private List<Map<String, Object>> accounts;

}
