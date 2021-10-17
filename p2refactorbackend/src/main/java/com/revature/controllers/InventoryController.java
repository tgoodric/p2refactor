package com.revature.controllers;

import java.util.Arrays;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;

import com.google.gson.Gson;
import com.revature.daos.InventoryDao;
import com.revature.models.Inventory;

import io.javalin.http.Handler;


public class InventoryController {
	
	private InventoryDao iDao;
	
public Handler getInventory = (ctx) -> {
		System.out.println("In the get inventory handler");
		
		if(ctx.req.getSession(false) != null){ //if a session exists
			
		//we create an Array with reimbursements data (using the service to talk to the dao)
		List<Inventory> i = iDao.getOneInventory(1);
		
		//instantiate a GSON object to make JSON <-> POJO conversions (Plain ol java object)
		Gson gson =new Gson();
		
		String JSONinventory = gson.toJson(i); //convert our java object into a JSON String
		
		ctx.result(JSONinventory); //return our reimbursements
		
		ctx.status(200); //200 = OK (success)
		
		}else {
			ctx.status(403); //forbidden status code
		}
		
		
	};
	
	
	
	
	
	
	
	
	

}

