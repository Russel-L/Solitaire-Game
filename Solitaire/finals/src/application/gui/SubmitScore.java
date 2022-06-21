package application.gui;

import java.io.IOException;


import application.Main;
import application.model.GameModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;

public class SubmitScore extends Button {

	public SubmitScore() {
		File saves = new File("saves.txt");
		try {
			saves.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setText("Submit Score");
		this.setStyle("-fx-background-color:#ffcd00; -fx-text-fill: #000000");
		this.setOnMouseClicked(event -> {
			try {

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("view/Player.fxml"));
				AnchorPane playerFXML = (AnchorPane) loader.load();

				Scene scene = new Scene(playerFXML);
				scene.getStylesheets().add("application/application.css");
				Stage ScoreStage = new Stage();
				ScoreStage.setTitle("Leaderboard");
				ScoreStage.initModality(Modality.WINDOW_MODAL);
				ScoreStage.initOwner(Main.PrimaryStage);
				ScoreStage.setScene(scene);

				NameScore pcontroller = loader.getController();
				pcontroller.setCSS();
				pcontroller.setScore();
				GameModel.getInstance().reset();
				ScoreStage.show();


			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		});
	}
}
