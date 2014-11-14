package model;

import java.util.Random;

public class Dice {
	
	//instance variable
	private int diceValue;
	
	//"roll" dice
	Random randomDice;
	
	public Dice (){
		randomDice = new Random();
		diceValue = 0;
	}
	
	public void rollDice(){
		diceValue = randomDice.nextInt(5) + 1;
	}
	
	//get dice value
	public int getDiceValue(){
		return diceValue;
	}
}
