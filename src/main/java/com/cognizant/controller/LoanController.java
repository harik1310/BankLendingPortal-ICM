package com.cognizant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.dto.LoanDTO;
import com.cognizant.services.LoanMasterService;
import com.cognizant.utilities.Status;

@RestController
@RequestMapping("api/loan")
public class LoanController {

	@Autowired
	private LoanMasterService service;

	// returns all the loans in the loan_master table
	@GetMapping("/")
	public ResponseEntity<List<LoanDTO>> getAllLoans() {
		List<LoanDTO> list = null;
		try {
			list = service.getAllLoans();
			if (!list.isEmpty()) {
				return new ResponseEntity<List<LoanDTO>>(list, HttpStatusCode.valueOf(200));
			}
			return new ResponseEntity<List<LoanDTO>>(list, HttpStatusCode.valueOf(204));
		} catch (Exception e) {
			e.getMessage();
			return new ResponseEntity<List<LoanDTO>>(list, HttpStatusCode.valueOf(204));
		}
	}

	// returns by specific loan id from the loan_master table
	@GetMapping("{loan_id}")
	public ResponseEntity<?> getByLoanId(@PathVariable String loanId) {
		LoanDTO dto = null;
		ResponseEntity<LoanDTO> responseEntity = null;
		try {
			dto = service.getLoanDetailsById(loanId);
			if (dto.getLoanId() != null) {
				responseEntity = new ResponseEntity<LoanDTO>(dto, HttpStatusCode.valueOf(200));
			} else {
				responseEntity = new ResponseEntity<LoanDTO>(dto, HttpStatusCode.valueOf(204));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return responseEntity;
	}

	// insert new loan into the loan_master table
	@PostMapping("new")
	public ResponseEntity<?> createNewLoan(@RequestBody LoanDTO newLoan) {
		LoanDTO st = null;
		try {
			st = service.persistNewLoan(newLoan);
			if (st.getLoanId() != null) {
				System.out.println(st);
				return new ResponseEntity<>(HttpStatusCode.valueOf(201));
			}
			return new ResponseEntity<>(HttpStatusCode.valueOf(500));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatusCode.valueOf(500));
		}
	}

	// update an existing loan from loan_master table
	@PutMapping("{loan_id}/update")
	public ResponseEntity<?> updateLoan(@PathVariable String loanId, @RequestBody LoanDTO toUpdate) {
		LoanDTO dto = null;
		ResponseEntity<LoanDTO> responseEntity = null;
		try {
		dto = service.updateLoanDetails(loanId, toUpdate);
		if (dto.getLoanId() != null) {
			responseEntity = new ResponseEntity<LoanDTO>(dto, HttpStatusCode.valueOf(200));
		} 
			responseEntity = new ResponseEntity<LoanDTO>(dto, HttpStatusCode.valueOf(204));
		}catch(Exception e) {
			e.getMessage();
			responseEntity = new ResponseEntity<LoanDTO>(dto, HttpStatusCode.valueOf(204));
		}
		return responseEntity;
	}
}
