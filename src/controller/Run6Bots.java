package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.IntermediateAIPlayer;
import model.Player;
import model.SimpleAIPlayer;

public class Run6Bots {
	private RiskController controller;
	private ArrayList<Player> players;
	
	public Run6Bots() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of SimpleAIPlayers");
		int simplePlayers = Integer.parseInt(scan.next());
		System.out.println("Enter the number of IntermediateAIPlayers");
		int intermediatePlayers = Integer.parseInt(scan.next());
		players = new ArrayList<Player>();
		for (int i = 0; i < simplePlayers; i++) {
			players.add(new SimpleAIPlayer());
		}
		for (int i = 0; i < intermediatePlayers; i++) {
			players.add(new IntermediateAIPlayer());	
		}
		int simpleWon = 0;
		int intermediateWon = 0;
		for (int i = 0; i < 1000; i++) {
			controller = new RiskController(players);
			if (controller.getPlayers().get(0) instanceof SimpleAIPlayer)
				simpleWon++;
			else
				intermediateWon++;
		}
		double simplePercent = 100.0*simpleWon/1000;
		double intermediatePercent = 100.0*intermediateWon/1000;
		System.out.println("SimpleAIPlayers won "+simpleWon+" times ("+
				simplePercent+"%.");
		System.out.println("IntermediateAIPlayers won "+intermediateWon+" times"
				+" ("+intermediatePercent+"%.");
		scan.close();
	}
	
	public static void main(String[] args) {
		new Run6Bots();
	}
}
