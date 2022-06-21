package application.gui;

import java.util.LinkedList;

import application.Main;
import application.card.Card;
import application.model.GameModel;
import application.model.GameModelListener;
import application.model.WorkingStackManager.Workingstack;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;

public class WorkingStackView extends StackPane implements GameModelListener{
	
	private static final int PADDING = 5;
	private static final int Y_OFFSET = 17;
	private Workingstack index;
	private LinkedList<Card> faceUpCards;
	
	public LinkedList<Card> getFaceUpCards () {
		return faceUpCards;
	}
	
	public WorkingStackView(Workingstack index, LinkedList<Card> faceUpCards) {
		this.index = index;
		setPadding(new Insets(PADDING));
		this.faceUpCards = faceUpCards;
		buildLayout();
		GameModel.getInstance().setScore(0);
		GameModel.getInstance().addListener(this);
	}
	
	private void buildLayout() {
		if (GameModel.checker != 0) {
			faceUpCards.clear();
			GameModel.checker = 0;
		}
		getChildren().clear();
		int offset = 0;
		Card[] stack = GameModel.getInstance().getStack(index);
		
		for (Card card : stack) {
			ImageView image;
			if (card == stack[stack.length-1] || faceUpCards.contains(card)) {
				if (!faceUpCards.contains(card)) {
					if (GameModel.checker2 != 0) {
						GameModel.getInstance().setScore(0);
						GameModel.checker2 -= 1;
						faceUpCards.add(card);
					}
					else {
					GameModel.getInstance().setScore(GameModel.getInstance().getScore() + 5);
					Main.scoreBoard.setScoreBoard();
					faceUpCards.add(card);
					}
				}
				image = new ImageView(CardImages.getImage(card));
			}
			else {
				image = new ImageView(CardImages.getBack());
			}
			image.setTranslateY(Y_OFFSET * offset);
			offset++;
			getChildren().add(image);
			
			setOnDragOver(createDragOverHandler());
			setOnDragDropped(createDragDroppedHandler());
			
			image.setOnDragDetected(createDragDetectedHandler(image, card));
		}
	}
	
	private EventHandler<MouseEvent> createDragDetectedHandler(final ImageView imageView, final Card card) {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				content.putString(card.getIDString());
				db.setContent(content);
				event.consume();
			}
		};
	}
	
	private EventHandler<DragEvent> createDragOverHandler() {
		return new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				if (GameModel.getInstance().canAdd(Card.get(event.getDragboard().getString()), index)) {
					event.acceptTransferModes(TransferMode.MOVE);
				}
				event.consume();
			}
		};
	}
	
	private EventHandler<DragEvent> createDragDroppedHandler() {
		return new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasString()) {
					GameModel.getInstance().getCardMove(Card.get(db.getString()), index).move();
					success = true;
				}
				
				event.setDropCompleted(success);
			}
		};
	}

	@Override
	public void gameStateChanged() {
		buildLayout();
	}
}
