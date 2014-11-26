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
	
	public IntermediateAIPlayer() {
		super();
	}
	
	public IntermediateAIPlayer(String name) {
		super(name);
		if (debug) System.out.println("New IntermediateAIPlayer created: "+name);
	}
	
	/**
	 * Description: First troop is place at a territory randomly. All other troops will
	 * then be place in adjacent territories that are not owned.
	 */
	@Override
	public void placeArmy() {
		if(debug) System.out.println("placeArmy called by "+getName());
		// TODO place an troop one per turn or place all the armies on player turn in the beginning?
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
						if(debug) System.out.println("Army successfully placed in " + selected.getName() + " by "+getName());
					}
					else
						r = genRan.nextInt(42);
				}
			}
			//choosing territories after the first one, want to cluster territories if possible. 
			else {
				boolean territoryChoosen = false;
				//nextPick = getTerritoriesOwned().get(genRandom.nextInt(firstPick.getAdjacent().size()));
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
						if(debug) System.out.println("Army successfully placed in " + selected.getName() + " by "+getName());
					}
					else
						r = genRan.nextInt(42);
				}

			}
		}//end of choosing empty territories
		else {//place army in territory			
			int r = genRan.nextInt(getTerritoriesOwned().size());
			getTerritoriesOwned().get(r).setNumArmies(getNumArmies() + 1);
			setNumArmies(getNumArmies() - 1);
			if (debug) System.out.println("Army successfully placed in owned territory by "+getName());
		}
	}

	
	@Override
	public Territory attackFrom() {
//		if (debug) System.out.println("attackFrom called by "+getName());
//		Territory attack = null;
//		// Semi-intelligently chooses a territory adjacent to the parameter, owned by another player, to attack
//		int x = 0;
//		while(x < getTerritoriesOwned().size()){
//			for(int i = 0; i< getTerritoriesOwned().get(x).getAdjacent().size(); i++){
//				System.out.println("tst");
//				if(getTerritoriesOwned().get(x).getAdjacent().get(i).getCurrentOwner()!= this){
//					i = getTerritoriesOwned().size() -1;
//					attack = getTerritoriesOwned().get(x);	
//				}
//			}
//			x++;
//		}
//		System.out.println(attack.toString());
//		return attack;
		
		if (debug) System.out.println("attackFrom called by "+getName());
		Territory choosenTerritory = null;
		while(choosenTerritory == null){
			int r = genRan.nextInt(getTerritoriesOwned().size());
			if(getTerritoriesOwned().get(r).getNumArmies() > 1) {
				for(Territory t: getTerritoriesOwned().get(r).getAdjacent()) {
					if(t.getCurrentOwner() != this) {
						choosenTerritory = getTerritoriesOwned().get(r);
					}
				}
			}
		}
		return choosenTerritory;
	}

	@Override
	public Territory attackTo(Territory attackFrom) {
		if (debug) System.out.println("attackTo called by "+getName());
		// Semi-intelligently chooses a territory and places one army there
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
		return attack;
	}

	/**
	 * TODO: Fix all Jeremy's nonSense below
	 */
	
	private int numTroopsTake = 0;
	private int numTimesCalled = 0;
	
	public void reinforce(){
		if (debug) System.out.println("reinforce called by "+getName());
		
		Territory high = null; 
		Territory low = null;
		int highTroop = 0;
		int lowTroop = 1000;
		boolean take =false;
		boolean adjacentNotOwned = false;
	
		for(int i =0; i <= getTerritoriesOwned().size(); i++){
			for(int j =0; j <= getTerritoriesOwned().get(i).getAdjacent().size(); j++) {// check all territories look through all armies find lowest with competitors or ones with no competitors and give to territories more in need
			
			
				if(getTerritoriesOwned().get(i).getAdjacent().get(j).getCurrentOwner() != this){ //checks for un-owned neighbors
					if(getTerritoriesOwned().get(i).getAdjacent().get(j).getNumArmies() >= getTerritoriesOwned().get(i).getNumArmies()) {// checks to see if neigbor has more armies than us
						if(getTerritoriesOwned().get(i).getNumArmies() < lowTroop) {// checks to see if we are more screwed than other territories
							low = getTerritoriesOwned().get(i);// sets this as the low territory
							lowTroop = getTerritoriesOwned().get(i).getNumArmies(); // sets how many current armies there are here
						}//if statement
					}//if statement
				}//if statement
			}//for loop
			for(int e = 0; e <= getTerritoriesOwned().get(i).getAdjacent().size(); e++) {//loops through adjacent territories
				if(getTerritoriesOwned().get(i).getAdjacent().get(e).getCurrentOwner()!= this) {// checks if we don't own an adjacent land
					adjacentNotOwned = true;// if we don't own something we can't automatically take from this territory
					if(getTerritoriesOwned().get(i).getNumArmies() >= highTroop){//checks to see if this is the highest available troop count
						if(getTerritoriesOwned().get(i).getAdjacent().get(e).getNumArmies() <= (getTerritoriesOwned().get(i).getNumArmies()*2)/3) { //checks to see if they should take from here for sure
							take = true;
							highTroop = getTerritoriesOwned().get(i).getNumArmies();
							high = getTerritoriesOwned().get(i);
						}
					}
					else {
						take = false;
					}
				}//if statement
			}// for loop
		
			if(adjacentNotOwned == false) {
				take = true;
				highTroop = getTerritoriesOwned().get(i).getNumArmies();
				high = getTerritoriesOwned().get(i);
				break;
			}// if statement
			if(take) {
				numTroopsTake = (highTroop * 2)/3;
			}
		}// master for loop
						
		reinforceArmies(high, low);

	}// close helper method

	@Override
	public void reinforceArmies(Territory takeArmy, Territory reinforceThis) {
		if (debug) System.out.println("reinforceArmies called by "+getName());
		if(numTimesCalled<1){
			reinforce();
			numTimesCalled++;
		}
		else {
			int x = takeArmy.getNumArmies();
			int y = reinforceThis.getNumArmies();
			takeArmy.setNumArmies(x -= numTroopsTake);
			reinforceThis.setNumArmies(y += numTroopsTake);
			numTimesCalled = 0;
		}
	}
}
