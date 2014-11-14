package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
	private String name;
	private int numArmies;
	private ArrayList<Territory> territoriesOwned;
	private ArrayList<Card> cards;
	private List<Dice> attackingDice;
	private List<Dice> defendingDice;
	private Dice die;
	
	//abstract methods to implement in subclasses
	public abstract void playTurn();
	public abstract void chooseTerritory();

	public Player() {
		attackingDice = new ArrayList<Dice>();
		defendingDice = new ArrayList<Dice>();
		attackingDice.add(die);
		attackingDice.add(die);
		attackingDice.add(die);
		defendingDice.add(die);
		defendingDice.add(die);
	}

	public void addArmies() {
		numArmies += (territoriesOwned.size() / 3);
	}
	
	public void addTerritory(Territory toAdd) {
		territoriesOwned.add(toAdd);
	}
	
	public void attack(Territory attackingTerritory, Territory DefendingTerritory){
		//TODO: Figure out a way to call 
	}
	
	public void rollDice(){
		for(int i = 0; i < attackingDice.size(); i++){
			attackingDice.get(i).rollDice();
		}
		for(int i = 0; i < attackingDice.size(); i++){
			attackingDice.get(i).rollDice();
		}
		//TODO: call the searching algorithm
	}
	
	//TODO: create the search algorithm
	
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
