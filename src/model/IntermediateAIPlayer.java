package model;

public class IntermediateAIPlayer extends Player{

	@Override
	public void playTurn() {
		// TODO Auto-generated method stub
		// obsolete, should be deleted
	}

	@Override
	public void chooseTerritory() {
		// TODO Auto-generated method stub
		// obsolete, should be deleted
	}

	@Override
	public void placeArmy() {
		// TODO Auto-generated method stub
		// Semi-intelligently chooses a territory with two or more armies to attack from
		
	}

	@Override
	public Territory attackFrom() {
		// TODO Auto-generated method stub
		// Semi-intelligently chooses a territory adjacent to the parameter, owned by another player, to attack
		return null;
	}

	@Override
	public Territory attackTo(Territory attackFrom) {
		// TODO Auto-generated method stub
		// Semi-intelligently chooses a territory and places one army there
		return null;
	}

}
