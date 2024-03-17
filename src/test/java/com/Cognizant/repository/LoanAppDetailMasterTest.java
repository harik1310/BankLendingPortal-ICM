package com.cognizant.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
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

@DataJpaTest
@ContextConfiguration(classes = BankLendingPortalIcmApplication.class)
class LoanAppDetailMasterTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private LoanAppDetailMasterRepository loanAppDetailMasterRepository;
	
	LoanAppMaster loanAppMaster;
	LoanAppDetailMaster loanDetails,loanDetails1 ;
	List<LoanAppDetailMaster> list=new ArrayList<>();
 
	@BeforeEach
	void setup() {
		
		loanAppMaster = LoanAppMaster.builder()
				.applicationDate(LocalDate.of(2023, 10, 12))
				.interestRate(12.0f)
				.loanAppId("AL1234")
				.loanAppDetails(list)
				.build();
		
		entityManager.persist(loanAppMaster);
		
		
		
		 loanDetails = LoanAppDetailMaster.builder()
												.id(2)//"BL1274"
												.loanAppId(loanAppMaster)
												.monthNo(1)
												.installment(48251)
												.interestRate(10.23f).pOutStandingBeginOfMon(5000000).pRepayment(6584).prOutStandingEndOfMon(4993416)
												.lastDateofinstallPay(LocalDate.of(2024, 4, 10))
												.build();
		
		 loanDetails1 = LoanAppDetailMaster.builder()
												.id(3)//"BL1234"
												.loanAppId(loanAppMaster)
												.monthNo(1)
												.installment(41612)
												.interestRate(10.23f).pOutStandingBeginOfMon(4993416).pRepayment(6584).prOutStandingEndOfMon(4986776)
												.lastDateofinstallPay(LocalDate.of(2024, 5, 10)).build();
		
		
		list.add(loanDetails1);
		list.add(loanDetails);
		
		
		

		
		
	}
	@Test
	void findAll_Test() {
		entityManager.persist(loanAppMaster);
		entityManager.persist(loanDetails);
		entityManager.persist(loanDetails1);
		List<LoanAppDetailMaster> list = loanAppDetailMasterRepository.findAll();
		assertEquals(2, list.size());
	}
	
	@Test
	void saveLoanMaster(){
		LoanAppDetailMaster loanDetails2 = LoanAppDetailMaster.builder()
				.id(1)//"BL1204"
				.loanAppId(loanAppMaster)
				.monthNo(1).installment(41556).interestRate(10.23f)
				.pOutStandingBeginOfMon(4986776).pRepayment(6584).prOutStandingEndOfMon(4993416)
				.lastDateofinstallPay(LocalDate.of(2024, 6, 10))
				.build();
		list.add(loanDetails2);
		entityManager.persist(loanAppMaster);

		LoanAppDetailMaster value = loanAppDetailMasterRepository.save(loanDetails2);
		assertNotNull(value);
	}
	



}
