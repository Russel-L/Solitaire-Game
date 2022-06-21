package application.model;

import java.util.Iterator;
import java.util.Stack;

import application.card.Card;
import application.card.Deck;

public class WorkingStack implements Iterable<Card>{

	private final Stack<Card> workingStack = new Stack<>();
	
	public WorkingStack(Deck deck, int num) {
		for(int i = 0; i < num; i++) {
			workingStack.add(deck.draw());
		}
	}

	@Override
	public Iterator<Card> iterator() {
		return workingStack.iterator();
	}
	
	public void push(Card card) {
		workingStack.push(card);
	}
	
	public boolean isEmpty() {
		return workingStack.isEmpty();
	}
	
	public Card peek() {
		assert !workingStack.isEmpty();
		return workingStack.peek();
	}
	
	public Card draw() {
		assert !workingStack.isEmpty();
		
		return workingStack.pop();
	}
}
