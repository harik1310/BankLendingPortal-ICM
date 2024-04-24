package com.cognizant.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.cognizant.dto.LoanDTO;
import com.cognizant.dto.NewLoanDTO;
import com.cognizant.dto.UserDTO;
import com.cognizant.entities.LoanAppMaster;
import com.cognizant.entities.LoanMaster;
import com.cognizant.entities.Users;
import com.cognizant.repository.LoanAppMasterRepository;
import com.cognizant.repository.LoanMasterRepository;
import com.cognizant.repository.UserRepository;
import com.cognizant.utilities.TypeOfLoan;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	@Mock
	private UserRepository repository;

	@InjectMocks
	private UserServiceImpl service;

	private Users user;
	private UserDTO loanDto, loanDto1;

	public List<Users> loanlt = new ArrayList<>();
	Users mockLoanMaster = mock(Users.class);
	UserDTO mockLoanDTO = mock(UserDTO.class);

	@BeforeEach
	void setUp() throws Exception {
		Users u = new Users();
		u.setUserName("admin");
		u.setPassword("admin");
		u.setRole("admin");
		u.setAccountLocked(false);
//		loanlt.add(mockLoanMaster);
		loanlt.add(u);
//		loanDTOlt.add(mockLoanDTO);

	}

	@Test
	void userListIsNotNull(){
		when(this.repository.findAll()).thenReturn(loanlt);
	   
	    assertNotNull(this.service.listOfUsers());
	}

	@Test
	void authenticate() {
//		when(this.repository.findAll()).thenReturn(loanlt);
		when(this.service.listOfUsers()).thenReturn(loanlt);
		UserDTO dt = this.service.authenticateUser("admin", "admin");
		assertNotNull(dt);
	}
}
