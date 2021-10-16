package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Trainer;
import com.revature.services.TrainerService;

@RestController
@RequestMapping(value = "/trainers")
public class TrainerController {
	
	private TrainerService ts;
	
	@Autowired
	public TrainerController(TrainerService ts) {
		super();
		this.ts = ts;
	}
	
	@PostMapping
	public ResponseEntity addTrainer(@RequestBody Trainer t) {
		System.out.println("in controller, " + t);
		ts.addTrainer(t);
		return ResponseEntity.status(201).build();
	}
	
	@GetMapping
	public ResponseEntity<List<Trainer>> getTrainers(){
		System.out.println("in controller");
		List<Trainer> result = ts.getTrainers();
		
		return ResponseEntity.ok(result);
	}
}
