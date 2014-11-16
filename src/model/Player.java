package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
	private String name;
	private int numArmies;
	// TODO I think we need a way to know how many of the player's total armies are on a territory. 
	// Should we subtract from numArmies when we place armies? or should we have another variable
	// just to keep track of unallocated armies? -Elizabeth
	private ArrayList<Territory> territoriesOwned;
	private ArrayList<Card> cards;
	private List<Dice> attackingDice;
	private List<Dice> defendingDice;
	private Dice die;
	
	//abstract methods to implement in subclasses
	public abstract void playTurn();
	public abstract void chooseTerritory();
	// TODO Also, I think we need a method to place armies (at the beginning once all
	// territories are chosen, as well as at the beginning of each turn. The player
	// must choose where to place an army, out of the territories he owns. -Elizabeth 
	// I (pseudo?)coded calling this method in RiskController's populateBoard()
	// By the rulebook, armies are placed one at a time. 

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
		if(territoriesOwned.size() / 3 <= 3 ) {
			numArmies += 3;
		}
		else
			numArmies += (territoriesOwned.size() / 3);
		// Rulebook: a player is entitled to a minimum of three armies even if
		// he owns fewer than nine territories
		// Continent bonuses: Asia, 7; North America and Europe, 5; Africa, 3; Australia and South America, 2
		int northAmerica = 0;
		int southAmerica = 0;
		int europe = 0;
		int africa = 0;
		int asia = 0;
		int australia = 0;
		for(Territory t: territoriesOwned) {
			Continent continent = t.getContinent();
			switch(continent) {
			case NORTH_AMERICA:
				northAmerica++;
				break;
			case SOUTH_AMERICA:
				southAmerica++;
				break;
			case EUROPE:
				europe++;
			break;
			case AFRICA:
				africa++;
				break;
			case ASIA:
				asia++;
				break;
			case AUSTRALIA:
				australia++;
				break;
			}
		}
		if(northAmerica == 1) {
			numArmies += 5;
		}
		if(southAmerica == 1) {
			numArmies += 2;
		}
		if(europe == 1) {
			numArmies += 5;
		}
		if(africa == 1) {
			numArmies += 3;
		}
		if(asia == 1) {
			numArmies += 7;
		}
		if(australia == 1){
			numArmies += 2;
		}
	}
	
	public void addTerritory(Territory toAdd) {
		territoriesOwned.add(toAdd);
	}
	
	public void attack(Territory attackingTerritory, Territory DefendingTerritory){
		//TODO: Figure out a way to call 
		// I think Controller does or should do this -Elizabeth
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
