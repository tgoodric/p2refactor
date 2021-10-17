package com.revature.services;

import java.util.List;





import com.revature.daos.TrainerDao;
import com.revature.models.Trainer;


public class TrainerService  {

	private static TrainerDao tDao = TrainerDao.getTrainerDao();

	public void addTrainer(Trainer t) {
                                     
		tDao.addTrainer(t);		
	} 
	
	public Trainer getTrainerByUsername(String username) {
		
		
		return tDao.getTrainers(username).get(0);
	}
	
	public List<Trainer> getTrainers(){
		return tDao.getTrainers();
	}
}
