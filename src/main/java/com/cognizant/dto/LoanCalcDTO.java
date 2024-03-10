package com.cognizant.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanCalcDTO {
	
	private String loanAppId;
	private double pAmount;
	private int loanTenureMonths;
	private double monthlyinterestRate;
	private double emi;
	private double totalAmountPayable;
	private LocalDate dueDate;
}
