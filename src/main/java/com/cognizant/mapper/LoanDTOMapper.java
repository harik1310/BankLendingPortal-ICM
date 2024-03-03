package com.cognizant.mapper;

import com.cognizant.DTO.LoanDTO;
import com.cognizant.Entities.LoanMaster;

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
