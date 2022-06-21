package application.gui;

import application.model.GameModel;
import javafx.scene.control.Button;

public class NewGame extends Button {
		
	public NewGame() {
		this.setText("New Game");
		this.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000");
		this.setOnMousePressed(event -> {
			GameModel.getInstance().reset();
		});
	}
		
}
