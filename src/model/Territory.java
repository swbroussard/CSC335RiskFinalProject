package model;

import java.util.ArrayList;

/**
 * 
 * @author Elizabeth Harris and team
 *
 */

public class Territory {
	private String name;
	private Player currentOwner;
	private int numArmies;
	private ArrayList<Territory> adjacent;
	private Continent continent;
	private int color;
	
	/**
	 * 
	 * @param name
	 * @param continent
	 */
	public Territory(String name, Continent continent) {
		this.name = name;
		currentOwner = null;
		numArmies = 0;
		adjacent = new ArrayList<Territory>();
	}
	
	/**
	 * 
	 * @param name
	 * @param continent
	 * @param colorRGB
	 */
	public Territory(String name, Continent continent, int colorRGB) {
		this.name = name;
		currentOwner = null;
		numArmies = 0;
		color = colorRGB;
		adjacent = new ArrayList<Territory>();
	}
	
	/**
	 * Unused
	 * @param name
	 * @param adjacent
	 * @param continent
	 */
	public Territory(String name, ArrayList<Territory> adjacent, Continent continent) {
		this.name = name;
		this.adjacent = adjacent;
		this.continent = continent;
		currentOwner = null;
		numArmies = 0;
	}
	
	/**
	 * 
	 * @param currentOwner
	 */
	public void setCurrentOwner(Player currentOwner) {
		this.currentOwner = currentOwner;
	}
	
	/**
	 * 
	 * @param numArmies
	 */
	public void setNumArmies(int numArmies){
		this.numArmies = numArmies;
	}
	
	/**
	 * 
	 * @param adjacent
	 */
	public void setAdjacent(ArrayList<Territory> adjacent) {
		this.adjacent = adjacent;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return
	 */
	public Player getCurrentOwner() {
		return currentOwner;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumArmies() {
		return numArmies;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getColor() {
		return color;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Territory> getAdjacent() {
		return adjacent;
	}
	
	/**
	 * 
	 * @param toCheck
	 * @return
	 */
	public boolean isAdjacent(Territory toCheck) {
		for (Territory t : adjacent) {
			if (toCheck.equals(t)) return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public Continent getContinent() {
		return continent;
	}
	
	public String toString() {
		return getName() + " " + getNumArmies() + " (" + currentOwner.getName() + ")";
	}
}
