package com.cognizant.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoanCalcDTO {
//	@NotNull
	private String loanAppId;
	@NotNull
	private double pAmount;
	@NotNull
	private int loanTenureMonths;
	private double monthlyinterestRate;
	private double emi;
	private double totalAmountPayable;
	private LocalDate dueDate;
}
