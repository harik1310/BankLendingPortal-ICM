package com.cognizant.services;

import java.util.List;

import com.cognizant.entities.Users;
import com.cognizant.dto.UserDTO;

public interface UserService {
	
	public List<Users> listOfUsers();
	public UserDTO authenticateUser(String username,String password);

}
