package model;

public class Card {
	private String cardType;
	private Territory cardTerritory;
	
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
	public void setCardTerritory(Territory cardTerritory) {
		this.cardTerritory = cardTerritory;
	}
	
	public String getCardType() {
		return cardType;
	}
	
	public Territory getCardTerritory() {
		return cardTerritory;
	}
}
