package com.revature;

import com.revature.controllers.InventoryController;
import com.revature.controllers.LoginController;
import com.revature.controllers.PokemonController;
import com.revature.controllers.TrainerController;
import io.javalin.Javalin;

public class Launcher {

	public static void main(String[] args) {		
		
		//to get access to the HTTP handlers in the controller layer
		TrainerController tc = new TrainerController();
		PokemonController pc = new PokemonController();
		InventoryController ic = new InventoryController();
		LoginController lc = new LoginController();
		
		///////////////////////////////////////

		Javalin app = Javalin.create(
				config -> {
					config.enableCorsForAllOrigins(); //allows the server 
				}
				).start(8090);
		
		//HTTP Handlers below...
		
		//Trainer requests 
		app.post("/login", lc.loginHandler);
		app.get("/trainers", tc.getTrainersHandler); 
		app.post("/trainers", tc.addTrainerHandler);
		
		
		//Inventory requests 
		app.get("/inventory/:trainerId", ic.getInventory);
		app.patch("/inventory/:trainerId/useItem/:item", ic.useItem);
		app.patch("/inventory/:trainerId/addItem/:item", ic.addItem);
		app.put("/inventory", ic.addInventory);
		
		
		//Pokemon requests
		app.post("/pokemon", pc.insertPokeHandler);
        app.get("/pokemon/:trainerId", pc.getPokemonByTrainerHandler);
        app.get("/pokemon/:trainerId/:level", pc.getPokemonByTrainerWithLevelHandler);
        app.put("/pokemon/update", pc.updatePokemonHandler);
        app.patch("/pokemon/update", pc.updatePokemonHandler);
        

	}
}

