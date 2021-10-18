package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.TrainerDao;
import com.revature.models.Trainer;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class LoginService {
	
	Logger log = LogManager.getLogger(); //Logger object so that we can implement Logging


    private static LoginService ls = null;

    private LoginService() {
        //no implementation
    }



    public Trainer login(String username, String password) {
        TrainerDao tDao = TrainerDao.getTrainerDao();
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        //String passwordHash = encoder.encode(password);
        //List<Trainer> result = tDao.getForLogin(0, username, password);
        List<Trainer> result = tDao.getTrainers(username);
        System.out.println(result);
       // if(tDao.getForLogin(0, username, password).size() != 1) {
        if (result.size() == 1 && result.get(0).getPassword().equals(password)) {
        	log.info("User " + username + " logged in successfully");
        	return result.get(0);
        }
        else {         	
        	log.warn("Unsuccessful login");
        	return null;
        }
        
        
    }

    public static LoginService getLoginService() {
        if(ls == null) {
            ls = new LoginService();
        }
        
        return ls;
    }

}