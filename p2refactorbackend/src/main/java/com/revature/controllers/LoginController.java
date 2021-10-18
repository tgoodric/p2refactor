package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.LoginDto;
import com.revature.models.Trainer;
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
        System.out.println(body);
        Trainer result = ls.login(ldto.getUsername(), ldto.getPassword());
        
        if(result != null) {
            //get the JWT and put it in the header
        	
        	//
        	//System.out.println("login fetch successful");
        	String resultJson = gson.toJson(result, Trainer.class);
        	//System.out.println("json serialized");
        	ctx.result(resultJson);
        	//System.out.println(resultJson);
            ctx.status(200);
        }
        else {
            ctx.status(401);
        }
    };


}
