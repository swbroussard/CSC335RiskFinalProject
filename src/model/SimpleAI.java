package model;

import java.util.Random;

public class SimpleAI extends Player{
	
	//instance variables
	Random randomGen;
	
	//constructor
	public SimpleAI(){
		randomGen = new Random(1);  //static state from the random generator
		//randomGen = new Random();  //random generator
	}

	@Override
	public void playTurn() {
		
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