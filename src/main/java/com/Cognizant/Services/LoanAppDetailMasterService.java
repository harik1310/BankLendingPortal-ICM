package com.Cognizant.Services;

import java.util.List;

import com.Cognizant.DTO.ReducedPaymentDTO;

public interface LoanAppDetailMasterService {
	//Return Reduced payment method on bases of particular loan_app_id
	List<ReducedPaymentDTO> getAllReducedPaymentByLoanAppId(int loanId);

}
