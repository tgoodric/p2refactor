package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
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
		try(Session ses = HibernateUtil.getSession()){
			String hql = "FROM Trainer t";
			
			List<Trainer> result = ses.createQuery(hql).list();
			HibernateUtil.closeSession();
			return result;
			
		}catch(HibernateException e) {
			
			e.printStackTrace();
			
		}
		return null;
		
	}

	@SuppressWarnings("unchecked")
	@Override
    public List<Trainer> getTrainers(String username) {
        try(Session ses = HibernateUtil.getSession()) {
			String hql = "FROM Trainer t WHERE t.username = :username";
			List<Trainer> result = ses.createQuery(hql)
			        .setParameter("username", username)                
			        .list();
			return result;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
        return null;
    }
	
	@Override
	public List<Trainer> getTrainers(int trainerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Trainer> getForLogin(int id, String username, String password) {
		//System.out.println("in dao");
		//Session is our EntityManager
		//Word to the wise: Don't use criteria API. Ever.
		try(Session ses = HibernateUtil.getSession()) {
			
			System.out.println("session open");
			String hql = "FROM Trainer T WHERE T.id = :username AND T.password = :password";
			List<Trainer> result = ses.createQuery(hql)
				.setParameter("username", username)
				.setParameter("password", password)
				.list(); 
			System.out.println("query successful-ish");
			return result;
		} catch (HibernateException e) {
			
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public void addTrainer(Trainer t) {
			if ((t.getUsername() == null || t.getUsername().isEmpty()) 			//Java needs String.isNullOrEmpty()
					|| t.getPassword() == null || t.getPassword().isEmpty()) {	//C# has it, and it's useful
				throw new IllegalArgumentException("Username or password was null or empty");
			}
		try(Session ses = HibernateUtil.getSession()) {
			System.out.println("session open");
			ses.save(t);
			HibernateUtil.closeSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}