package application.gui;

public class Score {
	String name;
	int score;
	
	public Score(String name, int score) {
		this.name = name;
		this.score = score;
	}

	@Override
	public String toString() {
		return name + " " + score;
	}
	
}
