

package immutabletree;

import java.util.NoSuchElementException;

/**
 * An immutable dictionary that associates string keys with values. None of the
 * methods of the dictionary should ever modify the dictionary. Any methods that
 * would typically modify the dictionary return a new dictionary instead.
 *
 * @author Jason Heard
 * @date Fall 2021
 * 
 * Updated Winter 2024 by Bita Sadeghi
 *
 * @param <T> The type of the associated values in the dictionary.
 */
public interface ImmutableDictionary<T> {
    /**
     * Returns a copy of this dictionary with the given value associated with
     * the given key. If the given value is already associated with the given
     * key, then <code>this</code> should be returned. To determine if the given
     * value is the same as the currently associated value, <code>==</code>
     * should be used. Both a <code>null</code> key and a <code>null</code>
     * value are acceptable.
     *
     * @param key The key to associate the value with in the new dictionary.
     * @param value The value to associate with the key in the new dictionary.
     * @return The new dictionary with the given value associated with the given
     *         key or this if the association was already present in this
     *         dictionary.
     */
    public ImmutableDictionary<T> put(String key, T value);

    /**
     * Returns a copy of this dictionary with no associated value for the given
     * key. If the given key does not have an associated value in this
     * dictionary, then <code>this</code> should be returned.
     *
     * @param key The key whose association should be removed in the returned
     *            dictionary.
     * @return The new dictionary with no value associated with the given key or
     *         <code>this</code> if no association is present in this
     *         dictionary.
     */
    public ImmutableDictionary<T> remove(String key);

    /**
     * Returns the value associated with the given key, throwing an exception if
     * the key has no association. A return value of <code>null</code> indicates
     * that <code>null</code> is associated with the given key.
     *
     * @param key The key whose association value is to be returned.
     * @return The associated value for the given key.
     * @throws NoSuchElementException Thrown if the given key does not have an
     *             associated value.
     */
    public T get(String key) throws NoSuchElementException;

    /**
     * Determines if the given key has an associated value in the dictionary.
     *
     * @param key The key whose presence in the dictionary is to be tested.
     * @return <code>true</code> if the key has an associated value in this
     *         dictionary; <code>false</code> otherwise.
     */
    public boolean contains(String key);

    /**
     * Returns the number of keys that have an associated value.
     *
     * @return The number of keys that have an associated value.
     */
    public int size();

    /**
     * Returns an array with all of the keys that have an associated value in
     * their natural order. If <code>null</code> has an associated value, then
     * it should be first in the array.
     *
     * @return An array with all of the keys that have an associated value in
     *         their natural order.
     */
    public String[] keys();

    /**
     * Returns an array with all of the keys that have an associated value in
     * a pre-order traversal order. If <code>null</code> has an associated
     * value, then it should be first in the array.
     *
     * @return An array with all of the keys that have an associated value in
     *         their natural order.
     */
    public String[] preOrder();

}
