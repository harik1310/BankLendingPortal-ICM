package com.Cognizant.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Cognizant.Entities.LoanMaster;

@Repository
public interface LoanMasterRepository extends JpaRepository<LoanMaster, String> {
		
}
