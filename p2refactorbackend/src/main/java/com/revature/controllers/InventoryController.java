package com.revature.controllers;

import java.util.Arrays;
import java.util.List;


import com.revature.daos.InventoryDao;
import com.revature.models.Inventory;


public class InventoryController {
	
	private InventoryDao iDao;
	
	
	public InventoryController(InventoryDao dao) {
		super();
		this.iDao = dao;
	}
	
	/*
	//now lets declare some methods to handle HTTP requests-----------------
	
	
	//Get inventory by trainer id
	@GetMapping("/{id}")
	public ResponseEntity<List<Inventory>> getOneInventory(@PathVariable("id") int id){
		
		List<Inventory> i = iDao.getOneInventory(id);
		
		if(i == null) { //if getById Fails...
			//send back an empty body with no content status code
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(i);
		}
		
		//if the "if" doesn't run, send back the avenger in the body of the response and an OK status code (200)
		return ResponseEntity.ok(i); //this is a short hand version of whats below
		
	}
	
	
	//update an inventory by removing an item
	@PatchMapping(value="/useItem/{id}") //any HTTP request ending in /addItem will go here
	public void useItem(@PathVariable("id") int id, @RequestBody String item) {
		
		System.out.println("inside the useItem in the inventory controller " + id + item);
		iDao.useItem(id, item); //take the incoming avenger object, and send it to the DAO

		ResponseEntity.status(HttpStatus.NO_CONTENT).body("Item used");
		
	}
	
	
	//update an inventory by adding an item
	@PatchMapping(value = "/addItem/{id}") //any HTTP request ending in /inventory will go here
	public void addItem(@PathVariable("id") int id, @RequestBody String item) {
		
		iDao.addItem(id, item); //take the incoming avenger object, and send it to the DAO

	}
	
	
	//add an inventory to the database
	@PutMapping //any put request ending in /inventory will go here
	public boolean addInventory(@RequestBody Inventory i) {
		
		if(i == null) { //if inventory Fails...
			//return false
			return false;
		}
		
		iDao.addInventory(i); //take the incoming avenger object, and send it to the DAO
		
		return true; 

	}
	*/
	
	

}

