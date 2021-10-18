package com.revature.daos;

import java.util.List;

import com.revature.models.Trainer;

public interface ITrainerDao {
	
	//TODO: decide what we will need in this
	
	//utility methods, may be removed in final version
	public List<Trainer> getTrainers();
	public List<Trainer> getTrainers(String username);
	public List<Trainer> getTrainers(int trainerId);
	public List<Trainer> getForLogin(int id,String username, String password);
	
	public void addTrainer(Trainer t);
	
}
