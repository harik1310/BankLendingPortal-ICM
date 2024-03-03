package com.Cognizant.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Cognizant.Entities.LoanAppDetailMaster;

@Repository
public interface LoanAppDetailMasterRepository extends JpaRepository<LoanAppDetailMaster, String> {

}
