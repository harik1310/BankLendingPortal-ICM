package com.cognizant.validation;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.services.LoanMasterServiceImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class DateValidator implements ConstraintValidator<ValidDate, LocalDate>{
	
	private LoanMasterServiceImpl loanMasterService;

	@Autowired
	public DateValidator(LoanMasterServiceImpl loanMasterService) {
		this.loanMasterService = loanMasterService;
	}

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		return loanMasterService.isDateValid(value);
	}
	
}
