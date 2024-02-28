package com.Cognizant.Services;

import java.util.List;

import com.Cognizant.DTO.LoanDTO;

public interface LoanMasterService {
	
	String persistNewLoan(LoanDTO loan);
	List<LoanDTO> getAllLoans();
	String updateLoanDetails(int loanId, LoanDTO toUpdateDetails);
	LoanDTO getLoanDetailsById(int loanId);	
}
