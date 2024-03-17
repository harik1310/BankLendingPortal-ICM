package com.cognizant.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

import com.cognizant.BankLendingPortalIcmApplication;
import com.cognizant.dto.LoanDTO;
import com.cognizant.entities.LoanMaster;
import com.cognizant.services.InstallmentCalcServiceImpl;
import com.cognizant.services.LoanMasterService;
import com.cognizant.utilities.TypeOfLoan;
import com.fasterxml.jackson.databind.ObjectMapper;

//@AutoConfigureMockMvc
@WebMvcTest(LoanController.class)
//@ContextConfiguration(classes = BankLendingPortalIcmApplication.class)
////@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
//@WebAppConfiguration()
class LoanControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private LoanMasterService service; 
	
	@InjectMocks
	private LoanController controller;
	
	@Mock
//	private RestTemplate restTemplate;
	
	@Autowired
//	private LocalValidatorFactoryBean validator;
//	private MockRestServiceServer mockServer;
//	private RestTemplate template;
	private ObjectMapper mapper=new ObjectMapper();
	
	LoanDTO loanMaster,loanMaster1;
	
	@BeforeEach
	void setUp() throws Exception {
//		MockitoAnnotations.initMocks(this);
//		mockMvc=MockMvcBuilders.standaloneSetup(controller).build();
//		template=new RestTemplate();
//		mockServer=MockRestServiceServer.createServer(template);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void getAllLoans_when_listHasValues() {
		List<LoanDTO> loanList = new ArrayList<>();
		loanMaster = LoanDTO.builder().loanId("HL1234").
				dateOfCreation(java.time.LocalDate.now())
				.interestRate(10.0f)
				.typeOfLoan(TypeOfLoan.HOME_LOANS).build();

		loanMaster1 = LoanDTO.builder().loanId("BL1234")
				.dateOfCreation(java.time.LocalDate.now())
				.interestRate(10.23f).typeOfLoan(TypeOfLoan.BUSINESS_LOANS).build();
		loanList.add(loanMaster);
		loanList.add(loanMaster1);
		
		when(service.getAllLoans()).thenReturn(loanList);
		
		try {
//		ResponseEntity<?> responseEntity=controller.getAllLoans();
//		List<LoanDTO> actual=(List<LoanDTO>)responseEntity.getBody();
//		assertTrue(actual.size()>0);
		this.mockMvc.perform(get("/loan")).andDo(print()).andExpect(status().is(200));
		
		}catch(Exception e) {
			assertTrue(false);
		}
		
		
	}

}
