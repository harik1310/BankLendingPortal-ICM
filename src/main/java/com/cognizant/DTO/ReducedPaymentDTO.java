package com.Cognizant.DTO;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import jakarta.validation.constraints.Size;

@Component
public class ReducedPaymentDTO {
	@Size(min = 6,max=6)
	private String Loan_App_ID;
	private int Month;
	private double pOutStandingBeginOfMonth;
	private double EMI;
	private double Interest;
	private double Principal_Repayment;
	private double pOutStandingEndOfMonth;
	private LocalDate lastDateOfEmi;
	
	public String getLoan_App_ID() {
		return Loan_App_ID;
	}
	public void setLoan_App_ID(String loan_App_ID) {
		Loan_App_ID = loan_App_ID;
	}
	public int getMonth() {
		return Month;
	}
	public void setMonth(int month) {
		Month = month;
	}
	public double getpOutStandingBeginOfMonth() {
		return pOutStandingBeginOfMonth;
	}
	public void setpOutStandingBeginOfMonth(double pOutStandingBeginOfMonth) {
		this.pOutStandingBeginOfMonth = pOutStandingBeginOfMonth;
	}
	public double getEMI() {
		return EMI;
	}
	public void setEMI(double emi2) {
		EMI = emi2;
	}
	public double getInterest() {
		return Interest;
	}
	public void setInterest(double interest) {
		Interest = interest;
	}
	public double getPrincipal_Repayment() {
		return Principal_Repayment;
	}
	public void setPrincipal_Repayment(double principal_Repayment) {
		Principal_Repayment = principal_Repayment;
	}
	public double getpOutStandingEndOfMonth() {
		return pOutStandingEndOfMonth;
	}
	public void setpOutStandingEndOfMonth(double pOutStandingEndOfMonth) {
		this.pOutStandingEndOfMonth = pOutStandingEndOfMonth;
	}
	public LocalDate getLastDateOfEmi() {
		return lastDateOfEmi;
	}
	public void setLastDateOfEmi(LocalDate lastDateOfEmi) {
		this.lastDateOfEmi = lastDateOfEmi;
	}
	public ReducedPaymentDTO() {
	}
}
