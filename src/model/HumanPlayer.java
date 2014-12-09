package model;

public class HumanPlayer extends Player{
	private Territory currentTerritory;
	private boolean territoryChosen;
	
	public HumanPlayer(String name){
		super(name);
		territoryChosen = false;
	}
	
	@Override
	public void placeArmy() {
		this.setChanged();
		notifyObservers(ObserverMessages.HUMAN_PLACE_ARMY);
		
		while(territoryChosen == false) {
			if(territoryChosen == true) {
				System.out.println("getting to here");
				return;
			}
			//System.out.println("Temp num in while: " + temp + " Num Armies in while: " + getNumArmies());
		}
		
	}

	@Override
	public Territory attackFrom() {
		this.setChanged();
		notifyObservers(ObserverMessages.HUMAN_SELECT_ATTACK_FROM);
		while (!territoryChosen) {
			//System.out.println("waiting for attackFrom territory to be chosen");
		}
		// choose one of your territories with two or more armies to attack from
		// if you no longer want to attack, return null
		return null;
	}

	@Override
	public Territory attackTo(Territory attackFrom) {
		this.setChanged();
		notifyObservers(ObserverMessages.HUMAN_SELECT_ATTACK_TO);
		while (!territoryChosen) {
			//System.out.println("waiting for attackTo territory to be chosen");
		}
		// choose another player's territory adjacent to attackFrom
		return null;
	}



	@Override
	public void reinforceArmies(Territory takeArmy, Territory reinforceThis) {
		
	}

	public boolean isTerritoryChosen() {
		return territoryChosen;
	}

	public void setTerritoryChosen(boolean territoryChosen) {
		this.territoryChosen = territoryChosen;
	}

	public Territory getCurrentTerritory() {
		return currentTerritory;
	}

	public void setCurrentTerritory(Territory currentTerritory) {
		this.currentTerritory = currentTerritory;
	}

}
