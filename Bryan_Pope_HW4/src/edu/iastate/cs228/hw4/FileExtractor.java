package edu.iastate.cs228.hw4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * @author Bryan Pope
 * 
 * A class for extracting the strings from a file, separating it into the tree code and the 
 * coded message.
 *
 */
public class FileExtractor {
	String treeBuilder = "";
	String codedMessage = "";
	Scanner scnr;

	/**
	 * Creates a fileExtractor object with the interted filename, throws a fileNotFound
	 * Exception if invalid file name.
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public FileExtractor(String fileName) throws FileNotFoundException {
		this.scnr = new Scanner(new FileReader(fileName));
	}
	
	/**
	 * The scanner takes the input of the tree code and the coded message
	 */
	public void scan() {
		String firstString, secondString, thirdString;
		firstString = scnr.nextLine();
		secondString = scnr.nextLine();
		
		if(scnr.hasNextLine() == false) {
			this.treeBuilder = firstString;
			this.codedMessage = secondString;
		}
		//This is for the chance that a newline character is in the tree
		else {
			thirdString = scnr.nextLine();
			this.treeBuilder = firstString + '\n' + secondString;
			this.codedMessage = thirdString;
		}
	}
	
	/**
	 * @return the string to generate the tree
	 */
	public String getTreeBuilder() {
		return this.treeBuilder;
	}
	
	/**
	 * @return The coded message string
	 */
	public String getCodedMessage() {
		return this.codedMessage;
	}
}
