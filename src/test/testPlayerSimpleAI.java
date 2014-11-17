package test;

import org.junit.Test;

import model.Player;
import model.SimpleAIPlayer;

public class testPlayerSimpleAI {

	@Test
	public void testTheAIExist(){
		Player player1 = new SimpleAIPlayer();
	}
	
	@Test
	public void testPlayTurnMethod(){
		Player player1 = new SimpleAIPlayer();
		//test if it will attack or not using the static state random generator
		//test if it will reinforce or not using the static state random generator
	}
	
	@Test
	public void testChooseTerritoryMethod(){
		Player player1 = new SimpleAIPlayer();
		//test if it chooses the correct territory using the static state random generator
	}

}