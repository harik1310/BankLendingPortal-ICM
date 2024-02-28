package com.Cognizant.Entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "loanAppDetailMaster")
public class LoanAppDetailMaster {
	
	public LoanAppDetailMaster(int id, LoanAppMaster loanAppId, int monthNo, int installment, float interestRate,
			int pOutStandingBeginOfMon, int pRepayment, int prOutStandingEndOfMon, Date lastDateofinstallPay) {
		super();
		this.id = id;
		this.loanAppId = loanAppId;
		this.monthNo = monthNo;
		this.installment = installment;
		this.interestRate = interestRate;
		this.pOutStandingBeginOfMon = pOutStandingBeginOfMon;
		this.pRepayment = pRepayment;
		this.prOutStandingEndOfMon = prOutStandingEndOfMon;
		this.lastDateofinstallPay = lastDateofinstallPay;
	}

	@Id
	@Column(name="Id")
	private int id;
	
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
	private Date lastDateofinstallPay;
		
}
