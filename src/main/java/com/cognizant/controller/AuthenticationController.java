package com.cognizant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.dto.UserDTO;
import com.cognizant.dto.UserRequest;
import com.cognizant.services.UserService;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
	private UserService userService;
	
	@Autowired
	public AuthenticationController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("users")
	public ResponseEntity<UserDTO> authenticate(@RequestBody UserRequest userRequest){
		UserDTO userDTO=userService.authenticateUser(userRequest.getUserName(), userRequest.getPassword());
		if(userDTO.getUserName()!=null) {
			return new ResponseEntity<>(userDTO,HttpStatusCode.valueOf(202));
		}else {
			return new ResponseEntity<>(HttpStatusCode.valueOf(403));
		}
		
	}

}
