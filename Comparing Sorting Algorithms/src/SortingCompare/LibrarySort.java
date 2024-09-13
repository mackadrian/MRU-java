package SortingCompare;

public class LibrarySort extends SortingAnalysis {
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
    
    public void librarySort(int[] arr) {
        int n = arr.length;
        int index = 0;

        while (index < n) {
            if (index == 0 || arr[index] >= arr[index - 1]) {
                index++;
            } else {
                // swap arr[index] with arr[index - 1]
                int temp = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = temp;
                moveCount++;

                if (index > 1) {
                    index--; // compare with the previous element
                }
            }
            compareCount++;
        }
    }

    
    

}
