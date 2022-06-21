package application.gui;

import application.card.Card;
import application.model.GameModel;
import application.model.GameModel.CardDeck;
import application.model.GameModelListener;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class WasteView extends HBox implements GameModelListener{
	
	private static final int PADDING = 5;
	private CardDragHandler dragHandler;
	
	public WasteView() {
		setPadding(new Insets(PADDING));
		final ImageView image = new ImageView(CardImages.getBack());
		image.setVisible(false);
		
		dragHandler = new CardDragHandler(image);
		image.setOnDragDetected(dragHandler);
		getChildren().add(image);
		GameModel.getInstance().addListener(this);
	}
	@Override
	public void gameStateChanged() {
		if (!GameModel.getInstance().canDraw(CardDeck.DISCARD)) {
			getChildren().get(0).setVisible(false);
		}
	
		else {
			getChildren().get(0).setVisible(true);
			Card topCard = GameModel.getInstance().peekWaste();
			ImageView image = (ImageView) this.getChildren().get(0);
			image.setImage(CardImages.getImage(topCard));
			dragHandler.setCard(topCard);
		}
	}
}
