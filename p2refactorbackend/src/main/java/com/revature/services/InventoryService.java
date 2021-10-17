package com.revature.services;

import java.util.List;

import org.hibernate.Session;

import com.revature.daos.InventoryDao;
import com.revature.models.Inventory;
import com.revature.utils.HibernateUtil;

public class InventoryService {
	
	//instantiate an InventoryDao to use its methods
	InventoryDao iDao = new InventoryDao();
	
	public List<Inventory> getOneInventory(int trainerId) {
		System.out.println("in the service layer");
		return iDao.getOneInventory(trainerId);
	}
	
	
	public boolean useItem(int trainerId, String item) {
	
		return iDao.useItem(trainerId, item);
	}
	
	public boolean addItem(int trainerId, String item) {
		
		return iDao.addItem(trainerId, item);
	}
	
	public boolean addInventory(Inventory inventory) {
		
		return iDao.addInventory(inventory);
	}
	
	
	
}
