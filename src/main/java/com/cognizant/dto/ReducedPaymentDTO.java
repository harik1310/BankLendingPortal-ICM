package com.cognizant.dto;

import java.time.LocalDate;

import com.cognizant.entities.LoanAppMaster;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReducedPaymentDTO {
	@Size(min = 6,max=6)
	@JsonIgnore
	private LoanAppMaster loanAppId;
	private int month;
	private double emi;
	private double interest;
	private double pOutStandingBeginOfMonth;
	private double principalRepayment;
	private double pOutStandingEndOfMonth;
	private LocalDate lastDateOfEmi;
}
