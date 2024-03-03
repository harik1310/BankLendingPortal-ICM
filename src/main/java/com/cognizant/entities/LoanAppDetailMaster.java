package com.cognizant.entities;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
// @NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "loanAppDetailMaster")
public class LoanAppDetailMaster {
	
	@Id
	@Column(name="Id")
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "LoanAppMaster_Loan_App_Id")
	private LoanAppMaster loanAppId;
	
	@Column(name="Month_No")
	private int monthNo;
	
	@Column(name="Installment")
	private int installment;
	
	@Column(name="Interest_Rate")
	private float interestRate;
	
	@Column(name="P_Outstanding_Begin_Of_Mon")
	private int pOutStandingBeginOfMon;
	
	@Column(name="P_Repayment")
	private int pRepayment;
	
	@Column(name="P_Outstanding_End_Of_Mon")
	private int prOutStandingEndOfMon;
	
	@Column(name="Last_Date_Of_Install_Pay")
	private LocalDate lastDateofinstallPay;

	public LoanAppDetailMaster() {}
	
	
}
