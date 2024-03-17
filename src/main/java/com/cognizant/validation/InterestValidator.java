package com.cognizant.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.services.LoanMasterService;
import com.cognizant.services.LoanMasterServiceImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class InterestValidator implements ConstraintValidator<InterestValid, Float> {

	private LoanMasterServiceImpl loanMasterService;

	@Autowired
	public InterestValidator(LoanMasterServiceImpl loanMasterService) {
		this.loanMasterService = loanMasterService;
	}

	@Override
	public boolean isValid(Float value, ConstraintValidatorContext context) {
		return loanMasterService.isInterestValid(value);
	}
}
