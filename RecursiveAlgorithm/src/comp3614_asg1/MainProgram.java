/**
 * Name: Mack Bautista
 * Email: mbaut981@mtroyal.ca
 * Course: COMP3614-001
 * Instructor: Andrew Bloch-Hansen
 * Assignment: 1
 * Due Date: Sept. 22, 2025
 */


package comp3614_asg1;

public class MainProgram {

    public static void main (String[] args) {
        //part a)
    	System.out.println("Running recursive function for Q4 part a) ... \n");
        for (int i = 0; i <= 10; i++) {
            FibonacciRecursion part_a = new FibonacciRecursion(i*4);
            System.out.println(part_a);
        }
        System.out.println();
        

        // part b)
    	System.out.println("Running recursive function for Q4 part b) ... \n");
        for (int i = 0; i <= 25; i++) {
            BetterRecursion part_b = new BetterRecursion(i * 20);
            System.out.println(part_b);
        }
        System.out.println();
    }

}
