package model;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Jeremy Jalnos, Steven Broussard, Becca Simon
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
	// TODO If you get the chance, please go through and delete comments and methods we don't need anymore 

	/** 
	 * During the start of the game, choose a territory in which to claim, then
	 * place remaining armies.  During turns, place armies until all armies for
	 * that turn are placed. Each time you place only one army.
	 * 
	 */
	public abstract void placeArmy();

	/**
	 * For iteration 2, at the end of turn, player is allowed to reinforce by 
	 * pulling armies from one country and placing them in another country
	 * @param takeArmy the territory selected to take armies from
	 * @param reinforceThis the territory selected to move the armies too. 
	 */
	public abstract void reinforceArmies(Territory takeArmy, Territory reinforceThis);

	/**
	 * determines the territory to attack from during a turn, territory must have at
	 * least 2 armies
	 * @return the territory to attack from
	 * 
	 */
	public abstract Territory attackFrom();

	/**
	 * Determines the territory that is being attacked by finding an adjacent territory
	 * that is owned by another player.
	 * @param attackFrom the territory that is attacking
	 * @return the territory that is being attacked
	 * 
	 */
	public abstract Territory attackTo(Territory attackFrom);

	/**
	 * Default constructor for the player that initializes the territories owned, 
	 * cards and the dice
	 */
	//TODO: Dice - I think we should take the dice out of the player. 
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
	 * Constructor for the player that initializes the array and sets the name of
	 * the Player.  
	 * @param name the name of the player being initialized
	 */
	//TODO: Dice - I think we should take the dice out of the player. 
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
	 * At the beginning of the turn determines the number of armies that the
	 * player receives to reinforce territories. Adds this number to the numArmies
	 * variable.  By the rulebook, a player is entitled to a minimum of three 
	 * armies even if he owns fewer than nine territories. 
	 * If the player owns an entire continent, then they get a bonus depending on
	 * which continent it is.  Continent bonuses: Asia (7 armies), 
	 * North America (5 armies), Europe (5 armies), Africa (3 armies),
	 * Australia (2 armies) and South America (2 armies).
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
	 * Adds a territory to the list of territories owned. 
	 * @param toAdd the territory to be added
	 */
	public void addTerritory(Territory toAdd) {
		if (debug) System.out.println("addTerritory is called by "+name);
		territoriesOwned.add(toAdd);
	}

	/**
	 * Determines if the player is able to attack.  In order to attack, the player
	 * must have a territory with at least two armies and has an adjacent territory
	 * that is owned by another player. The player also has the option of setting
	 * the variable doneAttacking to true to indicate they are done attacking and
	 * want to end their turn
	 * @return true if the player is able to attack, false if the player is unable
	 * to attack or is done with their turn.
	 */

	public boolean canAttack() {
		System.out.println("canAttack is called");
		if(doneAttacking)
			return false;
		for (Territory t : territoriesOwned) {
			if (t.getNumArmies() > 1)
				return true;
		}
		return false;
	}

	//set methods
	/**
	 * set the name of the player
	 * @param name the name of the player
	 */
	public void setName(String name) {
		if (debug) System.out.println("setName is called by "+name+" (pre-change name)");
		this.name = name;
	}

	/**
	 * set number of armies the player has to place
	 * @param numArmies the number of armies that the player has to place
	 */
	public void setNumArmies(int numArmies) {
		this.numArmies = numArmies;
		if (debug) System.out.println("set armies: " + numArmies+" called by "+name); 
	}

	//get methods
	/**
	 * Returns the players name
	 * @return the name of the player
	 * 
	 */
	public String getName() {
		if (debug) System.out.println("Get name has been called by"+name);
		return name;
	}
	/**
	 * returns the number of armies the player has that they have not placed
	 * @return the number of armies the player has to place
	 */
	public int getNumArmies() {
		if (debug) System.out.println("getNumArmies is called by "+name);
		return numArmies;
	}

	/**
	 * Gets the list of territories and returns it 
	 * @return the arrayList of territories owned by the player
	 */
	public ArrayList<Territory> getTerritoriesOwned() {
		if (debug) System.out.println("getTerritoryOwned is called by "+name);
		return territoriesOwned;
	}
	
	/**
	 * sets the value of the doneAttacking instance variable to the parameter
	 * @param doneAttacking true if the player is ending their turn, false if the 
	 * player is still playing.
	 */
	
	public void setDoneAttacking(boolean doneAttacking) {
		if (debug) System.out.println("setDoneAttacking called by "+name);
		this.doneAttacking = doneAttacking;
	}

	//TODO: Dice - do we need these methods?
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
	

	/**
	 * instantiates a new ArrayList of Territories and copys the parameter
	 * list to the instance variable
	 * @param territories all the territories used in the game
	 */
	
	public void setAllTerritories(ArrayList<Territory> territories) {
		if (debug) System.out.println("setAllTerritories called by "+name);
		allTerritories = new ArrayList<Territory>();
		allTerritories = territories;
	}
	
	public ArrayList<Territory> getAllTerritories() {
		if (debug) System.out.println("getAllTerritories called by "+name);
		return allTerritories;
	}


	/**
	 * used to roll dice for the purpose of attacking territories
	 */
	//TODO: Dice - is this necessary, move dice to controller?
	public void rollDice(){
		System.out.println("rollDice is called by "+name);
		for(int i = 0; i < getAttackingDice().size(); i++){
			getAttackingDice().get(i).rollDice();
		}
		for(int i = 0; i < getAttackingDice().size(); i++){
			getAttackingDice().get(i).rollDice();
		}
	}

	//TODO: create the search algorithm - what is this?




}
