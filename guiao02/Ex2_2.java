package guiao02;
import java.io.*;
import java.util.*;

public class Ex2_2 {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("/home/tiagorainho/Desktop/Uni/p3/eclipse-workspace/p3/src/guiao02/soup.txt");
		Scanner input = new Scanner(file);
		
		Soup soup = new Soup(getSize(file, input));
		
		input = new Scanner(file);					// restart scanner
		
		populateSoup(input, soup);					// populate soup and show result
		System.out.println(soup.toString());
		
		populateKeyWords(input, soup);				// add key words
		
		String resolution = soup.resolve();			// get resolution and show information
		System.out.println(resolution);
		
	}
	public static void populateSoup(Scanner input, Soup soup) {
		String line = "";
		int count = 0;
		while(input.hasNextLine() && count < soup.length()) {
			count++;
			line = input.nextLine();
			soup.add(line.toUpperCase());
		}
	}
	
	public static void populateKeyWords(Scanner input, Soup soup) {
		String words = "";
		String line = "";
		while(input.hasNextLine()) {
			line = input.nextLine();
			if(line.contains(",")) {
				String[] parts = line.split(",");
				if(parts.length > 0) {
					for(int i=0;i<parts.length;i++) {
						words += parts[i] + ",";
					}
				}
			}
		}
		String wordsToSave[] = words.split(",");
		String[] keyWords = new String[wordsToSave.length];
		for(int i=0;i<wordsToSave.length;i++) {
			keyWords[i] = wordsToSave[i].toUpperCase().replaceAll("\\s+","");
		}
		soup.addKeyWords(keyWords);
	}
	
	public static int getSize(File file, Scanner input) {
		int count = 0;
		while(input.hasNextLine()) {
			if(input.nextLine().length() != 0) {
				count++;				
			}
			else {
				return count;
			}
			
		}
		return count;
	}
}
