package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
	private String name;
	private int numArmies;
	private ArrayList<Territory> territoriesOwned;
	private ArrayList<Card> cards;
	private List<Dice> dice;
	
	//abstract methods to implement in subclasses
	public abstract void playTurn();
	public abstract void chooseTerritory();

	public Player() {
		
	}

	public void addArmies() {
		numArmies += (territoriesOwned.size() / 3);
	}
	
	public void addTerritory(Territory toAdd) {
		territoriesOwned.add(toAdd);
	}
	
	public void attack(Territory attackingTerritory, Territory DefendingTerritory){
		
	}
	
	
	
	//set methods
	public void setName(String name) {
		this.name = name;
	}
	
	public void setNumArmies(int numArmies) {
		this.numArmies = numArmies;
	}
	
	
	//get methods
	public String getName() {
		return name;
	}
	
	public int getNumArmies() {
		return numArmies;
	}
	
	public ArrayList<Territory> getTerritories() {
		return territoriesOwned;
	}
	
}
