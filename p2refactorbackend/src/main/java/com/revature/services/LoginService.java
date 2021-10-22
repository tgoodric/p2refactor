package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.TrainerDao;
import com.revature.models.Trainer;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;


public class LoginService {

	Logger log = LogManager.getLogger(); 			//Logger object so that we can implement Logging
	private Argon2 ar2 = Argon2Factory.create(); 	//Argon2 object for checking password hashes

	private static LoginService ls = null;

	private LoginService() {
		//no implementation
	}

	public Trainer login(String username, String password) {
		TrainerDao tDao = TrainerDao.getTrainerDao();
		List<Trainer> result = tDao.getTrainers(username);
		if(result != null && result.size() == 1) { 				//guard against null pointer exceptions
			String hashedPassword = result.get(0).getPassword();
			if (ar2.verify(hashedPassword, password)) {			//compare hashes
				log.info("User " + username + " logged in successfully");
				return result.get(0);
			}
			else {         	
				log.warn("Unsuccessful login");
				return null;
			}
		}  
		return null;
	}
	
	public static LoginService getLoginService() {
		if(ls == null) {
			ls = new LoginService();
		}

		return ls;
	}

}