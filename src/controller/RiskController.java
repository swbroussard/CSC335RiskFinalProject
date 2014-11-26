package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import model.*;

/**
 * Controls the game of Risk, as the name suggests. Maintains a list of <code>Player</code>s and every possible <code>Territory</code>. Handles gameplay, including board setup and turns, until a winner is declared.  
 * @author Elizabeth Harris, Becca Simon
 *
 */
public class RiskController {
	boolean debug = false;
	private ArrayList<Territory> territories;
	private Territory alaska, alberta, centralAmerica, easternUS, greenland, northwest,
	ontario, quebec, westernUS, argentina, brazil, peru, venezuela, 
	greatBritain, iceland, northernEurope, scandinavia, southernEurope, 
	ukraine, westernEurope, congo, eastAfrica, egypt, madagascar, northAfrica,
	southAfrica, afghanistan, china, india, irkutsk, japan, kamchatka, 
	middleEast, mongolia, siam, siberia, ural, yakutsk, easternAustralia,
	indonesia, newGuinea, westernAustralia;
	private ArrayList<Player> players;
	private ArrayList<Card> deckOfCards;
	static Random rand;

	/**
	 * Constructs a new <code>RiskController</code> object from a given <code>ArrayList<Player></code>. 
	 * Calls helper methods to set up all possible territories, give each <code>Player</code> that list, 
	 * set up the deck of cards, have players claim territories and allocate armies, and handle gameplay
	 * to the end of the game. 
	 * @param myPlayers
	 */
	public RiskController(ArrayList<Player> myPlayers) {
		if (debug) System.out.println("New RiskController created");
		setPlayers(myPlayers);
		setUpTerritories();
		sendTerritoriesToPlayers();
		setUpDeck();
		populateBoard();
		playGame();
	}

	/**
	 * Constructor for the GUI
	 */
	public RiskController() {
		setUpTerritories();
	}

	/**
	 * Helper method for the first stage of gameplay. Allots each <code>Player</code> the appropriate number of armies, 
	 * and has them place armies on territories in turn. 
	 */
	public void populateBoard() {
		if (debug) System.out.println("populateBoard called");
		int temp;
		switch(players.size()) {
		case 2:
			temp = 40; break;
		case 3:
			temp = 35; break;
		case 4:
			temp = 30; break;
		case 5:
			temp = 25; break;
		case 6:
			temp = 20; break;
		default:
			temp = 0;
			System.out.println("Illegal number of players.\n"
					+ "Number of players must be between two and six inclusive.");
			break;
		}
		for (Player p : getPlayers()) {
			p.setNumArmies(temp);
		}
		//for testing purposes, set one player to have no extra armies.
		//players.get(players.size() - 1).setNumArmies(2);
		

		while (getPlayers().get(0).getNumArmies() > 0) {
			for (Player p : players)
				if(p.getNumArmies() > 0)
					p.placeArmy();
			// TODO: ITERATION 2 - for a human player, maybe we could add the option to place multiple territories at once
		} // all armies placed for all players
	}

	/**
	 * Helper method for the second (main) stage of gameplay. While there is more than one <code>Player</code>
	 * left in the game, has players take turns. A turn has three stages: First, the player is given armies
	 * based on how many territories he controls and places them on his territories. Second, the player attacks
	 * other players' territories in an attempt to conquer more territories. Third, when the player is finished
	 * attacking, he has the option to fortify his territories by moving armies from one to another. 
	 */
	public void playGame() {
		if (debug) System.out.println("playGame called");
		while (getPlayers().size() > 1) {
//		for(int i = 0; i < 15; i++) {
//		if (debug) System.out.println("Round " + i);
//			if(players.size() == 1) {
//				i = 15;
//			}
//			else {
				//would an enhanced for loop be an issue if a player gets eliminated before their turn
				//i think we would get a null pointer exception -Becca
				int counter = 0;
				while(counter < players.size()) {
					//for (Player p : getPlayers()) {
					Player p = players.get(counter);
					if (debug) System.out.println(p.getName()+"'s turn");
					p.addArmies();
					while (p.getNumArmies() > 0) {
						p.placeArmy();
					}
					while (p.canAttack()) {
						Territory attacker = p.attackFrom();
						while (attacker == null || attacker.getCurrentOwner() != p) {
							attacker = p.attackFrom();
						}
						Territory defender = p.attackTo(attacker);
						if (debug) System.out.println("Attacking " + defender.toString());
						//if (debug) System.out.println(territories);
						while (defender == null || defender.getCurrentOwner() == p) {
							defender = p.attackTo(attacker);
							if (debug) System.out.println("Attacking" + defender.toString());
						}
						
						attack(attacker, defender);
					} 
					p.setDoneAttacking(false);
					// TODO: ITERATION 2 - fortify if desired
					counter++;
				}
//			}
		}
		if(players.size() == 1) {
			if (debug) System.out.println(players.get(0).getName() + " won the game");
		}
		//return players.get(0);
	}

	/**
	 * Helper method for initial territory setup. Instantiates the 42 territories on a Risk world map, 
	 * assigning them the proper continent and neighbors. 
	 */
	private void setUpTerritories() {
		if (debug) System.out.println("setUpTerritories called");
		territories = new ArrayList<Territory>();

		alaska = new Territory("Alaska", Continent.NORTH_AMERICA, -9884371);
		alberta = new Territory("Alberta", Continent.NORTH_AMERICA, -2991525);
		centralAmerica = new Territory("Central America", Continent.NORTH_AMERICA, -7330782);
		easternUS = new Territory("Eastern United States", Continent.NORTH_AMERICA, -1753279);
		greenland = new Territory("Greenland", Continent.NORTH_AMERICA, -5427408);
		northwest = new Territory("Northwest Territory", Continent.NORTH_AMERICA, -1794915);
		ontario = new Territory("Ontario", Continent.NORTH_AMERICA, -4116688);
		quebec = new Territory("Quebec", Continent.NORTH_AMERICA, -4173227);
		westernUS = new Territory("Western United States", Continent.NORTH_AMERICA, -6470845);

		argentina = new Territory("Argentina", Continent.SOUTH_AMERICA, -602202);
		brazil = new Territory("Brazil", Continent.SOUTH_AMERICA, -1734364);
		peru = new Territory("Peru", Continent.SOUTH_AMERICA, -5282278);
		venezuela = new Territory("Venezuela", Continent.SOUTH_AMERICA, -1400494);

		greatBritain = new Territory("Great Britain", Continent.EUROPE, -10509133);
		iceland = new Territory("Iceland", Continent.EUROPE, -3411720);
		northernEurope = new Territory("Northern Europe", Continent.EUROPE, -10103052);
		scandinavia = new Territory("Scandinavia", Continent.EUROPE, -15186346);
		southernEurope = new Territory("Southern Europe", Continent.EUROPE, -14655374);
		ukraine = new Territory("Ukraine", Continent.EUROPE, -13857884);
		westernEurope = new Territory("Western Europe", Continent.EUROPE, -14431749);

		congo = new Territory("Congo", Continent.AFRICA, -6338922);
		eastAfrica = new Territory("East Africa", Continent.AFRICA, -1283872);
		egypt = new Territory("Egypt", Continent.AFRICA, -11525300);
		madagascar = new Territory("Madagascar", Continent.AFRICA, -3109438);
		northAfrica = new Territory("North Africa", Continent.AFRICA, -1331481);
		southAfrica = new Territory("South Africa", Continent.AFRICA, -9030543);

		afghanistan = new Territory("Afghanistan", Continent.ASIA, -8089504);
		china = new Territory("China", Continent.ASIA, -9791385);
		india = new Territory("India", Continent.ASIA, -6045093);
		irkutsk = new Territory("Irkutsk", Continent.ASIA, -6968177);
		japan = new Territory("Japan", Continent.ASIA, -3552384);
		kamchatka = new Territory("Kamchatka", Continent.ASIA, -7353461);
		middleEast = new Territory("Middle East", Continent.ASIA, -6968177);
		mongolia = new Territory("Mongolia", Continent.ASIA, -8876477);
		siam = new Territory("Siam", Continent.ASIA, -10988236);
		siberia = new Territory("Siberia", Continent.ASIA, -12756942);
		ural = new Territory("Ural", Continent.ASIA, -11181775);
		yakutsk = new Territory("Yakutsk", Continent.ASIA, -3614050);

		easternAustralia = new Territory("Eastern Australia", Continent.AUSTRALIA, -5632);
		indonesia = new Territory("Indonesia", Continent.AUSTRALIA, -147129);
		newGuinea = new Territory("New Guinea", Continent.AUSTRALIA, -4157144);
		westernAustralia = new Territory("Western Australia", Continent.AUSTRALIA, -1605);

		// adjacent for Alaska
		ArrayList<Territory> adjacent = new ArrayList<Territory>();
		adjacent.add(northwest);
		adjacent.add(alberta);
		adjacent.add(kamchatka);
		alaska.setAdjacent(adjacent);

		// adjacent for Alberta
		adjacent = new ArrayList<Territory>();
		adjacent.add(alaska);
		adjacent.add(northwest);
		adjacent.add(westernUS);
		adjacent.add(ontario);
		alberta.setAdjacent(adjacent);

		// adjacent for Central America
		adjacent = new ArrayList<Territory>();
		adjacent.add(westernUS);
		adjacent.add(easternUS);
		adjacent.add(venezuela);
		centralAmerica.setAdjacent(adjacent);

		// adjacent for Eastern United States
		adjacent = new ArrayList<Territory>();
		adjacent.add(quebec);
		adjacent.add(westernUS);
		adjacent.add(ontario);
		adjacent.add(centralAmerica);
		easternUS.setAdjacent(adjacent);

		// adjacent for Greenland
		adjacent = new ArrayList<Territory>();
		adjacent.add(northwest);
		adjacent.add(ontario);
		adjacent.add(quebec);
		adjacent.add(iceland);
		greenland.setAdjacent(adjacent);

		// adjacent for Northwest Territories
		adjacent = new ArrayList<Territory>();
		adjacent.add(alaska);
		adjacent.add(alberta);
		adjacent.add(ontario);
		adjacent.add(greenland);
		northwest.setAdjacent(adjacent);

		// adjacent for Ontario
		adjacent = new ArrayList<Territory>();
		adjacent.add(northwest);
		adjacent.add(alberta);
		adjacent.add(greenland);
		adjacent.add(westernUS);
		adjacent.add(easternUS);
		adjacent.add(quebec);
		ontario.setAdjacent(adjacent);

		// adjacent for Quebec
		adjacent = new ArrayList<Territory>();
		adjacent.add(greenland);
		adjacent.add(easternUS);
		adjacent.add(ontario);
		quebec.setAdjacent(adjacent);

		// adjacent for Western United States
		adjacent = new ArrayList<Territory>();
		adjacent.add(alberta);
		adjacent.add(ontario);
		adjacent.add(easternUS);
		adjacent.add(centralAmerica);
		westernUS.setAdjacent(adjacent);

		// adjacent for Argentina
		adjacent = new ArrayList<Territory>();
		adjacent.add(peru);
		adjacent.add(brazil);
		argentina.setAdjacent(adjacent);

		// adjacent for Brazil
		adjacent = new ArrayList<Territory>();
		adjacent.add(venezuela);
		adjacent.add(peru);
		adjacent.add(argentina);
		adjacent.add(northAfrica);
		brazil.setAdjacent(adjacent);

		// adjacent for Peru
		adjacent = new ArrayList<Territory>();
		adjacent.add(venezuela);
		adjacent.add(brazil);
		adjacent.add(argentina);
		peru.setAdjacent(adjacent);

		// adjacent for Venezuela
		adjacent = new ArrayList<Territory>();
		adjacent.add(centralAmerica);
		adjacent.add(peru);
		adjacent.add(brazil);
		venezuela.setAdjacent(adjacent);

		// adjacent for Great Britain
		adjacent = new ArrayList<Territory>();
		adjacent.add(iceland);
		adjacent.add(scandinavia);
		adjacent.add(northernEurope);
		adjacent.add(westernEurope);
		greatBritain.setAdjacent(adjacent);

		// adjacent for Iceland
		adjacent = new ArrayList<Territory>();
		adjacent.add(greenland);
		adjacent.add(scandinavia);
		adjacent.add(greatBritain);
		iceland.setAdjacent(adjacent);

		// adjacent for Northern Europe
		adjacent = new ArrayList<Territory>();
		adjacent.add(greatBritain);
		adjacent.add(scandinavia);
		adjacent.add(westernEurope);
		adjacent.add(southernEurope);
		adjacent.add(ukraine);
		northernEurope.setAdjacent(adjacent);

		// adjacent for Scandinavia
		adjacent = new ArrayList<Territory>();
		adjacent.add(iceland);
		adjacent.add(greatBritain);
		adjacent.add(northernEurope);
		adjacent.add(ukraine);
		scandinavia.setAdjacent(adjacent);

		// adjacent for Southern Europe
		adjacent = new ArrayList<Territory>();
		adjacent.add(westernEurope);
		adjacent.add(northernEurope);
		adjacent.add(egypt);
		adjacent.add(northAfrica);
		adjacent.add(middleEast);
		adjacent.add(ukraine);
		southernEurope.setAdjacent(adjacent);

		// adjacent for Ukraine
		adjacent = new ArrayList<Territory>();
		adjacent.add(scandinavia);
		adjacent.add(northernEurope);
		adjacent.add(southernEurope);
		adjacent.add(middleEast);
		adjacent.add(afghanistan);
		adjacent.add(ural);
		ukraine.setAdjacent(adjacent);

		// adjacent for Western Europe
		adjacent = new ArrayList<Territory>();
		adjacent.add(greatBritain);
		adjacent.add(northernEurope);
		adjacent.add(southernEurope);
		adjacent.add(northAfrica);
		westernEurope.setAdjacent(adjacent);

		// adjacent for Congo
		adjacent = new ArrayList<Territory>();
		adjacent.add(northAfrica);
		adjacent.add(eastAfrica);
		adjacent.add(southAfrica);
		congo.setAdjacent(adjacent);

		// adjacent for East Africa
		adjacent = new ArrayList<Territory>();
		adjacent.add(middleEast);
		adjacent.add(northAfrica);
		adjacent.add(congo);
		adjacent.add(southAfrica);
		adjacent.add(madagascar);
		eastAfrica.setAdjacent(adjacent);

		// adjacent for Egypt
		adjacent = new ArrayList<Territory>();
		adjacent.add(middleEast);
		adjacent.add(southernEurope);
		adjacent.add(northAfrica);
		adjacent.add(eastAfrica);
		egypt.setAdjacent(adjacent);

		// adjacent for Madagascar
		adjacent = new ArrayList<Territory>();
		adjacent.add(eastAfrica);
		adjacent.add(southAfrica);
		madagascar.setAdjacent(adjacent);

		// adjacent for North Africa
		adjacent = new ArrayList<Territory>();
		adjacent.add(westernEurope);
		adjacent.add(southernEurope);
		adjacent.add(brazil);
		adjacent.add(egypt);
		adjacent.add(eastAfrica);
		adjacent.add(congo);
		northAfrica.setAdjacent(adjacent);

		// adjacent for South Africa
		adjacent = new ArrayList<Territory>();
		adjacent.add(congo);
		adjacent.add(eastAfrica);
		adjacent.add(madagascar);
		southAfrica.setAdjacent(adjacent);

		// adjacent for Afghanistan
		adjacent = new ArrayList<Territory>();
		adjacent.add(ukraine);
		adjacent.add(ural);
		adjacent.add(middleEast);
		adjacent.add(india);
		adjacent.add(china);
		afghanistan.setAdjacent(adjacent);

		// adjacent for China
		adjacent = new ArrayList<Territory>();
		adjacent.add(mongolia);
		adjacent.add(siberia);
		adjacent.add(ural);
		adjacent.add(afghanistan);
		adjacent.add(india);
		adjacent.add(siam);
		china.setAdjacent(adjacent);

		// adjacent for India
		adjacent = new ArrayList<Territory>();
		adjacent.add(middleEast);
		adjacent.add(afghanistan);
		adjacent.add(china);
		adjacent.add(siam);
		india.setAdjacent(adjacent);

		// adjacent for Irkutsk
		adjacent = new ArrayList<Territory>();
		adjacent.add(kamchatka);
		adjacent.add(yakutsk);
		adjacent.add(siberia);
		adjacent.add(mongolia);
		irkutsk.setAdjacent(adjacent);

		// adjacent for Japan
		adjacent = new ArrayList<Territory>();
		adjacent.add(kamchatka);
		adjacent.add(mongolia);
		japan.setAdjacent(adjacent);

		// adjacent for Kamchatka
		adjacent = new ArrayList<Territory>();
		adjacent.add(yakutsk);
		adjacent.add(irkutsk);
		adjacent.add(mongolia);
		adjacent.add(japan);
		adjacent.add(alaska);
		kamchatka.setAdjacent(adjacent);

		// adjacent for Middle East
		adjacent = new ArrayList<Territory>();
		adjacent.add(ukraine);
		adjacent.add(southernEurope);
		adjacent.add(egypt);
		adjacent.add(eastAfrica);
		adjacent.add(afghanistan);
		adjacent.add(india);
		middleEast.setAdjacent(adjacent);

		// adjacent for Mongolia
		adjacent = new ArrayList<Territory>();
		adjacent.add(kamchatka);
		adjacent.add(irkutsk);
		adjacent.add(japan);
		adjacent.add(siberia);
		adjacent.add(china);
		mongolia.setAdjacent(adjacent);

		// adjacent for Siam
		adjacent = new ArrayList<Territory>();
		adjacent.add(china);
		adjacent.add(india);
		adjacent.add(indonesia);
		adjacent.add(newGuinea);
		siam.setAdjacent(adjacent);

		// adjacent for Siberia
		adjacent = new ArrayList<Territory>();
		adjacent.add(ural);
		adjacent.add(china);
		adjacent.add(mongolia);
		adjacent.add(irkutsk);
		adjacent.add(yakutsk);
		siberia.setAdjacent(adjacent);

		// adjacent for Ural
		adjacent = new ArrayList<Territory>();
		adjacent.add(ukraine);
		adjacent.add(afghanistan);
		adjacent.add(siberia);
		adjacent.add(china);
		ural.setAdjacent(adjacent);

		// adjacent for Yakutsk
		adjacent = new ArrayList<Territory>();
		adjacent.add(siberia);
		adjacent.add(irkutsk);
		adjacent.add(kamchatka);
		yakutsk.setAdjacent(adjacent);

		// adjacent for Eastern Australia
		adjacent = new ArrayList<Territory>();
		adjacent.add(newGuinea);
		adjacent.add(westernAustralia);
		easternAustralia.setAdjacent(adjacent);

		// adjacent for Indonesia
		adjacent = new ArrayList<Territory>();
		adjacent.add(siam);
		adjacent.add(newGuinea);
		adjacent.add(westernAustralia);
		indonesia.setAdjacent(adjacent);

		// adjacent for New Guinea
		adjacent = new ArrayList<Territory>();
		adjacent.add(indonesia);
		adjacent.add(westernAustralia);
		adjacent.add(easternAustralia);
		newGuinea.setAdjacent(adjacent);

		// adjacent for Western Australia
		adjacent = new ArrayList<Territory>();
		adjacent.add(indonesia);
		adjacent.add(easternAustralia);
		adjacent.add(newGuinea);
		westernAustralia.setAdjacent(adjacent);

		territories.add(alaska);
		territories.add(alberta);
		territories.add(centralAmerica);
		territories.add(easternUS);
		territories.add(greenland);
		territories.add(northwest);
		territories.add(ontario);
		territories.add(quebec);
		territories.add(westernUS);
		territories.add(argentina);
		territories.add(brazil);
		territories.add(peru);
		territories.add(venezuela);
		territories.add(greatBritain);
		territories.add(iceland);
		territories.add(northernEurope);
		territories.add(scandinavia);
		territories.add(southernEurope);
		territories.add(ukraine);
		territories.add(westernEurope);
		territories.add(congo);
		territories.add(eastAfrica);
		territories.add(egypt);
		territories.add(madagascar);
		territories.add(northAfrica);
		territories.add(southAfrica);
		territories.add(afghanistan);
		territories.add(china);
		territories.add(india);
		territories.add(irkutsk);
		territories.add(japan);
		territories.add(kamchatka);
		territories.add(middleEast);
		territories.add(mongolia);
		territories.add(siam);
		territories.add(siberia);
		territories.add(ural);
		territories.add(yakutsk);
		territories.add(easternAustralia);
		territories.add(indonesia);
		territories.add(newGuinea);
		territories.add(westernAustralia);
	}

	/**
	 * Helper method for initial deck setup. Instantiates the cards in the deck and adds them to the deck. 
	 */
	private void setUpDeck() {
		if (debug) System.out.println("setUpDeck called");
		// TODO: ITERATION 2 - hard code deck of cards
		deckOfCards = new ArrayList<Card>();
		deckOfCards.add(new Card());
	}

	/**
	 * Simulates a dice roll by returning a pseudorandom integer between 1 and 6 inclusive. 
	 * @return int
	 */
	private int rollDice() {
		rand = new Random();
		return rand.nextInt(5) + 1;
	}

	/**
	 * Helper method to handle attacks, called by <code>playGame</code>. Based on the number of armies
	 * in the attacking and defending territory, simulates the appropriate dice rolls, then moves armies
	 * and reassigns territory ownership depending on the outcome of the rolls. 
	 * @param attackingTerritory
	 * @param defendingTerritory
	 */
	private void attack(Territory attackingTerritory, Territory defendingTerritory){
		if (debug) System.out.println("Attacker - "+attackingTerritory.getName()
				+" (owner - "+attackingTerritory.getCurrentOwner().getName()+")"
				+"\nDefender - "+defendingTerritory.getName()+" (owner - "
				+defendingTerritory.getCurrentOwner().getName()+")");

		//		List<Dice> attackingDice = attackingTerritory.getCurrentOwner().getAttackingDice();
		//		List<Dice> defendingDice = defendingTerritory.getCurrentOwner().getDefendingDice();
		ArrayList<Integer> attackingDiceValues = new ArrayList<Integer>();
		ArrayList<Integer> defendingDiceValues = new ArrayList<Integer>();

		int attackingNumDice;
		int defendingNumDice;
		if(attackingTerritory.getNumArmies() == 2) 
			attackingNumDice = 1;
		else if(attackingTerritory.getNumArmies() == 3)
			attackingNumDice = 2;
		else
			attackingNumDice = 3;

		if(defendingTerritory.getNumArmies() == 1)
			defendingNumDice = 1;
		else
			defendingNumDice = 2;

		for (int i = 0; i < attackingNumDice; i++) {
			attackingDiceValues.add(rollDice());
		}
		for (int i = 0; i < defendingNumDice; i++) {
			defendingDiceValues.add(rollDice());
		}

		Collections.sort(attackingDiceValues);
		Collections.reverse(attackingDiceValues);
		Collections.sort(defendingDiceValues);
		Collections.reverse(defendingDiceValues);

		if (debug) System.out.println("Attacking: " + attackingDiceValues + "\t" + "Defending: " + defendingDiceValues);

		int attackerWon = 0;
		int defenderWon = 0;

		for (int i = 0; i < 4; i++) {
			if(attackingDiceValues.size() <= i || defendingDiceValues.size() <= i) {

			}
			else {
				if (attackingDiceValues.get(i) > defendingDiceValues.get(i)) {
					attackerWon++;
				}
				else { // defender wins ties
					defenderWon++;
				}
			}
		}
		attackingTerritory.setNumArmies(attackingTerritory.getNumArmies() - defenderWon);
		defendingTerritory.setNumArmies(defendingTerritory.getNumArmies() - attackerWon);

		if (defendingTerritory.getNumArmies() <= 0) { // attacker conquered defending territory
			
			if (defendingTerritory.getCurrentOwner().getTerritoriesOwned().size() == 1) {
				// attacker conquered defender's last territory
				if (debug) System.out.println(defendingTerritory.getCurrentOwner().getName() + " has been eliminated");
				players.remove(defendingTerritory.getCurrentOwner());
			}
			
			if (debug) System.out.println(defendingTerritory.getName() + " now belongs to the attacker");
			// defending territory now belongs to attacker
			defendingTerritory.getCurrentOwner().getTerritoriesOwned().remove(defendingTerritory);
			defendingTerritory.setCurrentOwner(attackingTerritory.getCurrentOwner());
			attackingTerritory.getCurrentOwner().getTerritoriesOwned().add(defendingTerritory);
			// same number of armies as dice rolled move to conquered territory
			// TODO: ITERATION 2 - can choose the number of armies to move (>= number of dice rolled)
			defendingTerritory.setNumArmies(attackingDiceValues.size());
			attackingTerritory.setNumArmies(attackingTerritory.getNumArmies() - attackingDiceValues.size());
		}
		if (debug) System.out.println(territories + "\n");
	}

	/**
	 * Getter for <code>territories</code>
	 * @return territories
	 */
	public ArrayList<Territory> getTerritories() {
		return territories;
	}

	/**
	 * Getter for <code>players</code>
	 * @return players
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * Setter for <code>players</code>
	 * @param players
	 */
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	/**
	 * Helper method for game setup. Gives each player access to the list of all territories on the Risk world map. 
	 */
	public void sendTerritoriesToPlayers() {
		for(Player p: players) {
			p.setAllTerritories(territories);
		}
	}
}
