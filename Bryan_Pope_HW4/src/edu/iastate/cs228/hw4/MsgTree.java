package edu.iastate.cs228.hw4;

import java.util.InputMismatchException;

/**
 * @author Bryan pope
 *
 */
public class MsgTree {
	private static double numChars = 0;
	private static double numBits = 0;
	public char payloadChar;
	public MsgTree left;
	public MsgTree right;
	private static int staticCharInx = 1;
	
	/**
	 * Recursively generates a binary tree based on the string provided.
	 * @param encodingString - The string to be encoded into the tree
	 * 
	 */
	public MsgTree(String encodingString) {
		
		if(staticCharInx == encodingString.length() || (this.left != null && this.right !=null)) {
			return;
		}
		if(this.left == null) {
			if(encodingString.charAt(staticCharInx) == '^') {
				staticCharInx++;
				this.left = new MsgTree(encodingString);
			}
			else {
				left = new MsgTree(encodingString.charAt(staticCharInx));
				staticCharInx++;
			}
			
		}
		if(staticCharInx == encodingString.length()) {
			return;
		}
		if(this.right == null) {
			if(encodingString.charAt(staticCharInx) == '^') {
				staticCharInx++;
				this.right = new MsgTree(encodingString);
			}
			else {
				right = new MsgTree(encodingString.charAt(staticCharInx));
				staticCharInx++;
		}
		
		}
		
	}
	
	/**
	 * This constructor sets the left and right nodes to null, and sets the payload char to be 
	 * the selected value.
	 * @param payloadChar
	 */
	public MsgTree(char payloadChar) {
		this.left = null;
		this.right = null;
		this.payloadChar = payloadChar;
		
		
	}
	
	/**
	 * A method that outputs the codes of a given tree
	 * @param root The node to be explored
	 * @param code The string that holds the code
	 */
	public static void printCodes(MsgTree root, String code) {
		
		if(root.left == null && root.right == null) {
			//For the newline outliar case
			if(root.payloadChar =='\n') {
				System.out.println("\\n" + " " + code);
				return;
			}
			System.out.println(root.payloadChar + " " + code);
			numChars++;
			numBits = numBits + code.length();
			return;
		}
		if(root.left != null) {
			printCodes(root.left, code + "0");
		}
		if(root.right != null) {
			printCodes(root.right, code + "1");
		}
		
	}
	

}
