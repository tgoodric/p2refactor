
package com.revature.daos;

import java.util.List;

import com.revature.models.Inventory;

public interface IInventoryDao {
	
	//probably the only InventoryDao fetch method we will need
	public List<Inventory> getOneInventory(int trainerId);
	
	//inventory alteration methods
	//use boolean return type for error-checking, return false if something goes wrong
	
	//intent is to start every inventory with some preset items
//	public boolean addInventory(int userId);
	//probably will contain a switch or chained if/else to process item use
	public boolean useItem(int userId, String itemType);
	public boolean addItem(int userId, String itemType);

	boolean addInventory(Inventory inventory);
}
