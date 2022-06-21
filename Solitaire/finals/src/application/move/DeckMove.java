package application.move;

import application.model.GameModel;

public class DeckMove implements Move {
	
	GameModel gameModel;
	
	public DeckMove(GameModel gameModel) {
		this.gameModel = gameModel;
	}
	
	@Override
	public boolean move() {
		if (gameModel.discard()) {
			return true;
		}
		return false;
	}

}
