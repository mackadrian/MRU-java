/**
 * Name: Mack Bautista
 * Email: mbaut981@mtroyal.ca
 * Course: COMP3614-001
 * Instructor: Andrew Bloch-Hansen
 * Assignment: 1
 * Due Date: Sept. 22, 2025
 */


package comp3614_asg1;

/**
 * Class Name: FibonacciRecursion
 * Purpose: This class invokes the Fibonacci-like recursion algorithm for Q4 part a) of 
 * Assignment 1 of COMP3614: Algorithms and Complexity. See Assignment 1 for more details.
 *
 * @author Mack Bautista
 */

public class FibonacciRecursion {
    private static final int BASE_CASE = 0;
    private static final int NEXT_CASE = 1;
    private static final int L0 = 3;
    private static final int L1 = 2;

    private int iteration;


    public FibonacciRecursion(int iteration) {
        this.iteration = iteration;
    }

    public int getIteration()
    {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public int getRecursion() {
        return doRecursion(iteration);
    }

    private int doRecursion(int iteration) {
        if (iteration == BASE_CASE) {
            return L0;
        } else if (iteration == NEXT_CASE) {
            return L1;
        } else {
            return doRecursion(iteration - 1) + doRecursion(iteration - 2);
        }
    }

    @Override
    public String toString() {
        return "L(" + iteration + ") = " + String.format("%,d", getRecursion());
    }
}
