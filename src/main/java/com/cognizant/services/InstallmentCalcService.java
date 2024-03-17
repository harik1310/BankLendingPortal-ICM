package com.cognizant.services;

import java.util.List;

import com.cognizant.dto.LoanCalcDTO;
import com.cognizant.dto.ReducedPaymentDTO;
import com.cognizant.entities.LoanAppMaster;

public interface InstallmentCalcService {
		LoanCalcDTO insallmentCalc(LoanCalcDTO loan);
		
		List<ReducedPaymentDTO> reducedInsallmentCalc(LoanCalcDTO loan);

//		LoanAppMaster loanMasterData(LoanCalcDTO loan);
		
		List<ReducedPaymentDTO> getLoanDetailList(LoanCalcDTO loan);
}
