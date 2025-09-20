/**
 * Name: Mack Bautista
 * Email: mbaut981@mtroyal.ca
 * Course: COMP3614-001
 * Instructor: Andrew Bloc-Hansen
 * Assignment: 1
 * Due Date: Sept. 22, 2025
 */


package asg1;

public class FibonacciRecursion {
    private static final int BASE_CASE = 0;
    private static final int NEXT_CASE = 1;
    private static final int BASE_CASE_RESULT = 3;
    private static final int NEXT_CASE_RESULT = 2;

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
            return BASE_CASE_RESULT;
        } else if (iteration == NEXT_CASE) {
            return NEXT_CASE_RESULT;
        } else {
            return doRecursion(iteration - 1) + doRecursion(iteration - 2);
        }
    }

    @Override
    public String toString() {
        return "L(" + iteration + ") = " + String.format("%,d", getRecursion());
    }
}
