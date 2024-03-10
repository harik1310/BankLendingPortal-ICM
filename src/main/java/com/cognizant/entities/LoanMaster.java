package com.cognizant.entities;



import java.time.LocalDate;

import com.cognizant.utilities.TypeOfLoan;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "Loan_Master",
	indexes =
		{@Index(name = "Loan_Master_index",
		columnList = "Loan_Id, Type_Of_Loan, Date_Of_Creation")}
		)
public class LoanMaster {

	@Id
	@Column(name="Loan_Id")
	private String loanId;

	@Column(name="Type_Of_Loan")
	@Enumerated(EnumType.STRING)
	private TypeOfLoan typeOfLoan;
	
	@Column(name="Interest_Rate",unique=true)
	private float interestRate;
	
	@Column(name="Date_Of_Creation")
	private LocalDate dateOfCreation;
		
}
