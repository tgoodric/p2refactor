package com.revature.services;

import java.util.List;

import com.revature.daos.PokemonDao;
import com.revature.models.Pokemon;

public class PokemonService {
	
	PokemonDao pd = new PokemonDao();

	public boolean insertAllPokemon(int trainerId, Pokemon pokemon) {
		if(pd.insertPokemon(trainerId, pokemon)) {
			return true;
		}
		return false;
	}

	public List<Pokemon> findAllPokemonByTrainer(int trainerId) {
		
		return pd.getPokemon(trainerId);
	}

	public List<Pokemon> getAllPokemonWithTrainerAndLevel(int trainerId, int level) {
		
		return pd.getPokemonUpToLevel(trainerId, level);
	}
	
	

}