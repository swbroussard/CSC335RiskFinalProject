package model;

/**
 * 
 * @author Elizabeth Harris and team
 *
 */
public class Card {
	private String cardType;
	private Territory cardTerritory;
	
	/**
	 * Constructor
	 */
	public Card() {
		
	}
	
	/**
	 * Setter for <code>cardType</code>
	 * @param cardType
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
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
	public String getCardType() {
		return cardType;
	}
	
	/**
	 * Getter for <code>cardTerritory</code>
	 * @return cardTerritory
	 */
	public Territory getCardTerritory() {
		return cardTerritory;
	}
}
