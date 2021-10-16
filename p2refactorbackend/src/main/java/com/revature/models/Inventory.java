package com.revature.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "inventory")
public class Inventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inventory_id")
	private int inventoryId; //serial primary key
	
	@Column(name = "pokeballs")
	private int pokeballs; 
	@Column(name = "potions")
	private int potions;
	@Column(name = "super_potions")
	private int superPotions;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "trainer_id_fk")
	private Trainer trainerIdFk;
	
	//constructors
	public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Inventory(int inventoryId, int pokeballs, int potions, int superPotions, Trainer trainerIdFk) {
		super();
		this.inventoryId = inventoryId;
		this.pokeballs = pokeballs;
		this.potions = potions;
		this.superPotions = superPotions;
		this.trainerIdFk = trainerIdFk;
	}

	public Inventory(int pokeballs, int potions, int superPotions, Trainer trainerIdFk) {
		super();
		this.pokeballs = pokeballs;
		this.potions = potions;
		this.superPotions = superPotions;
		this.trainerIdFk = trainerIdFk;
	}
	
	//boilerplate instance methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + inventoryId;
		result = prime * result + pokeballs;
		result = prime * result + potions;
		result = prime * result + superPotions;
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
		Inventory other = (Inventory) obj;
		if (inventoryId != other.inventoryId)
			return false;
		if (pokeballs != other.pokeballs)
			return false;
		if (potions != other.potions)
			return false;
		if (superPotions != other.superPotions)
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
		return "Inventory [inventoryId=" + inventoryId + ", pokeballs=" + pokeballs + ", potions=" + potions
				+ ", superPotions=" + superPotions + ", trainerIdFk=" + trainerIdFk + "]";
	}

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public int getPokeballs() {
		return pokeballs;
	}

	public void setPokeballs(int pokeballs) {
		this.pokeballs = pokeballs;
	}

	public int getPotions() {
		return potions;
	}

	public void setPotions(int potions) {
		this.potions = potions;
	}

	public int getSuperPotions() {
		return superPotions;
	}

	public void setSuperPotions(int superPotions) {
		this.superPotions = superPotions;
	}

	public Trainer getTrainerIdFk() {
		return trainerIdFk;
	}

	public void setTrainerIdFk(Trainer trainerIdFk) {
		this.trainerIdFk = trainerIdFk;
	}

	
}
