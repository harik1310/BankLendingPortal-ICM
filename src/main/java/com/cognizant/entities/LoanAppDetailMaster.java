package com.cognizant.entities;


import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Loan_App_Detail_Master")
public class LoanAppDetailMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Id")
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Loan_App_Master_Loan_App_Id")
	private LoanAppMaster loanAppId;
	
	@Column(name="Month_No")
	private int monthNo;
	
	@Column(name="Installment")
	private double installment;
	
	@Column(name="Interest_Rate")
	private double interestRate;
	
	@Column(name="P_Outstanding_Begin_Of_Mon")
	private double pOutStandingBeginOfMon;
	
	@Column(name="P_Repayment")
	private double pRepayment;
	
	@Column(name="P_Outstanding_End_Of_Mon")
	private double prOutStandingEndOfMon;
	
	@Column(name="Last_Date_Of_Install_Pay")
	private LocalDate lastDateofinstallPay;	
}
