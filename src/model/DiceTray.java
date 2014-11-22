package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiceTray implements Comparable<DiceTray> {
private List<Dice> Tray = new ArrayList<Dice>();
	private int minDice; 
	
	/**
	 * constructor to pass in the minimum number of dice between the attacker and defender
	 * @param numDice
	 */
	public DiceTray(int numDice){
		minDice = numDice;
		this.Tray.add(Dice);
		this.Tray.add(Dice);
		this.Tray.add(Dice);
		Collections.sort(list);
	}
	
	@Override
	public int compareTo(DiceTray otherDice) {
		//return winnerOfRoll();
		return 0;
	}
	
	public void clearTray(){
		Tray.clear();
	}

	private int winnerOfRoll(){
		return 0;
	}
}
