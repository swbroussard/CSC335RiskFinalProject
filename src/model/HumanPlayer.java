package model;

public class HumanPlayer extends Player{
	private Territory currentTerritory, attackFromTerritory, attackToTerritory;
	private boolean territoryChosen;
	private int armies;
	private boolean armiesChosen;
	
	public HumanPlayer(String name){
		super(name);
		territoryChosen = false;
	}
	
	@Override
	public void placeArmy() {
		this.setChanged();
		notifyObservers(ObserverMessages.HUMAN_PLACE_ARMY);
		while(territoryChosen == false) {
			setChanged();
			notifyObservers();
			if(territoryChosen == true) {
				territoryChosen = false;
				return;
			}
		}
		territoryChosen = false;
		return;
		
	}

	@Override
	public Territory attackFrom() {
		attackFromTerritory = null;
		attackToTerritory = null;
		this.setChanged();
		notifyObservers(ObserverMessages.HUMAN_SELECT_ATTACK_FROM);
		while (!territoryChosen) {
			setChanged();
			notifyObservers();
			if(territoryChosen == true) {
				territoryChosen = false;
				return attackFromTerritory;
			}
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
			setChanged();
			notifyObservers();
			if(territoryChosen == true) {
				territoryChosen = false;
				return attackToTerritory;
			}
		}
		// choose another player's territory adjacent to attackFrom
		return null;
	}

	@Override
	public void reinforceArmies() {
		Territory reinforceFrom = null, reinforceTo = null;
		this.setChanged();
		notifyObservers(ObserverMessages.HUMAN_SELECT_REINFORCE_FROM);
		while (!territoryChosen) {
			setChanged();
			notifyObservers();
			if (territoryChosen == true) {
				territoryChosen = false;
				reinforceFrom = currentTerritory;
				break;
			}
		}
		this.setChanged();
		notifyObservers(ObserverMessages.HUMAN_SELECT_REINFORCE_TO);
		while (!territoryChosen) {
			setChanged();
			notifyObservers();
			if (territoryChosen == true) {
				territoryChosen = false;
				reinforceTo = currentTerritory;
				break;
			}
		}
		this.setChanged();
		notifyObservers(ObserverMessages.HUMAN_SELECT_NUM_ARMIES);
		while (!armiesChosen) {
			setChanged();
			notifyObservers();
			if (armiesChosen == true) {
				armiesChosen = false;
				if (armies > reinforceFrom.getNumArmies() - 1) {
					notifyObservers(ObserverMessages.HUMAN_TRY_AGAIN_ARMIES);
					continue;
				}
				else {
					notifyObservers();
					break;
				}
			}
		}
		
		reinforceFrom.setNumArmies(reinforceFrom.getNumArmies() - armies);
		reinforceTo.setNumArmies(reinforceTo.getNumArmies() + armies);
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

	public boolean getArmiesChosen() {
		return armiesChosen;
	}

	public void setArmiesChosen(boolean armiesChosen) {
		this.armiesChosen = armiesChosen;
	}

	public void setAttackFrom(Territory t) {
		attackFromTerritory = t;
	}
	public void setAttackTo(Territory t) {
		attackToTerritory = t;
	}
	public Territory getAttackFrom() {
		return attackFromTerritory;
	}
}
