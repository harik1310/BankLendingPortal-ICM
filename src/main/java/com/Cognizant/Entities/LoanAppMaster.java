package com.Cognizant.Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "LoanAppMaster")
public class LoanAppMaster {
	
	@Id
	@Column(name="Loan_App_Id")
	private int loanAppId;
	
	@Column(name="Interest_Rate")
	private float interestRate;

	@Column(name="Application_Date")
	private Date applicationDate; 
	
	@OneToMany ( targetEntity = LoanAppDetailMaster.class, mappedBy = "loanAppId")
	private List<LoanAppDetailMaster> LoanAppDetails = new ArrayList<LoanAppDetailMaster>();
	
	public int getLoanAppId() {
		return loanAppId;
	}

	public void setLoanAppId(int loanAppId) {
		this.loanAppId = loanAppId;
	}

	public float getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public LoanAppMaster() {
		super();
	}

	public List<LoanAppDetailMaster> getLoanAppDetails() {
		return LoanAppDetails;
	}

	public void setLoanAppDetails(List<LoanAppDetailMaster> loanAppDetails) {
		LoanAppDetails = loanAppDetails;
	}
	
}
