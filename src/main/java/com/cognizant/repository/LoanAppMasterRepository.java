package com.cognizant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.entities.LoanAppMaster;

@Repository
public interface LoanAppMasterRepository extends JpaRepository<LoanAppMaster, String> {
	
	@Query("select lm from LoanAppMaster lm")
	List<LoanAppMaster> findByLoanAppDetails(String loanAppId);
	
	@Query("select lm.interestRate from LoanAppMaster lm where lm.loanAppId = ?1")
	double findInterestRate(String loanAppId);

}
