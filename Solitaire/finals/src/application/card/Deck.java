package application.card;

import java.util.Collections;
import java.util.Stack;

public class Deck {
	private Stack<Card> cards = new Stack<>();
	
	public void reset() {
		cards.clear();
		for(Suit suit : Suit.values()) {
			for(Rank rank : Rank.values()) {
				cards.push(Card.getCard(rank, suit));
			}
		}
		
		Collections.shuffle(cards);
	}
	
	public boolean isEmpty() {
		return this.cards.isEmpty();
	}
	
	public Card draw() {
		assert !isEmpty();
		return this.cards.pop();
	}
	
	public void add(Card card) {
		cards.add(card);
	}
}
