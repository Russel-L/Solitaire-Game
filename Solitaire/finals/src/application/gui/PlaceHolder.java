package application.gui;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class PlaceHolder extends HBox {

	private static final int PADDING = 5;
	
	public PlaceHolder() {
		setPadding(new Insets(PADDING));
		final ImageView image = new ImageView(CardImages.getBack());
		image.setVisible(false);
		getChildren().add(image);
	}
}
