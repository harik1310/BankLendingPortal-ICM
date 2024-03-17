package com.cognizant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.dto.LoanDTO;
import com.cognizant.dto.NewLoanDTO;
import com.cognizant.services.LoanMasterService;
import com.cognizant.utilities.Status;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequestMapping("api/loan")
public class LoanController {

	
	private LoanMasterService service;
	
	@Autowired
	public LoanController(LoanMasterService service) {
		super();
		this.service = service;
	}

	// returns all the loans in the loan_master table
	@GetMapping("loan")
	public ResponseEntity<?> getAllLoans() {
		List<LoanDTO> list = null;
		try {
			list = service.getAllLoans();
			if (!list.isEmpty()) {
				return new ResponseEntity<>(list, HttpStatusCode.valueOf(200));
			}
			return new ResponseEntity<>(list, HttpStatusCode.valueOf(204));
		} catch (Exception e) {
			e.getMessage();
			return new ResponseEntity<>("could not retrieve the data", HttpStatusCode.valueOf(204));
		}
	}

	// returns by specific loan id from the loan_master table
	@GetMapping("{loanId}")
	public ResponseEntity<?> getByLoanId(@PathVariable String loanId) {
		LoanDTO dto = null;
		ResponseEntity<LoanDTO> responseEntity = null;
		try {
			dto = service.getLoanDetailsById(loanId);
			log.info("dto values {}",dto);
			if (dto == null) {
				return new ResponseEntity<>(dto, HttpStatusCode.valueOf(204));
			}
			responseEntity = new ResponseEntity<>(dto, HttpStatusCode.valueOf(201));
		} catch (Exception e) {
			return new ResponseEntity<>(dto, HttpStatusCode.valueOf(204));
		}
		return responseEntity;
	}

	
	// insert new loan into the loan_master table
	@PostMapping("new")
	public ResponseEntity<?> createNewLoan(@Valid @RequestBody NewLoanDTO newLoan) {
		LoanDTO st = null;
		try {
			st = service.persistNewLoan(newLoan);
			if (st.getLoanId() != null) {
				return new ResponseEntity<>(HttpStatusCode.valueOf(201));
			}
			return new ResponseEntity<>(HttpStatusCode.valueOf(500));
		} catch (Exception e) {
			return new ResponseEntity<>("Could not create a new loan, check the data",HttpStatusCode.valueOf(500));
		}
	}

	// update an existing loan from loan_master table
@PutMapping("{loanId}/update")
	public ResponseEntity<?> updateLoan(@Valid @PathVariable String loanId, @RequestBody NewLoanDTO toUpdate) {
		LoanDTO dto = null;
		try {
		dto = service.updateLoanDetails(loanId, toUpdate);
		if (dto.getLoanId() != null) {
			return new ResponseEntity<>(dto, HttpStatusCode.valueOf(200));
		} 
			return new ResponseEntity<>(dto, HttpStatusCode.valueOf(204));
		}catch(Exception e) {
			e.getMessage();
			return new ResponseEntity<>("Could not update the loan Details, check the data", HttpStatusCode.valueOf(400));
		}
	}
}
