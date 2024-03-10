package com.cognizant.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import com.cognizant.BankLendingPortalIcmApplication;
import com.cognizant.entities.LoanAppDetailMaster;
import com.cognizant.entities.LoanAppMaster;

import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import jakarta.persistence.OneToMany;

@ContextConfiguration(classes = BankLendingPortalIcmApplication.class )
@DataJpaTest
class LoanAppMasterTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private LoanAppMasterRepository repository;
	
	private LoanAppMaster loanAppMaster;
	private LoanAppMaster loanAppMaster2;
	private LoanAppDetailMaster loanDetails;
	
	List<LoanAppDetailMaster> loanDetail = new ArrayList<>(); 

	@BeforeEach
	void setup() {
		 loanDetails = LoanAppDetailMaster.builder()
										.id("BL1234")
										.loanAppId(loanAppMaster)
										.monthNo(1)
										.installment(48251)
										.interestRate(10.23f)
										.pOutStandingBeginOfMon(5000000)
										.pRepayment(6584).prOutStandingEndOfMon(4993416)
										.lastDateofinstallPay(LocalDate.of(2024, 4, 10))
										.build();
		
		loanDetail.add(loanDetails);
		
		loanAppMaster = LoanAppMaster.builder()
										.loanAppId("BL1234")
										.interestRate(10.04f)
										.loanAppDetails(loanDetail)
										.applicationDate(LocalDate.now())
										.build();
		
		loanAppMaster2 = LoanAppMaster.builder()
				.loanAppId("BL1224")
				.interestRate(10.05f)
				.loanAppDetails(loanDetail)
				.applicationDate(LocalDate.now())
				.build();
		
		entityManager.persist(loanDetails);
		entityManager.persist(loanAppMaster);
		entityManager.persist(loanAppMaster2);
		
	}
	
	@Test
	void save_Test() {
		loanAppMaster = LoanAppMaster.builder()
				.loanAppId("BL1135")
				.interestRate(10.07f)
				.loanAppDetails(loanDetail)
				.applicationDate(LocalDate.now())
				.build();
		LoanAppMaster value = repository.save(loanAppMaster);
		assertNotNull(value);
	}
	
	@Test
	void findAll_Test() {
		List<LoanAppMaster> value = repository.findAll();
		assertEquals(2,value.size());
	}

}
