package com.cognizant.utilities.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cognizant.dto.LoanDTO;
import com.cognizant.entities.LoanMaster;
import com.cognizant.utilities.TypeOfLoan;

class LoanDTOMapperTest {
	
	private LoanMaster loanMaster,loanMaster1;
	private LoanDTO loanDto;

	@BeforeEach
	void setUp() throws Exception {
		loanMaster = LoanMaster.builder()
								.loanId("HL1234")
								.dateOfCreation(java.time.LocalDate.now())
								.interestRate(10.0f)
								.typeOfLoan(TypeOfLoan.HOME_LOANS)
								.build();
							   
		
		loanMaster1 = LoanMaster.builder()
								.loanId("BL1234")
								.dateOfCreation(java.time.LocalDate.now())
								.interestRate(10.1f)
								.typeOfLoan(TypeOfLoan.BUSINESS_LOANS)
								.build();
		
//		loanDto = 
	}

	@Test
	void test() {
		
	}

}
