package com.cognizant.dto;

import java.time.LocalDate;

import com.cognizant.utilities.TypeOfLoan;
import com.cognizant.validation.InterestValid;
import com.cognizant.validation.ValidDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewLoanDTO {
	
	@NotBlank
	private String loanId;
	
	@NotNull
	private TypeOfLoan typeOfLoan;
	
	@InterestValid(message = "Interest should be greater that 0.0 and less than 50.0")
	private float interestRate;
	
	@ValidDate(message = "date should should be today's date" )
	private LocalDate dateOfCreation;
}
