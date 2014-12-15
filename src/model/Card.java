package model;

/**
 * Represents a card from the Risk deck. Cards are of one of four types - artillery/cannon,
 * infantry/foot soldier, cavalry/horseman, or wild. Each card except wildcards has a territory
 * on its face. 
 * @author Steven Broussard, Elizabeth Harris, Jeremy Jalnos, Rebecca Simon
 * 
 */
public class Card {
	/**
	 * Enum for card types
	 * @author Steven Broussard, Elizabeth Harris, Jeremy Jalnos, Rebecca Simon
	 *
	 */
	public enum CardType {
		CANNON, HORSEMAN, FOOT_SOLDIER, WILD;
	}
	
	private CardType type;
	private Territory cardTerritory;
	private boolean inDeck = true;
	
	/**
	 * Constructor. Creates a new Card object with the given type and territory. 
	 */
	public Card(CardType c, Territory t) {
		type = c;
		cardTerritory = t;
	}
	
	/**
	 * Compares one card to another
	 * @param c the card to be compared to the current card
	 * @return true if the cards' territories and types are equal, false otherwise. 
	 */
	public boolean equals(Card c) {
		return (c.type == this.type) && (c.cardTerritory.equals(this.cardTerritory));
	}
	
	/**
	 * Setter for <code>cardType</code>
	 * @param cardType
	 */
	public void setCardType(CardType c) {
		this.type = c;
	}
	
	/**
	 * Setter for <code>cardTerritory</code>
	 * @param cardTerritory
	 */
	public void setCardTerritory(Territory cardTerritory) {
		this.cardTerritory = cardTerritory;
	}
	
	/**
	 * Getter for <code>cardType</code>
	 * @return cardType
	 */
	public CardType getCardType() {
		return type;
	}
	
	/**
	 * Getter for <code>cardTerritory</code>
	 * @return cardTerritory
	 */
	public Territory getCardTerritory() {
		return cardTerritory;
	}

	/**
	 * @return true if the current card is in the game's deck, 
	 * false if it's in a player's hand
	 */
	public boolean isInDeck() {
		return inDeck;
	}

	/**
	 * @param inDeck boolean value representing whether the current card is in
	 * the game's deck
	 */
	public void setInDeck(boolean inDeck) {
		this.inDeck = inDeck;
	}
	
	/**
	 * Returns a string representation of the card of the format CARD_TYPE: Territory
	 * @return String
	 */
	public String toString() {
		//String deckness;
		//if (inDeck)
		//	deckness = "is in deck";
		//else
		//	deckness = "is not in deck";
		if (cardTerritory != null)
			return type + ": " + cardTerritory.getName();
		else
			return type + ": no territory";
	}
}
