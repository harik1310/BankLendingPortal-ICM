package com.cognizant.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "Loan_App_Master")
public class LoanAppMaster {
	

	@Id
	@Column(name="Loan_App_Id")
	private String loanAppId;
	
	@Column(name="Interest_Rate",unique=true)
	private float interestRate;

	@Column(name="Application_Date")
	private LocalDate applicationDate; 
	
	@OneToMany ( targetEntity = LoanAppDetailMaster.class, mappedBy = "loanAppId",
			cascade = CascadeType.MERGE, fetch = FetchType.EAGER )
	private List<LoanAppDetailMaster> loanAppDetails = new ArrayList<>();
	
}
