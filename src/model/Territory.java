package model;

import java.util.ArrayList;

public class Territory {
	private String name;
	private Player currentOwner;
	private int numArmies;
	private ArrayList<Territory> adjacent;
	private Continent continent;
	// need color
	
	public Territory(String name, Continent continent) {
		this.name = name;
		currentOwner = null;
		numArmies = 0;
		adjacent = new ArrayList<Territory>();
	}
	
	// this one isn't used
	public Territory(String name, ArrayList<Territory> adjacent, Continent continent) {
		this.name = name;
		this.adjacent = adjacent;
		this.continent = continent;
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
		for (Territory t : adjacent) {
			if (toCheck.equals(t)) return true;
		}
		return false;
	}
}
