package com.Cognizant.Mapper;

import com.Cognizant.DTO.LoanDTO;
import com.Cognizant.Entities.LoanMaster;

public class LoanDTOMapper {
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
