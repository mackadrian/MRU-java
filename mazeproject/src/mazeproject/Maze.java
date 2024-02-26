/*
Name: Mack Bautista
Email: mbaut981@mtroyal.ca
Course: COMP2631-001
Instructor: Bita Sadeghi
Assignment: 2
Due Date: Feb. 29, 2024
*/


package mazeproject;

import java.util.Scanner;

public class Maze {

	public static Scanner keyboard = new Scanner (System.in);
	
	MazeCell maze = new MazeCell();
	
	public static void main(String[] args ) {
		Maze program = new Maze();
		program.run();
	}
	
	public void run() {
		System.out.println("File name: ");
		String file = keyboard.nextLine();
		
		maze.readMaze(file);
		
	}
}
