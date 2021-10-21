package com.revature.daos;

import java.util.List;

import com.revature.models.Trainer;

public interface ITrainerDao {
	
	public List<Trainer> getTrainers(); 				//for testing purposes
	public List<Trainer> getTrainers(String username);	//used for login
	
	public void addTrainer(Trainer t);
	
}
