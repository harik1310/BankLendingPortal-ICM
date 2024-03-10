package com.cognizant.utilities.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.dto.ReducedPaymentDTO;
import com.cognizant.entities.LoanAppDetailMaster;
import com.cognizant.entities.LoanAppMaster;
import com.cognizant.repository.LoanAppMasterRepository;

@Component
public class InstallmentMapper {
	@Autowired
	private LoanAppMasterRepository repository;

	
	public static ReducedPaymentDTO toDTO(LoanAppDetailMaster loanDetails) {
		
		
		
		ReducedPaymentDTO mapped = new ReducedPaymentDTO();
		mapped.setLoanAppId(loanDetails.getLoanAppId());
		mapped.setMonth(loanDetails.getMonthNo());
		mapped.setEMI(loanDetails.getInstallment());
		mapped.setInterest(loanDetails.getInterestRate());
		mapped.setPOutStandingBeginOfMonth(loanDetails.getPOutStandingBeginOfMon());
		mapped.setPrincipal_Repayment(loanDetails.getPRepayment());
		mapped.setPOutStandingEndOfMonth(loanDetails.getPrOutStandingEndOfMon());
		mapped.setLastDateOfEmi(loanDetails.getLastDateofinstallPay());
		return mapped;
	}
	
	public static LoanAppDetailMaster toEntity(ReducedPaymentDTO loanDetails) {
		LoanAppDetailMaster mapped = new LoanAppDetailMaster();
//		mapped.setId(loanDetails.getLoanAppId());
		mapped.setLoanAppId(loanDetails.getLoanAppId());
		mapped.setMonthNo(loanDetails.getMonth());
		mapped.setInstallment(loanDetails.getEMI());
		mapped.setInterestRate(loanDetails.getInterest());
		mapped.setPOutStandingBeginOfMon(loanDetails.getPOutStandingBeginOfMonth());
		mapped.setPRepayment(loanDetails.getPrincipal_Repayment());
		mapped.setPrOutStandingEndOfMon(loanDetails.getPOutStandingEndOfMonth());
		mapped.setLastDateofinstallPay(loanDetails.getLastDateOfEmi());
		return mapped;
	}
}
