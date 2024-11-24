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
 * Interface Author: Jason Heard
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
     * Constructs an empty ImmutableSortedImplementation with a null root.
     * This constructor initializes the set as empty, meaning there are no elements in the set.
     * The root of the tree is set to null, indicating that the set has no elements.
     */
    public ImmutableSortedImplementation() {
        root = null;
    }

    /**
     * Private constructor used to create a new instance of ImmutableSortedImplementation with a specified root.
     * This constructor is intended for internal use to create a new instance of the set with a given root node.
     * The root node represents the starting point of the binary search tree that stores the elements of the set.
     *
     * @param root the root node of the binary search tree representing the elements in the set.
     */
    private ImmutableSortedImplementation(Node root) {
        this.root = root;
    }

    @Override
    public ImmutableSortedSet add(String key) {
        if (contains(key)) {
            return this;
        }
        return new ImmutableSortedImplementation(add(root, key));
    }



    /**
     * Helper method to add a key to the tree starting from the given node.
     * Creates a new node if the key is not found, otherwise returns the same node.
     *
     * @param node The current node in the tree.
     * @param key The key to add to the tree.
     * @return the new root node after the key has been added.
     */
    private Node add(Node node, String key) {
        if (node == null) {
            return new Node(key, null, null);
        }

        int cmp = compareKeys(key, node.key);

        if (cmp < 0) {
            return new Node(node.key, add(node.left, key), node.right);
        } else if (cmp > 0) {
            return new Node(node.key, node.left, add(node.right, key));
        } else {
            return node;
        }
    }

    @Override
    public ImmutableSortedSet remove(String key) {
        Node newRoot = remove(root, key);
        if (newRoot == root) {
            return this;
        }
        return new ImmutableSortedImplementation(newRoot);
    }

    /**
     * Helper method to remove a key from the tree starting from the given node.
     * It re-arranges the nodes to maintain the sorted order.
     *
     * @param node The current node in the tree.
     * @param key The key to remove.
     * @return the new root node after the key has been removed.
     */
    private Node remove(Node node, String key) {
        if (node == null) {
            return null;
        }

        int cmp = (key == null) ? (node.key == null ? 0 : 1) : key.compareTo(node.key);
        if (cmp < 0) {
            Node newLeft = remove(node.left, key);
            if (newLeft == node.left) {
                return node;
            }
            return new Node(node.key, newLeft, node.right);
        } else if (cmp > 0) {
            Node newRight = remove(node.right, key);
            if (newRight == node.right) {
                return node;
            }
            return new Node(node.key, node.left, newRight);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node minNode = findMin(node.right);
                Node newRight = remove(node.right, minNode.key);
                return new Node(minNode.key, node.left, newRight);
            }
        }
    }

    /**
     * Helper method to find the minimum node starting from the given node.
     * This is used when removing a node with two children.
     *
     * @param node The current node in the tree.
     * @return the minimum node in the right subtree.
     */
    private Node findMin(Node node) {

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    @Override
    public String getAtIndex(int index) throws NoSuchElementException {
        if (index < 0 || index >= size()) {
            throw new NoSuchElementException("Invalid index: " + index);
        }
        return getAtIndex(root, index);
    }

    /**
     * Helper method to recursively find the element at the specified index.
     *
     * @param node The current node in the tree.
     * @param index The index of the element to retrieve.
     * @return the key at the specified index.
     */
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

    /**
     * Helper method to recursively find the index of a given key.
     *
     * @param node The current node in the tree.
     * @param key The key to find the index of.
     * @param currentIndex The current index in the traversal.
     * @return the index of the given key.
     */
    private int getIndex(Node node, String key, int currentIndex) {
        if (node == null) {
            throw new NoSuchElementException("Key not found: " + key);
        }

        int cmp = (key == null) ? (node.key == null ? 0 : 1) : key.compareTo(node.key);

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

    /**
     * Helper method to recursively check if a key is in the tree.
     *
     * @param node The current node in the tree.
     * @param key The key to check for.
     * @return true if the key exists, otherwise false.
     */
    private boolean contains(Node node, String key) {
        if (node == null) {
            return false;
        }

        int cmp = compareKeys(key, node.key);

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
        return size(root);
    }

    /**
     * Helper method to recursively calculate the size of the tree.
     *
     * @param node The current node in the tree.
     * @return The number of elements in the subtree rooted at the given node.
     */
    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

    @Override
    public String[] keys() {
        String[] result = new String[size()];
        inOrderTraversal(root, result, 0);

        return result;
    }

    /**
     * Helper method to perform an in-order traversal of the tree to fill the result array.
     *
     * @param node The current node in the tree.
     * @param result The array to store the keys.
     * @param index The current index in the result array.
     * @return the updated index after the traversal.
     */
    private int inOrderTraversal(Node node, String[] result, int index) {
        if (node == null) {
            return index;
        }

        index = inOrderTraversal(node.left, result, index);
        result[index++] = node.key;
        return inOrderTraversal(node.right, result, index);
    }

    /**
     * Compares two keys (strings) in the context of the sorted tree.
     * This method is used to determine the relative order of the keys for
     * insertion or searching in the tree. It handles the case where one or
     * both keys might be null.
     *
     * @param key1 The first key to be compared. Can be null.
     * @param key2 The second key to be compared. Can be null.
     * @return A negative integer if key1 is less than key2, a positive integer
     *         if key1 is greater than key2, or 0 if key1 is equal to key2.
     */
    private int compareKeys(String key1, String key2) {
        if (key1 == null && key2 == null) {
            return 0;
        }
        if (key1 == null) {
            return -1;  // null should be considered "smaller" than any non-null value
        }
        if (key2 == null) {
            return 1;   // non-null values should come after null
        }
        return key1.compareTo(key2);
    }

}