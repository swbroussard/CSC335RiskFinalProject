package model;

import view.RiskGUI;

public class HumanPlayer extends Player{
	private RiskGUI gui;
	
	public HumanPlayer(String name, RiskGUI gui){
		super(name);
		this.gui = gui;
	}
	
	@Override
	public void placeArmy() {
		int temp = getNumArmies();
		//System.out.println("Temp num before: " + temp);
		this.setChanged();
		notifyObservers(ObserverMessages.HUMAN_PLACE_ARMY);
		
		while(temp == getNumArmies()) {
		//	System.out.println("Temp num in while: " + temp + " Num Armies in while: " + getNumArmies());
				
		}
		
		return;
	}

	@Override
	public Territory attackFrom() {
		// choose one of your territories with two or more armies to attack from
		// if you no longer want to attack, return null
		return null;
	}

	@Override
	public Territory attackTo(Territory attackFrom) {
		// choose another player's territory adjacent to attackFrom
		return null;
	}



	@Override
	public void reinforceArmies(Territory takeArmy, Territory reinforceThis) {
		
	}

}
