package application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import application.Main;
import application.card.Card;
import application.card.Deck;
import application.model.SuitStackManager.SuitStack;
import application.model.WorkingStackManager.Workingstack;
import application.move.DeckMove;
import application.move.Move;
import application.move.MultipleCardsMove;
import application.move.OneCardMove;

public class GameModel {
	
	private static final GameModel INSTANCE = new GameModel();
	private Deck deck = new Deck();
	private Stack<Card> waste;
	private List<GameModelListener> listenerList = new ArrayList<>();
	private WorkingStackManager workingStackManager;
	private SuitStackManager suitStackManager;
	private int score = -35;
	public static int checker = 0;
	public static int checker2 = 0;
	
	public enum CardDeck implements Location {
		DECK, DISCARD;
	}
	public static GameModel getInstance() {
		return INSTANCE;
	}
	
	public int getScore() {
		if (score < 0) {
			score = 0;
		}
		return getInstance().score;
	}
	
	public void setScore(int score) {
		getInstance().score = score;
	}
	
	public void addListener(GameModelListener listener) {
		listenerList.add(listener);
	}
	
	private void notifyListener() {
		for(GameModelListener listener : listenerList) {
			listener.gameStateChanged();
		}
	}
	
	public Move getDeckMove() {
		return new DeckMove(getInstance());
	}
	
	public void reset() {
		deck.reset();
		workingStackManager = new WorkingStackManager(deck);
		checker += 1;
		waste = new Stack<Card>();
		suitStackManager = new SuitStackManager();
		checker2 = 7;
		score = 0;
		Main.scoreBoard.setScoreBoard();
		notifyListener();
	}
	
	public void recycle() {
		deck = new Deck();
		while (!waste.isEmpty()) {
			deck.add(waste.pop());
		}
		getInstance().setScore(getInstance().getScore() - 100);
		Main.scoreBoard.setScoreBoard();
	}
	public boolean discard() {
		if(!this.deck.isEmpty()) {
			waste.add(deck.draw());
			notifyListener();
			return true;
		}
		return false;
	}

	public Card peekWaste() {
		if(waste.isEmpty()) {
			return null;
		}
		return waste.peek();
	}
	
	public Card peekSuitStack(SuitStack index) {
		return suitStackManager.viewSuitStack(index);
	}
	
	public Move getDiscardMove() {
		return new DeckMove(GameModel.getInstance()); 
	}
	
	
	public boolean canDraw(Location location) {
		
		if (location instanceof Workingstack) {
			if(workingStackManager.canDraw((Workingstack) location)) {
				return true;
			}
		}
		if (location instanceof SuitStack) {
			if (suitStackManager.canDraw(location)) {
				return true;
			}
		}
		
		if (location.equals(CardDeck.DECK)) {
			if(!deck.isEmpty()) {
				return true;
			}
		}
		if (location.equals(CardDeck.DISCARD)) {
			if(!waste.isEmpty()) {
				return true;
			}
		}
		return false;
	}
	
	public Card[] getStack(Workingstack index) {
		Card[] cards = new Card[workingStackManager.getWorkingStack(index).size()];
		for(int i = 0; i < workingStackManager.getWorkingStack(index).size(); i++) {
			cards[i] = workingStackManager.getWorkingStack(index).get(i);
		}
		return cards;
	}
	
	public boolean move(Location source, Location destination, Card card){
		if (canDraw(source) && canAdd(card, destination)) {
			workingStackManager.addMultiple(workingStackManager.drawMultiple(card, 
					(Workingstack) source), (Workingstack) destination);
			notifyListener();
			return true;
		}
		
		return false;
	}
	
	public Move getCardMove(Card top, Location destination) {
		if (top.equals(peekWaste())) {
			return new OneCardMove(CardDeck.DISCARD, destination, getInstance());
		}
		
		for (SuitStack ss : SuitStack.values()) {
			if (suitStackManager.canDraw(ss)) {
				if (suitStackManager.viewSuitStack(ss).equals(top))
				return new OneCardMove(ss, destination, getInstance());
			}
		}
		
		for (Workingstack ws : Workingstack.values()) {
			if (!workingStackManager.getCards(ws).isEmpty() && workingStackManager.getCards(ws).peek().equals(top)) {
				return new OneCardMove(ws, destination, getInstance());
			}
			for (Card c : workingStackManager.getCards(ws)) {
				if (c.equals(top)) {
					return new MultipleCardsMove(ws, destination, c, getInstance());
				}
			}
		}
		return null;
	}

	public boolean move(Location source, Location destination) {
		
		if (source instanceof Workingstack && destination instanceof SuitStack) {
			if (canDraw(source) && suitStackManager.canAdd(workingStackManager.getCards((Workingstack) source).peek())) {
				suitStackManager.add(workingStackManager.draw((Workingstack) source), (SuitStack) destination);
				notifyListener();
				getInstance().score += 10;
				Main.scoreBoard.setScoreBoard();
				return true;
			}
		}
		if (source.equals(CardDeck.DISCARD) && destination instanceof SuitStack) {
			if (canDraw(source) && canAdd(waste.peek(), destination)) {
				suitStackManager.add(waste.pop(), (SuitStack) destination);
				notifyListener();
				getInstance().score += 10;
				Main.scoreBoard.setScoreBoard();
				return true;
			}
		}
		if (source.equals(CardDeck.DISCARD) && destination instanceof Workingstack) {
			workingStackManager.add(waste.pop(), (Workingstack) destination);
			notifyListener();
			return true;
		}
		
		if (source instanceof Workingstack && destination instanceof Workingstack) {
			workingStackManager.add(workingStackManager.draw((Workingstack) source), (Workingstack) destination);
			notifyListener();
			return true;
		}
		
		if (source instanceof SuitStack && destination instanceof Workingstack) {
			if (canDraw(source)) {
				workingStackManager.add(suitStackManager.draw((SuitStack) source), (Workingstack) destination);
			}
			notifyListener();
			getInstance().score -= 15;
			Main.scoreBoard.setScoreBoard();
			return true;
		}
		return false;
	}
	
	public boolean canAdd(Card top, Location destination) {
		if (destination instanceof Workingstack) {
			if (workingStackManager.canAdd(top, (Workingstack) destination)) {
				return true;
			}
		}
		
		if (destination instanceof SuitStack) {
			if (suitStackManager.canAdd(top)) {
				return true;
			}
		}
		return false;
	}
	
	
}
