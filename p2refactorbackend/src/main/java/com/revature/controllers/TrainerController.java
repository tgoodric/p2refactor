package com.revature.controllers;

import java.util.List;

//import org.hibernate.internal.build.AllowSysOut;

import com.google.gson.Gson;
import com.revature.models.Inventory;
import com.revature.models.Pokemon;
import com.revature.models.Trainer;
import com.revature.services.InventoryService;
import com.revature.services.PokemonService;
import com.revature.services.TrainerService;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import io.javalin.http.Handler;


public class TrainerController {

	TrainerService ts = new TrainerService();
	Gson gson = new Gson();
	private Argon2 ar2 = Argon2Factory.create();
	
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
		//try {
		Trainer t = gson.fromJson(tBody, Trainer.class);
		System.out.println(t);
		String hashedPassword = ar2.hash(4, 1024*1024, 8, t.getPassword());
		t.setPassword(hashedPassword);
		int trainerId = ts.addTrainer(t);
		if(trainerId == -1) {
			ctx.status(400);
			return; //exit if error occurs without creating inventory/starter pokemon
		}
		//System.out.println("Trainer Id : " + trainerId);
		PokemonService ps = new PokemonService();
		InventoryService is = new InventoryService();
		ps.insertAllPokemon(trainerId, new Pokemon("nabin",1,1,45,45,49,65,49,65,0,
									ts.getTrainerByUsername(t.getUsername())));
		is.addInventory(new Inventory(5,5,5,ts.getTrainerByUsername(t.getUsername())));
		//TODO: create jwt and add to header
		
		ctx.status(201);
	};
	
	
}