package com.Cognizant.Repository;

import com.Cognizant.Entities.LoanAppMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanAppMasterRepository extends JpaRepository<LoanAppMaster, Integer> {

}
