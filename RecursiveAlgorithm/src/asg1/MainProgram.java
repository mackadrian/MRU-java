/**
 * Name: Mack Bautista
 * Email: mbaut981@mtroyal.ca
 * Course: COMP3614-001
 * Instructor: Andrew Bloc-Hansen
 * Assignment: 1
 * Due Date: Sept. 22, 2025
 */


package asg1;

public class MainProgram {

    public static void main (String[] args) {
        /*Q4 part a)
        for (int i = 0; i <= 10; i++) {
            FibonacciRecursion part_a = new FibonacciRecursion(i*4);
            System.out.println(part_a);
        }
        */

        // part b)
        for (int i = 0; i <= 25; i++) {
            int index = i * 20;
            BigEnumerator Ln = BigEnumerator.computeLnPair(index)[0];
            System.out.println("L(" + index + ") = " + Ln);
        }
    }

}
