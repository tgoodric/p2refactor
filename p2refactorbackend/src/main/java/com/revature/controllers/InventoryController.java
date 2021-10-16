package com.revature.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.daos.InventoryDao;
import com.revature.models.Inventory;

@RestController //this combines @Controller and @ResponseBody, so we don't have to write both!
@RequestMapping(value="/inventory") //all request ending in /avenger will to this controller
@CrossOrigin //this will act as a CORS filter, allowing requests from any origin
public class InventoryController {
	
	private InventoryDao iDao;
	
	@Autowired //we want a constructor with only the IventoryDAO so we can use constructor injection and use its methods
	public InventoryController(InventoryDao dao) {
		super();
		this.iDao = dao;
	}
	
	
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
	
	
	

}

