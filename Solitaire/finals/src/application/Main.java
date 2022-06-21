
package application;

import java.util.LinkedList;
import application.card.Card;
import application.card.Suit;
import application.gui.CheckScore;
import application.gui.DeckView;
import application.gui.ExitGame;
import application.gui.NewGame;
import application.gui.PlaceHolder;
import application.gui.ScoreBoard;
import application.gui.SubmitScore;
import application.gui.SuitStackView;
import application.gui.WasteView;
import application.gui.WorkingStackView;
import application.model.SuitStackManager.SuitStack;
import application.model.WorkingStackManager.Workingstack;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class Main extends Application {
	
	private static final int WIDTH = 700;
	private static final int HEIGHT = 500;
	private DeckView deckview = new DeckView();
	private WasteView wasteview = new WasteView();
	private SuitStackView[] suitStacks = new SuitStackView[Suit.values().length];
	private WorkingStackView[] stacks =  new WorkingStackView[Workingstack.values().length];
	private LinkedList<Card> faceUpCards = new LinkedList<Card>();
	private CheckScore ScoreButton = new CheckScore();
	private NewGame newGameButton = new NewGame();
	private ExitGame exitGameButton = new ExitGame();
	public static ScoreBoard scoreBoard = new ScoreBoard();
	private PlaceHolder placeholder = new PlaceHolder();
	public static Stage PrimaryStage;
	private SubmitScore submitScore = new SubmitScore();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			PrimaryStage = primaryStage;
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/SolitaireModel.fxml"));
			GridPane fxml = (GridPane) loader.load();
			fxml.setPadding(new Insets(10, 10, 10, 10));
			fxml.setVgap(8);
			fxml.setHgap(5);
			fxml.add(deckview, 0, 0);
			fxml.add(wasteview, 1, 0);
			fxml.add(placeholder, 2, 0);
			for (SuitStack index : SuitStack.values()) {
				suitStacks[index.ordinal()] = new SuitStackView(index);
				fxml.add(suitStacks[index.ordinal()], 3 + index.ordinal(), 0);
			}
			
			for (Workingstack index : Workingstack.values()) {
				stacks[index.ordinal()] = new WorkingStackView(index, faceUpCards);
				faceUpCards.addAll(stacks[index.ordinal()].getFaceUpCards());
				fxml.add(stacks[index.ordinal()], index.ordinal(), 1);
			}
			fxml.add(ScoreButton, 0, 24);
			fxml.add(submitScore, 1, 24);
			fxml.add(newGameButton, 5, 24);
			fxml.add(exitGameButton, 6, 24);
			fxml.add(scoreBoard, 0, 23);
			Scene scene = new Scene(fxml, WIDTH, HEIGHT);
			scene.getStylesheets().add("application/application.css");
			primaryStage.setTitle("Solitaire");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
