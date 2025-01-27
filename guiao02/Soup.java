package guiao02;
import java.util.*;

public class Soup {
	private String line[];
	private int index;
	private String[] keyWords;
	
	public Soup(int length) {
		this.line = new String[length];
		this.index = 0;
	}
	
	public void add(String line) {
		if(this.index < this.line.length) {
			this.line[index] = line;
			this.index++;
		}
	}
	
	
	public String resolve () {
		//Queue<String> keyWords = insertToQueue();
		char[][] soup = populateSoupTable();
		
		return discoverAlgorithm(soup);
	}
	
	private String discoverAlgorithm(char[][] soup) {
		String content = "\n";
		int size = this.length();
		boolean gotSomething = false;
		
		for(int k=0;k<this.keyWords.length;k++) {
			String word = this.keyWords[k];
			char first = word.charAt(0);
			int wordSize = word.length();
			int indI = 0;
			int indJ = 0;
			
			for(int i=0; i<size; i++) {
				for(int j=0;j<size; j++) {
					if(first == soup[i][j]) {
						indI = i+1;
						indJ = j+1;
						
						//UP
						if (i >= wordSize) {
							boolean found = true;
							for (int w=0; w<wordSize; w++) {
								if (soup[i-w][j] != word.charAt(w)) {
									found = false;
								}
							}
							if (found) {
								gotSomething = true;
								content += word.toLowerCase() + "\t" + indI + "," + indJ + "\tup\n";
							}
						}
						//UPRIGHT
						if ((i-wordSize>=0) && (j+wordSize<=line[0].length())) {
							boolean completeWord = true;
							for (int g=0, h=0; g<wordSize; g++, h++) {
								if (soup[i-g][j+h]!=word.charAt(g)) {
									completeWord = false;
								}
							}
							if (completeWord) {
								gotSomething = true;
								content += word.toLowerCase() + "\t" + indI + "," + indJ + "\tupright\n";
							}
						}
						
						//RIGHT
						if (j+wordSize<=line[0].length()) {
							boolean completeWord = true;
							for (int g=0; g<wordSize; g++) {
								if (soup[i][j+g]!=word.charAt(g)) {
									completeWord = false;
								}
							}
							if (completeWord) {
								gotSomething = true;
								content += word.toLowerCase() + "\t" + indI + "," + indJ + "\tright\n";
							}
						}
						
						//DOWNRIGHT
						if ((i+wordSize<=line[0].length())&&(j+wordSize<=line[0].length())) {
							boolean completeWord = true;
							for (int g=0, h=0; g<wordSize; g++, h++) {
								if (soup[i+g][j+h]!=word.charAt(g)) {
									completeWord = false;
								}
							}
							if (completeWord) {
								gotSomething = true;
								content += word.toLowerCase() + "\t" + indI + "," + indJ + "\tdownright\n";
							}
						}
						
						//DOWN
						if (i+wordSize<=line[0].length()) {
							boolean completeWord = true;
							for (int g=0; g<wordSize; g++) {
								if (soup[i+g][j]!=word.charAt(g)) {
									completeWord = false;
								}
							}
							if (completeWord) {
								gotSomething = true;
								content += word.toLowerCase() + "\t" + indI + "," + indJ + "\tdown\n";
							}
						}
						
						//DOWNLEFT
						if ((i+wordSize<=line[0].length())&&(j-wordSize>=0)) {
							boolean completeWord = true;
							for (int g=0, h=0; g<wordSize; g++, h++) {
								if (soup[i+g][j-h]!=word.charAt(g)) {
									completeWord = false;
								}
							}
							if (completeWord) {
								gotSomething = true;
								content += word.toLowerCase() + "\t" + indI + "," + indJ + "\tdownleft\n";
							}
						}
						
						//LEFT
						if (j-wordSize>=0) {
							boolean completeWord = true;
							for (int g=0; g<wordSize; g++) {
								if (soup[i][j-g]!=word.charAt(g)) {
									completeWord = false;
								}
							}
							if (completeWord) {
								gotSomething = true;
								content += word.toLowerCase() + "\t" + indI + "," + indJ + "\tleft\n";
							}
						}
						
						//UPLEFT
						if ((i-wordSize>=0)&&(j-wordSize>=0)) {
							boolean completeWord = true;
							for (int g=0, h=0; g<wordSize; g++, h++) {
								if (soup[i-g][j-h]!=word.charAt(g)) {
									completeWord = false;
								}
							}
							if (completeWord) {
								gotSomething = true;
								content += word.toLowerCase() + "\t" + indI + "," + indJ + "\tupleft\n";
							}
						}
					}
				}
			}
		}
		if(!gotSomething) {
			content += "No match found";
		}
		
		return content;
	}
	
	public String showTable(char[][] soup) {
		String content = "";
		int size = this.length();
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				content += soup[i][j] + " ";
			}
			content += "\n";
		}
		return content;
	}
	
	private char[][] populateSoupTable() {
		char[][] soup = new char[this.length()][this.length()];
		for (int i = 0; i < this.length(); i++) {
			if (i < this.line[0].length()) {
				for (int j = 0; j < this.length(); j++) {
					soup[i][j] = this.line[i].charAt(j);
				}
			}
		}
		return soup;
	}
	
	private Queue<String> insertToQueue(){
		Queue<String> keyWords = new LinkedList<String> ();
		for (int j=0; j<this.line.length; j++) {
			keyWords.add(this.line[j]);
		}
		return keyWords;
	}
	
	public void addKeyWords(String[] words ) {
		this.keyWords = new String[words.length];
		for(int i=0;i<words.length;i++) {
			this.keyWords[i] = words[i];
		}
	}
	
	public int length() {
		return line.length;
	}
	
	public String toString() {
		String content = "Soup of length " + this.length() + ":\n\n";
		char[][] soup = populateSoupTable();
		content += this.showTable(soup);
		return content;
	}
}
