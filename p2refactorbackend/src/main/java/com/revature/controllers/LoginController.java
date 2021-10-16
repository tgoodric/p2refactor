package com.revature.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.LoginDto;
import com.revature.services.LoginService;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {
	
	
	
	@PostMapping
	public void login(LoginDto ldto){
		LoginService ls = LoginService.getLoginService();
		if(ls.login(ldto.getUsername(), ldto.getPassword())) {
			System.out.println("in login function, ");
			//stuff happens
		}
		
	}
	
	public void logout() {
		//logout
	}
}
