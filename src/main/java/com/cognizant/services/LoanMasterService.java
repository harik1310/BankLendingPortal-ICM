package com.cognizant.services;

import java.util.List;

import com.cognizant.dto.LoanDTO;

public interface LoanMasterService {
	
	String persistNewLoan(LoanDTO loan);
	List<LoanDTO> getAllLoans();
	String updateLoanDetails(String loanId, LoanDTO toUpdateDetails);
	LoanDTO getLoanDetailsById(String loanId);	
}
