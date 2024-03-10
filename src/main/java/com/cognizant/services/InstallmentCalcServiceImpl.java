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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InstallmentCalcServiceImpl implements InstallmentCalcService {

	@Autowired
	private LoanAppDetailMasterRepository installmentRepository;

	@Autowired
	private LoanAppMasterRepository loanApplicationRepository;

	@Override
	public LoanCalcDTO insallmentCalc(LoanCalcDTO loan) {
		double principalAmt = loan.getPAmount();
		int loanTenure = loan.getLoanTenureMonths();

		double interest = (loan.getMonthlyinterestRate() / 12 / 100);

		// where emi = P x R x (1+R)^N / [(1+R)^N-1]
		double emi = principalAmt * interest * Math.pow((1 + interest), loanTenure)
				/ (Math.pow((1 + interest), loanTenure) - 1);
		double totalAmount = emi * loanTenure;
		loan.setEmi(emi);
		loan.setTotalAmountPayable(totalAmount);
		return loan;
	}

	@Override
	public List<ReducedPaymentDTO> reducedInsallmentCalc(LoanCalcDTO loan) {
		log.info("reduced payment called");

		List<LoanAppDetailMaster> loanApplist = installmentRepository.findAllByLoanAppId(loan.getLoanAppId());
		List<ReducedPaymentDTO> loanReport = new ArrayList<ReducedPaymentDTO>();
		if (!loanApplist.isEmpty()) {
			log.info("db has data");
			for (LoanAppDetailMaster lad : loanApplist) {
				ReducedPaymentDTO dto = InstallmentMapper.toDTO(lad);
				loanReport.add(dto);
			}
			return loanReport;
		} else {
			
			log.info("db is empty, calculating emi");
			System.out.println(loan.getLoanAppId());
			
			LoanAppMaster lm = loanApplicationRepository.findById(loan.getLoanAppId()).get();
			System.out.println(lm);
			
			
			double loanAmount = loan.getPAmount();
			System.out.println(loanAmount);
			int loanTermMonths = loan.getLoanTenureMonths(); 
			System.out.println(loanTermMonths);
			double annualInterestRate = loan.getMonthlyinterestRate(); 
			System.out.println(annualInterestRate);
			String loanAppId = loan.getLoanAppId(); 
			System.out.println(1);
			LocalDate startDate = loan.getDueDate(); 
			
			// Calculate monthly interest rate
			double monthlyInterestRate = (annualInterestRate/100) / 12;
			System.out.println(2);
			
			// Calculate EMI using [principal * interest * (1 + interest) ^ tenure]/[(1 +
			// interest)^ tenure - 1]
			double emi = loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanTermMonths)
					/ (Math.pow(1 + monthlyInterestRate, loanTermMonths) - 1);
			System.out.println(3);
			emi = Math.round(emi);
			
			double remainingPrincipal = loanAmount;

			int month = 1;
			
			while (loanTermMonths>0 ) {
				System.out.println("inside while");
				loanTermMonths--;
				// Calculate interest component for the month
				double interestComponent = Math.round( remainingPrincipal * monthlyInterestRate);
			
				// Calculate principal component for the month
				double principalComponent = emi - interestComponent;
			
				// Update remaining principal
				double principalAtBegin = remainingPrincipal;
			
				remainingPrincipal = Math.round( remainingPrincipal - principalComponent);
			
				LocalDate dueDateforCurrentMonth = (startDate.plusMonths(month++).withDayOfMonth(10));
			
				ReducedPaymentDTO temp = new ReducedPaymentDTO();

				// Setting the values for the DTO and adding to the returning list
				System.out.println("setting values");
				temp.setLoanAppId(lm);
				System.out.println("set id");
				temp.setMonth(month);
				
				temp.setPOutStandingBeginOfMonth(principalAtBegin);
				temp.setEMI(emi);
				temp.setInterest(interestComponent);
				temp.setPrincipal_Repayment(principalComponent);
				temp.setPOutStandingEndOfMonth(remainingPrincipal);
				temp.setLastDateOfEmi(dueDateforCurrentMonth);

				loanReport.add(temp);
				System.out.println("temp "+temp);
				System.out.println("setting mapper");
				LoanAppDetailMaster ladm = InstallmentMapper.toEntity(temp);
				loanApplist.add(ladm);
				log.info("{}",ladm);
				
			}
//			return loanReport;
			log.info("loanAppId");
			log.info("loan App id{}",loanApplist.get(0).getLoanAppId());
			List<LoanAppDetailMaster> ladm = installmentRepository.saveAll(loanApplist);		
//			System.out.println("ladm"+ladm);
			return loanReport;
		}
	}
}
