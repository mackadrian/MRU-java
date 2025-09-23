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
 * Class Name: BetterRecursion
 * Purpose: This class invokes the recursion algorithm for Q5 part b) of
 * Assignment 1 of COMP3614: Algorithms and Complexity. See Assignment 1 for more details.
 *
 * The sequence of numbers follow the recurrence:
 * L(n) = L(n-1) + L(n-2), with L(0) = 2 and L(1) = 1.
 *
 * This implementation uses matrix recursion:
 * K(n) = [L(n), L(n-1)]^T = [[1,1],[1,0]] * K(n-1)
 *
 * @author Mack Bautista
 */
public class BetterRecursion {
    private static final int BASE_CASE = 0;
    private static final int NEXT_CASE = 1;
    private static final int L0 = 3;
    private static final int L1 = 2;

    private int iteration;

    public BetterRecursion(int iteration) {
        this.iteration = iteration;
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public BigEnumerator getRecursion() {
        return doRecursion(iteration)[0];
    }

    /**
     * Recursive method using matrix multiplication to compute [L(n), L(n-1)].
     */
    private BigEnumerator[] doRecursion(int n) {
        if (n == BASE_CASE) {
            return new BigEnumerator[]{new BigEnumerator(L0), new BigEnumerator(0)};
        } else if (n == NEXT_CASE) {
            return new BigEnumerator[]{new BigEnumerator(L1), new BigEnumerator(L0)};
        } else {
            BigEnumerator[] prev = doRecursion(n - 1);
            BigEnumerator Ln = prev[0].add(prev[1]); 
            return new BigEnumerator[]{Ln, prev[0]};  
        }
    }

    @Override
    public String toString() {
        String number = getRecursion().toString(); // BigEnumerator -> digit string
        StringBuilder formatted = new StringBuilder();

        int len = number.length();
        int count = 0;

        // Separate place values with commas
        for (int i = len - 1; i >= 0; i--) {
            formatted.append(number.charAt(i));
            count++;
            if (count % 3 == 0 && i > 0) {
                formatted.append(",");
            }
        }

        // Reverse order since built backwards
        return "L(" + iteration + ") = " + formatted.reverse().toString();
    }
}

