package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.revature.models.Pokemon;
import com.revature.models.Trainer;
import com.revature.utils.HibernateUtil;

public class PokemonDao implements IPokemonDao {
	
	/*
	 * Getting all pokemon of certain user
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Pokemon> getPokemon(int trainerId) {
		
		try(Session ses = HibernateUtil.getSession()) {
			
			String HQL = "FROM Pokemon WHERE trainer_id=:tID";
			List<Pokemon> allPokemon = ses.createQuery(HQL)
					.setParameter("tID", trainerId)
					.list();
			
			return allPokemon;
		} catch (HibernateException e) {
			e.printStackTrace();
			return (List<Pokemon>) e;
		}finally {
			HibernateUtil.closeSession();
		}
		
		
	}
	
	/*
	 * Getting All the pokemons from Pokemon database with userId and level
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Pokemon> getPokemonUpToLevel(int trainerId, int level) {
		try(Session ses = HibernateUtil.getSession()){
			String Hql = "FROM Pokemon WHERE trainer_id=:tID and level=:lvl ";
			List<Pokemon> listPokeLevel = ses.createQuery(Hql)
					.setParameter("tID", trainerId)
					.setParameter("lvl", level)
					.list();
			
			return listPokeLevel;

		}catch(HibernateException e) {
			
			e.printStackTrace();
			return (List<Pokemon>) e;
		}
		finally {
			HibernateUtil.closeSession();
		}
		
}

	@Override
	public boolean addPokemon(int trainerId, int pokedexNumber) {
		return false;

	}

	@Override
	public boolean addPokemon(int trainerId, int pokedexNumber, int level) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Adding a new Pokemon to a Pokemon table in a database
	 */
	@Override
	public boolean insertPokemon(int trainerId, Pokemon pokemon) {
		
		//First we need to match trainerId from Trainer Model in Hibernate
				//Query for for finding trainer ID
		
		try(Session ses = HibernateUtil.getSession()){
			
			String hql = "FROM Trainer where trainer_id = :tID";
			Trainer trainer = (Trainer) ses.createQuery(hql)
					.setParameter("tID", trainerId)
					.uniqueResult();
			
			//we are setting trainerID from ^^ above in Pokemon Foreignkey TrainerId column
			pokemon.setTrainerIdFk(trainer);
			
			ses.save(pokemon);
			
			
			return true;
			
		}catch(HibernateException e) {
			e.printStackTrace();
			return false;
			
		}finally {
			HibernateUtil.closeSession();
		}
		
		
		
		
	}
	
	
	/*
	 * Updating Pokemon with certain fields
	 */
	public boolean updatePokemon(Pokemon p) { //set all fields except trainer and ID with new values
		String hql = "UPDATE Pokemon set attack = :newAttack, " + 
					 					"defense = :newDefense, " + 
					 					"experience = :newExperience, " +
					 					"hit_points = :newHitPoints, " + 
					 					"level = :newLevel, " + 
					 					"max_hit_points = :newMaxHitPoints, " +
					 					"special_attack = :newSpecialAttack, " + 
					 					"special_defense = :newSpecialDefense " +
					 "WHERE pokemon_id = :pokemonId";
		Transaction txn = null;
		
		try {
			Session ses = HibernateUtil.getSession();
		
			txn = ses.beginTransaction();
			
			Query q = ses.createQuery(hql);
			
			q.setParameter("newAttack", p.getAttack());
			q.setParameter("newDefense", p.getDefense());
			q.setParameter("newExperience", p.getExperience());
			q.setParameter("newHitPoints", p.getHitPoints());
			q.setParameter("newLevel", p.getLevel());
			q.setParameter("newMaxHitPoints", p.getMaxHitPoints());
			q.setParameter("newSpecialAttack", p.getSpecialAttack());
			q.setParameter("newSpecialDefense", p.getSpecialDefense());
			q.setParameter("pokemonId", p.getPokemonId());
			
			q.executeUpdate();
			
			txn.commit();
		}
		catch(ConstraintViolationException e) {
			txn.rollback();
			return false;
		}	// may add other exceptions as needed
		catch(Exception e) {
			e.printStackTrace();
			txn.rollback();
			return false;
		}
		finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

}
