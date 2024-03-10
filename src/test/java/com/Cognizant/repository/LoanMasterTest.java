package com.cognizant.repository;

import static org.assertj.core.api.Assertions.assertThatException;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.cognizant.BankLendingPortalIcmApplication;
import com.cognizant.entities.LoanMaster;
import com.cognizant.utilities.TypeOfLoan;

import jakarta.persistence.EntityManager;

import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = BankLendingPortalIcmApplication.class )
@DataJpaTest
class LoanMasterTest {
	
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private LoanMasterRepository loanMasterRepository;

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
							.loanId("BL1234")
							.dateOfCreation(java.time.LocalDate.now())
							.interestRate(10.1f)
							.typeOfLoan(TypeOfLoan.BUSINESS_LOANS)
							.build();
		
		entityManager.persist(loanMaster);
		entityManager.persist(loanMaster1);
	}
	
	@Test
	void saveNewLoan_details_Test() {
		LoanMaster verify = loanMasterRepository.save(loanMaster);
		LoanMaster verify1 = loanMasterRepository.save(loanMaster1);
		assertNotNull(verify);
		assertNotNull(verify1);
	}
	
	@Test
	void saveNewLoanDetails_NegativeTest() {
		loanMaster1 = LoanMaster.builder()
				.loanId("BL1234")
				.dateOfCreation(java.time.LocalDate.now())
				.interestRate(10.23f)
				.typeOfLoan(TypeOfLoan.BUSINESS_LOANS)
				.build();
	}
	
	@Test
	void findById_Test() {
		Optional<LoanMaster> returned_obj = loanMasterRepository.findById(loanMaster1.getLoanId()); 
		LoanMaster returned = returned_obj.get();
		assertTrue(returned_obj.isPresent());
		assertEquals(loanMaster1.getLoanId(), returned.getLoanId());
	}
	
	@Test
	void findById_When_Id_notPresent_Test() {
		Optional<LoanMaster> returned_obj = loanMasterRepository.findById("AX1234"); 
		assertFalse(returned_obj.isPresent());
	}
	
	@Test
	void findByInterestRate_Test() {
		Optional<LoanMaster> value = loanMasterRepository.findByInterestRate(10.0f);
		assertEquals(value, loanMaster);
	}
	
	@Test
	void findByInterestRate_when_Not_Present() {
		Optional<LoanMaster> value = loanMasterRepository.findByInterestRate(11.0f);
		assertNull(value);
	}
	
//	@Test
//	void findByInterestRate_Exception() {
//		LoanMaster value = loanMasterRepository.findByInterestRate(24);
////		assertNull(value);
//		assertThatException(Exception).
//		}
}
