/**
 * 
 */
package com.revature.daos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Inventory;
import com.revature.models.Trainer;
import com.revature.utils.HibernateUtil;

/**
 * @author C. Funaki
 */

public class InventoryDao implements IInventoryDao {

	//singleton
	private static InventoryDao iDao = null;
	
	
	public InventoryDao getInventoryDao() {
		if(iDao == null) {
			iDao = new InventoryDao();
		}
		return iDao;
	}

	//method to get a particular trainers inventory
	@SuppressWarnings("unchecked")
	@Override
	public List<Inventory> getOneInventory(int trainerId) {

		try(Session ses = HibernateUtil.getSession()) {
			
			String hql = "FROM Inventory where trainerIdFk = " + trainerId;
			List<Inventory> i = ses.createQuery(hql).list();
			
			HibernateUtil.closeSession();

			return i;

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	

	
	//subtract one from whatever item the user used
	@Override
	public boolean useItem(int trainerId, String itemType) {

		try(Session ses = HibernateUtil.getSession()) {
			Transaction tran = ses.beginTransaction(); //update and delete must happen within a transaction
			
			System.out.println(itemType);
			if(itemType.equals("pokeballs")) {
				  
				System.out.println("made it inside use pokeballs if");
				//get the current amount of pokeballs
			    Query query = ses.createQuery("SELECT pokeballs FROM Inventory where trainerIdFk=" + trainerId);
			    int pokeballs = (int) query.getSingleResult();

			    //add one pokeball to the respective trainers inventory of pokeballs
				String hql = "UPDATE Inventory SET pokeballs = " +  (pokeballs-1) + " WHERE trainerIdFk = " + trainerId;
				Query q = ses.createQuery(hql);
				q.executeUpdate();
					
				//close transaction and session to prevent memory leak
				tran.commit();
				HibernateUtil.closeSession();
				return true;
			    
			}else if(itemType.equals("potions")) {
				//get the current amount of potions
			    Query query2 = ses.createQuery("SELECT potions FROM Inventory where trainerIdFk=" + trainerId);
			    int potions = (int) query2.getSingleResult();
				
			    //add one potion to the respective trainers inventory of potions
				String hql2 = "UPDATE Inventory SET potions = " +  (potions-1) + " WHERE trainerIdFk = " + trainerId;
				Query q2 = ses.createQuery(hql2);
				q2.executeUpdate();
					
				//close transaction and session to prevent memory leak
				tran.commit();
				HibernateUtil.closeSession();
				return true;
			    
			    
			}else if(itemType.equals("superpotions")) {
				//get the current amount of super potions
			    Query query3 = ses.createQuery("SELECT superPotions FROM Inventory where trainerIdFk=" + trainerId);
			    int spotions = (int) query3.getSingleResult();
				
			    //add one potion to the respective trainers inventory of super potions
				String hql3 = "UPDATE Inventory SET superPotions = " +  (spotions-1) + " WHERE trainerIdFk = " + trainerId;
				Query q3 = ses.createQuery(hql3);
				q3.executeUpdate();
					
				//close transaction and session to prevent memory leak
				tran.commit();
				HibernateUtil.closeSession();
				return true;
			}else {
				tran.commit();
				HibernateUtil.closeSession();
				return false;
			}
			
		}catch(HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//add one to whatever item the user selects
	@Override
	public boolean addItem(int trainerId, String itemType) {
		try(Session ses = HibernateUtil.getSession()) {
			
			Transaction tran = ses.beginTransaction(); //update and delete must happen within a transaction
			
			if(itemType.equals("pokeballs")) {
				  
				//get the current amount of pokeballs
			    Query query = ses.createQuery("SELECT pokeballs FROM Inventory where trainerIdFk=" + trainerId);
			    int pokeballs = (int) query.getSingleResult();
				
			    //add one pokeball to the respective trainers inventory of pokeballs
				String hql = "UPDATE Inventory SET pokeballs = " +  (pokeballs+1) + " WHERE trainerIdFk = " + trainerId;
				Query q = ses.createQuery(hql);
				q.executeUpdate();
					
				//close transaction and session to prevent memory leak
				tran.commit();
				HibernateUtil.closeSession();
				return true;
			    
			}else if(itemType.equals("potions")) {
				//get the current amount of potions
			    Query query2 = ses.createQuery("SELECT potions FROM Inventory where trainerIdFk=" + trainerId);
			    int potions = (int) query2.getSingleResult();
				
			    //add one potion to the respective trainers inventory of potions
				String hql2 = "UPDATE Inventory SET potions = " +  (potions+1) + " WHERE trainerIdFk = " + trainerId;
				Query q2 = ses.createQuery(hql2);
				q2.executeUpdate();
					
				//close transaction and session to prevent memory leak
				tran.commit();
				HibernateUtil.closeSession();
				return true;
			    
			    
			}else if(itemType.equals("superpotions")) {
				//get the current amount of super potions
			    Query query3 = ses.createQuery("SELECT superPotions FROM Inventory where trainerIdFk=" + trainerId);
			    int spotions = (int) query3.getSingleResult();
				
			    //add one potion to the respective trainers inventory of super potions
				String hql3 = "UPDATE Inventory SET superPotions = " +  (spotions+1) + " WHERE trainerIdFk = " + trainerId;
				Query q3 = ses.createQuery(hql3);
				q3.executeUpdate();
					
				//close transaction and session to prevent memory leak
				tran.commit();
				HibernateUtil.closeSession();
				return true;
			}else {
				tran.commit();
				HibernateUtil.closeSession();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	//add an entire inventory for a new user
	@Override
	public boolean addInventory(Inventory inventory) {
		try(Session ses = HibernateUtil.getSession()) {
			
			String hql = "FROM Trainer where trainer_id=:tID";
			Trainer trainer = (Trainer) ses.createQuery(hql)
					.setParameter("tID", inventory.getTrainerIdFk().getUserId())
					.uniqueResult();
			
			if(trainer != null) {
				inventory.setTrainerIdFk(trainer);
				
				ses.save(inventory);
				HibernateUtil.closeSession();
				return true;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}


}
