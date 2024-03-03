package com.cognizant.utilities.mapper;

import com.cognizant.dto.LoanDTO;
import com.cognizant.entities.LoanMaster;

public class LoanDTOMapper {

	private LoanDTOMapper(){}
		public static LoanMaster mapToLoanMaster(LoanDTO loan) {
			LoanMaster loanMaster = new LoanMaster();
			loanMaster.setLoanId(loan.getLoanId());
			loanMaster.setTypeOfLoan(loan.getTypeOfLoan());
			loanMaster.setInterestRate(loan.getInterestRate());
			loanMaster.setDateOfCreation(loan.getDateOfCreation());
		return loanMaster;
		}
		
		public static LoanDTO mapToLoanDTO(LoanMaster loan) {
			LoanDTO loanMaster = new LoanDTO();
			loanMaster.setLoanId(loan.getLoanId());
			loanMaster.setTypeOfLoan(loan.getTypeOfLoan());
			loanMaster.setInterestRate(loan.getInterestRate());
			loanMaster.setDateOfCreation(loan.getDateOfCreation());
		return loanMaster;
		}
}
