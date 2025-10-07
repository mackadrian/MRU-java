/**
 * Name: Mack Bautista
 * Email: mbaut981@mtroyal.ca
 * Course: COMP3614-001
 * Instructor: Andrew Bloch-Hansen
 * Assignment: 2
 * Due Date: Oct. 10, 2025
 */

package comp3614_asg2;



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
	private int[] parent;
	private int[] rank;
	private int numSets;
	private boolean finalized;

	
	public UnionFind(int n) {
	    parent = new int[n];
	    rank = new int[n];
	    numSets = n;
	    finalized = false;
	    for (int i = 0; i < n; i++) makeSet(i);
	}

	
	public void makeSet(int i) {
		if (finalized) return;
		parent[i] = i;
		rank[i] = 0;
	}
	
	public int findSet(int i) {
		if (parent[i] != i) {
			parent[i] = findSet(parent[i]);
		}
		
		return parent[i];
	}
	
	public void unionSet(int i, int j) {
		if (finalized) return;
		
		int repI = findSet(i);
		int repJ = findSet(j);
		
		if (repI == repJ) return;
		
		if (rank[repI] > rank[repJ]) {
			parent[repJ] = repI;
		} else if (rank[repI] < rank[repJ]) {
			parent[repI] = repJ;
		} else {
			parent[repJ] = repI;
			rank[repI]++;
		}
		
		numSets--;
	}
	
	public int finalSet() {
		finalized = true;
		
		int[] newRep = new int[parent.length];
		int counter = 1;
		
		for (int i = 0; i < parent.length; i++) {
		    int rep = findSet(i);
		    if (newRep[rep] == 0) {
		        newRep[rep] = counter++;
		    }
		    parent[i] = newRep[rep];
		}
		return numSets;
	}
	
	
	public void printSet() {
		System.out.println("Elements and their representatives:");
		for (int i = 1; i <parent.length; i++) {
			System.out.println("Element " + i + "->" + findSet(i));
		}
	}
	
	
}
