package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import com.revature.daos.InventoryDao;
import com.revature.models.Inventory;
import com.revature.utils.HibernateUtil;

public class InventoryService {
	
	Logger log = LogManager.getLogger(); 
	
	InventoryDao iDao = new InventoryDao();
	
	public List<Inventory> getOneInventory(int trainerId) {
		return iDao.getOneInventory(trainerId);
	}
	
	public boolean useItem(int trainerId, String item) {
	
		boolean result = iDao.useItem(trainerId, item);
		if(result) {
			log.info("Trainer used an item");
		}
		else {
			log.info("Item update failed");
		}
		
		return result;
	}
	
	public boolean addItem(int trainerId, String item) {
		
		boolean result = iDao.addItem(trainerId, item);
		if(result) {
			log.info("Trainer added an item");
		}
		else {
			log.info("Item add failed");
		}
		
		return result;
	}
	
	public boolean addInventory(Inventory inventory) {
		
		boolean result = iDao.addInventory(inventory);
		if(result) {
			log.info("Trainer added an inventory");
		}
		else {
			log.info("Inventory add failed");
		}
		
		return result;
	}
	
	
	
}
