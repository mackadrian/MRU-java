import java.util.NoSuchElementException;

/**
 * A queue that allows efficient lookups and removals.
 *
 * @author Jason Heard
 */
public interface LookupQueue {

    /**
     * Adds the specified name to the back of the queue unless it is already in
     * the queue. Returns whether the name was added.
     *
     * @param name
     *            The name to increment in the lookup queue.
     * @return <code>true</code> if the name was added to the lookup queue;
     *         <code>false</code> otherwise.
     */
    boolean enqueue(String name);

    /**
     * Removes and returns the name at the front of the queue. If the queue is
     * empty, an exception is thrown.
     *
     * @return the name that was previously at the front of the queue.
     * @throws NoSuchElementException
     *             Thrown if there are no names in the lookup queue.
     */
    String dequeue() throws NoSuchElementException;

    /**
     * Determines if the lookup queue contains the specified name.
     *
     * @param name
     *            The name look for in the lookup queue.
     * @return <code>true</code> if this lookup queue contains the specified
     *         name; <code>false</code> otherwise.
     */
    boolean contains(String name);

    /**
     * Retrieves the name of the person behind of the specified name in this
     * lookup queue. If this lookup queue does not contain the given name, an
     * exception is thrown.
     *
     * @param name
     *            The name to lookup in the lookup queue.
     * @return The name behind of the given name or <code>null</code> if the
     *         name is at the back of the queue.
     * @throws NoSuchElementException
     *             Thrown if the given name is not in the lookup queue.
     */
    String getNext(String name) throws NoSuchElementException;

    /**
     * Retrieves the name of the person ahead of the specified name in this
     * lookup queue. If this lookup queue does not contain the given name, an
     * exception is thrown.
     *
     * @param name
     *            The name to lookup in the lookup queue.
     * @return The name ahead of the given name or <code>null</code> if the name
     *         is at the front of the queue.
     * @throws NoSuchElementException
     *             Thrown if the given name is not in the lookup queue.
     */
    String getPrevious(String name) throws NoSuchElementException;

    /**
     * Returns the number of names in this lookup queue.
     *
     * @return The number of names in this lookup queue.
     */
    int size();

    /**
     * Generates and returns an array with all of the names in the queue order.
     *
     * @return An array with all of the names that have a count in queue order.
     */
    public String[] names();

}
