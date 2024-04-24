package com.cognizant.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

import com.cognizant.BankLendingPortalIcmApplication;
import com.cognizant.dto.LoanCalcDTO;
import com.cognizant.dto.ReducedPaymentDTO;
import com.cognizant.entities.LoanAppMaster;
import com.cognizant.repository.LoanAppDetailMasterRepository;
import com.cognizant.repository.LoanAppMasterRepository;
import com.cognizant.services.InstallmentCalcService;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.cognizant.*;

@SpringBootTest(classes = { BankLendingPortalIcmApplicationTests.class })
class InstallmentControllerTest {
	
	private MockMvc mockMvc;

	@Mock
	private LoanAppDetailMasterRepository installmentRepository;

	@Mock
	private LoanAppMasterRepository loanAppMasterRepository;
	
	@Mock
	private InstallmentCalcService service;
	
	@InjectMocks
	private InstallmentController controller;

	private LocalValidatorFactoryBean validator;
	private MockRestServiceServer mockServer;
	private RestTemplate template;
	private ObjectMapper mapper = new ObjectMapper();
	
	ReducedPaymentDTO reducedDto;
	LoanCalcDTO lac, lac1;
	LoanAppMaster lId = mock(LoanAppMaster.class);
	

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		template = new RestTemplate();
		mockServer = MockRestServiceServer.createServer(template);
	}
	
	@Test
	void getLoanDetailList_when_listNotEmpty() {
		List<ReducedPaymentDTO> list = new ArrayList<>();
		
		lac = LoanCalcDTO.builder()
				.loanAppId("HL1234").loanTenureMonths(120).emi(0).dueDate(null)
				.monthlyinterestRate(10.0f).pAmount(1000000).totalAmountPayable(1400000)
				.dueDate(LocalDate.of(2024, 03,17))
				.build();
		
		reducedDto = ReducedPaymentDTO.builder()
				.loanAppId(lId).month(1).emi(47825)
				.interest(10.0f).lastDateOfEmi(LocalDate.of(2024, 03,17))
				.pOutStandingBeginOfMonth(5000000).pOutStandingEndOfMonth(4600000)
				.principalRepayment(6423)
				.build();
		list.add(reducedDto);
		
		when(service.getLoanDetailList(any())).thenReturn(list);
			ResponseEntity<?> responseEntity = controller.getLoanDetailList(lac);
			List<ReducedPaymentDTO> actual = (List<ReducedPaymentDTO>) responseEntity.getBody();
			assertTrue(actual.size() > 0);
	}
	
	@Test
	void getLoanDetailList_when_listEmpty() {
		List<ReducedPaymentDTO> list = new ArrayList<>();
		when(service.getLoanDetailList(any())).thenReturn(list);
			ResponseEntity<?> responseEntity = controller.getLoanDetailList(lac);
			List<ReducedPaymentDTO> actual = (List<ReducedPaymentDTO>) responseEntity.getBody();
			assertFalse(actual.size() > 0);

	}
	
	@Test
	void emiCalculation_whenCalculated() {
		lac1 = LoanCalcDTO.builder()
				.loanAppId("HL1234").loanTenureMonths(120).emi(0).dueDate(null)
				.monthlyinterestRate(10.0f).pAmount(1000000).totalAmountPayable(1400000)
				.dueDate(LocalDate.of(2024, 03,17))
				.build();
		
		when(service.installmentCalc(any())).thenReturn(lac1);
		
		try {
			ResponseEntity<?> responseEntity = controller.emiCalculation(mock(LoanCalcDTO.class));
			LoanCalcDTO dto = (LoanCalcDTO) responseEntity.getBody();
			assertNotNull(dto);
		}
		catch(Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	void emiCalculation_whenNotCalculated() {
		lac1 = LoanCalcDTO.builder()
				.loanAppId("HL1234").loanTenureMonths(120).emi(0).dueDate(null)
				.monthlyinterestRate(10.0f).pAmount(1000000).totalAmountPayable(1400000)
				.dueDate(LocalDate.of(2024, 03,17))
				.build();
		
		when(service.installmentCalc(any())).thenReturn(null);
		
		try {
			ResponseEntity<?> responseEntity = controller.emiCalculation(mock(LoanCalcDTO.class));
			LoanCalcDTO dto = (LoanCalcDTO) responseEntity.getBody();
			assertNull(dto);
		}
		catch(Exception e) {
			assertTrue(false);
		}
	}
}
