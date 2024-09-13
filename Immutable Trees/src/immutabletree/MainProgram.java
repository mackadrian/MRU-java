/*
Name: Mack Bautista
Email: mbaut981@mtroyal.ca
Course: COMP2631-001
Instructor: Bita Sadeghi
Assignment: 4
Due Date: Mar. 28, 2024

Source File: MainProgram.java
*/


package immutabletree;

import java.util.Arrays;
import java.util.Scanner;

public class MainProgram {

	public static String name;
	public static int yearOfBirth;
	public static Scanner keyboard = new Scanner (System.in);
	
	ImmutableDictionary<Integer> tree = new ImmutableTree<Integer>();
	
	public static void main(String[] args) {
		
		MainProgram program = new MainProgram();
		program.run();
	}
	
	public void run() {
	    System.out.print("Enter your name and year of birth (Name, Year): \n");
	    String input = keyboard.nextLine();
	    String[] parts = input.split(",");
	    if (parts.length < 2) {
	        System.out.println("Invalid input. Please enter in the format 'Name, Year'.\n");
	        return;
	    }
	    name = parts[0].trim();
	    try {
	        yearOfBirth = Integer.parseInt(parts[1].trim());
	    } catch (NumberFormatException e) {
	        System.out.println("Invalid year of birth. Please enter a valid number.\n");
	        return;
	    }
	    tree = tree.put(name, yearOfBirth);
	    System.out.println("The family tree for: " + name + ", " + yearOfBirth + " has been created.");
	    System.out.println("Now, let's add your ancestors to the family tree.");
	    System.out.println("Enter their names and years of birth in the same format.");
	    System.out.println("Press enter when finished.");
	    
	    while (!(input = keyboard.nextLine()).isEmpty()) {
	        parts = input.split(",");
	        if (parts.length < 2) {
	            System.out.println("Invalid input. Please enter in the format 'Name, Year'.\n");
	            continue;
	        }
	        name = parts[0].trim();
	        try {
	            yearOfBirth = Integer.parseInt(parts[1].trim());
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid year of birth. Please enter a valid number.\n");
	            continue;
	        }
	        tree = tree.put(name, yearOfBirth);
	    }
	    System.out.println(tree.size());
	    System.out.println(Arrays.toString(tree.keys()));
	    System.out.println(Arrays.toString(tree.preOrder()));
	    
	}
	
}
