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
import org.mockito.exceptions.verification.MoreThanAllowedActualInvocations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

import com.cognizant.BankLendingPortalIcmApplication;
import com.cognizant.BankLendingPortalIcmApplicationTests;
import com.cognizant.dto.LoanDTO;
import com.cognizant.dto.NewLoanDTO;
import com.cognizant.dto.UserDTO;
import com.cognizant.dto.UserRequest;
import com.cognizant.repository.LoanAppMasterRepository;
import com.cognizant.repository.LoanMasterRepository;
import com.cognizant.repository.UserRepository;
import com.cognizant.services.LoanMasterService;
import com.cognizant.services.UserService;
import com.cognizant.utilities.TypeOfLoan;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = { BankLendingPortalIcmApplicationTests.class })
class AuthenticationControllerTest {

	private MockMvc mockMvc;

	@Mock
	private UserRepository repo;

	
	@Mock
	private UserService service;

	@InjectMocks
	private AuthenticationController controller;

	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	void userIsValid() {
		UserRequest us =  new UserRequest();
		us.setUserName("admin");
		us.setPassword("admin");
		UserDTO usdto =new UserDTO();
		usdto.setUserName("admin");
		usdto.setPassword("admin");
		when(service.authenticateUser(any(),any())).thenReturn(usdto);

			ResponseEntity<?> responseEntity = controller.authenticate(us);
			UserDTO actual = (UserDTO) responseEntity.getBody();
			assertNotNull(actual);
	}
	
	@Test
	void userIsNotValid() {
		UserRequest us =  new UserRequest();
		us.setUserName("admin");
		us.setPassword("admin");
		
		UserDTO usdto =  new UserDTO();

		when(service.authenticateUser(any(),any())).thenReturn(usdto);

			ResponseEntity<?> responseEntity = controller.authenticate(us);
			Object actual =responseEntity.getBody();
			assertNull(actual);
	}

}
