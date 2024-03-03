package com.cognizant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.entities.LoanAppDetailMaster;

@Repository
public interface LoanAppDetailMasterRepository extends JpaRepository<LoanAppDetailMaster, String> {

}
