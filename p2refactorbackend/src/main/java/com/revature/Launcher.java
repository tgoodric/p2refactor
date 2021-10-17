package com.revature;

import com.revature.controllers.InventoryController;
import com.revature.controllers.LoginController;
import com.revature.controllers.PokemonController;
import com.revature.controllers.TrainerController;
import com.revature.daos.InventoryDao;
import com.revature.daos.PokemonDao;
import com.revature.daos.TrainerDao;

import io.javalin.Javalin;

public class Launcher {

	public static void main(String[] args) {
		
		//to get access to the DAO methods
		TrainerDao tDao = new TrainerDao();
		PokemonDao pDao = new PokemonDao();
		InventoryDao iDao = new InventoryDao();
		
		
		
		//to get access to the HTTP handlers in the controller layer
		TrainerController tc = new TrainerController();
		PokemonController pc = new PokemonController();
		InventoryController ic = new InventoryController();
		LoginController elc = new LoginController();
		

		
		///////////////////////////////////////
		
		//Set up connection to Postman server using Javalin
		//.create() instantiates a Javalin object, and .start() starts the server (you can use any free port)
		Javalin app = Javalin.create(
				config -> {
					config.enableCorsForAllOrigins(); //allows the server 
				}
				).start(3000);
		
		
		
		//HTTP Handlers below...
		
		
		//Trainer requests 
//		app.post("/login", tc.login);
		app.get("/trainers", tc.getTrainersHandler); 
		app.post("/trainers", tc.addTrainerHandler);
		
		
		//Inventory requests 
		app.get("/inventory/:trainerId", ic.getInventory);
		app.patch("/inventory/:trainerId:/useItem/:item", ic.useItem);
		app.patch("/inventory/:trainerId:/addItem/:item", ic.addItem);
		app.put("/inventory", ic.addInventory);
		
		
		//Pokemon requests
		app.post("/pokemon", pc.insertPokeHandler);
        app.get("/pokemon/:trainerId", pc.getPokemonByTrainerHandler);
        app.get("/pokemon/:trainerId/:level", pc.getPokemonByTrainerWithLevelHandler);

	}
}

