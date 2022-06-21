package application.gui;

import java.util.*;

import application.card.Card;
import javafx.scene.image.*;

public class CardImages {
	
	private static Map<String, Image> cards = new HashMap<>();
	private static final String IMAGE_LOCATION = "cards/";
	private static final String IMAGE_SUFFUX = ".gif";
	
	private static final String[] RANK_CODES = { "a", "2", "3", "4", "5", "6", "7", "8", "9", "t", "j", "q", "k" };
	private static final String[] SUIT_CODES = { "c", "d", "s", "h" };
	
	private static String getCode(Card card) {
		return RANK_CODES[card.getRank().ordinal()] + SUIT_CODES[card.getSuit().ordinal()];
	}
	
	public static Image getImage(Card card) {
		assert card != null;
		return getImage(getCode(card));
	}
	
	private static Image getImage(String code) {
		Image image = cards.get(code);
		if (image == null) {
			image = new Image(CardImages.class.getClassLoader().getResourceAsStream(IMAGE_LOCATION + code + IMAGE_SUFFUX));
			cards.put(code, image);
		}
		
		return image;
 	}
	
	public static Image getBack() {
		return getImage("b");
	}
}
