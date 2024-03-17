package com.cognizant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.entities.LoanAppDetailMaster;

@Repository
public interface LoanAppDetailMasterRepository extends JpaRepository<LoanAppDetailMaster, Integer> {
	
//	List<LoanAppDetailMaster> findAllById(String loanAppId);
	
	@Query("select lm from LoanAppDetailMaster lm where lm.loanAppId.loanAppId=?1 order by lm.monthNo")
	List<LoanAppDetailMaster> findAllByLoanAppId(String loanAppId);
}
