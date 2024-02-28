package com.Cognizant.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class LoanCalcDTO {
	private float pAmount;
	private int loanTenureMonths;
	private float monthlyinterestRate;
	private float EMI;
	private float totalAmountPayable;
}
