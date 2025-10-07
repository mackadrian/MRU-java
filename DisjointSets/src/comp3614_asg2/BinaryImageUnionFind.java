/**
 * Name: Mack Bautista
 * Email: mbaut981@mtroyal.ca
 * Course: COMP3614-001
 * Instructor: Andrew Bloch-Hansen
 * Assignment: 2
 * Due Date: Oct. 10, 2025
 */

package comp3614_asg2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
 * Class Name: BinaryImageUnionFind
 * 
 * Implementation for Assignment 2 Q4
 * 
 * Purpose: This class reads a binary image from a text file (face.txt) 
 * consisting of '+' and ' ' characters, and finds the connected components 
 * using the Union-Find (Disjoint-Set) data structure. 
 * 
 * The program outputs:
 * 1. The input binary image (requirement a)
 * 2. The connected component image, where each component is labeled 
 *    with a unique letter, and the representative of each component 
 *    is labeled with a number (requirement b)
 * 3. A list of component sizes sorted in descending order (requirement c)
 * 
 * Implementation Details:
 * - The image is read line by line into a single string.
 * - Each symbol is placed into a singleton set in the Union-Find data structure.
 * - Orthogonally adjacent '+' symbols are unioned into the same set.
 * - After all unions, finalSet() is called to fix representatives.
 * - Each component is assigned a unique letter, and representatives are assigned numbers.
 * - Component sizes are counted and sorted.
 * 
 * Usage:
 * Place face.txt in the same directory as the compiled program and run:
 *     java BinaryImageUnionFind
 * 
 * @author Mack Bautista
 */

public class BinaryImageUnionFind {

    private static final int ROWS = 71;
    private static final int COLS = 71;



    public void run(String filename) {
        StringBuilder imageBuilder = new StringBuilder();

        // Read input image
        try (Scanner sc = new Scanner(new File(filename))) {
        	while (sc.hasNextLine()) {
        	    String line = sc.nextLine();
        	    while (line.length() < COLS) {
        	        line += " ";
        	    }
        	    imageBuilder.append(line);
        	}

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return;
        }

        String image = imageBuilder.toString();
        int totalSymbols = image.length();

        // Print initial image
        System.out.println("Initial Binary Image:");
        for (int r = 0; r < ROWS; r++) {
            System.out.println(image.substring(r * COLS, (r + 1) * COLS));
        }

        // Initialize UnionFind
        UnionFind uf = new UnionFind(totalSymbols);

        // Union orthogonal '+' neighbors
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                int idx = r * COLS + c;
                if (image.charAt(idx) == '+') {
                    // Right neighbor
                	if (c + 1 < COLS && image.charAt(r * COLS + (c + 1)) == '+')
                	    uf.unionSet(idx, r * COLS + (c + 1));

                	// Down neighbor
                	if (r + 1 < ROWS && image.charAt((r + 1) * COLS + c) == '+')
                	    uf.unionSet(idx, (r + 1) * COLS + c);
                }
            }
        }

        // Ensure path compression
        for (int i = 0; i < totalSymbols; i++) uf.findSet(i);

        // Assign letters/numbers and build output image
        Map<Integer, Character> compLetters = new HashMap<>();
        Map<Integer, Integer> compNumbers = new HashMap<>();
        Map<Integer, Integer> compSizes = new HashMap<>();
        char currentLetter = 'a';
        int currentNumber = 1;
        char[] output = new char[totalSymbols];
        Set<Integer> seenReps = new HashSet<>();

        for (int i = 0; i < totalSymbols; i++) {
            if (image.charAt(i) == ' ') {
                output[i] = ' ';
            } else {
                int rep = uf.findSet(i);
                compSizes.put(rep, compSizes.getOrDefault(rep, 0) + 1);

                if (!compLetters.containsKey(rep)) {
                    compLetters.put(rep, currentLetter++);
                    compNumbers.put(rep, currentNumber++);
                }

                // First occurrence of representative gets number
                if (!seenReps.contains(rep)) {
                    output[i] = Character.forDigit(compNumbers.get(rep), 10);
                    seenReps.add(rep);
                } else {
                    output[i] = compLetters.get(rep);
                }
            }
        }

        // Print connected component image
        System.out.println("\nConnected Component Image:");
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                System.out.print(output[r * COLS + c]);
            }
            System.out.println();
        }

        // Print component sizes sorted descending
        List<Map.Entry<Integer, Integer>> compList = new ArrayList<>(compSizes.entrySet());
        compList.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("\nComponent Sizes:");
        for (Map.Entry<Integer, Integer> entry : compList) {
            int rep = entry.getKey();
            int size = entry.getValue();
            char letter = compLetters.get(rep);
            int number = compNumbers.get(rep);
            System.out.println("Component label: " + number + "(" + letter + "), Size: " + size);
        }
    }
}



