/*
Name: Mack Bautista
Email: mbaut981@mtroyal.ca
Course: COMP2631-001
Instructor: Bita Sadeghi
Assignment: 3
Due Date: Mar. 14, 2024

Source File: MainProgram.java
*/

package datacompareproject;

import java.util.Scanner;

public class MainProgram {
	
	public static String file;
	public static Scanner keyboard = new Scanner (System.in);
	
	SortedArray sortedArrayAccounts = new SortedArray();
	HashMap hashMapAccounts = new HashMap();
	
	public static void main (String[] args) {
		
		MainProgram program = new MainProgram();
		program.run();
		
	}
	
	public void run() {
		System.out.print("Database name: ");
		file = keyboard.nextLine();
		
		sortedArrayAccounts.readAccounts(file);
		hashMapAccounts.readAccounts(file);
		
	}
	
}
