package com.cognizant.services;

import java.util.List;

import com.cognizant.dto.LoanCalcDTO;
import com.cognizant.dto.ReducedPaymentDTO;

public interface InstallmentCalcService {
		LoanCalcDTO installmentCalc(LoanCalcDTO loan);
		
		List<ReducedPaymentDTO> reducedInsallmentCalc(LoanCalcDTO loan);
		
		List<ReducedPaymentDTO> getLoanDetailList(LoanCalcDTO loan);
}
