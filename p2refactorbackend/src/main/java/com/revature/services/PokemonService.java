package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.PokemonDao;
import com.revature.models.Pokemon;

public class PokemonService {
	
	Logger log = LogManager.getLogger(); //Logger object so that we can implement Logging
	
	PokemonDao pd = new PokemonDao();

	public boolean insertAllPokemon(int trainerId, Pokemon pokemon) {
		if(pd.insertPokemon(trainerId, pokemon)) {
			log.info("Trainer caught (and inserted) a Pokemon");
			return true;
		}
		log.warn("Pokemon insert failed");
		return false;
	}

	public List<Pokemon> findAllPokemonByTrainer(int trainerId) {
		
		return pd.getPokemon(trainerId);
	}

	public List<Pokemon> getAllPokemonWithTrainerAndLevel(int trainerId, int level) {
		
		return pd.getPokemonUpToLevel(trainerId, level);
	}

	public boolean updatePokemon(Pokemon pokemon) {
		return pd.updatePokemon(pokemon);		
	}
	
}