/*
Name: Mack Bautista
Email: mbaut981@mtroyal.ca
Course: COMP2631-001
Instructor: Bita Sadeghi
Assignment: 5
Due Date: Apr. 8, 2024

Source File: SortingAnalysis.java
*/


package SortingCompare;

public class SortingAnalysis {
	
	public static final int[] INPUTSIZES = {10000};
	
    static SelectionSort selection = new SelectionSort();
    static HeapSort heap = new HeapSort();
    static LibrarySort library = new LibrarySort();
	
	public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        return arr;
    }
	
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    public static void runSelection(int[] arr) {
            System.out.println("Original array: ");
            //printArray(arr);
            
            selection.selectionSort(arr);
            
            System.out.println("Selection Sort array:");
            //printArray(arr);

            System.out.println("Comparison Count: " + selection.getCompareCount());
            System.out.println("Data Movement Count: " + selection.getMoveCount());
            System.out.println();
            
            selection.setCompareCount(0);
            selection.setMoveCount(0);
    }
    
    public static void runHeap(int[] arr) {
        System.out.println("Original array: ");
        //printArray(arr);
        
        heap.heapSort(arr);
        
        System.out.println("Heap Sort array:");
        //printArray(arr);

        System.out.println("Comparison Count: " + heap.getCompareCount());
        System.out.println("Data Movement Count: " + heap.getMoveCount());
        System.out.println();
        
        heap.setCompareCount(0);
        heap.setMoveCount(0);
    }
    
    public static void runLibSort(int[] arr) {
        System.out.println("Original array: ");
        //printArray(arr);
        
        library.librarySort(arr);
        
        System.out.println("Library Sort array:");
        //printArray(arr);

        System.out.println("Comparison Count: " + library.getCompareCount());
        System.out.println("Data Movement Count: " + library.getMoveCount());
        System.out.println();
        
        library.setCompareCount(0);
        library.setMoveCount(0);
    }
	
    
	public static void main(String[] args) {

        for (int size : INPUTSIZES) {
            int[] arr = generateRandomArray(size);
            
            int[] arrSelection = arr.clone();
            int[] arrHeap = arr.clone();
            int[] arrLibSort = arr.clone();
            
            runSelection(arrSelection);
            runHeap(arrHeap);
            runLibSort(arrLibSort);
        }
	}
	
	
		
		
}
