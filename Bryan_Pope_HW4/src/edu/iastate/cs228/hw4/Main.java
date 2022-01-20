package edu.iastate.cs228.hw4;

import java.util.Scanner;

/**
 * @author Bryan Pope
 *
 * 
 */
public class Main {
	private static double numChars = 0;
	private static double numBits = 0;
	public static void main (String args[]) {
		boolean validFile = false;
		FileExtractor extractor = null;
		String fileName = "";
		Scanner scnr = new Scanner(System.in);
		
		while(validFile == false) {
		System.out.println("Please enter filename to decode:  ");
		fileName = scnr.next();
		validFile = isValidFileName(fileName);
			try {
			extractor = new FileExtractor(fileName);
			}
			catch(Exception FileNotFoundException){
			validFile = false;
			continue;
			}
		
		}
		extractor.scan();
		MsgTree tree = new MsgTree(extractor.getTreeBuilder());
		System.out.println("MESSAGE:");
		decode(tree, extractor.getCodedMessage());
		System.out.println("character code \n-------------------------");
		tree.printCodes(tree, "");
		System.out.println();
		printStatistics();
	}

	
	/**
	 * A method that returns true if the file ends in ".arch", false if it does not
	 * @param fileName
	 * @return true if the filename ends in .arch, returns false if it does not equal .arch or the string
	 * is too short and causes the substring to go out of bounds
	 */
	private static boolean isValidFileName(String fileName) {
		String fileSubstring = "";
		try {
		fileSubstring = fileName.substring(fileName.length() - 5, fileName.length());
		}
		catch(Exception IndexOutOfBoundsException){
			return false;
		}
		
		if(fileSubstring.equals(".arch")) {
			return true;
		}
		else return false;
	}
	
	/**
	 * A method that decodes the string using the provided tree
	 * @param codes - The tree to base decoding from
	 * @param msg - the message to be decoded
	 */
	public static void decode(MsgTree codes, String msg) {
		int msgIndex = 0;
		numBits = msg.length();
		String message = "";
		//This upper loop runs the length of the string
		while(msgIndex < msg.length()) {
			//Starts at the root
			MsgTree currentNode = codes;
			//Continues until a leaf is found
			while(currentNode.left != null && currentNode.right != null) {
				//goes right at one
				if(msg.charAt(msgIndex) == '1') {
					currentNode = currentNode.right;
				}
				else {
					currentNode = currentNode.left;
				}
				//Increment index
				msgIndex++;
			}
			//append string
			message = message + currentNode.payloadChar;
			numChars++;
		}
		System.out.println(message);
	}
	
	public static void printStatistics() {
		System.out.println("STATISTICS:");
		double spaceSavings = (1 - numBits/(numChars * 16))*100;
		System.out.println(String.format("%-20s","Avg bits/char: ") + String.format("%.1f", numBits / numChars));
		System.out.println(String.format("%-20s","Total characters: ") + (int)numChars);
		System.out.println(String.format("%-20s","Space savings: ") + String.format("%.1f", spaceSavings) + "%");
	}


}
