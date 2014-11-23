package model;

import java.util.Random;

/*
 * Please delete this comment. I am not sure if you saw my message but
 * the comparison of dice is done in the RiskController. I deleted the 
 * DiceTray. Please let me know if we need to debug 
 * intermediate player reinforce method.
 * 
 * Sincerely,
 * 
 * Steven Broussard
 */

/**
 * object of a die
 * @author jeremyjalnos StevenBroussard
 *
 */
public class Dice{
	
	//instance variable
	private int diceValue;
	
	//"roll" dice
	static Random randomDice;
	
	/**
	 * constructor to initialize the instance variables
	 */
	public Dice (){
		randomDice = new Random();
		this.diceValue = 0;
	}
	
	/**
	 * rolls a die
	 * @return random value of die
	 */
	public int rollDice() {
		return randomDice.nextInt(5) + 1;
	}
	
	/*public void rollDice(){
		diceValue = randomDice.nextInt(5) + 1;
	}*/
	
	//get dice value
	
	/**
	 * to get the value of the die
	 * @return diceValue
	 */
	public int getDiceValue(){
		return diceValue;
	}
}
