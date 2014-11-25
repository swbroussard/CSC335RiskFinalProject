package model;

import java.util.Random;

/**
 * Simple AI that makes decisions based on a random generator.  Easiest AI to beat
 * 
 * @author jeremyjalnos stevenBroussard
 */
public class SimpleAIPlayer extends Player {
	Random randomGen;
	Random randomAdjacent; //TODO: this isnt used at all, why do we need it?

	// constructor
	public SimpleAIPlayer() {
		super();
		randomGen = new Random(); //random generator
		randomAdjacent = new Random(); //removed the one in the Random argument -steven
	}

	// constructor with name
	public SimpleAIPlayer(String name) {
		super(name);
		randomGen = new Random(); 
		randomAdjacent = new Random(); 
	}

	/**
	 * Randomly chooses a territory that the player owns to attack from.  The territory
	 * must have at least two armies.
	 * 
	 * @return The territory that the player is attacking from.
	 */
	@Override
	public Territory attackFrom() {
		Territory choosenTerritory = null;
		while(choosenTerritory == null){
			int r = randomGen.nextInt(getTerritoriesOwned().size());
			if(getTerritoriesOwned().get(r).getNumArmies() > 1) 
				choosenTerritory = getTerritoriesOwned().get(r);
		}
		return choosenTerritory;
	}

	/**
	 * @param attacker The territory that the player is attacking from.
	 * 
	 * Randomly chooses a territory to attack.  The territory to attack must be 
	 * adjacent to the parameter and owned by a different player
	 * 
	 * @return The territory that that the player is attacking
	 */
	@Override
	public Territory attackTo(Territory attacker) {
		Territory choosenTerritory = null;
		while(choosenTerritory == null) {
			int r = randomGen.nextInt(attacker.getAdjacent().size());
			if(attacker.getAdjacent().get(r).getCurrentOwner() != this) {
				choosenTerritory = attacker.getAdjacent().get(r);
			}
		}
		return choosenTerritory; 
	}

	/**
	 * At the beginning of the game, randomly chooses empty territories to place an army in.
	 * Each time this method is called, the player is placing one territory.
	 * After all territories have one army on it, this randomly places an army in a territory
	 * that is currently owned. 
	 */
	@Override
	public void placeArmy() {
		//		int x = 0;
		//		while (x < getAllTerritories().size()) {
		//			if (getAllTerritories().get(x).getCurrentOwner() == null) {
		//				getAllTerritories().get(x).setCurrentOwner(this);
		//				getAllTerritories().get(x).setNumArmies(1);
		//				break;
		//			} else {
		//				x++;
		//				
		//				}
		//			}
		//		

		//rewriting because previous is not randomized. - Becca
		//first check to determine if the territories are all owned.  if they are not then select a territory
		boolean allSelected = true;
		for(Territory t: getAllTerritories()) {
			if(t.getCurrentOwner() == null) {
				allSelected = false;
			}
		}

		//select a territory if one is available.
		if(allSelected == false) {
			int r = randomGen.nextInt(42);
			boolean territorySelected = false;
			while(!territorySelected) {
				Territory selected = getAllTerritories().get(r);
				if(selected.getCurrentOwner() == null) {
					selected.setCurrentOwner(this);
					selected.setNumArmies(1);
					territorySelected = true;
					addTerritory(selected);
					setNumArmies(getNumArmies() - 1);
				}
				else
					r = randomGen.nextInt(42);
			}
		}

		//if territory is not available, add an army to one you own.
		else{
			int r = randomGen.nextInt(getTerritoriesOwned().size());
			getTerritoriesOwned().get(r).setNumArmies(getNumArmies() + 1);
			setNumArmies(getNumArmies() - 1);
		}


	}



	/**
	 * Not used in iteration 1
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