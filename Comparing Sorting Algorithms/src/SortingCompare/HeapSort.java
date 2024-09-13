package SortingCompare;

public class HeapSort extends SortingAnalysis {
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

    public void heapSort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0]; // swap roots with the largest element
            arr[0] = arr[i];
            arr[i] = temp;
            moveCount++;
            
         
            heapify(arr, i, 0); // heapify the reduced heap
        }
    }

    private void heapify(int[] arr, int n, int rootIndex) {
        int largest = rootIndex;
        int leftChild = 2 * rootIndex + 1;
        int rightChild = 2 * rootIndex + 2;

        if (leftChild < n && arr[leftChild] > arr[largest]) {
            largest = leftChild;
            compareCount++;
        }
        if (rightChild < n && arr[rightChild] > arr[largest]) {
            largest = rightChild;
            compareCount++;
        }

        if (largest != rootIndex) {
            int temp = arr[rootIndex];
            arr[rootIndex] = arr[largest];
            arr[largest] = temp;
            moveCount++;
            compareCount++;

            heapify(arr, n, largest); // heapify the affected subtree
        }
    }

}
