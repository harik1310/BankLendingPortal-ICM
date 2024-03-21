package com.cognizant.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

import com.cognizant.BankLendingPortalIcmApplication;
import com.cognizant.dto.LoanDTO;
import com.cognizant.dto.NewLoanDTO;
import com.cognizant.repository.LoanAppMasterRepository;
import com.cognizant.repository.LoanMasterRepository;
import com.cognizant.services.LoanMasterService;
import com.cognizant.utilities.TypeOfLoan;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = { BankLendingPortalIcmApplication.class })
class LoanControllerTest {

	private MockMvc mockMvc;

	@Mock
	private LoanMasterRepository loanMasterRepository;

	@Mock
	private LoanAppMasterRepository loanAppMasterRepository;

	
	@Mock
	private LoanMasterService service;

	@InjectMocks
	private LoanController controller;

	@Autowired
	private LocalValidatorFactoryBean validator;
	private MockRestServiceServer mockServer;
	private RestTemplate template;
	private ObjectMapper mapper = new ObjectMapper();
	
	LoanDTO loanMaster, loanMaster1;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		template = new RestTemplate();
		mockServer = MockRestServiceServer.createServer(template);
	}

	@Test
	void getAllLoans_when_listHasValues() {
		List<LoanDTO> loanList = new ArrayList<>();
		loanMaster = LoanDTO.builder().loanId("HL1234").dateOfCreation(java.time.LocalDate.now()).interestRate(10.0f)
				.typeOfLoan(TypeOfLoan.HOME_LOANS).build();

		loanMaster1 = LoanDTO.builder().loanId("BL1234").dateOfCreation(java.time.LocalDate.now()).interestRate(10.23f)
				.typeOfLoan(TypeOfLoan.BUSINESS_LOANS).build();
		loanList.add(loanMaster);
		loanList.add(loanMaster1);

		when(service.getAllLoans()).thenReturn(loanList);

			ResponseEntity<?> responseEntity = controller.getAllLoans();
			List<LoanDTO> actual = (List<LoanDTO>) responseEntity.getBody();
			assertTrue(actual.size() > 0);
			MvcResult rs;
			try {
				rs = this.mockMvc.perform(get("api/loan/")).andDo(print()).andExpect(status().is(200))
						.andReturn();
				assertEquals(200,rs.getResponse().getStatus() );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	@Test
	void getAllLoans_when_listHasNoValues() {
		List<LoanDTO> loanList = new ArrayList<>();
		when(service.getAllLoans()).thenReturn(loanList);

		try {
			ResponseEntity<?> responseEntity = controller.getAllLoans();
			List<LoanDTO> actual = (List<LoanDTO>) responseEntity.getBody();
			assertEquals(0,actual.size());
			MvcResult rs = this.mockMvc.perform(get("/api/loan/")).andDo(print()).andExpect(status().is(204))
					.andReturn();
			assertEquals(204, rs.getResponse().getStatus());

		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	void getByLoanId_when_idExists() {
		loanMaster = LoanDTO.builder().loanId("HL1234").dateOfCreation(java.time.LocalDate.now()).interestRate(10.0f)
				.typeOfLoan(TypeOfLoan.HOME_LOANS).build();
		when(service.getLoanDetailsById(anyString())).thenReturn(loanMaster);

		try {
			ResponseEntity<?> responseEntity = controller.getByLoanId("HL1234");
			LoanDTO response = (LoanDTO) responseEntity.getBody();
			assertNotNull(response);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	void getByLoanId_when_idDoesNotExist() {
		loanMaster = LoanDTO.builder().loanId("HL1234").dateOfCreation(java.time.LocalDate.now()).interestRate(10.0f)
				.typeOfLoan(TypeOfLoan.HOME_LOANS).build();
		when(service.getLoanDetailsById(anyString())).thenReturn(null);

		try {
			ResponseEntity<?> responseEntity = controller.getByLoanId("HL1234");
			LoanDTO response = (LoanDTO) responseEntity.getBody();
			assertNull(response);
		} catch (Exception e) {
			assertTrue(false);
		}

	}

	@Test
	void createNewLoan_when_dataIsCorrect() {
		LoanDTO loanMaster = LoanDTO.builder().loanId("HL1234").dateOfCreation(java.time.LocalDate.now())
				.interestRate(10.0f).typeOfLoan(TypeOfLoan.HOME_LOANS).build();

		when(service.persistNewLoan(any(NewLoanDTO.class))).thenReturn(loanMaster);

		try {	
			ResponseEntity<?> responseEntity = controller.createNewLoan(mock(NewLoanDTO.class));
			assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	void createNewLoan_when_dataIsInCorrect() {
		when(service.persistNewLoan(any(NewLoanDTO.class))).thenReturn(null);

		try {	
			ResponseEntity<?> responseEntity = controller.createNewLoan(mock(NewLoanDTO.class));
			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	void updateLoan_when_dataIsCorrect() {
		LoanDTO loanMaster = LoanDTO.builder().loanId("HL1234").dateOfCreation(java.time.LocalDate.now())
				.interestRate(10.0f).typeOfLoan(TypeOfLoan.HOME_LOANS).build();

		when(service.updateLoanDetails(anyString(),any(NewLoanDTO.class))).thenReturn(loanMaster);

		try {	
			ResponseEntity<?> responseEntity = controller.updateLoan("HL2342",mock(NewLoanDTO.class));
			assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	void updateLoan_when_dataIsInCorrect() {
		when(service.updateLoanDetails(anyString(),any(NewLoanDTO.class))).thenReturn(null);

		try {	
			ResponseEntity<?> responseEntity = controller.updateLoan("HL2342",mock(NewLoanDTO.class));
			System.out.println(responseEntity.getStatusCode());
			assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertTrue(false);
		}
	}
}
