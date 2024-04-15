package com.cognizant.controller;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.dto.LoanCalcDTO;
import com.cognizant.dto.ReducedPaymentDTO;
import com.cognizant.services.InstallmentCalcService;

@CrossOrigin("*")
@RestController
@RequestMapping("api/loan")
public class InstallmentController {

	private InstallmentCalcService service;

	public InstallmentController(InstallmentCalcService service) {
		super();
		this.service = service;
	}


	@PostMapping("emiCalc")
	public ResponseEntity<LoanCalcDTO> emiCalculation(@RequestBody LoanCalcDTO loan) {
		System.out.println(loan);
		LoanCalcDTO emiDetails = null;
			emiDetails = service.installmentCalc(loan);
		if(emiDetails!=null) {	
			return new ResponseEntity<>(emiDetails, HttpStatusCode.valueOf(201));
		}else {
			return new ResponseEntity<>(emiDetails, HttpStatusCode.valueOf(204));
		}
	}
	
		
	//this works perfectly
	@PostMapping("reducedPaymentCalc")
	public ResponseEntity<List<ReducedPaymentDTO>> getLoanDetailList(@RequestBody LoanCalcDTO loan){
		List<ReducedPaymentDTO> reducedEmi = service.getLoanDetailList(loan);
		if(reducedEmi.isEmpty()) {	
			return new ResponseEntity<>(reducedEmi, HttpStatusCode.valueOf(204));
		}else {
			return new ResponseEntity<>(reducedEmi, HttpStatusCode.valueOf(200));
		}
	}
}