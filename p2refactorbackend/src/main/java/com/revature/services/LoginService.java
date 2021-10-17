package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.TrainerDao;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class LoginService {
	
	Logger log = LogManager.getLogger(); //Logger object so that we can implement Logging


    private static LoginService ls = null;

    private LoginService() {
        //no implementation
    }



    public boolean login(String username, String password) {
        TrainerDao tDao = TrainerDao.getTrainerDao();
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        //String passwordHash = encoder.encode(password);
        if(tDao.getForLogin(username, password).size() != 1) {
        	log.warn("Unsuccessful login");
            return false;
        }
        log.info("SUCCESSFUL user login!");
        return true;
    }

    public static LoginService getLoginService() {
        if(ls == null) {
            ls = new LoginService();
        }
        
        return ls;
    }

}