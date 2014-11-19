package model;

public class IntermediateAIPlayer extends Player{

	

	@Override
	public void placeArmy() {
		// TODO Auto-generated method stub
		// Semi-intelligently chooses a territory with two or more armies to attack from
		
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
	 */
	
	int numTroopsTake = 0;
	
	public void reinforce(){
	Territory high = null; 
	Territory low = null;
	int highTroop = 0;
	int highAdjacent = 0;
	int lowTroop = 0;
	
	for(int i =0; i <= getTerritories().size(); i++){
		for(int j =0; j <= getTerritories().get(i).getAdjacent().size(); j++){// check all territories look through all armies find lowest with competitors or ones with no competitors and give to territories more in need
			if(getTerritories().get(i).getAdjacent().get(j).getCurrentOwner() != this){
			if(getTerritories().get(i).getAdjacent().get(j).getNumArmies() >= getTerritories().get(i).getNumArmies()){
				low = getTerritories().get(i);
			}
			else{
				highAdjacent = getTerritories().get(i).getAdjacent().get(j).getNumArmies();
				highTroop = getTerritories().get(i).getNumArmies();
				high = getTerritories().get(i);
				numTroopsTake = highTroop - highAdjacent +1;
				
			}
		}
			else{
				numTroopsTake = getTerritories().get(i).getNumArmies() -1;
			}
		
			
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
