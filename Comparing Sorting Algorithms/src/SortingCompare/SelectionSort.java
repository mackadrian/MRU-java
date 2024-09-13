package SortingCompare;

public class SelectionSort extends SortingAnalysis {
	private int compareCount = 0;
    private int moveCount = 0;

    public int getCompareCount() {
        return compareCount;
    }

    public void setCompareCount(int count) {
        compareCount = count;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int count) {
        moveCount = count;
    }
    
    public void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                compareCount++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
            moveCount++;
        }
    }

}
