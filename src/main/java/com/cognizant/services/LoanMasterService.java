package com.cognizant.services;

import java.time.LocalDate;
import java.util.List;

import com.cognizant.dto.LoanDTO;
import com.cognizant.dto.NewLoanDTO;
import com.cognizant.utilities.Status;

public interface LoanMasterService {
	
	LoanDTO persistNewLoan(NewLoanDTO loan);
	List<LoanDTO> getAllLoans();
	LoanDTO updateLoanDetails(String loanId, NewLoanDTO toUpdateDetails);
	LoanDTO getLoanDetailsById(String loanId);
	
}
