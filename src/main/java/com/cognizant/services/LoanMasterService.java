package com.cognizant.services;

import java.util.List;

import com.cognizant.dto.LoanDTO;
import com.cognizant.utilities.Status;

public interface LoanMasterService {
	
	LoanDTO persistNewLoan(LoanDTO loan);
	List<LoanDTO> getAllLoans();
	LoanDTO updateLoanDetails(String loanId, LoanDTO toUpdateDetails);
	LoanDTO getLoanDetailsById(String loanId);	
}
