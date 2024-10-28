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
 * The implementation of a queue that allows efficient lookups and removals.
 * Interface created by: Jason Heard
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

        return true; // Name was added
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


    private void insertInHashtable(String name, Node node) {
        int index = getIndex(name);
        while (hashtable[index] != null) {
            index = (index + 1) % capacity;
        }
        hashtable[index] = node;
        filled++;
    }

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


    private void resizeHashtable() {
        int oldCapacity = capacity;
        capacity = getNextPrime(oldCapacity * 2);
        Node[] oldHashtable = hashtable;
        hashtable = new Node[capacity];
        filled = 0; // Reset filled count

        for (int i = 0; i < oldCapacity; i++) {
            if (oldHashtable[i] != null) {
                insertInHashtable(oldHashtable[i].name, oldHashtable[i]);
            }
        }
    }


    private int getIndex(String name) {
        return Math.abs(name.hashCode()) % capacity;
    }


    private int getNextPrime(int number) {
        while (true) {
            if (isPrime(number)) {
                return number;
            }
            number++;
        }
    }


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
