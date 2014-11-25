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
		if (debug) System.out.println("New SimpleAIPlayer created: "+name);
		//randomGen = new Random(1); // static state from the random generator
		randomGen = new Random(); //random generator
		randomAdjacent = new Random(); //removed the one in the Random argument -steven

	}



	/**
	 * Randomly chooses a territory with two or more armies to attack from
	 */
	@Override
	public Territory attackFrom() {
		if (debug) System.out.println("attackFrom called by "+getName());
		Territory choosenTerritory = null;
		while(choosenTerritory == null){
			int r = randomGen.nextInt(getTerritoriesOwned().size());
			if(getTerritoriesOwned().get(r).getNumArmies() > 1) 
				choosenTerritory = getTerritoriesOwned().get(r);
		}
		return choosenTerritory;
	}

	/**
	 * @param attackFrom
	 * 
	 * Randomly chooses a territory adjacent to the parameter, owned by another player, to attack
	 * 
	 * @return a territory object
	 */
	@Override
	public Territory attackTo(Territory attacker) {
		if (debug) System.out.println("attackTo called by "+getName());
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
	 * places armies for the beginning of game, one soldier at a time in a territory at a time
	 * -Becca: this method should also reinforce armies in the initial setup (you get more armies than you have territories)
	 */
	@Override
	public void placeArmy() {
		if (debug) System.out.println("placeArmy called by "+getName());
		// Randomly chooses a territory and places one army there at the
		// beginning of the game--Jeremy & Steven
		//TODO:  getAllTerritories has the arrayList of all of the territories, will not shrink
		//This method should only place one army because the controller is handling rotating through players


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
		// TODO !! getAllTerritories, I think, may be the source of the problem. How can we get that functionality here? 
		// Might have to do the random selection in RiskController because Player doesn't know the list of all territories.   
		
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
					if (debug) System.out.println("Army successfully placed in new territory by "+getName());
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
			if (debug) System.out.println("Army successfully placed in owned territory by "+getName());
		}


	}



	/**
	 * @param takeArmy
	 * @param reinforceThis
	 * 
	 */
	@Override
	public void reinforceArmies(Territory takeArmy, Territory reinforceThis) {
		if (debug) System.out.println("reinforceArmies called by "+getName());
		int takeArmyFrom = 0;

		takeArmyFrom = randomGen.nextInt(takeArmy.getNumArmies() - 1);
		reinforceThis.setNumArmies(getNumArmies() + takeArmyFrom);
		takeArmy.setNumArmies(getNumArmies() - takeArmyFrom);

	}

}