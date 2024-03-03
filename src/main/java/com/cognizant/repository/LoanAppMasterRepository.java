package com.cognizant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.entities.LoanAppMaster;

@Repository
public interface LoanAppMasterRepository extends JpaRepository<LoanAppMaster, String> {

}
