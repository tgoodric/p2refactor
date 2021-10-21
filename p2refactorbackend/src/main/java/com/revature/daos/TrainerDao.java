package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.models.Trainer;
import com.revature.utils.HibernateUtil;


public class TrainerDao implements ITrainerDao {
	
	//instantiate singleton DAO
	private static TrainerDao tDao = null;
	
	public static TrainerDao getTrainerDao() {
		if(tDao == null) { 
			tDao = new TrainerDao();
		}
		
		return tDao;
	}
	
	@Override
	@SuppressWarnings("unchecked")
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

	@Override
	@SuppressWarnings("unchecked")
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
        finally {
        	HibernateUtil.closeSession();
        }
        return null;
    }



	@Override
	public void addTrainer(Trainer t) {
			if ((t.getUsername() == null || t.getUsername().isEmpty()) 			
					|| t.getPassword() == null || t.getPassword().isEmpty()) {	
				throw new IllegalArgumentException("Username or password was null or empty");
			}
		try(Session ses = HibernateUtil.getSession()) {
			ses.save(t);
			HibernateUtil.closeSession();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.closeSession();
		}
	}	
}