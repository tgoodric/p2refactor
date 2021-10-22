package com.revature.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.revature.models.Pokemon;
import com.revature.services.PokemonService;

import io.javalin.http.Handler;

public class PokemonController {
	
	PokemonService ps = new PokemonService();
	Logger log = LogManager.getLogger(); //Logger object so that we can implement Logging
	
	public Handler insertPokeHandler = (ctx)->{
		
//		int userId = Integer.parseInt(ctx.pathParam("trainerid"));
		
		String body = ctx.body();
		
		Gson gson = new Gson();
		
		Pokemon pokemon = gson.fromJson(body, Pokemon.class);
		
		int trainerId = pokemon.getTrainerIdFk().getUserId();
		String username = pokemon.getTrainerIdFk().getUsername();
		try {
			if(ps.insertAllPokemon(trainerId, pokemon)) {
				
				ctx.status(200);
				log.info("Pokemon inserted By Trainer: " + username);
			}
		}
		catch(Exception e) {
			

			ctx.status(400);
			log.warn("Pokemon inserted Failed" + username);

		}
		
		
		
		
	};


	public Handler getPokemonByTrainerHandler = (ctx) ->{
		
		
		int trainerId = Integer.parseInt(ctx.pathParam("trainerId"));
		
		Gson gson = new Gson();
		
		try {
			if(ps.findAllPokemonByTrainer(trainerId) != null) {
				
				List<Pokemon> pokeList = ps.findAllPokemonByTrainer(trainerId);
				String JsonPokeList = gson.toJson(pokeList);
				
				ctx.result(JsonPokeList);
				ctx.status(200);
				log.info("Pokemon is Successfully retrieved from database by " + trainerId);
				
			}
			
		}catch(Exception e) {
			
			ctx.status(403);
			ctx.result("Failed to retreive info" + e);
			log.warn("Pokemon cannot be retrieved from database ");
		}
	};


	public Handler getPokemonByTrainerWithLevelHandler = (ctx) -> {
		
		int trainerId = Integer.parseInt(ctx.pathParam("trainerId"));
		int level  = Integer.parseInt(ctx.pathParam("level"));
		
		Gson gson = new Gson();
		try {
			
			if(ps.getAllPokemonWithTrainerAndLevel(trainerId, level)!=null) {
				
				List<Pokemon> pokeList = ps.getAllPokemonWithTrainerAndLevel(trainerId, level);
				String JSONListOfPoke = gson.toJson(pokeList);
				ctx.result(JSONListOfPoke);
				ctx.status(200);
				log.info("Pokemon Successfully retrieved from database with level by " + trainerId);
			}
		}catch(Exception e) {
			
			ctx.status(401);
			ctx.result("Failed to retreive Pokemon "+ e);
			log.warn("Pokemon Failed to retreive from database, Error: " + e);
		}
		
	};


	public Handler updatePokemonHandler = (ctx) -> {
		String body = ctx.body();
		
		Gson gson = new Gson();
		
		Pokemon pokemon = gson.fromJson(body, Pokemon.class);
		String user = pokemon.getPokeName();
		
		if(ps.updatePokemon(pokemon)) {
			ctx.status(200);
			log.info("Update Successful for " + user);
		}
		else {
			ctx.status(400);
			log.warn("Update Failed :" + user);
		}
		
		
	};
	
	
	
	
	

}