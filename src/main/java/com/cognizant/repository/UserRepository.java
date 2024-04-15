package com.cognizant.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.entities.Users;

@Repository
public interface UserRepository extends CrudRepository<Users,String>{

}
