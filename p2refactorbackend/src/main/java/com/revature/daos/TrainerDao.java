package com.revature.daos;

import java.util.List;

import org.hibernate.Session;

import com.revature.models.Trainer;
import com.revature.utils.HibernateUtil;


public class TrainerDao implements ITrainerDao {
	
	
	private static TrainerDao tDao = null;
	//private static PasswordEncoder pe = new BCryptPasswordEncoder();
	
	
	public static TrainerDao getTrainerDao() {
		if(tDao == null) { //if the singleton variable above is null...
			tDao = new TrainerDao();//instantiate a new one
		}
		
		return tDao;
	}
	
	@Override
	public List<Trainer> getTrainers() {
		String hql = "FROM Trainer t";
		Session ses = HibernateUtil.getSession();
		List<Trainer> result = ses.createQuery(hql).list();
		return result;
	}

	@Override
	public List<Trainer> getTrainers(String username) {
		String hql = "FROM Trainer t WHERE t.username = username";
		Session ses = HibernateUtil.getSession();
		List<Trainer> result = ses.createQuery(hql).list();
		return result;
	}
	
	@Override
	public List<Trainer> getTrainers(int trainerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Trainer> getForLogin(String username, String password) {
		
		//Session is our EntityManager
		//Word to the wise: Don't use criteria API. Ever.
		Session ses = HibernateUtil.getSession();
		
		String hql = "FROM Trainer T WHERE T.id = :username AND T.password = :password";
		List<Trainer> result = ses.createQuery(hql)
			.setParameter("username", username)
			.setParameter("password", password)
			.list(); 
		return result;
	}


	@Override
	public void addTrainer(Trainer t) {
		Session ses = HibernateUtil.getSession();
		System.out.println("session open");
		ses.save(t);
		HibernateUtil.closeSession();
	}
	
	
}