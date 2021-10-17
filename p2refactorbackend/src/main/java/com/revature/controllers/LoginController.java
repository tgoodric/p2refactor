package com.revature.controllers;

import com.revature.models.LoginDto;
import com.revature.services.LoginService;


public class LoginController {
	
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
