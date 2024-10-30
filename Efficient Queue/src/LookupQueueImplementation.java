/**
 * Name: Mack Bautista
 * Email: mbaut981@mtroyal.ca
 * Course: COMP2631-001
 * Instructor: Jason Heard
 * Assignment: 3
 * Due Date: Nov. 3, 2024
 */

import java.util.NoSuchElementException;

/**
 * Class Name: LookupQueueImplementation
 * Purpose: The implementation of Jason Heard's efficient queue interface.
 *          Uses a doubly-linked list and hashtable to create an efficient way to look up
 *          queues.
 *
 * Interface Name: LookupQueue
 * Author: Jason Heard
 *
 * @author Mack Bautista
 */

public class LookupQueueImplementation implements LookupQueue {
    private static final int INITIAL_CAPACITY = 11;
    private static final double LOAD_FACTOR = 0.65;

    private class Node {
        String name;
        Node prev;
        Node next;

        Node(String name) {
            this.name = name;
        }
    }

    private Node head;
    private Node tail;
    private Node[] hashtable;
    private int size;
    private int capacity;
    private int filled;

    /**
     * Public constructor for implementing LookupQueue.
     */
    public LookupQueueImplementation() {
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
        this.filled = 0;
        this.head = null;
        this.tail = null;
        this.hashtable = new Node[capacity];
    }

    @Override
    public boolean enqueue(String name) {

        if (lookupInHashtable(name)) {
            return false;
        }

        Node newNode = new Node(name);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }


        insertInHashtable(name, newNode);

        size++;
        if ((double) filled / capacity > LOAD_FACTOR) {
            resizeHashtable();
        }

        return true;
    }

    @Override
    public String dequeue() throws NoSuchElementException {
        if (head == null) {
            throw new NoSuchElementException("No names in the lookup queue.");
        }

        String name = head.name;
        head = head.next;

        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }

        removeFromHashtable(name);

        size--;
        return name;
    }

    @Override
    public boolean contains(String name) {
        return lookupInHashtable(name);
    }

    @Override
    public String getNext(String name) throws NoSuchElementException {
        Node node = lookupNode(name);
        if (node == null) {
            throw new NoSuchElementException("The name is not in the lookup queue.");
        }
        return (node.next != null) ? node.next.name : null;
    }

    @Override
    public String getPrevious(String name) throws NoSuchElementException {
        Node node = lookupNode(name);
        if (node == null) {
            throw new NoSuchElementException("The name is not in the lookup queue.");
        }
        return (node.prev != null) ? node.prev.name : null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String[] names() {
        String[] namesArray = new String[size];
        Node current = head;
        for (int i = 0; i < size; i++) {
            namesArray[i] = current.name;
            current = current.next;
        }
        return namesArray;
    }

    /**
     * Inserts the name and the node inside the hash table.
     * @param name name of the person
     * @param node node of the doubly linked list
     */
    private void insertInHashtable(String name, Node node) {
        int index = getIndex(name);
        while (hashtable[index] != null) {
            index = (index + 1) % capacity;
        }
        hashtable[index] = node;
        filled++;
    }

    /**
     * Looks up the name to see if it is already inside the hashtable.
     * @param name check to see if name is in hash table already
     * @return false if name is not in the bucket yet
     *         true if name is in the bucket
     */
    private boolean lookupInHashtable(String name) {
        int index = getIndex(name);
        while (hashtable[index] != null) {
            if (hashtable[index].name.equals(name)) {
                return true;
            }
            index = (index + 1) % capacity;
        }
        return false;
    }

    /**
     * Looks up to see if the node exists or does not exists inside the hash table.
     * @param name looks up to see if node exists
     * @return Node hashtable index if the Node exists and returns null if empty
     */
    private Node lookupNode(String name) {
        int index = getIndex(name);
        while (hashtable[index] != null) {
            if (hashtable[index].name.equals(name)) {
                return hashtable[index];
            }
            index = (index + 1) % capacity;
        }
        return null;
    }


    /**
     * Removes the name from the hashtable and rehashes it after removal.
     * @param name to be removed from the hash table
     */
    private void removeFromHashtable(String name) {
        int index = getIndex(name);
        while (hashtable[index] != null) {
            if (hashtable[index].name.equals(name)) {
                hashtable[index] = null;
                filled--;
                rehashAfterRemoval(index);
                return;
            }
            index = (index + 1) % capacity;
        }
    }

    /**
     * Rehashes the hashtable from the start of the removed given name.
     * @param start starting index of the name
     */
    private void rehashAfterRemoval(int start) {
        int index = (start + 1) % capacity;
        while (hashtable[index] != null) {
            Node nodeToRehash = hashtable[index];
            hashtable[index] = null;
            filled--;
            insertInHashtable(nodeToRehash.name, nodeToRehash);
            index = (index + 1) % capacity;
        }
    }


    /**
     * Rehashes the hash table if the capacity is greater than the load factor.
     */
    private void resizeHashtable() {
        int oldCapacity = capacity;
        capacity = getNextPrime(oldCapacity * 2);
        Node[] oldHashtable = hashtable;
        hashtable = new Node[capacity];
        filled = 0;

        for (int i = 0; i < oldCapacity; i++) {
            if (oldHashtable[i] != null) {
                insertInHashtable(oldHashtable[i].name, oldHashtable[i]);
            }
        }
    }


    /**
     * Passes the name inside the hash table to return the hash index of name.
     * @param name contained inside the hash table
     * @return hash index of the given name
     */
    private int getIndex(String name) {
        return Math.abs(name.hashCode()) % capacity;
    }


    /**
     * Returns the next prime number of capacity in ascending order.
     * @param number current prime number
     * @return next current prime number
     */
    private int getNextPrime(int number) {
        while (true) {
            if (isPrime(number)) {
                return number;
            }
            number++;
        }
    }


    /**
     * Checks to see if the previous capacity of the hash table is prime.
     * @param num previous capacity of hash table
     * @return next capacity of hash table
     */
    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        if (num <= 3) {
            return true;
        }
        if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
