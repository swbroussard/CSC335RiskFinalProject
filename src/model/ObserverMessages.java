package model;

/**
 * Enum for messages passed from the observable classes to the observer, that is, 
 * from Player and RiskController to RiskGUI. 
 * @author Steven Broussard, Elizabeth Harris, Jeremy Jalnos, Becca Simon
 *
 */
public enum ObserverMessages {
	NUM_ARMIES_UPDATED, HUMAN_PLACE_ARMY, HUMAN_SELECT_ATTACK_FROM, HUMAN_SELECT_ATTACK_TO,
	HUMAN_SELECT_REINFORCE_FROM, HUMAN_SELECT_REINFORCE_TO, HUMAN_SELECT_NUM_ARMIES, HUMAN_TRY_AGAIN_ARMIES,
	START_EXPLOSION, NEW_TURN, HUMAN_ATTACK_MOVE_ARMIES
}
