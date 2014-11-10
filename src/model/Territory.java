package model;

import java.util.ArrayList;

public class Territory {
	private String name;
	private Player currentOwner;
	private int numArmies;
	private ArrayList<Territory> adjacent;
	
	public Territory(String name) {
		this.name = name;
		currentOwner = null;
		numArmies = 0;
		adjacent = new ArrayList<Territory>();
	}
	
	public Territory(String name, ArrayList<Territory> adjacent) {
		this.name = name;
		this.adjacent = adjacent;
		currentOwner = null;
		numArmies = 0;
	}
	
	public void setCurrentOwner(Player currentOwner) {
		this.currentOwner = currentOwner;
	}
	
	public void setNumArmies(int numArmies){
		this.numArmies = numArmies;
	}
	
	public void setAdjacent(ArrayList<Territory> adjacent) {
		this.adjacent = adjacent;
	}
	
	public String getName() {
		return name;
	}
	
	public Player getCurrentOwner() {
		return currentOwner;
	}
	
	public int getNumArmies() {
		return numArmies;
	}
	
	public ArrayList<Territory> getAdjacent() {
		return adjacent;
	}
	
	public boolean isAdjacent(Territory toCheck) {
		return false;
	}
}
