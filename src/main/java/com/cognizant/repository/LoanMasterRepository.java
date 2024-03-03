package com.cognizant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.entities.LoanMaster;

@Repository
public interface LoanMasterRepository extends JpaRepository<LoanMaster, String> {
    @Query("select * from LoanMaster where loanId=?")
    Optional<LoanMaster> findById(String loanId);
		
}
