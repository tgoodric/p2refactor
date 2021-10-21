package com.revature.controllers;

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
        Trainer result = ls.login(ldto.getUsername(), ldto.getPassword());
        
        if(result != null) {
        	String jwt = JwtUtil.generate(ldto.getUsername(), ldto.getPassword());
        	String resultJson = gson.toJson(result, Trainer.class);
        	ctx.req.getSession();
        	ctx.result(resultJson);
        	ctx.header("JWT", jwt); 
        	ctx.cookie("userId", Integer.toString(result.getUserId()));  
            ctx.status(200);											
            
        }
        else {
            ctx.status(401);
            ctx.result("Username or password not found");
        }
    };
    

}
