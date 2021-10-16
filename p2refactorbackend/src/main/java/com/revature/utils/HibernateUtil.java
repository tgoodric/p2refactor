package com.revature.utils;

import javax.security.auth.login.Configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;





public class HibernateUtil {
	
	
	//instantiate session factory

	private static SessionFactory sf = new org.hibernate.cfg.Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	
	//singleton session
	private static Session ses;
	
	//return session
	public static Session getSession() {
		if (ses == null) { //if there isn't a session...
			ses = sf.openSession(); //get one!
		}
		return ses; //return a session Object
	}
	
	public static void closeSession() {
		ses.close();
		ses = null;
	}
}
