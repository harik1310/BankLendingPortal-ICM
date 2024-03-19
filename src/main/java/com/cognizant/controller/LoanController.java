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
import jakarta.validation.Valid;

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
	@GetMapping("/")
	public ResponseEntity<List<LoanDTO>> getAllLoans() {
		List<LoanDTO> list = null;

		list = service.getAllLoans();
		if (!list.isEmpty()) {
			return new ResponseEntity<>(list, HttpStatusCode.valueOf(200));
		}
		return new ResponseEntity<>(list, HttpStatusCode.valueOf(204));
	}

	// returns by specific loan id from the loan_master table
	@GetMapping("{loanId}")
	public ResponseEntity<LoanDTO> getByLoanId(@PathVariable String loanId) {
		LoanDTO dto = null;
		dto = service.getLoanDetailsById(loanId);
		if (dto == null) {
			return new ResponseEntity<>(dto, HttpStatusCode.valueOf(204));
		}
		return new ResponseEntity<>(dto, HttpStatusCode.valueOf(200));
	}

	// insert new loan into the loan_master table
	@PostMapping("new")
	public ResponseEntity<LoanDTO> createNewLoan(@Valid @RequestBody NewLoanDTO newLoan) {
		LoanDTO st = null;
			st = service.persistNewLoan(newLoan);
			if (st != null) {
				return new ResponseEntity<>(HttpStatusCode.valueOf(201));
			}
			return new ResponseEntity<>(HttpStatusCode.valueOf(500));
	}

	// update an existing loan from loan_master table
	@PutMapping("{loanId}/update")
	public ResponseEntity<LoanDTO> updateLoan(@Valid @PathVariable String loanId, @RequestBody NewLoanDTO toUpdate) {
		LoanDTO dto = null;
			dto = service.updateLoanDetails(loanId, toUpdate);
			if (dto!=null) {
				return new ResponseEntity<>(dto, HttpStatusCode.valueOf(200));
			}
			return new ResponseEntity<>(dto, HttpStatusCode.valueOf(204));
	}
}
