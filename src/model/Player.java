package model;

import java.awt.Color;
import java.util.ArrayList;

import model.Card.CardType;
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
	private boolean doneAttacking;
	private Color color;

	//abstract methods to implement in subclasses

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
	public Player() {
		if (debug) System.out.println("player constructor (no arguments) is called");
		
		territoriesOwned = new ArrayList<Territory>();
		setCards(new ArrayList<Card>());
	}

	/**
	 * Constructor for the player that initializes the array and sets the name of
	 * the Player.  
	 * @param name the name of the player being initialized
	 */
	public Player(String name) {
		if (debug) System.out.println("player constructor (String) is called");
		this.name = name;
		
		territoriesOwned = new ArrayList<Territory>();
		setCards(new ArrayList<Card>());
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
	 * If the player turns in a set of cards, they get a bonus number of armies, 
	 * and if the player owns the territory on the face of one of the cards being
	 * turned in, they get an additional two armies for that territory. 
	 */

	public boolean addArmies(int cardBonus) {
		if (debug) System.out.println("addArmies is called by "+name);
		// minimum armies
		if(territoriesOwned.size() / 3 <= 3 ) {
			numArmies += 3;
		}
		else
			numArmies += (territoriesOwned.size() / 3);

		// continent bonus
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
		
		// card bonus
		if (canTurnInCards()) {
			numArmies += cardBonus;
			return true;
		}
		return false;
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
		if (debug) System.out.println("canAttack is called");
		if(doneAttacking)
			return false;
		for (Territory t : territoriesOwned) {
			if (t.getNumArmies() > 1) {
				for(Territory a: t.getAdjacent()) {
					if(a.getCurrentOwner() != this)
						return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Determines if the player can turn in a set of three cards, and if possible, 
	 * turns in a set of cards. A set of cards consists of three of a kind (cannon,
	 * horseman, or foot soldier), one of each kind, or a wild card plus any two. 
	 * @return true if the player can turn in a set of cards, false if they cannot. 
	 */
	public boolean canTurnInCards() {
		int cannon = 0, horseman = 0, footSoldier = 0, wild = 0;
		int cardsRemoved = 0; 
		for (Card c : cards) {
			switch (c.getCardType()) {
				case CANNON: cannon++; break;
				case HORSEMAN: horseman++; break;
				case FOOT_SOLDIER: footSoldier++; break;
				case WILD: wild++; break;
			}
		}

		if (cannon == 3) {
			for (Card c : cards) {
				if (c.getCardType() == CardType.CANNON) {
					if (cardsRemoved == 3)
						break;
					removeCard(c);
					cardsRemoved++;
				}
			}
			return true;
		}
		
		else if (horseman == 3) {
			for (Card c : cards) {
				if (c.getCardType() == CardType.HORSEMAN) {
					if (cardsRemoved == 3)
						break;
					removeCard(c);
					cardsRemoved++;
				}
			}
			return true;
		}
		
		else if (footSoldier == 3) {
			for (Card c : cards) {
				if (c.getCardType() == CardType.FOOT_SOLDIER) {
					if (cardsRemoved == 3)
						break;
					removeCard(c);
					cardsRemoved++;
				}
			}
			return true;
		}
		
		else if (cannon >= 1 && horseman >= 1 && footSoldier >= 1) {
			int cannonsRemoved = 0, horsemenRemoved = 0, footSoldiersRemoved = 0;
			for (Card c : cards) {
				if (cannonsRemoved < 1 && c.getCardType() == CardType.CANNON) {
					removeCard(c);
					cannonsRemoved++;
				}
				else if (horsemenRemoved < 1 && c.getCardType() == CardType.HORSEMAN) {
					removeCard(c);
					horsemenRemoved++;
				}
				else if (footSoldiersRemoved < 1 && c.getCardType() == CardType.FOOT_SOLDIER) {
					removeCard(c);
					footSoldiersRemoved++;
				}
			}
			return true;
		}
		
		else if (wild >= 1) {
			int wildCardsRemoved = 0;
			for (Card c : cards) {
				if (cardsRemoved == 2 && wildCardsRemoved == 1)
					break;
				if (cardsRemoved < 2 && c.getCardType() != CardType.WILD) {
					removeCard(c);
					cardsRemoved++;
				}
				else if (wildCardsRemoved < 1 && c.getCardType() == CardType.WILD) {
					removeCard(c);
					wildCardsRemoved++;
				} 
			}
			return true;
		}
		
		return false;
	}
	
	/**
	 * Helper method for canTurnInCards. Removes a card from the player's hand and
	 * puts it back in the deck of cards. Checks whether the player owns the territory
	 * on the card's face, and adds two armies to that territory if so. 
	 * @param c the Card to be removed
	 */
	private void removeCard(Card c) {
		for (Territory t : territoriesOwned) {
			if (t.equals(c.getCardTerritory()))
				t.setNumArmies(t.getNumArmies() + 2);
		}
		cards.remove(c);
		c.setInDeck(true);
	}

	/**
	 * Represents a player drawing a card from the deck. Takes the card passed by
	 * the deck, adds it to the player's hand, and removes it from the deck. 
	 * @param c
	 */
	public void issueCard(Card c) {
		cards.add(c);
		c.setInDeck(false);
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
	
	public void setColor(Color labelColor) {
		color = labelColor;
	}
	
	/**
	 * @param cards
	 */
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
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
	 * instantiates a new ArrayList of Territories and copys the parameter
	 * list to the instance variable
	 * @param territories all the territories used in the game
	 */
	public void setAllTerritories(ArrayList<Territory> territories) {
		if (debug) System.out.println("setAllTerritories called by "+name);
		allTerritories = new ArrayList<Territory>();
		allTerritories = territories;
	}
	
	/**
	 * @return the list of all territories in the game
	 */
	public ArrayList<Territory> getAllTerritories() {
		if (debug) System.out.println("getAllTerritories called by "+name);
		return allTerritories;
	}
	
	public Color getColor() {
		return color;
	}

	/**
	 * @return the player's hand of cards
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}
	
}
