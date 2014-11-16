package controller;

import java.util.ArrayList;

import model.IntermediateAIPlayer;
import model.Player;
import model.SimpleAIPlayer;

public class Run6Bots {
	private RiskController controller;
	private ArrayList<Player> players;
	
	public Run6Bots() {
		// TODO Wait, does the user choose how many players and what kinds? 
		// in which case you would have to read in the number of Simple and Intermediate
		// from the console, and add players accordingly.  
		players = new ArrayList<Player>();
		players.add(new SimpleAIPlayer());
		players.add(new IntermediateAIPlayer());
		
		controller = new RiskController(players);
	}
	
	public static void main(String[] args) {
		new Run6Bots();
	}
}
