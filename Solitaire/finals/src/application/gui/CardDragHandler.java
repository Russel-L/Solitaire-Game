package application.gui;

import application.card.Card;
import javafx.scene.image.ImageView;
import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class CardDragHandler implements EventHandler<MouseEvent> {
	
	private static final ClipboardContent CLIPBOARD_CONTENT = new ClipboardContent();
	
	private Card card;
	private ImageView image;
	
	CardDragHandler(ImageView image) {
		this.image = image;
	}
	
	public void setCard(Card card) {
		this.card = card;
	}

	@Override
	public void handle(MouseEvent event) {
		Dragboard db = image.startDragAndDrop(TransferMode.ANY);
        CLIPBOARD_CONTENT.putString(card.getIDString());
        db.setContent(CLIPBOARD_CONTENT);
        event.consume();
		
	}
}
