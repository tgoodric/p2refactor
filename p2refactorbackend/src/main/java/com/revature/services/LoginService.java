package com.revature.services;

import com.revature.daos.TrainerDao;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class LoginService {

    private static LoginService ls = null;

    private LoginService() {
        //no implementation
    }



    public boolean login(String username, String password) {
        TrainerDao tDao = TrainerDao.getTrainerDao();
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        //String passwordHash = encoder.encode(password);
        if(tDao.getForLogin(username, password).size() != 1) {
            return false;
        }
        return true;
    }

    public static LoginService getLoginService() {
        if(ls == null) {
            ls = new LoginService();
        }

        return ls;
    }

}