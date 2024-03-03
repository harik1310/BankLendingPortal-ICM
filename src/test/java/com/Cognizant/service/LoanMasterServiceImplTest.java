package com.Cognizant.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cognizant.repository.LoanMasterRepository;
import com.cognizant.services.LoanMasterServiceImpl;

@DataJpaTest
class LoanMasterServiceImplTest {
	
	
	@Autowired
	private LoanMasterRepository loanMasterRepository;
	
	@Autowired
	private LoanMasterServiceImpl service;
	
	@Test
	void persistNewLoanTest() {
		
	}

}
