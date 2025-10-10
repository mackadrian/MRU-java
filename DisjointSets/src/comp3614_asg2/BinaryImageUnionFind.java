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
 * Purpose: This class processes a binary (text-based) image to identify and label 
 * connected components using a Union-Find (Disjoint-Set) structure. Each non-space 
 * character in the image is treated as a pixel, and adjacent identical characters 
 * are merged into the same set through union operations. The class then outputs 
 * the labeled connected components along with their corresponding sizes, sorted 
 * in descending order.
 * 
 * @author Mack Bautista
 */

public class BinaryImageUnionFind {
	
    private static final int ROWS = 71;
    private static final int COLS = 71;

    public void run(String filename) {
        TreeNode[][] nodes = new TreeNode[ROWS][COLS];
        StringBuilder imageBuilder = new StringBuilder();

        // Step 1: Read input image and create TreeNodes
        try (Scanner sc = new Scanner(new File(filename))) {
            for (int r = 0; r < ROWS && sc.hasNextLine(); r++) {
                String line = sc.nextLine();
                while (line.length() < COLS) line += " ";
                for (int c = 0; c < COLS; c++) {
                    char ch = line.charAt(c);
                    imageBuilder.append(ch);
                    nodes[r][c] = new TreeNode(String.valueOf(ch), r * COLS + c);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return;
        }

        String image = imageBuilder.toString();

        // Step 2: Print initial image
        System.out.println("Initial Binary Image:");
        for (int r = 0; r < ROWS; r++) {
            System.out.println(image.substring(r * COLS, (r + 1) * COLS));
        }

        int totalSymbols = ROWS * COLS;
        UnionFind uf = new UnionFind(totalSymbols);

        // Step 3: Make all sets
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                uf.makeSet(nodes[r][c]);
            }
        }

        // Step 4: Union orthogonal '+' neighbors 
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                String current = nodes[r][c].getElement();
                if (!current.equals(" ")) {  // foreground symbol (not blank)
                    // Right neighbor
                    if (c + 1 < COLS && nodes[r][c + 1].getElement().equals(current)) {
                        uf.unionSet(nodes[r][c].getIndex(), nodes[r][c + 1].getIndex());
                    }
                    // Down neighbor
                    if (r + 1 < ROWS && nodes[r + 1][c].getElement().equals(current)) {
                        uf.unionSet(nodes[r][c].getIndex(), nodes[r + 1][c].getIndex());
                    }
                }
            }
        }

        // Step 5: Finalize sets
        uf.finalSet();

        // Step 6: Build output image with letters/numbers
        Map<Integer, Character> compLetters = new HashMap<>();
        Map<Integer, Integer> compNumbers = new HashMap<>();
        Map<Integer, Integer> compSizes = new HashMap<>();
        char currentLetter = 'a';
        int currentNumber = 1;

        // Step 7: Using String[] instead of char[]
        String[] output = new String[totalSymbols];
        Set<Integer> seenReps = new HashSet<>();

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                TreeNode node = nodes[r][c];
                int idx = node.getIndex();

                if (node.getElement().equals(" ")) {
                    output[idx] = " ";
                } else {
                    int rep = uf.findSet(idx).getIndex();
                    compSizes.put(rep, compSizes.getOrDefault(rep, 0) + 1);

                    if (!compLetters.containsKey(rep)) {
                        compLetters.put(rep, currentLetter++);
                        compNumbers.put(rep, currentNumber++);
                    }

                    if (!seenReps.contains(rep)) {
                        output[idx] = String.valueOf(compNumbers.get(rep));
                        seenReps.add(rep);
                    } else {
                        output[idx] = String.valueOf(compLetters.get(rep));
                    }
                }
            }
        }
        // Step 8: Print connected component image
        System.out.println("\nConnected Component Image:");
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                System.out.print(output[r * COLS + c]);
            }
            System.out.println();
        }

        // Step 9: Print sorted component sizes
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
