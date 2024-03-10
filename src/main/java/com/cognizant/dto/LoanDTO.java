package com.cognizant.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.cognizant.utilities.TypeOfLoan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {
	private String loanId;
	private TypeOfLoan typeOfLoan;
	private float interestRate;
	private LocalDate dateOfCreation;
	
	public String getLoanId() {
		return loanId;
	}
	public void setLoanId(String string) {
		this.loanId = string;
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
	public LocalDate getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(LocalDate dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
}
