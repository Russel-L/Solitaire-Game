package application.model;

import java.util.HashMap;
import java.util.Map;

import application.card.Card;
import application.card.Rank;

public class SuitStackManager {
	
	private final Map<SuitStack, Card> suitStackMap = new HashMap<>();
	
	public enum SuitStack implements Location{
		StackClubs, StackDiamonds, StackSpades, StackHearts, 
	}
	
	private final Suitstack[] suitStacks = new Suitstack[SuitStack.values().length];

	public void add(Card card, SuitStack index) {
		assert card != null;
		if (!suitStackMap.containsKey(SuitStack.values()[card.getSuit().ordinal()])) {
			suitStackMap.put(SuitStack.values()[card.getSuit().ordinal()], card);
			suitStacks[card.getSuit().ordinal()] = new Suitstack();
			suitStacks[card.getSuit().ordinal()].push(card);
		}
		else {
			suitStackMap.replace(SuitStack.values()[card.getSuit().ordinal()], card);
			suitStacks[index.ordinal()].push(card);
		}
	}
	
	public boolean canDraw(Location suitStack) {
		if (suitStackMap.containsKey(suitStack)) {
			return true;
		}
		
		return false;
	}
	
	public boolean canAdd(Card card) {
		assert card != null;
		if(!suitStackMap.containsKey(SuitStack.values()[card.getSuit().ordinal()])) {
			if (card.getRank() == Rank.ACE) {
				return true;
			}
		}
		else {
			if (suitStackMap.get(SuitStack.values()[card.getSuit().ordinal()]).getRank().ordinal() + 1 
					== card.getRank().ordinal()) {
				return true;
			}
		}
		
		return false;
	}
	
	public Card draw(SuitStack index) {
		Card card = suitStacks[index.ordinal()].draw();
		if (suitStacks[index.ordinal()].isEmpty()) {
			suitStackMap.remove(SuitStack.values()[card.getSuit().ordinal()]);
		}
		else {
			suitStackMap.replace(SuitStack.values()[card.getSuit().ordinal()], suitStacks[index.ordinal()].peek());
		}
		
		return card;
	}
	
	public Card viewSuitStack(SuitStack suitStack) {
		if (suitStackMap.containsKey(suitStack)) {
			return suitStackMap.get(suitStack);
		}
		else {
			return null;
		}
	}
}
