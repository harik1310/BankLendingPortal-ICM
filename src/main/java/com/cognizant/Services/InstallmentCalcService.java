package com.Cognizant.Services;

import java.util.List;

import com.Cognizant.DTO.LoanCalcDTO;
import com.Cognizant.DTO.ReducedPaymentDTO;

public interface InstallmentCalcService {
		LoanCalcDTO insallmentCalc(LoanCalcDTO loan);
		
		List<ReducedPaymentDTO> reducedInsallmentCalc(LoanCalcDTO loan);
}
