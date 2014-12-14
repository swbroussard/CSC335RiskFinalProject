package model;

/**
 * 
 * @author Elizabeth Harris and team
 *
 */
public class Card {
	public enum CardType {
		CANNON, HORSEMAN, FOOT_SOLDIER, WILD;
	}
	
	private CardType type;
	private Territory cardTerritory;
	private boolean inDeck = true;
	
	/**
	 * Constructor
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
	 * @return true if the current card is in the game's deck
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
	
	public String toString() {
		//String deckness;
		//if (inDeck)
		//	deckness = "is in deck";
		//else
		//	deckness = "is not in deck";
		return type + ": " + cardTerritory;
	}
}
