package com.cognizant.utilities.mapper;

import org.springframework.stereotype.Component;

import com.cognizant.dto.ReducedPaymentDTO;
import com.cognizant.entities.LoanAppDetailMaster;
import com.cognizant.entities.LoanAppMaster;

@Component
public class InstallmentMapper {
	
	private InstallmentMapper() {}

	public static ReducedPaymentDTO toDTO(LoanAppDetailMaster loanDetails) {

		ReducedPaymentDTO mapped = new ReducedPaymentDTO();
		LoanAppMaster l = new LoanAppMaster();
		
		l.setLoanAppId(loanDetails.getLoanAppId().getLoanAppId());
		l.setInterestRate(loanDetails.getLoanAppId().getInterestRate());
		l.setApplicationDate(loanDetails.getLoanAppId().getApplicationDate());
		l.setLoanAppDetails(loanDetails.getLoanAppId().getLoanAppDetails());
		
		mapped.setLoanAppId(l);
		mapped.setMonth(loanDetails.getMonthNo());
		mapped.setEmi(loanDetails.getInstallment());
		mapped.setInterest(loanDetails.getInterestRate());
		mapped.setPOutStandingBeginOfMonth(loanDetails.getPOutStandingBeginOfMon());
		mapped.setPrincipalRepayment(loanDetails.getPRepayment());
		mapped.setPOutStandingEndOfMonth(loanDetails.getPrOutStandingEndOfMon());
		mapped.setLastDateOfEmi(loanDetails.getLastDateofinstallPay());
		return mapped;
	}

	public static LoanAppDetailMaster toEntity(ReducedPaymentDTO loanDetails) {
		LoanAppDetailMaster mapped = new LoanAppDetailMaster();

		LoanAppMaster l = new LoanAppMaster();
		
		l.setLoanAppId(loanDetails.getLoanAppId().getLoanAppId());
		l.setInterestRate(loanDetails.getLoanAppId().getInterestRate());
		l.setApplicationDate(loanDetails.getLoanAppId().getApplicationDate());
		l.setLoanAppDetails(loanDetails.getLoanAppId().getLoanAppDetails());	

		mapped.setLoanAppId(l);
		mapped.setMonthNo(loanDetails.getMonth());
		mapped.setInstallment(loanDetails.getEmi());
		mapped.setInterestRate(loanDetails.getInterest());
		mapped.setPOutStandingBeginOfMon(loanDetails.getPOutStandingBeginOfMonth());
		mapped.setPRepayment(loanDetails.getPrincipalRepayment());
		mapped.setPrOutStandingEndOfMon(loanDetails.getPOutStandingEndOfMonth());
		mapped.setLastDateofinstallPay(loanDetails.getLastDateOfEmi());
		return mapped;
	}
}
