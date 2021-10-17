package com.revature.services;

import java.util.List;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import java.util.List;
/*
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;*/
//import org.springframework.stereotype.Service;

import com.revature.daos.TrainerDao;
import com.revature.models.Trainer;


public class TrainerService /*implements UserDetailsService*/ {

	//private static PasswordEncoder pe = new BCryptPasswordEncoder(16);
	private static TrainerDao tDao = TrainerDao.getTrainerDao();
	/*
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return tDao.getByUsername(username)
				.orElseThrow(()-> 
						new UsernameNotFoundException(String.format("Username %s not found", username)));
	}
	*/
	public int addTrainer(Trainer t) {
		//String encodedPassword = pe.encode(t.getPassword());
		
		//t.setPassword(encodedPassword);
		//System.out.println(encodedPassword);                                       
		tDao.addTrainer(t);
		Trainer result = tDao.getTrainers(t.getUsername()).get(0);
		return result.getUserId();
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
