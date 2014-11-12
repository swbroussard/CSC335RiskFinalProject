package test;

import org.junit.Test;
import model.SimpleAI;
import model.Player;

public class testPlayerSimpleAI {

	@Test
	public void testTheAIExist(){
		Player player1 = new SimpleAI();
	}
	
	@Test
	public void testPlayTurnMethod(){
		Player player1 = new SimpleAI();
		//test if it will attack or not using the static state random generator
		//test if it will reinforce or not using the static state random generator
	}
	
	@Test
	public void testChooseTerritoryMethod(){
		Player player1 = new SimpleAI();
		//test if it chooses the correct territory using the static state random generator
	}

}