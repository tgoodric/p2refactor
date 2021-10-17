package com.revature.controllers;

import java.util.List;

import org.hibernate.internal.build.AllowSysOut;

import com.google.gson.Gson;
import com.revature.models.Trainer;
import com.revature.services.TrainerService;

import io.javalin.http.Handler;


public class TrainerController {

	TrainerService ts = new TrainerService();
	Gson gson = new Gson();
	
	public Handler getTrainersHandler = (ctx) ->{
		System.out.println("in handler");
		List<Trainer> result = ts.getTrainers();
		String resultJson = gson.toJson(result);
		if (result == null) {
			System.out.println("syntax error");
			ctx.status(400);
			return;
		}
		else if (result.isEmpty()){
			System.out.println("empty");
			ctx.status(204);
			return;
		}
		else {
			
			ctx.result(resultJson);
			ctx.status(200);
		}
	};
	
	
	public Handler addTrainerHandler = (ctx) -> {
		System.out.println("in handler");
		String tBody = ctx.body();
		Trainer t = gson.fromJson(tBody, Trainer.class);
		System.out.println(t);
		ts.addTrainer(t);	
	};
	
	
}
