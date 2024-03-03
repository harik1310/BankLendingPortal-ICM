package com.cognizant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.entities.LoanMaster;

@Repository
public interface LoanMasterRepository extends JpaRepository<LoanMaster, String> {
    @Query("SELECT lm FROM LoanMaster lm WHERE lm.loanId = ?1")
    Optional<LoanMaster> findById(String loanId);
		
}
