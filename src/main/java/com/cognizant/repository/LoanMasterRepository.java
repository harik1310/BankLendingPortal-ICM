package com.cognizant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.entities.LoanMaster;
import com.cognizant.utilities.TypeOfLoan;

@Repository
public interface LoanMasterRepository extends JpaRepository<LoanMaster, String> {
	
    Optional<LoanMaster> findById(String loanId);

    Optional<LoanMaster>  findByInterestRate(float interestRate);
    
    @Query("select lm from LoanMaster lm where lm.typeOfLoan=?1 order by lm.dateOfCreation limit 1")
    Optional<LoanMaster> findByTypeOfLoanAndDateOfCreation(TypeOfLoan type);
		
}
