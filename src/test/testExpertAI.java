package test;

import java.util.ArrayList;
import java.util.Scanner;

import controller.RiskController;
import model.Player;

import model.ExpertAiPlayer;

/**
 * This class tests <code>RiskController</code> by running 1000 games among 
 * <code>ExpertAIPlayer</code>s and <code>IntermediateAIPlayer</code>s, and 
 * reporting the number of winners for each type of AI. 
 * 
 * @author Elizabeth Harris, Becca Simon, Jeremy Jalnos
 *
 */

public class testExpertAI {
	boolean debug = false;
	private RiskController controller;
	private ArrayList<Player> players;
	private int ExpertWon, intermediateWon;

	/**
	 * Constructs a new <code>Run6Bots</code>, reads in the number and type of players 
	 * from the command line, and reports the percentages of wins for each type of player. 
	 */
	public testExpertAI() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Choose your players. You must have between 2 and 6 players");
		System.out.println("Enter the number of ExpertAIPlayers");
		int ExpertPlayers = Integer.parseInt(scan.next());

		

		//make sure user has entered no more than 6 players
		if(ExpertPlayers > 6) {
			ExpertPlayers = 6;
		}
		
		
		
		ExpertWon = 0;
		intermediateWon = 0;
		
		for (int i = 1; i <= 1000; i++) { 
			players = new ArrayList<Player>();
			int counter = 1;
			//add players to list
			for (int j = 0; j < ExpertPlayers; j++) {
				players.add(new ExpertAiPlayer("Expert Player " + counter));
				counter++;
				if (debug) System.out.println("Expert player "+players.get(j).toString()+" added");
			}
			
			if (debug) System.out.println("Game "+i+" started");
			controller = new RiskController(players);
			
			if (controller.getPlayers().get(0) instanceof ExpertAiPlayer) 
				ExpertWon++;
			else
				intermediateWon++;
			//System.out.println("Player "+controller.getPlayers().get(0).getName() + " won Game #" + i);
		}
		
		
		double ExpertPercent = ExpertWon / 10.0;
		double intermediatePercent = intermediateWon / 10.0;
		System.out.println("ExpertAIPlayers won " + ExpertWon + " times (" + ExpertPercent + "%).");
		System.out.println("IntermediateAIPlayers won " + intermediateWon + " times" + " (" + intermediatePercent + "%).");
		scan.close();
	}

	/**
	 * Runs the program by creating a new <code>Run6Bots</code>.  
	 * @param args
	 */
	public static void main(String[] args) {
		new testExpertAI();
	}
}
