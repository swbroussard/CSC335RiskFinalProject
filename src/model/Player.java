package model;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author jeremyjalnos StevenBroussard
 *
 */
public abstract class Player {
	boolean debug = false;
	private String name;
	private int numArmies; // number of un-placed armies
	private ArrayList<Territory> territoriesOwned, allTerritories;
	private ArrayList<Card> cards;
	private List<Dice> attackingDice;
	private List<Dice> defendingDice;
	
	private boolean doneAttacking;
	
	//abstract methods to implement in subclasses
	//public abstract void playTurn();

	// TODO If you get the chance, please go through and delete comments and methods we don't need anymore 
	
	// TODO Also, I think we need a method to place armies (at the beginning once all
	// territories are chosen, as well as at the beginning of each turn. The player
	// must choose where to place an army, out of the territories he owns. -Elizabeth 
	// I (pseudo?)coded calling this method in RiskController's populateBoard()
	// By the rulebook, armies are placed one at a time. 
/**
 * sets up the player
 */
	public Player() {
		if (debug) System.out.println("player constructor (no arguments) is called");
		setAttackingDice(new ArrayList<Dice>());
		setDefendingDice(new ArrayList<Dice>());
		attackingDice.add(new Dice());
		attackingDice.add(new Dice());
		attackingDice.add(new Dice());
		defendingDice.add(new Dice());
		defendingDice.add(new Dice());
		
		territoriesOwned = new ArrayList<Territory>();
		cards = new ArrayList<Card>();
	}

	/**
	 * sets up the player using a name
	 */
	public Player(String name) {
		if (debug) System.out.println("player constructor (String) is called");
		this.name = name;
		setAttackingDice(new ArrayList<Dice>());
		setDefendingDice(new ArrayList<Dice>());
		attackingDice.add(new Dice());
		attackingDice.add(new Dice());
		attackingDice.add(new Dice());
		defendingDice.add(new Dice());
		defendingDice.add(new Dice());
		
		territoriesOwned = new ArrayList<Territory>();
		cards = new ArrayList<Card>();
	}
	
/**
 * Rulebook: a player is entitled to a minimum of three armies even if he owns fewer than nine territories 
 * Continent bonuses: Asia, 7; North America and Europe, 5; Africa, 3; Australia and South America, 2
 */
	public void addArmies() {
		if (debug) System.out.println("addArmies is called by "+name);
		if(territoriesOwned.size() / 3 <= 3 ) {
			numArmies += 3;
		}
		else
			numArmies += (territoriesOwned.size() / 3);
		int northAmerica = 0;
		int southAmerica = 0;
		int europe = 0;
		int africa = 0;
		int asia = 0;
		int australia = 0;
		for(Territory t: territoriesOwned) {
			Continent continent = t.getContinent();
			if(continent == Continent.NORTH_AMERICA)
				northAmerica++;
			if(continent == Continent.SOUTH_AMERICA)
				southAmerica++;
			if(continent == Continent.EUROPE)
				europe++;
			if(continent == Continent.AFRICA)
				africa++;
			if(continent == Continent.ASIA)
				asia++;
			if(continent == Continent.AUSTRALIA)
				australia++;
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
	/**
	 * 
	 * @param toAdd
	 * adds a territory to the list of owned territories
	 */
	public void addTerritory(Territory toAdd) {
		if (debug) System.out.println("addTerritory is called by "+name);
		territoriesOwned.add(toAdd);
	}
	/**
	 * 
	 * @param attackingTerritory
	 * @param defendingTerritory
	 * never used this--- Consider removing
	 */
	public void attack(Territory attackingTerritory, Territory defendingTerritory){
		//TODO: Figure out a way to call 
		
	}
	/**
	 * 
	 * @param takeArmy
	 * @param reinforceThis
	 * calls for reinforcing owned territories
	 */
	public abstract void reinforceArmies(Territory takeArmy, Territory reinforceThis);
	
	/**
	 * used to roll dice for the purpose of attacking territories
	 */
	public void rollDice(){
		if (debug) System.out.println("rollDice is called by "+name);
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
	/**
	 * 
	 * @param name
	 * set player name
	 */
	public void setName(String name) {
		if (debug) System.out.println("setName is called by "+name+" (pre-change name)");
		this.name = name;
	}
	/**
	 * set number of armies in a particular territory
	 * @param numArmies
	 */
	public void setNumArmies(int numArmies) {
		this.numArmies = numArmies;
		if (debug) System.out.println("set armies: " + numArmies+" called by "+name);  //that will work also
		//I tried using the Run6Bots for test instead, because testing using the
		//JUnit would be very hard. There is a infinite loop in the main method
		//I am adding a lot of print lines and see if the methods are being called.
	}//sounds good ill be back in a few minutes
	//okay
	
	
	//get methods
	/**
	 * 
	 * @return name
	 * gets player name
	 */
	public String getName() {
		if (debug) System.out.println("Get name has been called by"+name);
		return name;
	}
	/**
	 * 
	 * @return numArmies
	 * returns number of armies in a territory
	 */
	public int getNumArmies() {
		if (debug) System.out.println("getNumArmies is called by "+name);
		return numArmies;
	}
	
	/**
	 * 
	 * @return territoriesOwned
	 * holds arraylist of owned territories
	 */
	public ArrayList<Territory> getTerritoriesOwned() {
		if (debug) System.out.println("getTerritoryOwned is called by "+name);
		return territoriesOwned;
	}
	/**
	 * 
	 */
	public abstract void placeArmy();
		// TODO Takes an un-placed army, chooses a territory, and places it there
	
	/**
	 * 
	 * @return
	 * see if one territory is eligible to attack another
	 */
	public boolean canAttack() {
		if (debug) System.out.println("canAttack is called by "+name);
		if(doneAttacking)
			return false;
		for (Territory t : territoriesOwned) {
			if (t.getNumArmies() > 1)
				return true;
		}
		return false;
	}
	
	public void setDoneAttacking(boolean doneAttacking) {
		if (debug) System.out.println("setDoneAttacking called by "+name);
		this.doneAttacking = doneAttacking;
	}
	/**
	 * 
	 * @return
	 * set the attacking country
	 */
	public abstract Territory attackFrom();
		// TODO Chooses, from territories with two or more armies, which to attack with. 
	/**
	 * 
	 * @param attackFrom
	 * @return
	 * sets the territory that is being attacked
	 */
	public abstract Territory attackTo(Territory attackFrom);
		// TODO Chooses, from territories adjacent to the parameter, which to attack. 
	/**
	 * holds the attacking dice
	 * @return attackingDice
	 */
	public List<Dice> getAttackingDice() {
		if (debug) System.out.println("getAttackingDice is called by "+name);
		return attackingDice;
	}
	/**
	 * 
	 * @param attackingDice
	 * sets the attacking dice
	 */
	public void setAttackingDice(List<Dice> attackingDice) {
		if (debug) System.out.println("setAttackingDice called by "+name);
		this.attackingDice = attackingDice;
	}
	
	/**
	 * 
	 * @return defendingDice
	 * gets the defending dice
	 */
	public List<Dice> getDefendingDice() {
		if (debug) System.out.println("getDefendingDice called by "+name);
		return defendingDice;
	}
	/**
	 * 
	 * @param defendingDice
	 * sets the defending dice
	 */
	public void setDefendingDice(List<Dice> defendingDice) {
		if (debug) System.out.println("setDefendingDice called by "+name);
		this.defendingDice = defendingDice;
	}
	
	public void setAllTerritories(ArrayList<Territory> territories) {
		if (debug) System.out.println("setAllTerritories called by "+name);
		allTerritories = new ArrayList<Territory>();
		allTerritories = territories;
	}
	
	public ArrayList<Territory> getAllTerritories() {
		if (debug) System.out.println("getAllTerritories called by "+name);
		return allTerritories;
	}

}
