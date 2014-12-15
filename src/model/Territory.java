package model;

import java.awt.Point;
import java.util.ArrayList;

/**
 * The <code>Territory</code> class represents, intuitively, a territory in the game of Risk, 
 * complete with name, owner, armies, continent, color, and list of adjacent territories. 
 * @author Steven Broussard, Elizabeth Harris, Jeremy Jalnos, Rebecca Simon
 *
 */

public class Territory {
	private String name;
	private Player currentOwner;
	private int numArmies;
	private ArrayList<Territory> adjacent;
	private Continent continent;
	private int color;
	private Point armyLabel;

	/**
	 * Constructs a new Territory object with the given parameters. 
	 * @param name, the name of the territory
	 * @param continent, the continent that the territory is part of
	 * @param colorRGB, the territory's color on the map image
	 * @param armyLabel, the position of the territory's label on the map image
	 */
	public Territory(String name, Continent continent, int colorRGB, Point armyLabel) {
		this.name = name;
		this.continent = continent;
		currentOwner = null;
		numArmies = 0;
		color = colorRGB;
		adjacent = new ArrayList<Territory>();
		this.armyLabel = armyLabel;
	}

	/**
	 * Checks if the parameter is adjacent to this Territory by looping through <code>adjacent</code>. 
	 * @param toCheck
	 * @return boolean
	 */
	public boolean isAdjacent(Territory toCheck) {
		for (Territory t : adjacent) {
			if (toCheck.equals(t)) return true;
		}
		return false;
	}

	/**
	 * Setter for <code>currentOwner</code>
	 * @param currentOwner
	 */
	public void setCurrentOwner(Player currentOwner) {
		this.currentOwner = currentOwner;
	}

	/**
	 * Setter for <code>numArmies</code>
	 * @param numArmies
	 */
	public void setNumArmies(int numArmies){
		this.numArmies = numArmies;
	}

	/**
	 * Setter for <code>adjacent</code>
	 * @param adjacent
	 */
	public void setAdjacent(ArrayList<Territory> adjacent) {
		this.adjacent = adjacent;
	}

	/**
	 * Getter for <code>name</code>
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for <code>currentOwner</code>
	 * @return currentOwner
	 */
	public Player getCurrentOwner() {
		return currentOwner;
	}

	/**
	 * Getter for <code>numArmies</code>
	 * @return numArmies
	 */
	public int getNumArmies() {
		return numArmies;
	}

	/**
	 * Getter for <code>color</code>
	 * @return color
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Getter for <code>adjacent</code>
	 * @return adjacent
	 */
	public ArrayList<Territory> getAdjacent() {
		return adjacent;
	}

	/**
	 * Getter for <code>continent</code>
	 * @return continent
	 */
	public Continent getContinent() {
		return continent;
	}

	/**
	 * Getter for the position of the label on the map in the GUI
	 * @return position of the label
	 */
	public Point getLabelPosition() {
		return armyLabel;
	}

	/**
	 * Returns a <code>String</code> representation of the <code>Territory</code> by concatenating its <code>name</code>, 
	 * <code>numArmies</code>, and <code>currentOwner</code>.  
	 * @return String
	 */
	public String toString() {
		if(currentOwner == null)
			return getName();
		return getName() + " " + getNumArmies() + " (" + currentOwner.getName() + ")";
	}
}
