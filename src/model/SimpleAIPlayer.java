package model;

import java.util.Random;

public class SimpleAIPlayer extends Player{
	/**
	 * TODO: Figure out how to call attack & Reinforce
	 */
	
	//instance variables
	Random randomGen;
	Random randomAdjacent;
	//constructor
	public SimpleAIPlayer(){
		randomGen = new Random(1);  //static state from the random generator
		//randomGen = new Random();  //random generator
		randomAdjacent = new Random(1);
		
	}
	
	@Override
	public void playTurn() {
		// obsolete - we no longer need this method in Player. however, much of this code looks like it could be useful elsewhere
		// Much of this might be the controller's responsibility
		int decisionAttack = (int) Math.random();
		int decideTerritory = randomGen.nextInt(getTerritories().size());
		if(decisionAttack == 0){ //-the simple AI is random so randomly generating whether or not to attack and whether or not to reinforce --Jeremy & Steven
			Territory t = getTerritories().get(decideTerritory);
			
			int decisionAdjacent = (t.getAdjacent().size());
			Territory toAttack = t.getAdjacent().get(randomAdjacent.nextInt(decisionAdjacent));
			if(getTerritories().contains(toAttack)){
				toAttack = t.getAdjacent().get(randomAdjacent.nextInt(decisionAdjacent)); // Make Sure that the current player does not already own this territory
			}
			// need an attack method
		}
		if(decisionAttack == 1){ //don't attack
			return;
		}
		/*
		 * if decision is to reinforce find the territory with the fewest armies and the territory with the most and swap a few armies 
		 * -Jeremy 
		 */
		
		int decisionReinforce = (int) Math.random();
		if(decisionReinforce == 0){
			int armies =0;
			int armyMax = 0;
			Territory reinforceThis;
			Territory takeArmy;
			for (Territory t2: getTerritories()){
				if(t2.getNumArmies() >= armyMax){ //get most Armies
					takeArmy = t2;
					armyMax = t2.getNumArmies();
				}
				if(t2.getNumArmies() <= armies){ // get least Armies
					reinforceThis = t2;
					armies = t2.getNumArmies();
				}
				
				
			}
			//TODO: get reinforce method that takes from takeArmy and gives troops to reinforceThis
		}
		
		
		
		
		//-steven, makes an random decision to attack or not
		//also to reinforce troops or not
		
		
		//makes decision according to a random number generator
		//for test only, the decision will be static
		
	}

	@Override
	public void chooseTerritory() {
		// we no longer need this method
		
		//-steven, makes random decision to choose which territory
		
		//makes decision according to a random number generator
		//for test only, the decision will be static
		
	}

	@Override
	public Territory attackFrom() {
		// TODO Auto-generated method stub
		// Randomly chooses a territory with two or more armies to attack from
		return null;
	}

	@Override
	public Territory attackTo(Territory attackFrom) {
		// TODO Auto-generated method stub
		// Randomly chooses a territory adjacent to the parameter, owned by another player, to attack
		return null;
	}

	@Override
	public void placeArmy() {
		// TODO Auto-generated method stub
		// Randomly chooses a territory and places one army there
	}
}