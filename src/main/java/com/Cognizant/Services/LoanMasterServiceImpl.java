package com.Cognizant.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.Cognizant.DTO.LoanDTO;
import com.Cognizant.Entities.LoanMaster;
import com.Cognizant.Mapper.LoanDTOMapper;
import com.Cognizant.Repository.LoanMasterRepository;

public class LoanMasterServiceImpl implements LoanMasterService {
	
	@Autowired
	private LoanMasterRepository loanMasterRepository;

	@Override
	public String persistNewLoan(LoanDTO loan) {
		LoanMaster newLoan = LoanDTOMapper.mapToLoanMaster(loan);
		LoanMaster verify = loanMasterRepository.save(newLoan);
		if(verify != null) {
			return "Success";
		}
		return "Fail";
	}

	@Override
	public List<LoanDTO> getAllLoans() {
		List<LoanMaster> allLoansfromDb = loanMasterRepository.findAll();
		List<LoanDTO> loanReturnList = new ArrayList<LoanDTO>();
		for(LoanMaster loan : allLoansfromDb) {
			LoanDTO newLoan= LoanDTOMapper.mapToLoanDTO(loan);
			loanReturnList.add(newLoan);
		}
		return loanReturnList;
	}

	@Override
	public String updateLoanDetails(int loanId,LoanDTO toUpdateDetails) {
		Optional<LoanMaster> optionalLoan =loanMasterRepository.findById(loanId);
		LoanMaster loanMaster=null;
		if(optionalLoan.isPresent()) {
			LoanMaster updatedLoanDetails = optionalLoan.get();
			if(toUpdateDetails.getTypeOfLoan()!=null) {
			updatedLoanDetails.setTypeOfLoan(toUpdateDetails.getTypeOfLoan());
			}
			if(toUpdateDetails.getInterestRate() != 0.0f) {
			updatedLoanDetails.setInterestRate(toUpdateDetails.getInterestRate());
			}
			if(toUpdateDetails.getDateOfCreation()!=null) {
			updatedLoanDetails.setDateOfCreation(toUpdateDetails.getDateOfCreation());
			}
			loanMaster=loanMasterRepository.save(updatedLoanDetails);
		}
		if(loanMaster!=null)
			return "success";
		else
		return "fail";
		}

	@Override
	public LoanDTO getLoanDetailsById(int loanId) {
		Optional<LoanMaster> optionalLoan =loanMasterRepository.findById(loanId);
		LoanDTO newLoan=null;
		if(optionalLoan.isPresent()) {
			LoanMaster loan = optionalLoan.get();
		newLoan= LoanDTOMapper.mapToLoanDTO(loan);
		}
		return newLoan;
	}

}
