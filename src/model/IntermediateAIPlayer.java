package model;

import java.util.Random;

/**
 * More intelligent then Simple AI
 * 
 * Strategy: randomly places first in a territory, then add other troops in adjacent
 * territories. Only attack the fewest amount enemy troops.
 * 
 * @author Steven W Broussard & Jeremy Jalnos
 *
 */
public class IntermediateAIPlayer extends Player{

	
	/**
	 * Description: First troop is place at a territory randomly. All other troops will
	 * then be place in adjacent territories that are not owned.
	 */
	@Override
	public void placeArmy() {
		// TODO place an troop one per turn or place all the armies on player turn in the beginning?
		// Semi-intelligently chooses a territory with two or more armies to attack from
		Random genRan;
		genRan = new Random();
		int randomTerritoryIndex = getTerritories().size();
		Territory firstPick = null;
		
		//first troop is place at a territory randomly
		firstPick = getTerritories().get(genRan.nextInt(randomTerritoryIndex));
		firstPick.setCurrentOwner(this);
		firstPick.setNumArmies(1);
		
		//second and more troops are put around the first troop in the 
		//beginning
		
	}

	@Override
	public Territory attackFrom() {
		// TODO Auto-generated method stub
		// Semi-intelligently chooses a territory adjacent to the parameter, owned by another player, to attack
		return null;
	}

	@Override
	public Territory attackTo(Territory attackFrom) {
		// TODO Auto-generated method stub
		// Semi-intelligently chooses a territory and places one army there
		return null;
	}

	
	
	/**
	 * TODO: Fix all Jeremy's nonSense below 
	 * numTroopsTake2 wont work with current implementation
	 */
	
	int numTroopsTake = 0;
	int numTroopsTake2 = 0;
	
	public void reinforce(){
	Territory high = null; 
	Territory high2 = null;
	Territory low = null;
	Territory low2 = null;
	int highTroop = 0;
//	int highTroop2 =0;
	int highAdjacent = 0;
//	int highAdjacent2 = 0;
	int lowTroop = 0;
//	int lowTroop2 =0;
	
	for(int i =0; i <= getTerritories().size(); i++){
		for(int j =0; j <= getTerritories().get(i).getAdjacent().size(); j++){// check all territories look through all armies find lowest with competitors or ones with no competitors and give to territories more in need
			if(getTerritories().get(i).getAdjacent().get(j).getCurrentOwner() != this){
			if(getTerritories().get(i).getAdjacent().get(j).getNumArmies() >= getTerritories().get(i).getNumArmies()){
				low = getTerritories().get(i);
			}
//			if(getTerritories().get(i).getAdjacent().get(j).getNumArmies() >= getTerritories().get(i).getNumArmies() && getTerritories().get(i).getNumArmies() >= low.getNumArmies()){
//				low2 = getTerritories().get(i);
//				lowTroop2 = getTerritories().get(i).getNumArmies();
//			}
			else{
				highAdjacent = getTerritories().get(i).getAdjacent().get(j).getNumArmies();
				highTroop = getTerritories().get(i).getNumArmies();
				high = getTerritories().get(i);
				numTroopsTake = highTroop - highAdjacent +1;
				
//				if(getTerritories().get(i).getAdjacent().get(j).getNumArmies() <= getTerritories().get(i).getNumArmies() && getTerritories().get(i).getNumArmies() <= highTroop){
//					highAdjacent2 = getTerritories().get(i).getAdjacent().get(j).getNumArmies();
//					highTroop2 = getTerritories().get(i).getNumArmies();
//					high2 = getTerritories().get(i);
//					numTroopsTake2 = highTroop - highAdjacent +1;
			//	}
				
				
			}
		}
			else{
				numTroopsTake = getTerritories().get(i).getNumArmies() -1;
			}
		
			
		}
		reinforceArmies(high, low);
//		reinforceArmies(high2, low2);
		
	}
	

}

	@Override
	public void reinforceArmies(Territory takeArmy, Territory reinforceThis) {
		int x = takeArmy.getNumArmies();
		int y = reinforceThis.getNumArmies();
		takeArmy.setNumArmies(x -= numTroopsTake);
		reinforceThis.setNumArmies(y += numTroopsTake);
	}}
