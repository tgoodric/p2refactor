package com.revature.daos;

import java.util.List;
//import java.util.Optional;

//import javax.persistence.Query;
//import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;

import com.revature.models.Trainer;
import com.revature.utils.HibernateUtil;

@Repository("trainers")
public class TrainerDao implements ITrainerDao {
	
	
	private static TrainerDao tDao = null;
	//private static PasswordEncoder pe = new BCryptPasswordEncoder();
	
	@Autowired
	private TrainerDao() {
		//this.passwordEncoder = passwordEncoder;
	}
	
	public static TrainerDao getTrainerDao() {
		if(tDao == null) { //if the singleton variable above is null...
			tDao = new TrainerDao();//instantiate a new one
		}
		
		return tDao;
	}
	
	@Override
	public List<Trainer> getTrainers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Trainer> getTrainers(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	public Optional<Trainer> getByUsername(String username){
		return getTrainers()													//start with all of them
				.stream()														//prepare to filter
				.filter(trainer -> username.equals(trainer.getUsername()))		//select trainers whose username is the same as the one passed
				.findFirst();													//and pick the first one, out of at most one, since username has unique constraint
	}
	 */
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
		ses.save(t);
		HibernateUtil.closeSession();
	}
	
	
}