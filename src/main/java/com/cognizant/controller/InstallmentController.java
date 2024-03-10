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
import com.cognizant.services.InstallmentCalcService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("api/loan")
public class InstallmentController {

	@Autowired
	private InstallmentCalcService service;

	@PostMapping("emiCalc")
	public ResponseEntity<?> emiCalculation(@RequestBody LoanCalcDTO loan) {
		log.info("Calculating EMI ");
		LoanCalcDTO emiDetails = null;
		try {
			emiDetails = service.insallmentCalc(loan);
			return new ResponseEntity<LoanCalcDTO>(emiDetails, HttpStatusCode.valueOf(201));
			
//			return new ResponseEntity<LoanCalcDTO>(emiDetails, HttpStatusCode.valueOf(204));
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<LoanCalcDTO>(emiDetails, HttpStatusCode.valueOf(204));
		}
	}

	@PostMapping("reducedPaymentCalc")
	public ResponseEntity<?> reducedEmiCalculation(@RequestBody LoanCalcDTO loan) {
		log.info("Calculating reduced payment");
		List<ReducedPaymentDTO> emiDetails = null;
		try {
			log.info("inside try");
			emiDetails = service.reducedInsallmentCalc(loan);
			if (emiDetails != null) {
				log.info("inside try's if");
				System.out.println(emiDetails);
				return new ResponseEntity<List<ReducedPaymentDTO>>(emiDetails, HttpStatusCode.valueOf(201));
			}
			log.info("list was null");
			return new ResponseEntity<List<ReducedPaymentDTO>>(emiDetails, HttpStatusCode.valueOf(204));
		} catch (Exception e) {
			// TODO: handle exception
//			System.out.println(;
			e.printStackTrace();
			log.info("exception occured {}",e.getMessage());
			return new ResponseEntity<List<ReducedPaymentDTO>>(emiDetails, HttpStatusCode.valueOf(204));
		}
	}
}