package com.cognizant.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.dto.LoanCalcDTO;
import com.cognizant.dto.ReducedPaymentDTO;

public class InstallmentCalcServiceImpl implements InstallmentCalcService {

	@Override
	public LoanCalcDTO insallmentCalc(LoanCalcDTO loan) {
		int principalAmt = loan.getpAmount();
		int loanTenure = loan.getLoanTenureMonths();
		
		double interest = ( loan.getMonthlyinterestRate() / 12 / 100 );
		
		// where emi = P x R x (1+R)^N / [(1+R)^N-1]
		double emi = principalAmt * interest * Math.pow((1+ interest), loanTenure) / (Math.pow((1+ interest), loanTenure)-1);
		double totalAmount = emi * loanTenure ;
		loan.setEmi(emi);
		loan.setTotalAmountPayable(totalAmount);
		return loan;
	}		

	@Override
	public List<ReducedPaymentDTO>reducedInsallmentCalc(LoanCalcDTO loan) {
		int loanAmount = loan.getpAmount(); 				        // Loan amount (Principal)
        int loanTermMonths = loan.getLoanTenureMonths();	 	    // Loan term in months
        double annualInterestRate = loan.getMonthlyinterestRate();  // Annual interest rate
        String loanAppId= loan.getLoanAppId();  					// Loan application id
        LocalDate dueDate = loan.getDueDate();  					// due date for the loan
 
        // Calculate monthly interest rate
        double monthlyInterestRate = annualInterestRate / 12;
  
        // Calculate EMI using [principal * interest * (1 + interest) ^ tenure]/[(1 + interest)^ tenure - 1]
        double emi = loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanTermMonths)
                				/ ( Math.pow ( 1 + monthlyInterestRate, loanTermMonths ) - 1 );
        
        List<ReducedPaymentDTO> loanReport = new ArrayList<ReducedPaymentDTO>();
        double remainingPrincipal = loanAmount;
        while(remainingPrincipal>=0 && loanTermMonths>0){
        	loanTermMonths--;
        	int month=1;
        	
        	// Calculate interest component for the month
            double interestComponent = remainingPrincipal * monthlyInterestRate;
 
            // Calculate principal component for the month
            double principalComponent = emi - interestComponent;
 
            // Update remaining principal
            double principalAtBegin = remainingPrincipal;
            remainingPrincipal = remainingPrincipal - principalComponent;
            
            ReducedPaymentDTO temp = new ReducedPaymentDTO();

            //Setting the values for the DTO and adding to the returning list
            temp.setLoan_App_ID(loanAppId);
            temp.setMonth(month++);
            temp.setpOutStandingBeginOfMonth(principalAtBegin);
            temp.setEMI(emi);
            temp.setInterest(interestComponent);
            temp.setPrincipal_Repayment(principalComponent);
            temp.setpOutStandingEndOfMonth(remainingPrincipal);
            temp.setLastDateOfEmi(null);
            loanReport.add(temp);
        }
		return loanReport;
	}
}
