package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.IntermediateAIPlayer;
import model.Player;
import model.SimpleAIPlayer;

/**
 * This class tests <code>RiskController</code> by running 1000 games among <code>SimpleAIPlayer</code>s and <code>IntermediateAIPlayer</code>s, and reporting the number of winners for each type of AI. 
 * @author Elizabeth Harris, Becca Simon
 *
 */

public class Run6Bots {
	boolean debug = true;
	private RiskController controller;
	private ArrayList<Player> players;
	private int simpleWon, intermediateWon;

	/**
	 * Constructs a new <code>Run6Bots</code>, reads in the number and type of players from the command line, and reports the percentages of wins for each type of player. 
	 */
	public Run6Bots() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Choose your players, you must have between 2 and 6 players");
		System.out.println("Enter the number of SimpleAIPlayers");
		int simplePlayers = Integer.parseInt(scan.next());
		System.out.println("Enter the number of IntermediateAIPlayers");
		int intermediatePlayers = Integer.parseInt(scan.next());
//		int simplePlayers = 1;
//		int intermediatePlayers = 2;
//		
		players = new ArrayList<Player>();

		//make sure user has entered no more than 6 players
		if(simplePlayers > 6) {
			simplePlayers = 6;
		}
		if(intermediatePlayers > 6) {
			intermediatePlayers = 6;
		}
		if(intermediatePlayers + simplePlayers > 6) {
			intermediatePlayers = 6 - simplePlayers;
		}
		int counter = 1;
		//add players to list
		for (int i = 0; i < simplePlayers; i++) {
			players.add(new SimpleAIPlayer("Simple Player " + counter));
			counter++;
			if (debug) System.out.println("Simple player "+players.get(i).toString()+" added");
		}
		for (int i = 0; i < intermediatePlayers; i++) {
			players.add(new IntermediateAIPlayer("Intermediate Player " + counter));	
			counter++;
			if (debug) System.out.println("Intermediate player "+players.get(i).toString()+" added");
		}
		simpleWon = 0;
		intermediateWon = 0;
		// for (int i = 0; i < 1000; i++) { TODO while debugging just run two games; change this when ready to run 1000 games
		for (int i = 0; i < 2; i++) {
			controller = new RiskController(players);
			if (debug) System.out.println("Game "+i+" started");
			//empty while loop to wait for end of game
//			int m = 0;
//			while(controller.getPlayers().size() > 1) {
//				System.out.println(m);
//				m++;
//			}
			if (controller.getPlayers().get(0) instanceof SimpleAIPlayer) 
				simpleWon++;
			else
				intermediateWon++;
			if (debug) System.out.println("Player "+controller.getPlayers().get(0).toString()+ "won");
		}
		double simplePercent = simpleWon / 10.0;
		double intermediatePercent = intermediateWon / 10.0;
		System.out.println("SimpleAIPlayers won " + simpleWon + " times (" + simplePercent + "%.");
		System.out.println("IntermediateAIPlayers won " + intermediateWon + " times" + " (" + intermediatePercent + "%.");
		scan.close();
	}

	/**
	 * Runs the program by creating a new <code>Run6Bots</code>.  
	 * @param args
	 */
	public static void main(String[] args) {
		new Run6Bots();
	}
}
