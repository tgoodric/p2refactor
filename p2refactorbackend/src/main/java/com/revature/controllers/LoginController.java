package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.LoginDto;
import com.revature.services.LoginService;

import io.javalin.http.Handler;

public class LoginController {
	
	Gson gson = new Gson();
	LoginService ls = LoginService.getLoginService();
	/*
	public void login(LoginDto ldto){
		
		if(ls.login(ldto.getUsername(), ldto.getPassword())) {
			System.out.println("in login function, ");
			//stuff happens for auth involving jwt
			
		}
		
	}
	
	public void logout() {
		//logout
	}*/
	public Handler loginHandler = (ctx) ->{
		String body = ctx.body();
		LoginDto ldto = gson.fromJson(body, LoginDto.class);
		if(ls.login(ldto.getUsername(), ldto.getPassword())) {
			//get the JWT and but it in the body
			ctx.status(200);
		}
		else {
			ctx.status(401);
		}
	};
	
	
}
