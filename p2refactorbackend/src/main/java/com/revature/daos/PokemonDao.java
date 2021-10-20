package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.models.Pokemon;
import com.revature.models.Trainer;
import com.revature.utils.HibernateUtil;

public class PokemonDao implements IPokemonDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Pokemon> getPokemon(int trainerId) {
		
		try(Session ses = HibernateUtil.getSession()) {
			
			String HQL = "FROM Pokemon WHERE trainer_id=:tID";
			List<Pokemon> allPokemon = ses.createQuery(HQL)
					.setParameter("tID", trainerId)
					.list();
			HibernateUtil.closeSession();
			return allPokemon;
		} catch (HibernateException e) {
			e.printStackTrace();
			return (List<Pokemon>) e;
		}
		
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Pokemon> getPokemonUpToLevel(int trainerId, int level) {
		try(Session ses = HibernateUtil.getSession()){
			String Hql = "FROM Pokemon WHERE trainer_id=:tID and level=:lvl ";
			List<Pokemon> listPokeLevel = ses.createQuery(Hql)
					.setParameter("tID", trainerId)
					.setParameter("lvl", level)
					.list();
			HibernateUtil.closeSession();
			return listPokeLevel;

		}catch(HibernateException e) {
			
			e.printStackTrace();
			return (List<Pokemon>) e;
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
			
			HibernateUtil.closeSession();
			return true;
			
		}catch(HibernateException e) {
			e.printStackTrace();
			return false;
			
		}
		
		
		
		
	}

}
