package model;

/**
 * Allows a human user to play a game of Risk. Sends messages back and forth to the GUI to 
 * record the user's decisions and interactions. 
 * @author Steven Broussard, Elizabeth Harris, Jeremy Jalnos, Becca Simon
 */
public class HumanPlayer extends Player{
	private Territory currentTerritory, attackFromTerritory, attackToTerritory;
	private boolean territoryChosen;
	private int armies;
	private boolean armiesChosen;
	
	/**
	 * Constructs a new HumanPlayer object using the Player constructor. The player's
	 * name is set to the parameter and territoryChosen is set to false. 
	 * @param name
	 */
	public HumanPlayer(String name){
		super(name);
		territoryChosen = false;
	}
	
	/**
	 * Prompts the user to select a territory on which to place an army
	 */
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

	/**
	 * Prompts the user to select a territory to attack from
	 */
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

	/**
	 * Prompts the user to select an enemy territory to attack
	 */
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

	/**
	 * Prompts the human user to select territories to reinforce from and to, 
	 * and select a number of armies to move
	 */
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

	/**
	 * getter for territoryChosen
	 * @return
	 */
	public boolean isTerritoryChosen() {
		return territoryChosen;
	}

	/**
	 * setter for territoryChosen
	 * @param territoryChosen
	 */
	public void setTerritoryChosen(boolean territoryChosen) {
		this.territoryChosen = territoryChosen;
	}

	/**
	 * getter for currentTerritory, the user's most recently selected territory
	 * @return
	 */
	public Territory getCurrentTerritory() {
		return currentTerritory;
	}

	/**
	 * setter for currentTerritory
	 * @param currentTerritory
	 */
	public void setCurrentTerritory(Territory currentTerritory) {
		this.currentTerritory = currentTerritory;
	}

	/**
	 * getter for armiesChosen
	 * @return
	 */
	public boolean getArmiesChosen() {
		return armiesChosen;
	}

	/**
	 * setter for armiesChosen
	 * @param armiesChosen
	 */
	public void setArmiesChosen(boolean armiesChosen) {
		this.armiesChosen = armiesChosen;
	}

	/**
	 * setter for attackFromTerritory
	 * @param t
	 */
	public void setAttackFrom(Territory t) {
		attackFromTerritory = t;
	}
	
	/**
	 * setter for attackToTerritory
	 * @param t
	 */
	public void setAttackTo(Territory t) {
		attackToTerritory = t;
	}
	
	/**
	 * getter for attackFromTerritory
	 * @return
	 */
	public Territory getAttackFrom() {
		return attackFromTerritory;
	}
}
