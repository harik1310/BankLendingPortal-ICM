package com.cognizant.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognizant.entities.LoanMaster;
import com.cognizant.utilities.TypeOfLoan;

@Repository
public interface LoanMasterRepository extends JpaRepository<LoanMaster, String> {
	
	@Override
	@Query("select lm from LoanMaster lm where lm.loanId = ?1 ")
    Optional<LoanMaster> findById(String loanId);

    Optional<LoanMaster>  findByInterestRate(float interestRate);
    
    @Query("select lm from LoanMaster lm where lm.typeOfLoan=?1 order by ?2 limit 1")
    Optional<LoanMaster> findByTypeOfLoanAndDateOfCreation(TypeOfLoan type, LocalDate applyingDate);
		
}
