package application.model;

import java.util.Iterator;
import java.util.Stack;

import application.card.Card;

public class Suitstack implements Iterable<Card>{

	private final Stack<Card> suitStack = new Stack<Card>();

	@Override
	public Iterator<Card> iterator() {
		return suitStack.iterator();
	}
	
	public void push(Card card) {
		suitStack.push(card);
	}
	
	public boolean isEmpty() {
		return suitStack.isEmpty();
	}
	
	public Card peek() {
		assert !suitStack.isEmpty();
		return suitStack.peek();
	}
	
	public Card draw() {
		assert !suitStack.isEmpty();
		
		return suitStack.pop();
	}
}
