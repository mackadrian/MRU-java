/**
 * Name: Mack Bautista
 * Email: mbaut981@mtroyal.ca
 * Course: COMP2631-001
 * Instructor: Jason Heard
 * Assignment: 4
 * Due Date: Nov. 24, 2024
 */

import java.util.NoSuchElementException;

/**
 * Class Name: ImmutableSortedSetImplementation
 * Purpose: The implementation of Jason Heard's immutable sorted set.
 *
 *
 * Interface Name: ImmutableSortedSet
 * Author: Jason Heard
 *
 * @author Mack Bautista
 */
public class ImmutableSortedImplementation implements ImmutableSortedSet {

    private final Node root;

    private static class Node {
        final String key;
        final Node left;
        final Node right;
        final int size;

        Node(String key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.size = (left == null ? 0 : left.size) + (right == null ? 0 : right.size) + 1;
        }
    }

    /**
     * Public constructor for implementing ImmutableSoredImplementation.
     */
    public ImmutableSortedImplementation() {
        root = null;
    }

    private ImmutableSortedImplementation(Node root) {
        this.root = root;
    }

    @Override
    public ImmutableSortedSet add(String key) {
        return new ImmutableSortedImplementation(add(root, key));
    }

    private Node add(Node node, String key) {

        if (node == null) {
            return new Node(key, null, null);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return new Node(node.key, add(node.left, key), node.right);
        } else if (cmp > 0) {
            return new Node(node.key, node.left, add(node.right, key));
        } else {
            return node;  // Key already exists, return the same node
        }
    }

    @Override
    public ImmutableSortedSet remove(String key) {
        return new ImmutableSortedImplementation(remove(root, key));
    }

    private Node remove(Node node, String key) {

        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return new Node(node.key, remove(node.left, key), node.right);
        } else if (cmp > 0) {
            return new Node(node.key, node.left, remove(node.right, key));
        } else {
            // Key found, remove it
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node minNode = findMin(node.right);
                return new Node(minNode.key, node.left, remove(node.right, minNode.key));
            }
        }
    }

    private Node findMin(Node node) {

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    @Override
    public String getAtIndex(int index) throws NoSuchElementException {

        if (index < 0 || index >= size()) {
            throw new NoSuchElementException();
        }
        return getAtIndex(root, index);
    }

    private String getAtIndex(Node node, int index) {

        int leftSize = (node.left == null) ? 0 : node.left.size;

        if (index < leftSize) {
            return getAtIndex(node.left, index);
        } else if (index == leftSize) {
            return node.key;
        } else {
            return getAtIndex(node.right, index - leftSize - 1);
        }
    }

    @Override
    public int getIndex(String key) throws NoSuchElementException {
        return getIndex(root, key, 0);
    }

    private int getIndex(Node node, String key, int currentIndex) {

        if (node == null) {
            throw new NoSuchElementException();
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            return getIndex(node.left, key, currentIndex);
        } else if (cmp > 0) {
            return getIndex(node.right, key, currentIndex + (node.left == null ? 0 : node.left.size) + 1);
        } else {
            return currentIndex + (node.left == null ? 0 : node.left.size);
        }
    }

    @Override
    public boolean contains(String key) {
        return contains(root, key);
    }

    private boolean contains(Node node, String key) {
        if (node == null) {
            return false;
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            return contains(node.left, key);
        } else if (cmp > 0) {
            return contains(node.right, key);
        } else {
            return true;
        }
    }

    @Override
    public int size() {
        return (root == null) ? 0 : root.size;
    }

    @Override
    public String[] keys() {
        String[] result = new String[size()];
        inOrderTraversal(root, result, 0);

        return result;
    }

    private int inOrderTraversal(Node node, String[] result, int index) {

        if (node == null) {
            return index;
        }

        index = inOrderTraversal(node.left, result, index);
        result[index++] = node.key;

        return inOrderTraversal(node.right, result, index);
    }
}