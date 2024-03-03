package com.Cognizant.Entities;



import java.time.LocalDate;

import com.Cognizant.Utilities.TypeOfLoan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
// @NoArgsConstructor
@EqualsAndHashCode
@Table( name = "LoanMaster",
	indexes =
		{@Index(name = "LoanMaster_index",
		columnList = "loanId, Type_Of_Loan, Date_Of_Creation")}
		)
public class LoanMaster {
	
	@Id
	@Column(name="loanId")
	private String loanId;

	@Column(name="Type_Of_Loan")
	@Enumerated(EnumType.STRING)
	private TypeOfLoan typeOfLoan;
	
	@Column(name="Interest_Rate",unique=true)
	private float interestRate;
	
	@Column(name="Date_Of_Creation")
	private LocalDate dateOfCreation;
		
	//getters and setters
	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String i) {
		this.loanId = i;
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
	
	//Constructor
	public LoanMaster() {}
		
}
