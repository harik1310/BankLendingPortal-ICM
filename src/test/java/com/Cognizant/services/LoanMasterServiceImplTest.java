package com.Cognizant.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.cognizant.BankLendingPortalIcmApplication;
import com.cognizant.entities.LoanMaster;
import com.cognizant.repository.LoanMasterRepository;
import com.cognizant.services.LoanMasterServiceImpl;
import com.cognizant.utilities.TypeOfLoan;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration(classes = BankLendingPortalIcmApplication.class )
@DataJpaTest
class LoanMasterServiceImplTest {
	
	@Autowired
    TestEntityManager entityManager;

	@Autowired
	private LoanMasterRepository loanMasterRepository;
	
	@Autowired
	private LoanMasterServiceImpl service;

	private LoanMaster loanMaster;
	private LoanMaster loanMaster1;

	@BeforeEach
	public void setUpData(){
		loanMaster = LoanMaster.builder()
							.loanId("HL1234")
							.dateOfCreation(java.time.LocalDate.now())
							.interestRate(10.0f)
							.typeOfLoan(TypeOfLoan.HOME_LOANS)
							.build();

		loanMaster1 = LoanMaster.builder()
							.loanId("AL1234")
							.dateOfCreation(java.time.LocalDate.now())
							.interestRate(10.0f)
							.typeOfLoan(TypeOfLoan.AUTO_LOANS)
							.build();
						
	}
	
	@Test
	void persistNewLoanTest() {
		LoanMaster verify = loanMasterRepository.save(loanMaster);
		
		assertNotNull(verify);
	}

}
