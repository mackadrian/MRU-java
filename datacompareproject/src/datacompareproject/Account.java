/*
Name: Mack Bautista
Email: mbaut981@mtroyal.ca
Course: COMP2631-001
Instructor: Bita Sadeghi
Assignment: 3
Due Date: Mar. 14, 2024

*/



package datacompareproject;

import java.util.Scanner;

public class Account {
	
	public static String file;
	public static Scanner keyboard = new Scanner (System.in);
	
	AccountSystem accounts = new AccountSystem();
	
	public static void main (String[] args) {
		
		Account program = new Account();
		program.run();
		
	}
	
	public void run() {
		System.out.print("Database name: ");
		file = keyboard.nextLine();
		accounts.readAccounts(file);
		
	}
	
}
