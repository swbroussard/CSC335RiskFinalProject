package model;

import java.util.Random;

/**
 * Easiest AI to beat
 * Makes decisions based on random generator.
 * 
 * @author jeremyjalnos stevenBroussard
 */
public class SimpleAIPlayer extends Player {

	// instance variables
	Random randomGen;
	Random randomAdjacent;

	// constructor
	public SimpleAIPlayer() {
		super();
		//randomGen = new Random(1); // static state from the random generator
		randomGen = new Random(); //random generator
		randomAdjacent = new Random(); //removed the one in the Random argument -steven

	}

	// constructor with name
		public SimpleAIPlayer(String name) {
			super(name);
			//randomGen = new Random(1); // static state from the random generator
			randomGen = new Random(); //random generator
			randomAdjacent = new Random(); //removed the one in the Random argument -steven

		}
		


	/**
	 * Randomly chooses a territory with two or more armies to attack from
	 */
	@Override
	public Territory attackFrom() {
		int choosenTerritory = 0;
		choosenTerritory = randomGen.nextInt(getTerritoriesOwned().size());
		return getTerritoriesOwned().get(choosenTerritory);
	}

	/**
	 * @param attackFrom
	 * 
	 * Randomly chooses a territory adjacent to the parameter, owned by another player, to attack
	 * 
	 * @return a territory object
	 */
	@Override
	public Territory attackTo(Territory attackFrom) {
		int choosenAttack = 0;
		choosenAttack = randomGen.nextInt(attackFrom().getAdjacent().size());
		return attackFrom().getAdjacent().get(choosenAttack); // Because Steven
																// is a boss
																// -Jeremy

	}

	/**
	 * places armies for the beginning of game, one soldier at a time in a territory at a time
	 */
	@Override
	public void placeArmy() {
		// Randomly chooses a territory and places one army there at the
		// beginning of the game--Jeremy & Steven
		//TODO:  getAllTerritories has the arrayList of all of the territories, will not shrink
		//This method should only place one army because the controller is handling rotating through players
		
		
		int x = 0;
		while (x < getAllTerritories().size()) {
			if (getAllTerritories().get(x).getCurrentOwner() == null) {
				getAllTerritories().get(x).setCurrentOwner(this);
				getAllTerritories().get(x).setNumArmies(1);
				break;
			} else {
				x++;
				
				}
			}
		}

	

	/**
	 * @param takeArmy
	 * @param reinforceThis
	 * 
	 */
	@Override
	public void reinforceArmies(Territory takeArmy, Territory reinforceThis) {
		int takeArmyFrom = 0;

		takeArmyFrom = randomGen.nextInt(takeArmy.getNumArmies() - 1);
		reinforceThis.setNumArmies(getNumArmies() + takeArmyFrom);
		takeArmy.setNumArmies(getNumArmies() - takeArmyFrom);

	}

}