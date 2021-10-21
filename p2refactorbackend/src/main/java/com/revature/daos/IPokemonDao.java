package com.revature.daos;

import java.util.List;

import com.revature.models.Pokemon;

public interface IPokemonDao {
	
	
	//all pokemon for a trainer
	public List<Pokemon> getPokemon(int trainerId);
	//all pokemon for a trainer up to a leve
	public List<Pokemon> getPokemonUpToLevel(int trainerId, int level);
	
	//Boolean return type is for error catching
	public boolean addPokemon(int trainerId, int pokedexNumber);
	public boolean addPokemon(int trainerId, int pokedexNumber, int level);
	boolean insertPokemon(int trainerId, Pokemon pokemon);
	
}
