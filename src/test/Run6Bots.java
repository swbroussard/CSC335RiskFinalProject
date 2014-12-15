package test;

import java.util.ArrayList;
import java.util.Scanner;

import controller.RiskController;
import model.ExpertAIPlayer;
import model.IntermediateAIPlayer;
import model.Player;
import model.SimpleAIPlayer;

/**
 * This class tests <code>RiskController</code> by running 1000 games among 
 * <code>SimpleAIPlayer</code>s and <code>IntermediateAIPlayer</code>s, and 
 * reporting the number of winners for each type of AI. 
 * 
 * @author Steven Broussard, Elizabeth Harris, Jeremy Jalnos, Becca Simon
 *
 */
public class Run6Bots {
	private boolean debug = true;
	private RiskController controller;
	private ArrayList<Player> players;
	private int simpleWon, intermediateWon, expertWon;

	/**
	 * Constructs a new <code>Run6Bots</code>, reads in the number and type of players 
	 * from the command line, and reports the percentages of wins for each type of player. 
	 */
	public Run6Bots() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Choose your players. You must have between 2 and 6 players");
		System.out.println("Enter the number of SimpleAIPlayers");
		int simplePlayers = Integer.parseInt(scan.next());
		System.out.println("Enter the number of IntermediateAIPlayers");
		int intermediatePlayers = Integer.parseInt(scan.next());
		System.out.println("Enter the number of ExpertAIPlayers");
		int expertPlayers = Integer.parseInt(scan.next());

		//make sure user has entered no more than 6 players
		if(simplePlayers > 6) {
			simplePlayers = 6;
		}
		if(intermediatePlayers > 6) {
			intermediatePlayers = 6;
		}
		if (expertPlayers > 6) {
			expertPlayers = 6;
		}
		if(intermediatePlayers + simplePlayers + expertPlayers > 6) {
			expertPlayers = 6 - intermediatePlayers - simplePlayers;
		}
		
		simpleWon = 0;
		intermediateWon = 0;
		expertWon = 0;
		
		for (int i = 1; i <= 1000; i++) { 
			players = new ArrayList<Player>();
			int counter = 1;
			//add players to list
			for (int j = 0; j < simplePlayers; j++) {
				players.add(new SimpleAIPlayer("Simple Player " + counter));
				counter++;
				if (debug) System.out.println("Simple player "+players.get(j).toString()+" added");
			}
			for (int j = 0; j < intermediatePlayers; j++) {
				players.add(new IntermediateAIPlayer("Intermediate Player " + counter));	
				counter++;
				if (debug) System.out.println("Intermediate player "+players.get(j).toString()+" added");
			}
			for (int j = 0; j < expertPlayers; j++) {
				players.add(new ExpertAIPlayer("Expert Player " + counter));
				counter++;
			}
			if(debug) System.out.println("Game "+i+" started");
			controller = new RiskController(players);
			
			if (controller.getPlayers().get(0) instanceof SimpleAIPlayer) 
				simpleWon++;
			else if (controller.getPlayers().get(0) instanceof IntermediateAIPlayer)
				intermediateWon++;
			else
				expertWon++;
			System.out.println("Player "+controller.getPlayers().get(0).getName() + " won Game #" + i);
		}
		double simplePercent = simpleWon / 10.0;
		double intermediatePercent = intermediateWon / 10.0;
		double expertPercent = expertWon / 10.0;
		System.out.println("SimpleAIPlayers won " + simpleWon + " times (" + simplePercent + "%).");
		System.out.println("IntermediateAIPlayers won " + intermediateWon + " times" + " (" + intermediatePercent + "%).");
		System.out.println("ExpertAIPlayers won " + expertWon + "times (" + expertPercent + "%).");
		scan.close();
	}

	/**
	 * Main method. Runs the program by creating a new <code>Run6Bots</code>.  
	 * @param args
	 */
	public static void main(String[] args) {
		new Run6Bots();
	}
}
