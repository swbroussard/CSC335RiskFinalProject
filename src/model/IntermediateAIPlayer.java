package model;

import java.util.Random;

/**
 * More intelligent then Simple AI
 * 
 * Strategy: randomly places first in a territory, then add other troops in adjacent
 * territories. Only attack the fewest amount enemy troops.
 * 
 * @author Steven W Broussard & Jeremy Jelnos
 *
 */
public class IntermediateAIPlayer extends Player{
	public IntermediateAIPlayer() {
		super();
	}
	
	public IntermediateAIPlayer(String name) {
		super(name);
	}
	
	/**
	 * Description: First troop is place at a territory randomly. All other troops will
	 * then be place in adjacent territories that are not owned.
	 */
	@Override
	public void placeArmy() {
		// TODO place an troop one per turn or place all the armies on player turn in the beginning?
		// Semi-intelligently chooses a territory with two or more armies to attack from
		Random genRan;
		Random genRandom;
		genRan = new Random();
		genRandom = new Random();
		int randomTerritoryIndex = getAllTerritories().size();
		int randomNeighbor = getAllTerritories().size();
		Territory firstPick = getAllTerritories().get(genRan.nextInt(randomTerritoryIndex));
		Territory nextPick = null;
		boolean boolFirstPick = false;
		
		if(boolFirstPick == false){
		//first troop is place at a territory randomly
		firstPick.setCurrentOwner(this);
		firstPick.setNumArmies(1);
		}
		else{
			nextPick = getTerritoriesOwned().get(genRandom.nextInt(firstPick.getAdjacent().size()));
			if(nextPick.getCurrentOwner() != null){
				nextPick = getTerritoriesOwned().get(genRandom.nextInt(firstPick.getAdjacent().size()));
			}
			else{
				nextPick.setCurrentOwner(this);
				nextPick.setNumArmies(1);
			}
		}
		//second and more troops are put around the first troop in the 
		//beginning
		
	}

	
	@Override
	public Territory attackFrom() {
		Territory attack = null;
		// TODO Auto-generated method stub
		// Semi-intelligently chooses a territory adjacent to the parameter, owned by another player, to attack
		int x = 0;
		while(x < getTerritoriesOwned().size()){
			for(int i = 0; i< getTerritoriesOwned().get(x).getAdjacent().size(); i++){
			if(getTerritoriesOwned().get(x).getAdjacent().get(i).getCurrentOwner()!= this){
				i = getTerritoriesOwned().size() -1;
				attack = getTerritoriesOwned().get(x);
					
			}
			}
			x++;
		}
		return attack;
	}

	@Override
	public Territory attackTo(Territory attackFrom) {
		// TODO Auto-generated method stub
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
	
	
	public void reinforce(){
		
	Territory high = null; 
	Territory low = null;
	int highTroop = 0;
	int highAdjacent = 0;
	int lowTroop = 1000;
	

	
	//changed getTerritories to getTerritoriesOwned.  If you want getAllTerritories you need to change it.
	for(int i =0; i <= getTerritoriesOwned().size(); i++){
		for(int j =0; j <= getTerritoriesOwned().get(i).getAdjacent().size(); j++){// check all territories look through all armies find lowest with competitors or ones with no competitors and give to territories more in need
			if(getTerritoriesOwned().get(i).getAdjacent().get(j).getCurrentOwner() != this){
			if(getTerritoriesOwned().get(i).getAdjacent().get(j).getNumArmies() >= getTerritoriesOwned().get(i).getNumArmies()){
				if(getTerritoriesOwned().get(i).getNumArmies() < lowTroop){
				low = getTerritoriesOwned().get(i);
				lowTroop = getTerritoriesOwned().get(i).getNumArmies();
			}}
			if(getTerritoriesOwned().get(i).getAdjacent().get(j).getCurrentOwner()!= this){
			if(getTerritoriesOwned().get(i).getAdjacent().get(j).getNumArmies() <= getTerritoriesOwned().get(i).getNumArmies()){
				if(getTerritoriesOwned().get(i).getNumArmies()>= highAdjacent){

				highTroop = getTerritoriesOwned().get(i).getNumArmies();
				high = getTerritoriesOwned().get(i);
				numTroopsTake = (highTroop * 2)/3;
				
			}}
		}}
//			else{
//				numTroopsTake = getTerritoriesOwned().get(i).getNumArmies() -1;
//			}
		
			
		}
		reinforceArmies(high, low);
		
	}
	

}

	@Override
	public void reinforceArmies(Territory takeArmy, Territory reinforceThis) {
		int x = takeArmy.getNumArmies();
		int y = reinforceThis.getNumArmies();
		takeArmy.setNumArmies(x -= numTroopsTake);
		reinforceThis.setNumArmies(y += numTroopsTake);
	}}
