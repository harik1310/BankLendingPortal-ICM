package com.cognizant.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.cognizant.entities.LoanAppMaster;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ReducedPaymentDTO {
	@Size(min = 6,max=6)
	private LoanAppMaster loanAppId;
	private int Month;
	private double EMI;
	private double Interest;
	private double pOutStandingBeginOfMonth;
	private double Principal_Repayment;
	private double pOutStandingEndOfMonth;
	private LocalDate lastDateOfEmi;
}
