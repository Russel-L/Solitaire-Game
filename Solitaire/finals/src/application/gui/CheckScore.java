package application.gui;


import java.io.IOException;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CheckScore  extends Button {
	
	
	public CheckScore() {
		this.setText("Leaderboard");
		this.setStyle("-fx-background-color: #22A517; -fx-text-fill: #000000");
		this.setOnMouseClicked(event -> {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("view/ScoreBoard.fxml"));
				AnchorPane scorefxml = (AnchorPane) loader.load();
				
				Scene scene = new Scene(scorefxml);
				scene.getStylesheets().add("application/application.css");
				Stage ScoreStage = new Stage();
				ScoreStage.setTitle("Leaderboards");
				ScoreStage.initModality(Modality.WINDOW_MODAL);
				ScoreStage.initOwner(Main.PrimaryStage);
				ScoreStage.setScene(scene);
				
				ScoreController scontroller = loader.getController();
				scontroller.setCSS();
				scontroller.putScores();
				ScoreStage.show();
				
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		});
	}
}
