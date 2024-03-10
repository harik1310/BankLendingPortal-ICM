package com.cognizant.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.dto.LoanDTO;
import com.cognizant.entities.LoanMaster;
import com.cognizant.repository.LoanMasterRepository;
import com.cognizant.utilities.Status;
import com.cognizant.utilities.TypeOfLoan;
import com.cognizant.utilities.mapper.LoanDTOMapper;

@Service
public class LoanMasterServiceImpl implements LoanMasterService {


	private LoanMasterRepository loanMasterRepository;

	public LoanMasterServiceImpl(LoanMasterRepository loanMasterRepository) {
		this.loanMasterRepository = loanMasterRepository;
	}

	@Override
	public LoanDTO persistNewLoan(LoanDTO loan) {
		LoanDTO newLoan = null;
		try {
			LoanMaster loanTaken = checkIfInterestIsTaken(loan.getInterestRate()).get();
			newLoan = LoanDTOMapper.toLoanDTO(loanTaken);
			return newLoan;
		} catch (NoSuchElementException ne) {
			ne.getMessage();
			newLoan = getLatestInterestValue(loan.getTypeOfLoan(), loan.getDateOfCreation());
		}
		catch (Exception e) {
			e.getMessage();
		}
		return newLoan;
	}
	
	public LoanDTO getLatestInterestValue(TypeOfLoan type, LocalDate applyingDate) {
		Optional<LoanMaster> latestValue = loanMasterRepository.findByTypeOfLoanAndDateOfCreation(type,applyingDate);
		
		
		
		return null;
	}

	public Optional<LoanMaster> checkIfInterestIsTaken(float interestRate) {
		Optional<LoanMaster> taken = loanMasterRepository.findByInterestRate(interestRate);
		return taken;
	}

	@Override
	public List<LoanDTO> getAllLoans() {
		List<LoanMaster> allLoansfromDb = loanMasterRepository.findAll();
		List<LoanDTO> loanReturnList = new ArrayList<LoanDTO>();
		for (LoanMaster loan : allLoansfromDb) {
			LoanDTO newLoan = LoanDTOMapper.toLoanDTO(loan);
			loanReturnList.add(newLoan);
		}
		return loanReturnList;
	}

	@Override
	public LoanDTO updateLoanDetails(String loanId, LoanDTO toUpdateDetails) {
		Optional<LoanMaster> optionalLoan = loanMasterRepository.findById(loanId);
		LoanMaster loanMaster = null;
		LoanDTO dto;
		if (optionalLoan.isPresent()) {
			LoanMaster updatedLoanDetails = optionalLoan.get();
			if (toUpdateDetails.getTypeOfLoan() != null) {
				updatedLoanDetails.setTypeOfLoan(toUpdateDetails.getTypeOfLoan());
			}
			if (toUpdateDetails.getInterestRate() != 0.0f) {
				Optional<LoanMaster> taken = loanMasterRepository.findByInterestRate(toUpdateDetails.getInterestRate());
				updatedLoanDetails.setInterestRate(toUpdateDetails.getInterestRate());
			}
			if (toUpdateDetails.getDateOfCreation() != null) {

				updatedLoanDetails.setDateOfCreation(toUpdateDetails.getDateOfCreation());
			}
			loanMaster = loanMasterRepository.save(updatedLoanDetails);
		}
		dto = LoanDTOMapper.toLoanDTO(loanMaster);
		return dto;
	}

	@Override
	public LoanDTO getLoanDetailsById(String loanId) {
		Optional<LoanMaster> optionalLoan = loanMasterRepository.findById(loanId);
		LoanDTO newLoan = null;
		if (optionalLoan.isPresent()) {
			LoanMaster loan = optionalLoan.get();
			newLoan = LoanDTOMapper.toLoanDTO(loan);
		}
		return newLoan;
	}

}
