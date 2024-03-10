package com.cognizant.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cognizant.dto.LoanDTO;
import com.cognizant.entities.LoanMaster;
import com.cognizant.repository.LoanMasterRepository;
import com.cognizant.utilities.Status;
import com.cognizant.utilities.TypeOfLoan;

import jakarta.inject.Inject;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class LoanMasterServiceTest {
	
	
	@Mock
	private LoanMasterRepository repository;
	
	@InjectMocks
	private LoanMasterServiceImpl service;
	
	private LoanMaster loanMaster, loanMaster1, loanMaster2;
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
				.loanId("BL1235")
				.dateOfCreation(java.time.LocalDate.now())
				.interestRate(10.23f)
				.typeOfLoan(TypeOfLoan.BUSINESS_LOANS)
				.build();

	    loanMaster2 = LoanMaster.builder()
				.loanId("BL1234")
				.dateOfCreation(java.time.LocalDate.now())
				.interestRate(10.1f)
				.typeOfLoan(TypeOfLoan.BUSINESS_LOANS)
				.build();
	    
	    loanDto = LoanDTO.builder()
				.loanId("BL1234")
				.dateOfCreation(java.time.LocalDate.now())
				.interestRate(10.1f)
				.typeOfLoan(TypeOfLoan.BUSINESS_LOANS)
				.build();

}

//	@Test
//	void checkIfInterestIsTaken_positive(){
//		when(this.repository.findByInterestRate(loanMaster.getInterestRate())).thenReturn(Optional.of(loanMaster));
//	    boolean value = service.checkIfInterestIsTaken(loanMaster.getInterestRate());
//	    assertTrue(value);
//	}
//	
//	@Test
//	void checkIfInterestIs_negative(){
//		when(this.repository.findByInterestRate(loanMaster.getInterestRate())).thenReturn(null);
//	    boolean value = service.checkIfInterestIsTaken(loanMaster.getInterestRate());
//	    assertFalse(value);
//	}
//	

	

}
