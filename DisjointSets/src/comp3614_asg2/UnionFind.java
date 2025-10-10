/**
 * Name: Mack Bautista
 * Email: mbaut981@mtroyal.ca
 * Course: COMP3614-001
 * Instructor: Andrew Bloch-Hansen
 * Assignment: 2
 * Due Date: Oct. 10, 2025
 */

package comp3614_asg2;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Name: UnionFind
 * 
 * Implementation for Assignment 2 Q3
 * 
 * Purpose: This class implements the Union-Find (Disjoint-Set) data structure with 
 * path compression and union by rank. It supports operations to create sets, 
 * find the representative of a set, and unite two sets efficiently. 
 * 
 * @author Mack Bautista
 */

public class UnionFind {
	
    private TreeNode[] forest;
    private int numSets;
    private boolean finalized;

    public UnionFind(int n) {
        forest = new TreeNode[n];
        numSets = 0;
        finalized = false;
    }

    public void makeSet(TreeNode node) {
        if (finalized) return;

        forest[node.getIndex()] = node;
        node.setParent(node);
        node.setRank(0);

        if (node.getElement().equals("+")) {
            numSets++;
        }
    }

    /** Find set by index */
    public TreeNode findSet(int index) {
        TreeNode node = forest[index];
        if (node.getParent() != node) {
            node.setParent(findSet(node.getParent().getIndex())); // path compression
        }
        return node.getParent();
    }

    /** Union two sets by indices */
    public void unionSet(int indexA, int indexB) {
        if (finalized) return;

        TreeNode rootA = findSet(indexA);
        TreeNode rootB = findSet(indexB);

        if (rootA == rootB) return;

        // union by rank
        if (rootA.getRank() > rootB.getRank()) {
            rootB.setParent(rootA);
        } else if (rootA.getRank() < rootB.getRank()) {
            rootA.setParent(rootB);
        } else {
            rootB.setParent(rootA);
            rootA.setRank(rootA.getRank() + 1);
        }

        numSets--;
    }

    /** Finalize sets and assign unique representative ids */
    public int finalSet() {
        finalized = true;

        // Ensure path compression
        for (TreeNode node : forest) {
            findSet(node.getIndex());
        }

        // Assign stable representative IDs
        Map<TreeNode, Integer> repIDs = new HashMap<>();
        int counter = 1;
        for (TreeNode node : forest) {
            TreeNode rep = findSet(node.getIndex());
            if (!repIDs.containsKey(rep)) {
                repIDs.put(rep, counter++);
            }
            node.setRoot(repIDs.get(rep));
        }

        return numSets;
    }

    /** Print elements and their set representatives */
    public void printSet() {
        System.out.println("Elements and their representatives:");
        for (TreeNode node : forest) {
            System.out.println("Element " + node.getElement() + " -> " + findSet(node.getIndex()).getElement());
        }
    }
}
