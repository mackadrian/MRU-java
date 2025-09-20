/**
 * Name: Mack Bautista
 * Email: mbaut981@mtroyal.ca
 * Course: COMP3614-001
 * Instructor: Andrew Bloc-Hansen
 * Assignment: 1
 * Due Date: Sept. 22, 2025
 */


package asg1;

import java.util.ArrayList;

public class BigEnumerator {
    private final int METRIC = 10;
    private ArrayList<Integer> digits;

    public BigEnumerator(int n) {
        digits = new ArrayList<>();
        if (n == 0) {
            digits.add(0);
        }
        while (n > 0) {
            digits.add(n % METRIC);
            n /= METRIC;
        }
    }

    public BigEnumerator(BigEnumerator other) {
        digits = new ArrayList<>(other.digits);
    }

    public BigEnumerator add(BigEnumerator other) {
        BigEnumerator result = new BigEnumerator(0);
        result.digits.clear();

        int carry = 0;
        int maxLength = Math.max(this.digits.size(), other.digits.size());
        for (int i = 0; i < maxLength; i++) {
            int sum = carry;
            if (i < this.digits.size()) {
                sum += this.digits.get(i);
            }
            if (i < other.digits.size()) {
                sum += other.digits.get(i);
            }
            result.digits.add(sum % METRIC);
            carry = sum / METRIC;
        }
        if (carry > 0) {
            result.digits.add(carry);
        }
        return result;
    }

    public static BigEnumerator[] computeLnPair(int n) {
        if (n == 0) {
            return new BigEnumerator[]{new BigEnumerator(3), new BigEnumerator(0)};
        }
        if (n == 1) {
            return new BigEnumerator[]{new BigEnumerator(2), new BigEnumerator(3)};
        }

        BigEnumerator[] prev = computeLnPair(n - 1);
        BigEnumerator Ln = prev[0].add(prev[1]);
        return new BigEnumerator[]{Ln, prev[0]};
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int n = digits.size();

        for (int i = n - 1; i >= 0; i--) {
            sb.append(digits.get(i));
            if (i > 0 && (n - i) % 3 == 0) {
                sb.append(',');
            }
        }
        return sb.toString();
    }
}
