package com.cognizant.services;

import java.lang.ProcessHandle.Info;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.dto.LoanDTO;
import com.cognizant.dto.NewLoanDTO;
import com.cognizant.entities.LoanMaster;
import com.cognizant.repository.LoanMasterRepository;
import com.cognizant.utilities.TypeOfLoan;
import com.cognizant.utilities.mapper.LoanDTOMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoanMasterServiceImpl implements LoanMasterService {

	private LoanMasterRepository loanMasterRepository;

	@Autowired
	public LoanMasterServiceImpl(LoanMasterRepository loanMasterRepository) {
		this.loanMasterRepository = loanMasterRepository;
	}

	@Override
	public LoanDTO persistNewLoan(NewLoanDTO loan) {
		log.info("{}", loan);
		boolean loanTaken = checkIfInterestIsTaken(loan.getInterestRate());
		if (loanTaken) {
			log.info("the interest rate was taken");
			float interest = getLatestInterestValue(loan.getTypeOfLoan());
			loan.setInterestRate(interest + 0.01f);
			log.info("inserting loan at {}", loan.getInterestRate());
		}else {			
			log.info("the interest rate was not taken, {}");
		}
		LoanMaster saved = loanMasterRepository.save(LoanDTOMapper.newLoanToLoanMaster(loan));
		return LoanDTOMapper.toLoanDTO(saved);
	}

	public boolean checkIfInterestIsTaken(float interestRate){
		Optional<LoanMaster> taken = loanMasterRepository.findByInterestRate(interestRate);
		return taken.isPresent();
	}

	public float getLatestInterestValue(TypeOfLoan type) throws NoSuchElementException {
		Optional<LoanMaster> latestValue = loanMasterRepository.findByTypeOfLoanAndDateOfCreation(type);
		if (latestValue.isPresent()) {
			return latestValue.get().getInterestRate();
		}
		return 0.0f;
	}

	@Override
	public List<LoanDTO> getAllLoans() {
		List<LoanDTO> loanReturnList = new ArrayList<>();
		List<LoanMaster> allLoansfromDb = loanMasterRepository.findAll();
		for (LoanMaster loan : allLoansfromDb) {
			LoanDTO newLoan = LoanDTOMapper.toLoanDTO(loan);
			loanReturnList.add(newLoan);
		}
		return loanReturnList;
	}

	@Override
	public LoanDTO updateLoanDetails(String loanId, NewLoanDTO toUpdateDetails) {
		Optional<LoanMaster> optionalLoan = loanMasterRepository.findById(loanId);
		LoanDTO dto = null;
		if (optionalLoan.isPresent()) {
			LoanMaster updatedLoanDetails = optionalLoan.get();

			updatedLoanDetails.setTypeOfLoan(toUpdateDetails.getTypeOfLoan());

			Optional<LoanMaster> taken = loanMasterRepository.findByInterestRate(toUpdateDetails.getInterestRate());
			if (taken.isEmpty()) {
				updatedLoanDetails.setInterestRate(toUpdateDetails.getInterestRate());
			} else {
				float interest = getLatestInterestValue(toUpdateDetails.getTypeOfLoan());
				updatedLoanDetails.setInterestRate(interest+0.1f);
			}
			dto = LoanDTOMapper.toLoanDTO(loanMasterRepository.save(updatedLoanDetails));
		} else {
			log.warn("record does not exist");
		}
		return dto;
	}

	@Override
	public LoanDTO getLoanDetailsById(String loanId) {
		Optional<LoanMaster> optionalLoan = loanMasterRepository.findById(loanId);
		LoanDTO dto = null;
		if (optionalLoan.isPresent()) {
			dto = LoanDTOMapper.toLoanDTO(optionalLoan.get());
		}
		return dto;
	}

	public boolean isDateValid(LocalDate value) {
		LocalDate today = LocalDate.now();
		return ( value.equals(today) || value.isBefore(today));
	}

	public boolean isInterestValid(float value) {
		return (value > 0.0f && value < 50.0);
	}

}
