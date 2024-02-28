package com.Cognizant.DTO;

import java.util.Date;

public class ReducedPaymentDTO {
	private int Loan_App_ID;
	private int Month;
	private int pOutStandingBeginOfMonth;
	private int EMI;
	private int Interest;
	private int Principal_Repayment;
	private int pOutStandingEndOfMonth;
	private Date lastDateOfEmi;
	
	public int getLoan_App_ID() {
		return Loan_App_ID;
	}
	public void setLoan_App_ID(int loan_App_ID) {
		Loan_App_ID = loan_App_ID;
	}
	public int getMonth() {
		return Month;
	}
	public void setMonth(int month) {
		Month = month;
	}
	public int getpOutStandingBeginOfMonth() {
		return pOutStandingBeginOfMonth;
	}
	public void setpOutStandingBeginOfMonth(int pOutStandingBeginOfMonth) {
		this.pOutStandingBeginOfMonth = pOutStandingBeginOfMonth;
	}
	public int getEMI() {
		return EMI;
	}
	public void setEMI(int eMI) {
		EMI = eMI;
	}
	public int getInterest() {
		return Interest;
	}
	public void setInterest(int interest) {
		Interest = interest;
	}
	public int getPrincipal_Repayment() {
		return Principal_Repayment;
	}
	public void setPrincipal_Repayment(int principal_Repayment) {
		Principal_Repayment = principal_Repayment;
	}
	public int getpOutStandingEndOfMonth() {
		return pOutStandingEndOfMonth;
	}
	public void setpOutStandingEndOfMonth(int pOutStandingEndOfMonth) {
		this.pOutStandingEndOfMonth = pOutStandingEndOfMonth;
	}
	public Date getLastDateOfEmi() {
		return lastDateOfEmi;
	}
	public void setLastDateOfEmi(Date lastDateOfEmi) {
		this.lastDateOfEmi = lastDateOfEmi;
	}
}
