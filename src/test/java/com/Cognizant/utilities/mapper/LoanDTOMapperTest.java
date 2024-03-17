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
							   
		
		loanDto = LoanDTO.builder()
								.loanId("BL1234")
								.dateOfCreation(java.time.LocalDate.now())
								.interestRate(10.1f)
								.typeOfLoan(TypeOfLoan.BUSINESS_LOANS)
								.build();
		
	}

	@Test
	void toEntity_Test() {
		LoanMaster entity = LoanDTOMapper.toLoanMaster(loanDto);
		assertEquals(entity.getLoanId(), loanDto.getLoanId());
		assertEquals(entity.getDateOfCreation(), loanDto.getDateOfCreation());
		assertEquals(entity.getTypeOfLoan(), loanDto.getTypeOfLoan());
		assertEquals(entity.getInterestRate(), loanDto.getInterestRate());
	}
	
	@Test
	void toDTO_Test() {
		LoanDTO dto = LoanDTOMapper.toLoanDTO(loanMaster);
		assertEquals(loanMaster.getLoanId(), dto.getLoanId());
		assertEquals(loanMaster.getDateOfCreation(), dto.getDateOfCreation());
		assertEquals(loanMaster.getTypeOfLoan(), dto.getTypeOfLoan());
		assertEquals(loanMaster.getInterestRate(), dto.getInterestRate());
		
	}

}
