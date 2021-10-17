package com.revature.controllers;

import java.util.Arrays;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;

import com.google.gson.Gson;
import com.revature.daos.InventoryDao;
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
		
//		if(ctx.req.getSession(false) != null){ //if a session exists
			
			//get the trainer id from the path parameter
			int id = Integer.parseInt(ctx.pathParam("trainerId"));
				
			//we create an Array with reimbursements data (using the service to talk to the dao)
			List<Inventory> i = is.getOneInventory(id);
			
			//instantiate a GSON object to make JSON <-> POJO conversions (Plain ol java object)
			Gson gson =new Gson();
			
			String JSONinventory = gson.toJson(i); //convert our java object into a JSON String
			
			ctx.result(JSONinventory); //return our reimbursements
			
			ctx.status(200); //200 = OK (success)
			
			log.info("got a users inventory");
			
		
//		}else {
//			ctx.status(403); //forbidden status code
//		}
	};
		
	
	public Handler useItem = (ctx) -> {
		System.out.println("In the use item handler");
		System.out.println(ctx.pathParam("trainerId"));
		System.out.println(ctx.pathParam("item"));
		
//		if(ctx.req.getSession(false) != null){ //if a session exists
			
			//get the trainer id from the path parameter
			int id = Integer.parseInt(ctx.pathParam("trainerId"));
			String item = ctx.pathParam("item");
			
			//we create an Array with reimbursements data (using the service to talk to the dao)
			boolean i = is.useItem(id, item);
			
			//instantiate a GSON object to make JSON <-> POJO conversions (Plain ol java object)
			Gson gson =new Gson();
			
			String JSONinventory = gson.toJson(i); //convert our java object into a JSON String
			
			ctx.result(JSONinventory); //return our reimbursements
			
			ctx.status(200); //200 = OK (success)
		
//		}else {
//			ctx.status(403); //forbidden status code
//		}
	};
			
			
	public Handler addItem = (ctx) -> {
			System.out.println("In the add item handler");
			
//			if(ctx.req.getSession(false) != null){ //if a session exists
				
				//get the trainer id from the path parameter
				int id = Integer.parseInt(ctx.pathParam("trainerId"));
				String item = ctx.pathParam("item");
				
				//we create an Array with reimbursements data (using the service to talk to the dao)
				boolean i = is.addItem(id, item);
				
				//instantiate a GSON object to make JSON <-> POJO conversions (Plain ol java object)
				Gson gson =new Gson();
				
				String JSONinventory = gson.toJson(i); //convert our java object into a JSON String
				
				ctx.result(JSONinventory); //return our reimbursements
				
				ctx.status(200); //200 = OK (success)
			
//			}else {
//				ctx.status(403); //forbidden status code
//			}
	};
			
				
	public Handler addInventory = (ctx) -> {
			System.out.println("In the get inventory handler");
			
//			if(ctx.req.getSession(false) != null){ //if a session exists
				
				String body = ctx.body();
				
				//instantiate a GSON object to make JSON <-> POJO conversions (Plain ol java object)
				Gson gson =new Gson();
				
				Inventory i = gson.fromJson(body, Inventory.class); //turn that JSON String into a LoginDTO object
				
				//we create an Array with reimbursements data (using the service to talk to the dao)
				boolean newI = is.addInventory(i);
				
				ctx.result(body); //return our reimbursements
				
				ctx.status(200); //200 = OK (success)
			
//			}else {
//				ctx.status(403); //forbidden status code
//			}
		};

}

