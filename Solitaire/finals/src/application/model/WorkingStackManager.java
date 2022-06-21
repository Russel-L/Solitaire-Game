package application.model;

import application.card.Deck;
import java.util.Stack;
import application.card.Card;

public class WorkingStackManager {
	
	public enum Workingstack implements Location{
		StackOne, StackTwo, StackThree, StackFour, StackFive, StackSix, StackSeven;
	}
	
	private final WorkingStack[] workingStacks = new WorkingStack[Workingstack.values().length];
	
	public WorkingStackManager(Deck deck) {
		for (int i = 0; i < workingStacks.length; i++) {
			workingStacks[i] = new WorkingStack(deck, Workingstack.StackOne.ordinal() + 1 + i);
		}
	}
	
	public Stack<Card> getWorkingStack(Workingstack index) {
		Stack<Card> stack = new Stack<>();
		for (Card card : this.workingStacks[index.ordinal()]) {
			stack.push(card);
		}
		
		return stack;
	}
	
	public void add(Card card, Workingstack index) {
		workingStacks[index.ordinal()].push(card);
	}
	
	public boolean canAdd(Card card, Workingstack index) {
		assert card != null;
		if (workingStacks[index.ordinal()].isEmpty()) {
			return true;
		}
		else {
			if ((card.getSuit().ordinal() + workingStacks[index.ordinal()].peek().getSuit().ordinal()) % 2 != 0) {
				if (card.getRank().ordinal() == workingStacks[index.ordinal()].peek().getRank().ordinal() - 1) {
					return true;
				}
			}
		}
		return false;
	}
	
	public Card draw(Workingstack index) {
		Card card = workingStacks[index.ordinal()].draw();
		return card;
	}
	
	public Stack<Card> getCards(Workingstack index) {
		Stack<Card> stack = new Stack<>();
		for (Card card : workingStacks[index.ordinal()]) {
			stack.push(card);
		}
		return stack;
	}
	
	public boolean canDraw(Workingstack index) {
		if (workingStacks[index.ordinal()].isEmpty()) {
			return false;
		}
		return true;
	}
	
	public void addMultiple(Stack<Card> stack, Workingstack index) {
		assert canAdd(stack.lastElement(), index);
		while (!stack.isEmpty()) {
			workingStacks[index.ordinal()].push(stack.pop());
		}
	}
	
	public Stack<Card> drawMultiple(Card card, Workingstack index) {
		assert canDraw(index);
		Stack<Card> stack = new Stack<>();
		while(card != workingStacks[index.ordinal()].peek()) {
			stack.push(workingStacks[index.ordinal()].draw());
		}
		stack.push(workingStacks[index.ordinal()].draw());
		
		
		return stack;
	}
}
