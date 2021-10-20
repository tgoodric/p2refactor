package com.revature.controllers;

import java.util.List;

import com.google.gson.Gson;
import com.revature.models.Pokemon;
import com.revature.services.PokemonService;

import io.javalin.http.Handler;

public class PokemonController {
	
	PokemonService ps = new PokemonService();
	
	
	public Handler insertPokeHandler = (ctx)->{
		
//		int userId = Integer.parseInt(ctx.pathParam("trainerid"));
		
		String body = ctx.body();
		
		Gson gson = new Gson();
		
		Pokemon pokemon = gson.fromJson(body, Pokemon.class);
		
		int trainerId = pokemon.getTrainerIdFk().getUserId();
		
		if(ps.insertAllPokemon(trainerId, pokemon)) {
			
				ctx.status(200);
		}else {
			
			ctx.status(400);
		}
		
		
		
	};


	public Handler getPokemonByTrainerHandler = (ctx) ->{
		
		
		int trainerId = Integer.parseInt(ctx.pathParam("trainerId"));
		
		Gson gson = new Gson();
		if(ps.findAllPokemonByTrainer(trainerId) != null) {
				
			List<Pokemon> pokeList = ps.findAllPokemonByTrainer(trainerId);
			String JsonPokeList = gson.toJson(pokeList);
			
			ctx.result(JsonPokeList);
			ctx.status(200);
			
		}else {
			
			ctx.status(403);
			ctx.result("Failed to retreive info");
		}
	};


	public Handler getPokemonByTrainerWithLevelHandler = (ctx) -> {
		
		int trainerId = Integer.parseInt(ctx.pathParam("trainerId"));
		int level  = Integer.parseInt(ctx.pathParam("level"));
		
		Gson gson = new Gson();
		if(ps.getAllPokemonWithTrainerAndLevel(trainerId, level)!=null) {
			
			List<Pokemon> pokeList = ps.getAllPokemonWithTrainerAndLevel(trainerId, level);
			String JSONListOfPoke = gson.toJson(pokeList);
			ctx.result(JSONListOfPoke);
			ctx.status(200);
		}
	};


	public Handler updatePokemonHandler = (ctx) -> {
		String body = ctx.body();
		
		Gson gson = new Gson();
		
		Pokemon pokemon = gson.fromJson(body, Pokemon.class);
		
		if(ps.updatePokemon(pokemon)) {
			ctx.status(200);
		}
		else {
			ctx.status(400);
		}
		
		
	};
	
	
	
	
	

}