package application.gui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;


public class ReadWrite {
	
	public static void addScoreToFile(Score score) throws IOException{
		String filename = "save.txt";
		try {
			FileWriter filewritter = new FileWriter(filename, true);
			BufferedWriter save = new BufferedWriter(filewritter);
			save.newLine();
			save.write(score.toString());
			save.close();
		} catch (IOException e) {
            System.out.println("Exception Occurred" + e);
        }
	}
	
	
	public static Vector<Score> ReadFile() throws FileNotFoundException {
		Vector<Score> scores = new Vector<>();
		File file = new File("save.txt");
		Scanner sc  = new Scanner(file);
		while (sc.hasNextLine()) {
			String str = sc.nextLine();
			String split[] = str.split(" ");
			String a = split[0];
			int b = Integer.parseInt(split[1]);
			Score score = new Score(a, b);
			scores.add(score);
		}
		scores.sort(new Comparator<Score>() {
			@Override
			public int compare(Score lhs, Score rhs) {
				return lhs.score > rhs.score ? -1 : (lhs.score < rhs.score) ? 1 : 0;
			}
		});
		sc.close();
		return scores;
	}
}

