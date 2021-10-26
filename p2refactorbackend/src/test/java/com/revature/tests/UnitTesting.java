package com.revature.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.models.Inventory;
import com.revature.models.Pokemon;
import com.revature.models.Trainer;
import com.revature.services.InventoryService;
import com.revature.services.LoginService;
import com.revature.services.PokemonService;
import com.revature.services.TrainerService;

class UnitTesting {

	public static Pokemon p;
	public static PokemonService ps;
	public static Inventory i;
	public static InventoryService is;
	public static Trainer t;
	public static TrainerService ts;
	public static LoginService ls;
	public int trainerId = 17; 
	public int level = 20;
	public String itemType = "pokeballs";
	public String username = "testhashing";
	public String password = "password";

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		
		p = new Pokemon();
		ps = new PokemonService();
		
		i = new Inventory();
		is = new InventoryService();
		
		t = new Trainer();
		ts = new TrainerService();
		System.out.println("In the @beforeall method");
		
		System.out.println("In the @beforeall method");
		
		
	}


	@Test
	final void testGetPokemon() {
		//System.out.println(trainerId);
		System.out.println("Getting Pokemon with trainerId");
		try {
			
			List<Pokemon> result = ps.findAllPokemonByTrainer(trainerId);
					
			assertNotEquals(null, result);
		}catch(NullPointerException e) {
			System.out.println(e);
			assertNull(e);
			
		}
		
	}



	
	@Test
	final void testInsertPokemon() {
		boolean result = true;
		try {
				result = ps.insertAllPokemon(trainerId, p);
				assertTrue(result);
						
		}catch(Exception e) {
			e.printStackTrace();
			assertFalse(result);
			
		}
	}
	
	/*
	 * Testing for Inventory
	 */
	

	@Test
	final void testGetOneInventory() {
		try {
			List<Inventory> result = is.getOneInventory(trainerId);
				
			assertNotEquals(null, result);
		}catch(Exception e) {
			
		}
	}

	@Test
	final void testUseItem() {
		
		boolean result = true;
		
		try {
			
			result = is.useItem(33, itemType);
					
			
			assertTrue(result);
			
		}catch(Exception e){
			System.out.println("Cannot Use Item Since: " + e);
			assertFalse(result);
		}
		
		
	}

	@Test
	final void testAddItem() {
		
		boolean result = true;
		try {
			result = is.addItem(trainerId, itemType);
					
					
			assertTrue(result);
		}catch(Exception e) {
			System.out.println(e);
			assertFalse(result);
		}
	}
/*
	@Test
	final void testAddInventory() {
		boolean result = true;
		try {
			t.setUserId(trainerId);
			i = new Inventory(1, 2, 2, t);
			result = is.addInventory(i);	
			assertTrue(result);
		}catch(Exception e) {
			
			System.out.println(e);
			assertFalse(result);
			
		}
	}
*/	
	/*
	 * Testing for Trainers
	 */
	@Test
	final void testGetTrainerDao() {
		
		try {
			List<Trainer> result = ts.getTrainers();
					
			assertNotEquals(null, result);
		}catch(Exception e) {
			
			System.out.println(e);
			assertNull(e);
			
		}
	}

	

	@SuppressWarnings("unchecked")
	@Test
	final void testGetForLogin() {
			List<Trainer> result = null;
		try {
			
			result = (List<Trainer>) ts.getTrainerByUsername(username);
//					
			
			assertNotEquals(null, result);
			
			
		}catch(Exception e) {
			
			System.out.println(e);
			assertNull(result);
			
		}
	}

	@Test
	final void testAddTrainer() {
		
		int result = 0;
		
		try {
			
			result = ts.addTrainer(t);
			assertNotEquals(0, result);
			
		}catch(Exception e) {
			
			System.out.println(e);
			assertEquals(0, result);
		}
	}
	
	@Test
	final void testLogin() {
		
		Trainer result = null;
		
		try {
			
			result = ls.login(username, password);
			assertNotEquals(null, result);
			
		}catch(Exception e) {
			
			System.out.println(e);
			assertEquals(null, result);
		}
	}

}
