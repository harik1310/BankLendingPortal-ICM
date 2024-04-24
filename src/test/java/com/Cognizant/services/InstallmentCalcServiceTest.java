package com.cognizant.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
import static org.mockito.Mockito.*;

import com.cognizant.dto.LoanCalcDTO;
import com.cognizant.dto.ReducedPaymentDTO;
import com.cognizant.entities.LoanAppDetailMaster;
import com.cognizant.entities.LoanAppMaster;
import com.cognizant.repository.LoanAppDetailMasterRepository;
import com.cognizant.repository.LoanAppMasterRepository;

@ExtendWith(MockitoExtension.class)
class InstallmentCalcServiceTest {
	
	@Mock
	private LoanAppDetailMasterRepository installmentRepository;
	@Mock
	private LoanAppMasterRepository loanApplicationRepository;
	
	@InjectMocks
	private InstallmentCalcServiceImpl service;
	LoanCalcDTO lac,lac1;
	ReducedPaymentDTO reducedDto;
	LoanAppMaster lId = mock(LoanAppMaster.class);
	
	LoanAppDetailMaster ladm;
	LoanAppMaster loanAppMaster;
	List<LoanAppDetailMaster> loanApplist = new ArrayList<>();
	List<ReducedPaymentDTO> dtoList = new ArrayList<>();
	
	@BeforeEach
	void setUp() throws Exception {
		lac = LoanCalcDTO.builder()
				.loanAppId("HL1234").loanTenureMonths(120).emi(0).dueDate(null)
				.monthlyinterestRate(10.0f).pAmount(1000000).totalAmountPayable(1400000)
				.dueDate(LocalDate.of(2024, 03,17))
				.build();
		
		lac1 = LoanCalcDTO.builder()
				.loanAppId("HL1234").loanTenureMonths(240).emi(0).dueDate(LocalDate.now())
				.monthlyinterestRate(10.0f).pAmount(5000000).totalAmountPayable(0).dueDate(LocalDate.of(2024, 03,17))
				.build();
		
		
		
		
		ladm = LoanAppDetailMaster.builder()
				.loanAppId(lId).monthNo(1).installment(47825)
				.interestRate(10.0f).lastDateofinstallPay(LocalDate.of(2024, 03,17))
				.pOutStandingBeginOfMon(5000000).prOutStandingEndOfMon(4600000)
				.pRepayment(6423)
				.build();
		
		reducedDto = ReducedPaymentDTO.builder()
					.loanAppId(lId).month(1).emi(47825)
					.interest(10.0f).lastDateOfEmi(LocalDate.of(2024, 03,17))
					.pOutStandingBeginOfMonth(5000000).pOutStandingEndOfMonth(4600000)
					.principalRepayment(6423)
					.build();
		
		loanAppMaster = LoanAppMaster.builder().loanAppId("HL1234").interestRate(10.0f)
				.applicationDate(LocalDate.of(2024, 03,17)).loanAppDetails(loanApplist).build();
		
	}
	

	@Test
	void installmentCalc() {
		assertNotNull(service.installmentCalc(lac));
	}
	@Test
	void reducedInstallmentCalc_when_calculating() {
//		loanApplist.add(ladm);
		when(loanApplicationRepository.findById(any())).thenReturn(Optional.of(loanAppMaster));
		when(installmentRepository.findAllByLoanAppId(any())).thenReturn(loanApplist);
		assertNotNull(service.reducedInsallmentCalc(lac));
	}
	
	@Test
	void reducedInstallmentCalc() {
		loanApplist.add(ladm);
		when(installmentRepository.findAllByLoanAppId(any())).thenReturn(loanApplist);
		when(loanApplicationRepository.findById(any())).thenReturn(Optional.of(loanAppMaster));
		assertNotNull(service.reducedInsallmentCalc(lac));
	}
	
	
	@Test
	void reducedInstallmentCalc_when_loapListIsEmpty() {
		loanAppMaster.setPAmount(120000);
		loanAppMaster.setTenure(12);
		List<LoanAppDetailMaster> list = new ArrayList<>();
		when(installmentRepository.findAllByLoanAppId(any())).thenReturn(list);
		when(loanApplicationRepository.findById(any())).thenReturn(Optional.of(loanAppMaster));
		assertNotNull(service.reducedInsallmentCalc(lac));
	}
	
	
	@Test
	void getLoanDetails() {
//		when(loanApplicationRepository.findInterestRate(any())).thenReturn(10.0);
		when(loanApplicationRepository.findById(any())).thenReturn(Optional.of(loanAppMaster));
		assertNotNull(service.getLoanDetailList(lac));
	}
	
	@Test
	void getLoanDetails_when_InterestIsNull() {
		lac.setMonthlyinterestRate(0);
//		when(loanApplicationRepository.findInterestRate(any())).thenReturn(10.0);
		when(loanApplicationRepository.findById(any())).thenReturn(Optional.of(loanAppMaster));
		assertNotNull(service.getLoanDetailList(lac));
	}
//	@Test
//	void getLoanDetails_when() {
////		lac.setMonthlyinterestRate(0);
//		when(loanApplicationRepository.findInterestRate(any())).thenReturn(10.0);
//		when(loanApplicationRepository.findById(any())).thenReturn(Optional.ofNullable(loanAppMaster));
//		assertNotNull(service.getLoanDetailList(lac));
//	}
	@Test
	void getLoanDetails_when_listHasValues() {
		loanApplist.add(ladm);
		when(loanApplicationRepository.findById(any())).thenReturn(Optional.of(loanAppMaster));
		assertNotNull(service.getLoanDetailList(lac));
	}
	
	@Test
	void getLoanDetails_when_list() {
		loanApplist.add(ladm);
		lac.setMonthlyinterestRate(0);
		loanAppMaster.setInterestRate(0);
		lac.setDueDate(null);
		when(loanApplicationRepository.findById(any())).thenReturn(Optional.of(loanAppMaster));
		assertNotNull(service.getLoanDetailList(lac));
	}
	
}
