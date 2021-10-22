package com.revature.controllers;

import java.util.List;

import com.google.gson.Gson;
import com.revature.models.Inventory;
import com.revature.services.InventoryService;

import io.javalin.http.Handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class InventoryController {
	
	Logger log = LogManager.getLogger(); //Logger object so that we can implement Logging
	
	private InventoryService is = new InventoryService();
	
	
	public Handler getInventory = (ctx) -> {
		System.out.println("In the get inventory handler in controller layer");
			
			//get the trainer id from the path parameter
			int id = Integer.parseInt(ctx.pathParam("trainerId"));
			try {
				List<Inventory> i = is.getOneInventory(id);
				
				//instantiate a GSON object to make JSON <-> POJO conversions (Plain ol java object)
				Gson gson =new Gson();
				
				String JSONinventory = gson.toJson(i); //convert our java object into a JSON String
				
				ctx.result(JSONinventory); //return our inventory
				
				ctx.status(200); 
				
				log.info("got a users inventory");
			} catch (Exception e) {
				ctx.status(403); //forbidden status code
				e.printStackTrace();
				log.info("Cannot get a user inventory due to error "+ e);
			}
			
		
	};
		
	
	public Handler useItem = (ctx) -> {

			int id = Integer.parseInt(ctx.pathParam("trainerId"));
			String item = ctx.pathParam("item");

			try {
				boolean i = is.useItem(id, item);
				
				//instantiate a GSON object to make JSON <-> POJO conversions (Plain ol java object)
				Gson gson =new Gson();
				
				String JSONinventory = gson.toJson(i); //convert our java object into a JSON String
				
				ctx.result(JSONinventory); //return our inventory
				
				ctx.status(200); //200 = OK (success)
			}catch(Exception e) {
				
				ctx.status(403); //forbidden status code
			}

	};
			
			
	public Handler addItem = (ctx) -> {
		
				int id = Integer.parseInt(ctx.pathParam("trainerId"));
				String item = ctx.pathParam("item");
				
				try {

					boolean i = is.addItem(id, item);

					Gson gson =new Gson();
					
					String JSONinventory = gson.toJson(i); //convert our java object into a JSON String
					
					ctx.result(JSONinventory); 
					
					ctx.status(200); //200 = OK (success)
				} catch (Exception e) {
					e.printStackTrace();
					ctx.status(403); //forbidden status code
				}
			
	};
			
				
	public Handler addInventory = (ctx) -> {
				String body = ctx.body();
				
				//instantiate a GSON object to make JSON <-> POJO conversions (Plain ol java object)
				Gson gson =new Gson();
				
				try {
					Inventory i = gson.fromJson(body, Inventory.class); 
					
					is.addInventory(i);
					
					ctx.result(body); //return our inventory
					
					ctx.status(200); //200 = OK (success)
				} catch (Exception e) {
					e.printStackTrace();
					ctx.status(403); //forbidden status code
				}
			
		};

}

