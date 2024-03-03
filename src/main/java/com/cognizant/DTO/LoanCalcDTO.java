package com.Cognizant.DTO;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class LoanCalcDTO {
	
	private String loanAppId;
	private int pAmount;
	private int loanTenureMonths;
	private double monthlyinterestRate;
	private double EMI;
	private double totalAmountPayable;
	private LocalDate dueDate;

	//getters and setters
	public String getLoanAppId() {
		return loanAppId;
	}
		
	public void setLoanAppId(String loanAppId) {
		this.loanAppId = loanAppId;
	}
	
	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public int getpAmount() {
		return pAmount;
	}

	public void setpAmount(int pAmount) {
		this.pAmount = pAmount;
	}

	public int getLoanTenureMonths() {
		return loanTenureMonths;
	}

	public void setLoanTenureMonths(int loanTenureMonths) {
		this.loanTenureMonths = loanTenureMonths;
	}

	public double getMonthlyinterestRate() {
		return monthlyinterestRate;
	}

	public void setMonthlyinterestRate(double monthlyinterestRate) {
		this.monthlyinterestRate = monthlyinterestRate;
	}

	public double getEMI() {
		return EMI;
	}

	public void setEMI(double eMI) {
		EMI = eMI;
	}

	public double getTotalAmountPayable() {
		return totalAmountPayable;
	}
	
	public void setTotalAmountPayable(double totalAmountPayable) {
		this.totalAmountPayable = totalAmountPayable;
	}
	
	//constructor
	public LoanCalcDTO() {}
}
