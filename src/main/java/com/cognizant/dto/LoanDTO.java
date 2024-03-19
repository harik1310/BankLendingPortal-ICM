package com.cognizant.dto;

import java.time.LocalDate;

import com.cognizant.utilities.TypeOfLoan;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {
	
	@NotBlank
	private String loanId;
	
	@NotNull
	@Pattern(regexp = "HOME_LOANS|BUSINESS_LOANS|PERSONAL_LOANS|AUTO_LOANS", message = "typeOfLoan should be valid")
	private TypeOfLoan typeOfLoan;
	
	private float interestRate;
	
//	@ValidDate
	private LocalDate dateOfCreation;
}
