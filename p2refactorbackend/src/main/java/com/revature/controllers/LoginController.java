package com.revature.controllers;

import org.apache.logging.log4j.core.tools.Generate;

import com.google.gson.Gson;
import com.revature.models.LoginDto;
import com.revature.models.Trainer;
import com.revature.services.LoginService;
import com.revature.utils.JwtUtil;
import io.javalin.http.Handler;

public class LoginController {

    Gson gson = new Gson();
    LoginService ls = LoginService.getLoginService();
    
    public Handler loginHandler = (ctx) ->{
        String body = ctx.body();
        LoginDto ldto = gson.fromJson(body, LoginDto.class);
        System.out.println(body);
        Trainer result = ls.login(ldto.getUsername(), ldto.getPassword());
        
        if(result != null) {
            //TODO: get the JWT and put it in the header
        	String jwt = JwtUtil.generate(ldto.getUsername(), ldto.getPassword());
        	//
        	//System.out.println("login fetch successful");
        	String resultJson = gson.toJson(result, Trainer.class);
        	
        	ctx.req.getSession();
        	
        	//System.out.println("json serialized");
        	ctx.result(resultJson);
        	ctx.header("JWT", jwt); //stores JWT in the header.
        	//System.out.println(resultJson);
            ctx.status(200);
        }
        else {
            ctx.status(401);
            ctx.result("Username or password not found");
        }
    };
    

}
