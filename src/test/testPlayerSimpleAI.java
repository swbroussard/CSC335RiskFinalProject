package test;

import java.util.ArrayList;

import org.junit.Test;

import controller.RiskController;
import model.IntermediateAIPlayer;
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
	
	//im here btw just eating real quick
	//okay
	@Test
	public void testExistingIntermediate(){
		Player player1 = new IntermediateAIPlayer();
	}

	@Test
	public void testingIntermediateReinforceArmies(){
		Player player1 = new IntermediateAIPlayer();
		Player player2 = new IntermediateAIPlayer();
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(player1);
		playerList.add(player2);
		RiskController risk = new RiskController(playerList);
		
		
	}
}