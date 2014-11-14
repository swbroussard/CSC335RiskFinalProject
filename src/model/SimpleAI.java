package model;

import java.util.ArrayList;
import java.util.Random;

public class SimpleAI extends Player{
	/**
	 * TODO: Figure out how to call attack & Reinforce
	 */
	
	//instance variables
	Random randomGen;
	Random randomAdjacent;
	//constructor
	public SimpleAI(){
		randomGen = new Random(1);  //static state from the random generator
		//randomGen = new Random();  //random generator
		randomAdjacent = new Random(1);
		
	}
	
	@Override
	public void playTurn() {
		int decisionAttack = (int) Math.random();
		int decideTerritory = randomGen.nextInt(getTerritories().size());
		if(decisionAttack == 0){ //-the simple AI is random so randomly generating wether or not to attack and wether or not to reinforce --Jeremy & Steven
			Territory t = getTerritories().get(decideTerritory);
			
			int decisionAdjacent = (t.getAdjacent().size());
			Territory toAttack = t.getAdjacent().get(randomAdjacent.nextInt(decisionAdjacent));
			
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
		
		//-steven, makes random decision to choose which territory
		
		//makes decision according to a random number generator
		//for test only, the decision will be static
		
	}
}