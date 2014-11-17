package model;

import java.util.Random;

/**
 * @author jeremyjalnos stevenBroussard
 */
public class SimpleAIPlayer extends Player {

	// instance variables
	Random randomGen;
	Random randomAdjacent;

	// constructor
	public SimpleAIPlayer() {
		randomGen = new Random(1); // static state from the random generator
		// randomGen = new Random(); //random generator
		randomAdjacent = new Random(1);

	}

	/**
	 * sets up whether or not the player will attack or reinforce
	 */
	@Override
	public void playTurn() {
		// obsolete - we no longer need this method in Player. however, much of
		// this code looks like it could be useful elsewhere
		// Much of this might be the controller's responsibility
		int decisionAttack = (int) Math.random();
		int decideTerritory = randomGen.nextInt(getTerritories().size());
		if (decisionAttack == 0) { // -the simple AI is random so randomly
									// generating whether or not to attack and
									// whether or not to reinforce --Jeremy &
									// Steven
			Territory t = getTerritories().get(decideTerritory);

			int decisionAdjacent = (t.getAdjacent().size());
			Territory toAttack = t.getAdjacent().get(
					randomAdjacent.nextInt(decisionAdjacent));
			if (getTerritories().contains(toAttack)) {
				toAttack = t.getAdjacent().get(
						randomAdjacent.nextInt(decisionAdjacent)); // Make Sure
																	// that the
																	// current
																	// player
																	// does not
																	// already
																	// own this
																	// territory
			}

			// need an attack method
			if (canAttack()) {
				attackTo(attackFrom());

			}

		}
		if (decisionAttack == 1) { // don't attack
			return;
		}
		/*
		 * if decision is to reinforce find the territory with the fewest armies
		 * and the territory with the most and swap a few armies -Jeremy
		 */

		int decisionReinforce = (int) Math.random();
		if (decisionReinforce == 0) {
			int armies = 0;
			int armyMax = 0;
			Territory reinforceThis = null;
			Territory takeArmy = null;
			for (Territory t2 : getTerritories()) {
				if (t2.getNumArmies() >= armyMax) { // get most Armies
					takeArmy = t2;
					armyMax = t2.getNumArmies();
				}
				if (t2.getNumArmies() <= armies) { // get least Armies
					reinforceThis = t2;
					armies = t2.getNumArmies();
				}

			}
			reinforceArmies(takeArmy, reinforceThis);

		}
	}

	/**
	 * Randomly chooses a territory with two or more armies to attack from
	 */
	@Override
	public Territory attackFrom() {
		int choosenTerritory = 0;
		choosenTerritory = randomGen.nextInt(getTerritories().size());
		return getTerritories().get(choosenTerritory);
	}

	/**
	 * @param attackFrom
	 * 
	 * Randomly chooses a territory adjacent to the parameter, owned by another player, to attack
	 * 
	 * @return a territory object
	 */
	@Override
	public Territory attackTo(Territory attackFrom) {
		int choosenAttack = 0;
		choosenAttack = randomGen.nextInt(attackFrom().getAdjacent().size());
		return attackFrom().getAdjacent().get(choosenAttack); // Because Steven
																// is a boss
																// -Jeremy

	}

	/**
	 * places armies for the beginning of game, one soldier at a time in a territory at a time
	 */
	@Override
	public void placeArmy() {
		// Randomly chooses a territory and places one army there at the
		// beginning of the game--Jeremy & Steven
		int choosenTerritory = 0;
		choosenTerritory = randomGen.nextInt(getTerritories().size());
		int i = getTerritories().size();
		int x = 0;
		while (getTerritories().get(x).getCurrentOwner() != null) {

			if (getTerritories().get(x).getCurrentOwner() == null) {
				getTerritories().get(x).setCurrentOwner(this);
				getTerritories().get(x).setNumArmies(1);
				break;
			} else {
				x++;
				if (x == i) {
					break;
				}
			}
		}

	}

	/**
	 * @param takeArmy
	 * @param reinforceThis
	 * 
	 */
	@Override
	public void reinforceArmies(Territory takeArmy, Territory reinforceThis) {
		int takeArmyFrom = 0;

		takeArmyFrom = randomGen.nextInt(takeArmy.getNumArmies() - 1);
		reinforceThis.setNumArmies(getNumArmies() + takeArmyFrom);
		takeArmy.setNumArmies(getNumArmies() - takeArmyFrom);

	}

}