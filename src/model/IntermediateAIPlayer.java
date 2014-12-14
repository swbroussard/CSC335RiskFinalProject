package model;

import java.util.Random;

/**
 * More intelligent than Simple AI
 * 
 * Strategy: randomly places first in a territory, then add other troops in adjacent
 * territories. Only attack the fewest amount enemy troops.
 * 
 * @author Steven W Broussard & Jeremy Jalnos
 *
 */
public class IntermediateAIPlayer extends Player{
	private Random genRan;
	private int numAttacks;
	private Territory attackTo;

	public IntermediateAIPlayer() {
		super();
		attackTo = null;
	}

	public IntermediateAIPlayer(String name) {
		super(name);
		if (debug) System.out.println("New IntermediateAIPlayer created: "+name);
		attackTo = null;
	}

	/**
	 * Description: First troop is place at a territory randomly. All other troops will
	 * then be place in adjacent territories that are not owned.
	 */
	@Override
	public void placeArmy() {
		if(debug) System.out.println("placeArmy called by "+getName());
		// Semi-intelligently chooses a territory with two or more armies to attack from
		genRan = new Random();

		//determines if you are still in territory selecting mode or if in army placing mode
		boolean allSelected = true;
		for(Territory t: getAllTerritories()) {
			if(t.getCurrentOwner() == null) {
				allSelected = false;
			}
		}

		if(allSelected == false) {
			//if it is the first territory to select
			if(getTerritoriesOwned().size() == 0) {
				int r = genRan.nextInt(42);
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
						if(debug) System.out.println("Army successfully placed in " + selected.getName() + " by "+getName());
					}
					else
						r = genRan.nextInt(42);
				}
			}
			//choosing territories after the first one, want to cluster territories if possible. 
			else {
				boolean territoryChoosen = false;
				for(int i = 0; i < 7; i++) {//try to cluster 7 times, then just pick a random one
					int n = 0;
					if(debug) System.out.println("itr " + i);
					while(!territoryChoosen && n < getTerritoriesOwned().size()) {
						Territory clusterAround = getTerritoriesOwned().get(n);
						Territory selected = clusterAround.getAdjacent().get(genRan.nextInt(clusterAround.getAdjacent().size()));
						if(selected.getCurrentOwner() == null) {
							selected.setCurrentOwner(this);
							selected.setNumArmies(1);
							territoryChoosen = true;
							addTerritory(selected);
							setNumArmies(getNumArmies() - 1);
							setChanged();
							notifyObservers("" + this.getName() + " claimed " + selected.getName());
							if(debug) System.out.println("Army successfully placed in " + selected.getName() + " by "+getName());
							if(debug) System.out.println(getAllTerritories());
						}
						n++;
					}
				}
				int r = genRan.nextInt(42);
				while(!territoryChoosen) {
					Territory selected = getAllTerritories().get(r);
					if(selected.getCurrentOwner() == null) {
						selected.setCurrentOwner(this);
						selected.setNumArmies(1);
						territoryChoosen = true;
						addTerritory(selected);
						setNumArmies(getNumArmies() - 1);
						setChanged();
						notifyObservers("" + this.getName() + " claimed " + selected.getName());
						if(debug) System.out.println("Army successfully placed in " + selected.getName() + " by "+getName());
					}
					else
						r = genRan.nextInt(42);
				}
			}
		}//end of choosing empty territories
		else {//place army in territory			
			Territory selected = null;
			for(Territory t: getTerritoriesOwned()) {
				if(t.getNumArmies() < 1)
					selected = t;
			}

			int r = genRan.nextInt(getTerritoriesOwned().size());
			for(int i = 0; i < 5; i++) {
				if(selected == null) {
					Territory temp = getTerritoriesOwned().get(r);
					for(Territory t: temp.getAdjacent()) {
						if(t.getCurrentOwner() != this) {
							selected = temp;
						}
					}
				}
			}
			if(selected == null) {
				selected = getTerritoriesOwned().get(genRan.nextInt(getTerritoriesOwned().size()));
			}
			selected.setNumArmies(selected.getNumArmies() + 1);
			setNumArmies(getNumArmies() - 1);
			setChanged();
			notifyObservers("" + this.getName() + " placed an army in " + selected.getName());
			if (debug) System.out.println("Army successfully placed in owned territory by "+getName());
		}
	}

	/**
	 * randomly chooses owned territories to attack from
	 * @return Territory
	 */

	@Override
	public Territory attackFrom() {
		attackTo = null;
		if (debug) System.out.println("attackFrom called by "+getName());
		Territory choosenTerritory = null;
		for(int i = 0; i < 30; i++){
			Territory temp = getTerritoriesOwned().get(genRan.nextInt(getTerritoriesOwned().size()));
			if(temp.getNumArmies() > 1) {
				for(Territory t: temp.getAdjacent()) {
					if(t.getCurrentOwner() != this){ 
						if(temp.getNumArmies() > t.getNumArmies()) {
							choosenTerritory = temp;
							attackTo = t;
						}
					}
				}
			}
		}
		while(choosenTerritory == null) {
			Territory temp = getTerritoriesOwned().get(genRan.nextInt(getTerritoriesOwned().size()));
			if(temp.getNumArmies() > 1) {
				for(Territory t: temp.getAdjacent()) {
					if(t.getCurrentOwner() != this)
						choosenTerritory = temp;
				}
			}
		}
		return choosenTerritory;
	}
	/**
	 * randomly attacks to adjacent enemy territory
	 * @param attackForm
	 * @return Territory
	 */
	@Override
	public Territory attackTo(Territory attackFrom) {
		if (debug) System.out.println("attackTo called by "+getName());
		// Semi-intelligently chooses a territory and places one army there
		if(attackTo != null) {
			return attackTo;
		}

		int lowTroop = 1000;
		Territory attack = null;
		for(int i = 0; i< attackFrom.getAdjacent().size(); i++){
			if(attackFrom.getAdjacent().get(i).getCurrentOwner()!= this){
				if(attackFrom.getAdjacent().get(i).getNumArmies() < lowTroop){
					lowTroop = attackFrom.getAdjacent().get(i).getNumArmies();
					attack = attackFrom.getAdjacent().get(i);
				}

			}
		}
		numAttacks++;
		if(numAttacks == 7) {
			setDoneAttacking(true);
			numAttacks = 0;
		}
		return attack;
	}

	/**
	 * randomly puts reinforce armies around adjacent territories owned by other player
	 * @param takeArmy, reinforceThis
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

		for(Territory t: getTerritoriesOwned()) {
			if(takeArmy == null && t.getNumArmies() > 1 && t.getCurrentOwner() == this) {
				boolean adjacentAllThis = true;
				for(Territory s: t.getAdjacent()) {
					if(s.getCurrentOwner() != this)
						adjacentAllThis = false;
				}
				if(adjacentAllThis)
					takeArmy = t;
			}
		}
		while(takeArmy == null) {
			Territory temp = getTerritoriesOwned().get(genRan.nextInt(getTerritoriesOwned().size()));
			if(temp.getNumArmies() > 1) {
				for(Territory t: temp.getAdjacent()) {
					if(t.getCurrentOwner() == this)
						takeArmy = temp;
				}			
			}
		}

		boolean reinforceSelected = false;
		do{
			reinforceThis = takeArmy.getAdjacent().get(genRan.nextInt(takeArmy.getAdjacent().size()));
			if(reinforceThis.getCurrentOwner() == this) {
				reinforceSelected = true;
			}
		}while(!reinforceSelected);

		armiesToMove = takeArmy.getNumArmies() - 1;

		if (reinforceThis != null) {
			reinforceThis.setNumArmies(reinforceThis.getNumArmies() + armiesToMove);
			takeArmy.setNumArmies(takeArmy.getNumArmies() - armiesToMove);
		}

		this.setChanged();
		notifyObservers(this.getName() + " has moved " + armiesToMove + " armies from " + 
				takeArmy.getName() + " to " + reinforceThis.getName());
	}
}
