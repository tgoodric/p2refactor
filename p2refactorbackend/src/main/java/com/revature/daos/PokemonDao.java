package com.revature.daos;

import java.util.List;

import org.hibernate.Session;

import com.revature.models.Pokemon;
import com.revature.models.Trainer;
import com.revature.utils.HibernateUtil;

public class PokemonDao implements IPokemonDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Pokemon> getPokemon(int trainerId) {
		Session ses = HibernateUtil.getSession();
		String HQL = "FROM Pokemon WHERE trainer_id=:tID";
		List<Pokemon> allPokemon = ses.createQuery(HQL)
				.setParameter("tID", trainerId)
				.list();
		HibernateUtil.closeSession();
		return allPokemon;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Pokemon> getPokemonUpToLevel(int trainerId, int level) {
		
		Session ses = HibernateUtil.getSession();
		String Hql = "FROM Pokemon WHERE trainer_id=:tID and level=:lvl ";
		List<Pokemon> listPokeLevel = ses.createQuery(Hql)
				.setParameter(":tId", trainerId)
				.setParameter(":lvl", level)
				.list();
		HibernateUtil.closeSession();
		return listPokeLevel;
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
		/*
		Session ses = HibernateUtil.getSession();
		
		//First we need to match trainerId from Trainer Model in Hibernate
		//Query for for finding trainer ID
		String hql = "FROM Trainer where trainer_id = :tID";
		Trainer trainer = (Trainer) ses.createQuery(hql)
				.setParameter("tID", trainerId)
				.uniqueResult();
		
		//we are setting trainerID from ^^ above in Pokemon Foreignkey TrainerId column
		pokemon.setTrainerIdFk(trainer);
		
		ses.save(pokemon);
		
		HibernateUtil.closeSession();*/
		return true;
		
	}

}
