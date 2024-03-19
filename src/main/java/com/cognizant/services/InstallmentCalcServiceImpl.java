package com.cognizant.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.dto.LoanCalcDTO;
import com.cognizant.dto.ReducedPaymentDTO;
import com.cognizant.entities.LoanAppDetailMaster;
import com.cognizant.entities.LoanAppMaster;
import com.cognizant.repository.LoanAppDetailMasterRepository;
import com.cognizant.repository.LoanAppMasterRepository;
import com.cognizant.utilities.mapper.InstallmentMapper;

@Service
public class InstallmentCalcServiceImpl implements InstallmentCalcService {

	private LoanAppDetailMasterRepository installmentRepository;

	private LoanAppMasterRepository loanApplicationRepository;

	@Autowired
	public InstallmentCalcServiceImpl(LoanAppDetailMasterRepository installmentRepository,
			LoanAppMasterRepository loanApplicationRepository) {
		this.installmentRepository = installmentRepository;
		this.loanApplicationRepository = loanApplicationRepository;
	}

	@Override
	public LoanCalcDTO installmentCalc(LoanCalcDTO loan) {
		double principalAmt = loan.getPAmount();
		int loanTenure = loan.getLoanTenureMonths();

		double interest = (loan.getMonthlyinterestRate() / 12 / 100);

		// where emi = P x R x (1+R)^N / [(1+R)^N-1]
		double emi = principalAmt * interest * Math.pow((1 + interest), loanTenure)
				/ (Math.pow((1 + interest), loanTenure) - 1);
		double totalAmount = emi * loanTenure;
		loan.setEmi(Math.round(emi));
		loan.setTotalAmountPayable(totalAmount);
		return loan;
	}

	@Override
	public List<ReducedPaymentDTO> reducedInsallmentCalc(LoanCalcDTO loan) {
		LoanAppMaster lm = new LoanAppMaster();
		lm.setLoanAppId(loan.getLoanAppId());
		
		List<LoanAppDetailMaster> loanApplist = installmentRepository.findAllByLoanAppId(loan.getLoanAppId());
		
		List<ReducedPaymentDTO> loanReport = new ArrayList<>();
		if (!loanApplist.isEmpty()) {

			for (LoanAppDetailMaster lad : loanApplist) {
				ReducedPaymentDTO dto = InstallmentMapper.toDTO(lad);
				loanReport.add(dto);
			}
			return loanReport;
		} else {

			

			double loanAmount = loan.getPAmount();

			int loanTermMonths = loan.getLoanTenureMonths();

			double annualInterestRate = loan.getMonthlyinterestRate();

			LocalDate startDate = loan.getDueDate();

			// Calculate monthly interest rate
			double monthlyInterestRate = (annualInterestRate / 100) / 12;

			// Calculate EMI using [principal * interest * (1 + interest) ^ tenure] / [ (1 +
			// interest) ^ tenure - 1 ]
			double emi = loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanTermMonths)
					/ (Math.pow(1 + monthlyInterestRate, loanTermMonths) - 1);
			emi = Math.round(emi);

			double remainingPrincipal = loanAmount;

			int month = 0;

			while (loanTermMonths > 0) {
				loanTermMonths--;
				// Calculate interest component for the month
				double interestComponent = Math.round(remainingPrincipal * monthlyInterestRate);

				// Calculate principal component for the month
				double principalComponent = emi - interestComponent;

				// Update remaining principal
				double principalAtBegin = remainingPrincipal;

				remainingPrincipal = Math.round(remainingPrincipal - principalComponent);

				LocalDate dueDateforCurrentMonth = (startDate.plusMonths(++month).withDayOfMonth(10));

				ReducedPaymentDTO temp = new ReducedPaymentDTO();

				// Setting the values for the DTO and adding to the returning list
				temp.setLoanAppId(lm);
				temp.setMonth(month);

				temp.setPOutStandingBeginOfMonth(principalAtBegin);
				temp.setEmi(emi);
				temp.setInterest(interestComponent);
				temp.setPrincipalRepayment(principalComponent);
				temp.setPOutStandingEndOfMonth(remainingPrincipal);
				temp.setLastDateOfEmi(dueDateforCurrentMonth);

				loanReport.add(temp);
				LoanAppDetailMaster ladm = InstallmentMapper.toEntity(temp);
				loanApplist.add(ladm);
			}
			installmentRepository.saveAll(loanApplist);
		
			return loanReport;
		}
	}

	public List<ReducedPaymentDTO> getLoanDetailList(LoanCalcDTO loan) {
		List<ReducedPaymentDTO> list = new ArrayList<>();

			if (loan.getMonthlyinterestRate() == 0) {

				double interest = loanApplicationRepository.findInterestRate(loan.getLoanAppId());
				loan.setMonthlyinterestRate(interest);
			}

			Optional<LoanAppMaster> value = loanApplicationRepository.findById(loan.getLoanAppId());
			
			LoanAppMaster lm=new LoanAppMaster();
			if (value.isPresent()) {
				
				lm = value.get();
			}

			List<LoanAppDetailMaster> details = lm.getLoanAppDetails();
			for (LoanAppDetailMaster ladm : details) {
				ReducedPaymentDTO dto = InstallmentMapper.toDTO(ladm);
				list.add(dto);
			}
			if (list.isEmpty()) {
				
				return reducedInsallmentCalc(loan);
			}
		return list;
	}

}
