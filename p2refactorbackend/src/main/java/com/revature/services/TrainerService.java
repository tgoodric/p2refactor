package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.exception.ConstraintViolationException;

import com.revature.daos.TrainerDao;
import com.revature.models.Trainer;

public class TrainerService {

	Logger log = LogManager.getLogger(); //Logger object so that we can implement Logging

	//instantiate DAO
	private static TrainerDao tDao = TrainerDao.getTrainerDao();

	public int addTrainer(Trainer t) {
                                      
		try {
			tDao.addTrainer(t);
			Trainer result = tDao.getTrainers(t.getUsername()).get(0);
			log.info("Created a new user");
			return result.getUserId(); //if successful, return ID to controller layer
		}
		catch (ConstraintViolationException e) {
			log.warn("User entered duplicate username");
			return -1; //error code
		}
		catch (IllegalArgumentException e) {
			log.warn("User attempted to use null or empty username or password");
			return -1;
		}
	} 

	public Trainer getTrainerByUsername(String username) {
		return tDao.getTrainers(username).get(0);
	}

	public List<Trainer> getTrainers(){
		return tDao.getTrainers();
	}

	public List<Trainer> getTrainers(int id){
		return tDao.getTrainers();
	}
}
