package application.gui;

import application.model.GameModel;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ScoreBoard extends Label {
	
	
	Font f = Font.font("Verdana", FontWeight.BOLD, 15);
	public ScoreBoard() {
		this.setTextFill(Color.WHITE);
		this.setFont(f);
		this.setText("Score: " + Integer.toString(GameModel.getInstance().getScore()));
	}
	
	public void setScoreBoard() {
		this.setText("Score: " + Integer.toString(GameModel.getInstance().getScore()));
	}
}
