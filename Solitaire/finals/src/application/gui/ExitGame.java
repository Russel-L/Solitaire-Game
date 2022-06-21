package application.gui;

import javafx.application.Platform;
import javafx.scene.control.Button;

public class ExitGame extends Button {
	
	
	public ExitGame() {
		this.setText("Exit Game");
		this.setStyle("-fx-background-color: #EE0000; -fx-text-fill: #000000");
		this.setOnMousePressed(event -> {
			Platform.exit();
		});
	}
}
