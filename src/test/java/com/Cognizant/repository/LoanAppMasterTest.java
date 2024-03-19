package com.cognizant.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import com.cognizant.BankLendingPortalIcmApplication;
import com.cognizant.entities.LoanAppDetailMaster;
import com.cognizant.entities.LoanAppMaster;

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
	
	List<LoanAppDetailMaster> loanDetailList = new ArrayList<>(); 

	@BeforeEach
	void setup() {
		//Database is already populated with 7 values
		
		loanDetailList.add(loanDetails);
		
		loanAppMaster = LoanAppMaster.builder()
										.loanAppId("BL1234")
										.interestRate(10.04f)
										.loanAppDetails(loanDetailList)
										.applicationDate(LocalDate.now())
										.build();
		
//		loanAppMaster2 = LoanAppMaster.builder()
//				.loanAppId("BL1224")
//				.interestRate(10.05f)
//				.loanAppDetails(loanDetail)
//				.applicationDate(LocalDate.now())
//				.build();
		
		loanDetails = LoanAppDetailMaster.builder()
				.id(6)//"BL1234"
				.loanAppId(loanAppMaster)
				.monthNo(1)
				.installment(48251)
				.interestRate(10.23f)
				.pOutStandingBeginOfMon(5000000)
				.pRepayment(6584).prOutStandingEndOfMon(4993416)
				.lastDateofinstallPay(LocalDate.of(2024, 4, 10))
				.build();
		
		entityManager.persist(loanAppMaster);
		entityManager.persist(loanDetails);
//		entityManager.persist(loanAppMaster2);
		
	}
	
	@Test
	void findAll_Test() {
		List<LoanAppMaster> value = repository.findAll();
		System.out.println(value.size());
		//Database is already populated with 7 values
		assertEquals(8,value.size());
	}

	@Test
	void save_Test() {
		loanAppMaster = LoanAppMaster.builder()
				.loanAppId("BL1135")
				.interestRate(10.07f)
				.loanAppDetails(loanDetailList)
				.applicationDate(LocalDate.now())
				.build();
		LoanAppMaster value = repository.save(loanAppMaster);
		assertNotNull(value);
	}
	

}
