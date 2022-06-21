package application.gui;

import java.io.IOException;
import application.Main;
import application.model.GameModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NameScore {

	private String iname;
	private int iscore; 
	
	@FXML 
	Button scoreboard;
	
	@FXML 
	Button submit;
	
	@FXML
	Text score;
	
	@FXML
	TextField playname;
	
	@FXML
	AnchorPane playerFXML;
	
	@FXML
	Label label;
	
	public void setCSS () {
		//playerFXML.setStyle("-fx-background-color: #09692a;");
		submit.setStyle("-fx-background-color: #ffcd00; -fx-text-fill: #000000");
		scoreboard.setStyle("-fx-background-color: #ffcd00; -fx-text-fill: #000000");
	}
	
	public void setScore() {
		score.setText(Integer.toString(GameModel.getInstance().getScore()));
		iscore = GameModel.getInstance().getScore();
	}
	
	@FXML
	private void SubmitScore() throws IOException {
		if (!playname.getText().isEmpty()) {
			if (playname.getText().length() > 10 || playname.getText().contains(" ")) {
				label.setWrapText(true);
				label.setText("Name must be shorter than 10 characters and should not contain a space");
			}
			else {
				iname = playname.getText();
				Score score = new Score(iname, iscore);
				ReadWrite.addScoreToFile(score);
				label.setAlignment(Pos.CENTER);
				label.setText(iname + " scored " + iscore + "!");
			}
		}
		else {
			label.setAlignment(Pos.CENTER);
			label.setText("Put a name");
		}
	}
	
	@FXML 
	private void openSB() {
		try {
			Stage s = (Stage) scoreboard.getScene().getWindow();
			s.close();
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/ScoreBoard.fxml"));
			AnchorPane scorefxml = (AnchorPane) loader.load();
			
			Scene scene = new Scene(scorefxml);
			scene.getStylesheets().add("application/application.css");
			Stage ScoreStage = new Stage();
			ScoreStage.setTitle("Leaderboard");
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
	}
}
