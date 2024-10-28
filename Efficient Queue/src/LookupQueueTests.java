import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tests for a lookup queue implementation.
 *
 * @author Jason Heard
 * @version 1.0
 */
public class LookupQueueTests {

    /**
     * Constructs a lookup queue to test. This should be updated to construct
     * your implementation.
     *
     * @return The lookup queue implementation to test.
     */
    public static LookupQueue makeLookupQueue() {
        return new DummyLookupQueue();
    }

    /**
     * Tests that the size operation works on an empty queue.
     */
    @Test
    public void sizeWorksOnEmpty() {
        final LookupQueue queue = makeLookupQueue();

        assertEquals(0, queue.size());
    }

    /**
     * Tests that the size operation works on a lookup queue with multiple
     * names.
     */
    @Test
    public void sizeWorksWithMultipleNames() {
        final LookupQueue queue = makeLookupQueue();
        final LookupQueue queue2 = makeLookupQueue();

        queue.enqueue("Jemma");
        queue.enqueue("Harry");
        queue.enqueue("Bob");
        queue.enqueue("Julia");
        queue.enqueue("Jemma");
        queue.enqueue("Harry");
        queue.enqueue("Bob");
        queue.enqueue("Jemma");
        queue.enqueue("Harry");
        queue.enqueue("Jemma");
        queue.enqueue("Jemma");
        queue.enqueue("Jemma");
        queue.enqueue("Jemma");

        // ensure lookup queues do not share data
        queue2.enqueue("Jon Jacob Jingleheimer Schmidt");

        assertEquals(4, queue.size());

        assertEquals(1, queue2.size());
    }

    /**
     * Tests that the enqueue operation works on a lookup queue with multiple
     * names.
     */
    @Test
    public void enqueueWorksWithMultipleNames() {
        final LookupQueue queue = makeLookupQueue();
        final LookupQueue queue2 = makeLookupQueue();

        // ensure lookup queues do not share data
        queue2.enqueue("Jemma");

        assertEquals(true, queue.enqueue("Jemma"));
        assertEquals(true, queue.enqueue("Harry"));
        assertEquals(true, queue.enqueue("Bob"));
        assertEquals(true, queue.enqueue("Julia"));
        assertEquals(false, queue.enqueue("Jemma"));
        assertEquals(false, queue.enqueue("Harry"));
        assertEquals(false, queue.enqueue("Bob"));
    }

    /**
     * Tests that the dequeue operation throws an exception on an empty lookup
     * queue.
     */
    @Test(expected = NoSuchElementException.class)
    public void dequeueWorksOnEmpty() {
        final LookupQueue queue = makeLookupQueue();

        // this should throw an exception
        queue.dequeue();
    }

    /**
     * Tests that the dequeue operation works on a lookup queue with one name.
     */
    @Test
    public void dequeueWorksOnSingleton() {
        final LookupQueue queue = makeLookupQueue();
        final LookupQueue queue2 = makeLookupQueue();

        // ensure lookup queues do not share data
        queue2.enqueue("Jon Jacob Jingleheimer Schmidt");

        queue.enqueue("Jemma");
        assertEquals("Jemma", queue.dequeue());
    }

    /**
     * Tests that the names operation works on an empty queue.
     */
    @Test
    public void namesWorksWithEmpty() {
        final LookupQueue queue = makeLookupQueue();

        String[] names1 = queue.names();
        // ensure it works twice and produces a new array each time
        String[] names2 = queue.names();

        assertNotSame(names1, names2);

        assertArrayEquals(new String[0], names1);
        assertArrayEquals(new String[0], names2);
    }

    /**
     * Tests that the names operation works on a lookup queue with multiple
     * names.
     */
    @Test
    public void namesWorksWithMultipleNames() {
        final LookupQueue queue = makeLookupQueue();
        final LookupQueue queue2 = makeLookupQueue();

        queue.enqueue("Jemma");
        queue.enqueue("Harry");
        queue.enqueue("Bob");
        queue.enqueue("Julia");
        queue.enqueue("Jemma");
        queue.enqueue("Harry");
        queue.enqueue("Bob");
        queue.enqueue("Jemma");
        queue.enqueue("Harry");
        queue.enqueue("Jemma");
        queue.enqueue("Jemma");
        queue.enqueue("Jemma");
        queue.enqueue("Jemma");

        // ensure lookup queues do not share data
        queue2.enqueue("Jon Jacob Jingleheimer Schmidt");

        String[] names1 = queue.names();
        String[] names2 = queue.names();

        assertNotSame(names1, names2);

        assertArrayEquals(new String[] { "Jemma", "Harry", "Bob", "Julia" }, names1);
        assertArrayEquals(new String[] { "Jemma", "Harry", "Bob", "Julia" }, names2);

        assertArrayEquals(new String[] { "Jon Jacob Jingleheimer Schmidt" }, queue2.names());
    }

    /**
     * Tests that the contains operation works on an empty lookup queue.
     */
    @Test
    public void containsWorksOnEmpty() {
        final LookupQueue queue = makeLookupQueue();

        assertFalse(queue.contains("Anythone"));
    }

    /**
     * Tests that the contains operation works on a lookup queue after a dequeue
     * operation.
     */
    @Test
    public void containsWorksAfterDequeue() {
        final LookupQueue queue = makeLookupQueue();
        final LookupQueue queue2 = makeLookupQueue();

        queue.enqueue("Jemma");
        queue.enqueue("Harry");
        queue.enqueue("Bob");
        queue.enqueue("Julia");
        queue.enqueue("Jemma");
        queue.enqueue("Harry");
        queue.enqueue("Bob");
        queue.enqueue("Jemma");
        queue.enqueue("Harry");
        queue.enqueue("Jemma");
        queue.enqueue("Jemma");
        queue.enqueue("Jemma");
        queue.dequeue();
        queue.dequeue();

        // ensure lookup queues do not share data
        queue2.enqueue("Jemma");
        queue2.enqueue("Harry");

        assertFalse(queue.contains("Jemma"));
        assertFalse(queue.contains("Harry"));
        assertTrue(queue.contains("Bob"));
        assertTrue(queue.contains("Julia"));
        assertFalse(queue.contains("Jon Jacob Jingleheimer Schmidt"));

        assertTrue(queue2.contains("Jemma"));
        assertTrue(queue2.contains("Harry"));
        assertFalse(queue2.contains("Bob"));
        assertFalse(queue2.contains("Julia"));
        assertFalse(queue2.contains("Jon Jacob Jingleheimer Schmidt"));
    }

    /**
     * Tests that the getNext operation throws an exception on an empty lookup
     * queue.
     */
    @Test(expected = NoSuchElementException.class)
    public void getNextThrowsOnEmpty() {
        final LookupQueue queue = makeLookupQueue();

        // this should throw an exception
        queue.getNext("Anything");
    }

    /**
     * Tests that the getNext operation works on a lookup queue with multiple
     * names.
     */
    @Test
    public void getNextWorksWithMultipleNames() {
        final LookupQueue queue = makeLookupQueue();
        final LookupQueue queue2 = makeLookupQueue();

        queue.enqueue("Jemma");
        queue.enqueue("Harry");
        queue.enqueue("Bob");
        queue.enqueue("Julia");
        queue.enqueue("Jemma");
        queue.enqueue("Harry");
        queue.enqueue("Bob");
        queue.enqueue("Jemma");
        queue.enqueue("Harry");
        queue.enqueue("Jemma");
        queue.enqueue("Jemma");
        queue.enqueue("Jemma");
        queue.enqueue("Jemma");

        // ensure lookup queues do not share data
        queue2.enqueue("Jemma");
        queue2.enqueue("Jon Jacob Jingleheimer Schmidt");

        assertEquals("Harry", queue.getNext("Jemma"));
        assertEquals("Bob", queue.getNext("Harry"));
        assertEquals("Julia", queue.getNext("Bob"));
        assertEquals(null, queue.getNext("Julia"));

        // ensure lookup queues do not share data
        assertEquals("Jon Jacob Jingleheimer Schmidt", queue2.getNext("Jemma"));
        assertEquals(null, queue2.getNext("Jon Jacob Jingleheimer Schmidt"));
    }

    /**
     * Tests that the getNext operation throws on non-existent names on a lookup
     * queue with multiple names.
     */
    @Test(expected = NoSuchElementException.class)
    public void getNextThrowsWithMultipleNames() {
        final LookupQueue queue = makeLookupQueue();
        final LookupQueue queue2 = makeLookupQueue();

        queue.enqueue("Jemma");
        queue.enqueue("Harry");
        queue.enqueue("Bob");
        queue.enqueue("Julia");
        queue.enqueue("Jemma");
        queue.enqueue("Harry");
        queue.enqueue("Bob");
        queue.enqueue("Jemma");
        queue.enqueue("Harry");
        queue.enqueue("Jemma");
        queue.enqueue("Jemma");
        queue.enqueue("Jemma");
        queue.enqueue("Jemma");

        // ensure lookup queues do not share data
        queue2.enqueue("Jon Jacob Jingleheimer Schmidt");

        // this should throw an exception
        queue.getNext("Jon Jacob Jingleheimer Schmidt");
    }

    /**
     * Tests that the getPrevious operation throws an exception on an empty
     * lookup queue.
     */
    @Test(expected = NoSuchElementException.class)
    public void getPreviousThrowsOnEmpty() {
        final LookupQueue queue = makeLookupQueue();

        // this should throw an exception
        queue.getPrevious("Anything");
    }

    /**
     * Tests that the getPrevious operation works on a lookup queue after a
     * dequeue operation.
     */
    @Test
    public void getPreviousWorksAfterDequque() {
        final LookupQueue queue = makeLookupQueue();
        final LookupQueue queue2 = makeLookupQueue();

        queue.enqueue("Jemma");
        queue.enqueue("Harry");
        queue.enqueue("Bob");
        queue.enqueue("Julia");
        queue.enqueue("Jemma");
        queue.enqueue("Harry");
        queue.enqueue("Bob");
        queue.enqueue("Jemma");
        queue.enqueue("Harry");
        queue.enqueue("Jemma");
        queue.enqueue("Jemma");
        queue.enqueue("Jemma");
        queue.enqueue("Jemma");
        queue.dequeue();
        queue.dequeue();

        // ensure lookup queues do not share data
        queue2.enqueue("Jemma");
        queue2.enqueue("Jon Jacob Jingleheimer Schmidt");

        assertEquals(null, queue.getPrevious("Bob"));
        assertEquals("Bob", queue.getPrevious("Julia"));

        // ensure lookup queues do not share data
        assertEquals(null, queue2.getPrevious("Jemma"));
        assertEquals("Jemma", queue2.getPrevious("Jon Jacob Jingleheimer Schmidt"));
    }

    /**
     * Tests that the getPrevious operation throws on non-existent names on a
     * names after they are dequeued.
     */
    @Test(expected = NoSuchElementException.class)
    public void getPreviousThrowsAfterDequeue() {
        final LookupQueue queue = makeLookupQueue();
        final LookupQueue queue2 = makeLookupQueue();

        queue.enqueue("Jemma");
        queue.enqueue("Harry");
        queue.enqueue("Bob");
        queue.enqueue("Julia");
        queue.enqueue("Jemma");
        queue.enqueue("Harry");
        queue.enqueue("Bob");
        queue.enqueue("Jemma");
        queue.enqueue("Harry");
        queue.enqueue("Jemma");
        queue.enqueue("Jemma");
        queue.enqueue("Jemma");
        queue.enqueue("Jemma");
        queue.dequeue();

        // ensure lookup queues do not share data
        queue2.enqueue("Jemma");
        assertEquals(null, queue2.getNext("Jemma"));

        // this should throw an exception
        queue.getPrevious("Jemma");
    }

    /**
     * Tests that all of the operations work with 20000 "names".
     */
    @Test(timeout = 1000)
    public void superStressTest() {
        final LookupQueue queue = makeLookupQueue();

        List<String> numberNames = new ArrayList<>(30000);
        for (int i = 0; i < 30000; i++) {
            numberNames.add(Integer.toString(i));
        }

        Collections.shuffle(numberNames);

        // enqueue lots
        for (int i = 0; i < 20000; i++) {
            assertEquals(true, queue.enqueue(numberNames.get(i)));
        }

        // test size
        assertEquals(20000, queue.size());

        // enqueue repeats
        for (int i = 0; i < 20000; i++) {
            assertEquals(false, queue.enqueue(numberNames.get(i)));
        }

        // test contains
        for (int i = 0; i < 20000; i++) {
            assertTrue(queue.contains(numberNames.get(i)));
        }
        for (int i = 20000; i < 30000; i++) {
            assertFalse(queue.contains(numberNames.get(i)));
        }

        // test previous
        for (int i = 1; i < 20000; i++) {
            assertEquals(numberNames.get(i - 1), queue.getPrevious(numberNames.get(i)));
        }

        // test next
        for (int i = 1; i < 20000; i++) {
            assertEquals(numberNames.get(i), queue.getNext(numberNames.get(i - 1)));
        }

        // test names
        String[] addedNames = new String[20000];
        numberNames.subList(0, 20000).toArray(addedNames);
        assertArrayEquals(addedNames, queue.names());

        // dequeue some
        for (int i = 0; i < 5000; i++) {
            assertEquals(numberNames.get(i), queue.dequeue());
        }

        // test size after dequeue
        assertEquals(15000, queue.size());

        // test contains after dequeue
        for (int i = 0; i < 5000; i++) {
            assertFalse(queue.contains(numberNames.get(i)));
        }
        for (int i = 5000; i < 20000; i++) {
            assertTrue(queue.contains(numberNames.get(i)));
        }
        for (int i = 20000; i < 30000; i++) {
            assertFalse(queue.contains(numberNames.get(i)));
        }

        // test previous after dequeue
        for (int i = 5001; i < 20000; i++) {
            assertEquals(numberNames.get(i - 1), queue.getPrevious(numberNames.get(i)));
        }

        // test next after dequeue
        for (int i = 5001; i < 20000; i++) {
            assertEquals(numberNames.get(i), queue.getNext(numberNames.get(i - 1)));
        }

        // test names after dequeue
        String[] addedNamesAfterDequeue = new String[15000];
        numberNames.subList(5000, 20000).toArray(addedNamesAfterDequeue);
        assertArrayEquals(addedNamesAfterDequeue, queue.names());
    }

}
