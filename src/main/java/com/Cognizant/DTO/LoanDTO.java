package com.Cognizant.DTO;

import java.util.Date;

import com.Cognizant.Utilities.TypeOfLoan;

import lombok.Data;

public class LoanDTO {
	private int loanId;
	private TypeOfLoan typeOfLoan;
	private float interestRate;
	private Date dateOfCreation;
	
	public int getLoanId() {
		return loanId;
	}
	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}
	public TypeOfLoan getTypeOfLoan() {
		return typeOfLoan;
	}
	public void setTypeOfLoan(TypeOfLoan typeOfLoan) {
		this.typeOfLoan = typeOfLoan;
	}
	public float getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}
	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
}
