package application.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import application.model.GameModel;
import application.model.GameModelListener;
import application.model.GameModel.CardDeck;
import javafx.event.EventHandler;

public class DeckView extends HBox implements GameModelListener{
	
	
	public DeckView() {
		GameModel.getInstance().reset();
		Button button = new Button();
		button.setGraphic(new ImageView(CardImages.getBack()));
		button.setStyle("-fx-background-color: transparent; -fx-padding: 5,5,5,5;");
		
		button.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				((Button) event.getSource()).setStyle("-fx-background-color: transparent; -fx-padding: 6,4,4,6;");
			}
		});
		
		button.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				((Button) event.getSource()).setStyle("-fx-background-color: transparent; -fx-padding: 5,5,5,5;");
				
				if (!GameModel.getInstance().canDraw(CardDeck.DECK)) {
					button.setGraphic(new ImageView(CardImages.getBack()));
					GameModel.getInstance().recycle();
				}else {
					GameModel.getInstance().getDiscardMove().move();
				}
			}
		});
		getChildren().add(button);
		GameModel.getInstance().addListener(this);
	}

	@Override
	public void gameStateChanged() {
		if (!GameModel.getInstance().canDraw(CardDeck.DECK)) {
			((Button) getChildren().get(0)).setGraphic(RecycleImage());
		}
		else {
			((Button) getChildren().get(0)).setGraphic(new ImageView(CardImages.getBack()));
		}
	}

	private Canvas RecycleImage() {
		double width = CardImages.getBack().getWidth();
		double height = CardImages.getBack().getWidth();
		Canvas canvas = new Canvas(width, height); 
		GraphicsContext context = canvas.getGraphicsContext2D();
		context.setTextAlign(TextAlignment.CENTER);
		context.setFill(Color.DARKKHAKI);
		context.setFont(Font.font(Font.getDefault().getName(), 12));
		context.fillText("Recycle", Math.round(width/2), 12);
		context.setStroke(Color.DARKGREEN);
		context.setLineWidth(10);
		context.strokeOval(width / 4, height / 2 - width/4 + 12, width / 2, width / 2);
		return canvas;
	}

}
