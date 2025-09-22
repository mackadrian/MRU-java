/**
 * Name: Mack Bautista
 * Email: mbaut981@mtroyal.ca
 * Course: COMP3614-001
 * Instructor: Andrew Bloch-Hansen
 * Assignment: 1
 * Due Date: Sept. 22, 2025
 */

package comp3614_asg1;

import java.util.ArrayList;
/**
 * Class Name: BigEnumerator
 * Purpose: This class implements a simple large integer using arrays of digits.
 * It supports addition, which is required for computing large numbers in BetterRecursion.
 *
 * @author Mack Bautista
 */

public class BigEnumerator {
    private ArrayList<Integer> digits;

    public BigEnumerator(String value) {
        digits = new ArrayList<>();
        // Store digits in LSB in reverse order.
        for (int i = value.length() - 1; i >= 0; i--) {
            digits.add(value.charAt(i) - '0');
        }
    }

    public BigEnumerator(int value) {
        this(String.valueOf(value));
    }

    public BigEnumerator(ArrayList<Integer> digits) {
        this.digits = digits;
    }

    /**
     * Add two BigEnumerators.
     */
    public BigEnumerator add(BigEnumerator other) {
        ArrayList<Integer> result = new ArrayList<>();
        int carry = 0;

        int maxLen = Math.max(this.digits.size(), other.digits.size());
        for (int i = 0; i < maxLen; i++) {
            int d1 = (i < this.digits.size()) ? this.digits.get(i) : 0;
            int d2 = (i < other.digits.size()) ? other.digits.get(i) : 0;

            int sum = d1 + d2 + carry;
            result.add(sum % 10);
            carry = sum / 10;
        }

        if (carry > 0) {
            result.add(carry);
        }

        return new BigEnumerator(result);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = digits.size() - 1; i >= 0; i--) {
            sb.append(digits.get(i));
        }
        return sb.toString();
    }
}
