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
import com.cognizant.entities.LoanAppMaster;
import com.cognizant.entities.LoanMaster;
import com.cognizant.repository.LoanAppMasterRepository;
import com.cognizant.repository.LoanMasterRepository;
import com.cognizant.utilities.TypeOfLoan;

@ExtendWith(MockitoExtension.class)
class LoanMasterServiceTest {

	@Mock
	private LoanMasterRepository repository;
	
	@Mock
	private LoanAppMasterRepository loanRepository;

	@InjectMocks
	private LoanMasterServiceImpl service;

	private LoanMaster loanMaster, loanMaster1, loanMaster2;
	private LoanDTO loanDto, loanDto1;

	public List<LoanMaster> loanlt = new ArrayList<>();
	public List<LoanDTO> loanDTOlt = new ArrayList<>();
	LoanMaster mockLoanMaster = mock(LoanMaster.class);
	LoanDTO mockLoanDTO = mock(LoanDTO.class);

	@BeforeEach
	void setUp() throws Exception {

		loanlt.add(mockLoanMaster);
		loanDTOlt.add(mockLoanDTO);

		loanMaster = LoanMaster.builder().loanId("HL1234").
				dateOfCreation(java.time.LocalDate.now())
				.interestRate(10.0f)
				.typeOfLoan(TypeOfLoan.HOME_LOANS).build();

		loanMaster1 = LoanMaster.builder().loanId("BL1234")
				.dateOfCreation(java.time.LocalDate.now())
				.interestRate(10.23f).typeOfLoan(TypeOfLoan.BUSINESS_LOANS).build();

		loanDto = LoanDTO.builder().loanId("BL1234")
				.dateOfCreation(java.time.LocalDate.now())
				.interestRate(10.0f)
				.typeOfLoan(TypeOfLoan.BUSINESS_LOANS).build();

		loanDto1 = LoanDTO.builder().loanId("BL1214")
				.dateOfCreation(java.time.LocalDate.now())
				.interestRate(10.67f)
				.typeOfLoan(TypeOfLoan.BUSINESS_LOANS).build();

	}

	@Test
	void checkIfInterestIsTaken_positive(){
		when(this.repository.findByInterestRate(anyFloat())).thenReturn(Optional.of(loanMaster));
	    boolean value = service.checkIfInterestIsTaken(loanMaster.getInterestRate());
	    assertTrue(value);
	}

	@Test
	void checkIfInterestIs_negative() {
		Optional<LoanMaster> opt = Optional.ofNullable(null);
		when(repository.findByInterestRate(anyFloat())).thenReturn(opt);
		assertFalse(this.service.checkIfInterestIsTaken(anyFloat()));
	}

	@Test
	void getAllLoans_when_listHasValues() {
		when(repository.findAll()).thenReturn(loanlt);
		assertTrue(service.getAllLoans().size()>0);
	}

	@Test
	void getAllLoans_when_NoValueExists() {
		List<LoanMaster> ltm = new ArrayList<>();
		when(repository.findAll()).thenReturn(ltm);
		List<LoanDTO> loanReturnList = service.getAllLoans();
		assertTrue(loanReturnList.isEmpty());
	}

	@Test
	void getLoanDetailsById_when_valueWithIdExists() {
		when(repository.findById(anyString())).thenReturn(Optional.of(mockLoanMaster));
		assertNotNull(service.getLoanDetailsById("HL1234"));
	}

	@Test
	void getLoanDetailsById_when_valueWithIdDoesNotExists() {
		when(repository.findById(anyString())).thenReturn(Optional.ofNullable(null));
		
		assertNull(service.getLoanDetailsById("HL1234"));
	}

	@Test
	void getLatestInterestValue_when_valueExists() {
		when(repository.findByTypeOfLoanAndDateOfCreation(any(TypeOfLoan.class))).thenReturn(Optional.of(loanMaster));
		assertTrue(0.0f<service.getLatestInterestValue(TypeOfLoan.AUTO_LOANS));
	}

	@Test
	void getLatestInterestValue_when_valueDoesNotExists() {
		when(repository.findByTypeOfLoanAndDateOfCreation(any(TypeOfLoan.class))).thenReturn(Optional.ofNullable(null));
		assertEquals(0.0f,service.getLatestInterestValue(TypeOfLoan.AUTO_LOANS));
	}

	@Test
	void updateLoantest_when_Positive() {
		
		when(repository.findById(any())).thenReturn(Optional.of(loanMaster1));
		when(repository.findByInterestRate(anyFloat())).thenReturn(Optional.ofNullable(null));
		
		when(repository.save(any(LoanMaster.class))).thenReturn(loanMaster1);
		assertNotNull(service.updateLoanDetails("BL1234", mock(NewLoanDTO.class)));
	}

	@Test
	void updateLoantest_when_recordDoesNotExist() {
		when(repository.findById("BL1234")).thenReturn(Optional.ofNullable(null));
		assertNull(service.updateLoanDetails("BL1234", mock(NewLoanDTO.class)));
	}
	
	@Test
	void updateLoantest_when_findByInterestReturns_withValue() {
		
		when(repository.findById(any())).thenReturn(Optional.of(loanMaster1));
		when(repository.findByInterestRate(anyFloat())).thenReturn(Optional.of(loanMaster1));
		when(repository.findByTypeOfLoanAndDateOfCreation(any())).thenReturn(Optional.of(loanMaster));
		when(repository.save(any(LoanMaster.class))).thenReturn(loanMaster1);
		assertNotNull(service.updateLoanDetails("BL1234", mock(NewLoanDTO.class)));
	}
	
	@Test
	void isDateValid_when_dateIsToday() {
		assertTrue(service.isDateValid(LocalDate.now()));
	}
	
	@Test
	void isDateValid_when_dateIsAfterToday() {
		LocalDate after = LocalDate.now();
		after = after.plusDays(2);
		assertTrue(service.isDateValid(after));
	}
	
	@Test
	void isDateValid_when_dateIsbeforeToday() {
		LocalDate after = LocalDate.now();
		after = after.minusDays(2);
		assertFalse(service.isDateValid(after));
	}
	
	@Test
	void isInterestValid_when_lessThan50() {
		float value = 12.0f;
		assertTrue(service.isInterestValid(value));
	}
	
	@Test
	void isInterestValid_when_greaterThan50() {
		float value = 51.0f;
		assertFalse(service.isInterestValid(value));
	}
	
	@Test
	void isInterestValid_when_null() {
		float value = 0.0f;
		assertFalse(service.isInterestValid(value));
	}

//	@Test
//	void persistNewLoan_when_dataIsInvalid() {
//		NewLoanDTO newDto = NewLoanDTO.builder().loanId("HL1234").dateOfCreation(LocalDate.now()).interestRate(12.9f).typeOfLoan(TypeOfLoan.AUTO_LOANS).build();
//		when(repository.findByInterestRate(anyFloat())).thenReturn(Optional.ofNullable(null));
//		when(repository.save(any())).thenReturn(loanMaster);
//		assertNull(service.persistNewLoan(newDto));
//	}
	@Test
	void persistNewLoan_when_InterestIsTaken() {
		NewLoanDTO newDto = NewLoanDTO.builder().loanId("HL1234").dateOfCreation(LocalDate.now()).interestRate(12.9f).typeOfLoan(TypeOfLoan.AUTO_LOANS).build();
		when(repository.findByInterestRate(anyFloat())).thenReturn(Optional.of(loanMaster));
		when(repository.findByTypeOfLoanAndDateOfCreation(any())).thenReturn(Optional.of(loanMaster));
		when(repository.save(any())).thenReturn(loanMaster);
		assertNotNull(service.persistNewLoan(newDto));
	}
}
