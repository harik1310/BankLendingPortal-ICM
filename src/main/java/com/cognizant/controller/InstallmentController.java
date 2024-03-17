package com.cognizant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.dto.LoanCalcDTO;
import com.cognizant.dto.ReducedPaymentDTO;
import com.cognizant.entities.LoanAppDetailMaster;
import com.cognizant.entities.LoanAppMaster;
import com.cognizant.services.InstallmentCalcService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("api/loan")
public class InstallmentController {

	private InstallmentCalcService service;

	public InstallmentController(InstallmentCalcService service) {
		super();
		this.service = service;
	}


	@PostMapping("emiCalc")
	public ResponseEntity<LoanCalcDTO> emiCalculation(@RequestBody LoanCalcDTO loan) {
		log.info("Calculating EMI ");
		LoanCalcDTO emiDetails = null;
		try {
			emiDetails = service.insallmentCalc(loan);
			return new ResponseEntity<>(emiDetails, HttpStatusCode.valueOf(201));
		} catch (Exception e) {
			log.info(e.getMessage());
			return new ResponseEntity<>(emiDetails, HttpStatusCode.valueOf(204));
		}
	}
	
		
	//this works perfectly
	@PostMapping("reducedPaymentCalc")
	public ResponseEntity<?> getLoanDetailList(@RequestBody LoanCalcDTO loan){
		List<ReducedPaymentDTO> reducedEmi = service.getLoanDetailList(loan);
		return new ResponseEntity<>(reducedEmi,HttpStatusCode.valueOf(200));
	}
	
//	@PostMapping("getEmi")
//	public ResponseEntity<?> getLoanData(@RequestBody LoanCalcDTO loan) {
//		LoanAppMaster emiDetails = null;
//		try {
//			emiDetails= service.loanMasterData(loan);
//			return new ResponseEntity<>(emiDetails, HttpStatusCode.valueOf(201));
//		} catch (Exception e) {
//			return new ResponseEntity<>(emiDetails, HttpStatusCode.valueOf(204));
//		}
//	}
	

////	@PostMapping("reducedPaymentCalc")
//	public ResponseEntity<?> reducedEmiCalculation(@RequestBody LoanCalcDTO loan) {
//		log.info("Calculating reduced payment");
//		List<ReducedPaymentDTO> emiDetails = null;
//		try {
//			emiDetails = service.reducedInsallmentCalc(loan);
//			if (emiDetails != null) {
//				return new ResponseEntity<>(emiDetails, HttpStatusCode.valueOf(201));
//			}
//			log.info("list was null");
//			return new ResponseEntity<>(emiDetails, HttpStatusCode.valueOf(204));
//		} catch (Exception e) {
//			log.info("exception occured {}",e.getMessage());
//			return new ResponseEntity<>(emiDetails, HttpStatusCode.valueOf(204));
//		}
//	}
}