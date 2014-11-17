package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
	private String name;
	private int numArmies; // number of un-placed armies
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
		setAttackingDice(new ArrayList<Dice>());
		setDefendingDice(new ArrayList<Dice>());
		getAttackingDice().add(die);
		getAttackingDice().add(die);
		getAttackingDice().add(die);
		getDefendingDice().add(die);
		getDefendingDice().add(die);
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
		if(northAmerica == 9) {
			numArmies += 5;
		}
		if(southAmerica == 4) {
			numArmies += 2;
		}
		if(europe == 7) {
			numArmies += 5;
		}
		if(africa == 6) {
			numArmies += 3;
		}
		if(asia == 12) {
			numArmies += 7;
		}
		if(australia == 4){
			numArmies += 2;
		}
	}
	
	public void addTerritory(Territory toAdd) {
		territoriesOwned.add(toAdd);
	}
	
	public void attack(Territory attackingTerritory, Territory defendingTerritory){
		//TODO: Figure out a way to call 
		// Maybe this needs to be the Controller's responsibility
		// this method is now obsolete
	}
	
	public void rollDice(){
		for(int i = 0; i < getAttackingDice().size(); i++){
			getAttackingDice().get(i).rollDice();
		}
		for(int i = 0; i < getAttackingDice().size(); i++){
			getAttackingDice().get(i).rollDice();
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
	public abstract void placeArmy();
		// TODO Takes an un-placed army, chooses a territory, and places it there
	
	public boolean canAttack() {
		for (Territory t : territoriesOwned) {
			if (t.getNumArmies() > 1)
				return true;
		}
		return false;
	}
	
	public abstract Territory attackFrom();
		// TODO Chooses, from territories with two or more armies, which to attack with. 
	
	public abstract Territory attackTo(Territory attackFrom);
		// TODO Chooses, from territories adjacent to the parameter, which to attack. 
	
	public List<Dice> getAttackingDice() {
		return attackingDice;
	}
	public void setAttackingDice(List<Dice> attackingDice) {
		this.attackingDice = attackingDice;
	}
	public List<Dice> getDefendingDice() {
		return defendingDice;
	}
	public void setDefendingDice(List<Dice> defendingDice) {
		this.defendingDice = defendingDice;
	}
	
}
