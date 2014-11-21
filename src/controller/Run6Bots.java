package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.IntermediateAIPlayer;
import model.Player;
import model.SimpleAIPlayer;

public class Run6Bots {
	private RiskController controller;
	private ArrayList<Player> players;
	private int simpleWon, intermediateWon;
	
	public Run6Bots() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Choose your players, you must have between 2 and 6 players");
		System.out.println("Enter the number of SimpleAIPlayers");
		int simplePlayers = Integer.parseInt(scan.next());
		System.out.println("Enter the number of IntermediateAIPlayers");
		int intermediatePlayers = Integer.parseInt(scan.next());
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
			players.add(new SimpleAIPlayer("Player " + counter));
			counter++;
		}
		for (int i = 0; i < intermediatePlayers; i++) {
			players.add(new IntermediateAIPlayer("Player " + counter));	
			counter++;
		}
		int simpleWon = 0;
		int intermediateWon = 0;
		for (int i = 0; i < 1000; i++) {
			runRisk(players);
		}
		double simplePercent = simpleWon/10.0;
		double intermediatePercent = intermediateWon/10.0;
		System.out.println("SimpleAIPlayers won "+simpleWon+" times ("+
				simplePercent+"%.");
		System.out.println("IntermediateAIPlayers won "+intermediateWon+" times"
				+" ("+intermediatePercent+"%.");
		scan.close();
	}
	
	public void runRisk(ArrayList<Player> players) {
		controller = new RiskController(players);
		//empty while loop to wait for end of game
		while(controller.getPlayers().size() > 1) {

		}
		if (controller.getPlayers().get(0) instanceof SimpleAIPlayer)
			simpleWon++;
		else
			intermediateWon++;
		
	}

	public static void main(String[] args) {
		new Run6Bots();
	}
}
