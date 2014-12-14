package model;

import java.util.Random;

/**
 * Simple AI that makes decisions based on a random generator.  Easiest AI to beat
 * 
 * @author jeremyjalnos stevenBroussard
 */
public class SimpleAIPlayer extends Player {
	private Random randomGen;
	private int numAttacks;

	// constructor
	public SimpleAIPlayer() {
		super();
		randomGen = new Random(); //random generator
	}

	// constructor with name
	public SimpleAIPlayer(String name) {
		super(name);
		randomGen = new Random(); 
		if (debug) System.out.println("New SimpleAIPlayer created: "+name);
	}

	/**
	 * Randomly chooses a territory that the player owns to attack from.  The territory
	 * must have at least two armies.
	 * 
	 * @return The territory that the player is attacking from.
	 */
	@Override
	public Territory attackFrom() {
		if (debug) System.out.println("attackFrom called by "+getName());
		Territory choosenTerritory = null;
		while(choosenTerritory == null){
			int r = randomGen.nextInt(getTerritoriesOwned().size());
			Territory temp = getTerritoriesOwned().get(r);
			if(temp.getNumArmies() > 1) {
				for(Territory t: temp.getAdjacent()) {
					if(t.getCurrentOwner() != this) {
						choosenTerritory = temp; break;
					}
				}
			}
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
		if (debug) System.out.println("attackTo called by "+getName());
		Territory choosenTerritory = null;
		while(choosenTerritory == null) {
			int r = randomGen.nextInt(attacker.getAdjacent().size());
			if(attacker.getAdjacent().get(r).getCurrentOwner() != this) {
				choosenTerritory = attacker.getAdjacent().get(r);
			}
		}
		numAttacks++;
		if(numAttacks == 5) {
			setDoneAttacking(true);
			numAttacks = 0;
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
		if (debug) System.out.println("placeArmy called by "+getName());
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
					setChanged();
					notifyObservers("" + this.getName() + " claimed " + selected.getName());
					if (debug) System.out.println("Army successfully placed in new territory by "+getName());
				}
				else
					r = randomGen.nextInt(42);
			}
		}

		//if territory is not available, add an army to one you own.
		else{
			int r = randomGen.nextInt(getTerritoriesOwned().size());
			Territory selected = getTerritoriesOwned().get(r);
			selected.setNumArmies(selected.getNumArmies() + 1);
			setNumArmies(getNumArmies() - 1);
			setChanged();
			notifyObservers("" + this.getName() + " placed an army in " + selected.getName());
			if (debug) System.out.println("Army successfully placed in owned territory by "+getName());
		}
		
	}



	/**
	 * Not used in iteration 1
	 * @param takeArmy
	 * @param reinforceThis
	 * 
	 */
	@Override
	public void reinforceArmies() {
		if (debug) System.out.println("reinforceArmies called by "+getName());
		int armiesToMove = 0;
		
		boolean reinforcePossible = false;
		for(Territory t: getTerritoriesOwned()) {
			for(Territory a: t.getAdjacent()) {
				if(a.getCurrentOwner() == this && t.getNumArmies() > 1)
					reinforcePossible = true;
			}
		}
		if(!reinforcePossible)
			return;
		
		Territory reinforceThis = null;
		Territory takeArmy = null;
		boolean takeArmySelected = false;
		do {
			takeArmy = getTerritoriesOwned().get(randomGen.nextInt(getTerritoriesOwned().size()));
			if(takeArmy.getCurrentOwner() == this && takeArmy.getNumArmies() > 1) {
				for(Territory t : takeArmy.getAdjacent()) {
					if(t.getCurrentOwner() == this) 
						takeArmySelected = true;
				}
					
			}
		}while(!takeArmySelected);
		boolean reinforceSelected = false;
		do{
			reinforceThis = takeArmy.getAdjacent().get(randomGen.nextInt(takeArmy.getAdjacent().size()));
			if(reinforceThis.getCurrentOwner() == this) {
				reinforceSelected = true;
			}
		}while(!reinforceSelected);
			
		do {
		armiesToMove = randomGen.nextInt(takeArmy.getNumArmies() - 1);
		}while(armiesToMove > 0);
		
		if (reinforceThis != null) {
			reinforceThis.setNumArmies(reinforceThis.getNumArmies() + armiesToMove);
			takeArmy.setNumArmies(takeArmy.getNumArmies() - armiesToMove);
		}
		
		this.setChanged();
		notifyObservers(this.getName() + " has moved " + armiesToMove + " armies from " + 
				takeArmy.getName() + " to " + reinforceThis.getName());
		
	}

}