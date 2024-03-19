package com.cognizant.utilities.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cognizant.dto.ReducedPaymentDTO;
import com.cognizant.entities.LoanAppDetailMaster;
import com.cognizant.entities.LoanAppMaster;

class InstallmentMapperTest {
	
	
	LoanAppMaster loanAppMaster;
	LoanAppDetailMaster entity;
	ReducedPaymentDTO dto ;
	List<LoanAppDetailMaster> list=new ArrayList<>();

	@BeforeEach
	void setUp() throws Exception {
		loanAppMaster = LoanAppMaster.builder()
								.loanAppId("AL1234")
								.interestRate(12.0f)
								.applicationDate(LocalDate.of(2023, 10, 12))
								.loanAppDetails(list)
								.build();
		
		dto = ReducedPaymentDTO.builder()
								.loanAppId(loanAppMaster)
								.month(1)
								.emi(48251)
								.interest(10.23f).pOutStandingBeginOfMonth(5000000).principalRepayment(6584).pOutStandingEndOfMonth(4993416)
								.lastDateOfEmi(LocalDate.of(2024, 4, 10))
								.build();
		
		loanAppMaster = LoanAppMaster.builder()
				.loanAppId("AL1234")
				.interestRate(12.0f)
				.applicationDate(LocalDate.of(2023, 10, 12))
				.loanAppDetails(list)
				.build();
		
		entity = LoanAppDetailMaster.builder()
				.id(2)//"BL1274"
				.loanAppId(loanAppMaster)
				.monthNo(1)
				.installment(48251)
				.interestRate(10.23f).pOutStandingBeginOfMon(5000000).pRepayment(6584).prOutStandingEndOfMon(4993416)
				.lastDateofinstallPay(LocalDate.of(2024, 4, 10))
				.build();
		
		
}
	@Test
	void toDTO_Test() {
		ReducedPaymentDTO dto = InstallmentMapper.toDTO(entity);
			assertEquals(dto.getLoanAppId(), entity.getLoanAppId());
			assertEquals(dto.getEmi(), entity.getInstallment());
			assertEquals(dto.getInterest(), entity.getInterestRate());
			assertEquals(dto.getMonth(), entity.getMonthNo());
			assertEquals(dto.getPOutStandingBeginOfMonth(), entity.getPOutStandingBeginOfMon());
			assertEquals(dto.getPOutStandingEndOfMonth(), entity.getPrOutStandingEndOfMon());
			assertEquals(dto.getPrincipalRepayment(), entity.getPRepayment());
			assertEquals(dto.getLastDateOfEmi(), entity.getLastDateofinstallPay());
	}
	
	@Test
	void todto_Test() {
		LoanAppDetailMaster entity = InstallmentMapper.toEntity(dto);
		assertEquals(dto.getLoanAppId(), entity.getLoanAppId());
		assertEquals(dto.getEmi(), entity.getInstallment());
		assertEquals(dto.getInterest(), entity.getInterestRate());
		assertEquals(dto.getMonth(), entity.getMonthNo());
		assertEquals(dto.getPOutStandingBeginOfMonth(), entity.getPOutStandingBeginOfMon());
		assertEquals(dto.getPOutStandingEndOfMonth(), entity.getPrOutStandingEndOfMon());
		assertEquals(dto.getPrincipalRepayment(), entity.getPRepayment());
		assertEquals(dto.getLastDateOfEmi(), entity.getLastDateofinstallPay());
	}

}
