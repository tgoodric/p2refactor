package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "pokemon")
public class Pokemon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pokemon_id")
	private int pokemonId; //serial primary key
	
	@Column(name = "pokedex_number")
	private int pokedexNumber; //species etc. can be pulled from the PokeAPI from this
	@Column(name = "level")
	private int level;
	@Column(name = "max_hit_points")
	private int maxHitPoints;
	@Column(name = "hit_points")
	private int hitPoints;
	@Column(name = "attack")
	private int attack; 
	@Column(name = "special_attack")
	private int specialAttack;
	@Column(name = "defense")
	private int defense;
	@Column(name = "special_defense")
	private int specialDefense;
	@Column(name="experience")
	private int experience;
	
	
	@ManyToOne
	@JoinColumn(name = "trainer_id")
	private Trainer trainerIdFk; //foreign key, references trainers

	public Pokemon() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Pokemon(int pokemonId, int pokedexNumber, int level, int maxHitPoints, int hitPoints, int attack,
			int specialAttack, int defense, int specialDefense, int experience, Trainer trainerIdFk) {
		super();
		this.pokemonId = pokemonId;
		this.pokedexNumber = pokedexNumber;
		this.level = level;
		this.maxHitPoints = maxHitPoints;
		this.hitPoints = hitPoints;
		this.attack = attack;
		this.specialAttack = specialAttack;
		this.defense = defense;
		this.specialDefense = specialDefense;
		this.experience = experience;
		this.trainerIdFk = trainerIdFk;
	}

	


	public Pokemon(int pokedexNumber, int level, int maxHitPoints, int hitPoints, int attack, int specialAttack,
			int defense, int specialDefense, int experience, Trainer trainerIdFk) {
		super();
		this.pokedexNumber = pokedexNumber;
		this.level = level;
		this.maxHitPoints = maxHitPoints;
		this.hitPoints = hitPoints;
		this.attack = attack;
		this.specialAttack = specialAttack;
		this.defense = defense;
		this.specialDefense = specialDefense;
		this.experience = experience;
		this.trainerIdFk = trainerIdFk;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + attack;
		result = prime * result + defense;
		result = prime * result + experience;
		result = prime * result + hitPoints;
		result = prime * result + level;
		result = prime * result + maxHitPoints;
		result = prime * result + pokedexNumber;
		result = prime * result + pokemonId;
		result = prime * result + specialAttack;
		result = prime * result + specialDefense;
		result = prime * result + ((trainerIdFk == null) ? 0 : trainerIdFk.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemon other = (Pokemon) obj;
		if (attack != other.attack)
			return false;
		if (defense != other.defense)
			return false;
		if (experience != other.experience)
			return false;
		if (hitPoints != other.hitPoints)
			return false;
		if (level != other.level)
			return false;
		if (maxHitPoints != other.maxHitPoints)
			return false;
		if (pokedexNumber != other.pokedexNumber)
			return false;
		if (pokemonId != other.pokemonId)
			return false;
		if (specialAttack != other.specialAttack)
			return false;
		if (specialDefense != other.specialDefense)
			return false;
		if (trainerIdFk == null) {
			if (other.trainerIdFk != null)
				return false;
		} else if (!trainerIdFk.equals(other.trainerIdFk))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pokemon [pokemonId=" + pokemonId + ", pokedexNumber=" + pokedexNumber + ", level=" + level
				+ ", maxHitPoints=" + maxHitPoints + ", hitPoints=" + hitPoints + ", attack=" + attack
				+ ", specialAttack=" + specialAttack + ", defense=" + defense + ", specialDefense=" + specialDefense
				+ ", experience=" + experience + ", trainerIdFk=" + trainerIdFk + "]";
	}

	public int getPokemonId() {
		return pokemonId;
	}

	public void setPokemonId(int pokemonId) {
		this.pokemonId = pokemonId;
	}

	public int getPokedexNumber() {
		return pokedexNumber;
	}

	public void setPokedexNumber(int pokedexNumber) {
		this.pokedexNumber = pokedexNumber;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getSpecialAttack() {
		return specialAttack;
	}

	public void setSpecialAttack(int specialAttack) {
		this.specialAttack = specialAttack;
	}

	public Trainer getTrainerIdFk() {
		return trainerIdFk;
	}

	public void setTrainerIdFk(Trainer trainerIdFk) {
		this.trainerIdFk = trainerIdFk;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getSpecialDefense() {
		return specialDefense;
	}

	public void setSpecialDefense(int specialDefense) {
		this.specialDefense = specialDefense;
	}

	
}
