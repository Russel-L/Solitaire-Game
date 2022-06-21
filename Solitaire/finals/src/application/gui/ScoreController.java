package application.gui;

import java.io.IOException;
import java.util.Vector;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScoreController {
	
	@FXML
	VBox vboxscore;
	
	@FXML 
	Button close;
	
	@FXML
	AnchorPane ap; 
	
	
	public void setCSS () {
		vboxscore.setStyle("-fx-background-color: #FFFFFF;");
		close.setStyle("-fx-background-color: #ffcd00; -fx-text-fill: #000000");
	}
	
	@FXML
	public void closeHS () {
		Stage s = (Stage) close.getScene().getWindow();
		s.close();
	}
	
	
	public void putScores() throws IOException { 
		Vector<Score> scores = ReadWrite.ReadFile(); 
		int i = 1;
		for (Score score : scores) { 
			if (i > 10) {
				return;
			}
			else {
			String line = " " + i + ". " + score.name +  " - " + score.score;
			vboxscore.getChildren().add(new Label(line)); 
			vboxscore.getChildren().get(i-1).setStyle("-fx-font-family: Arial; -fx-font-size: 15");
			i++;
			} 
		}
	}
}
