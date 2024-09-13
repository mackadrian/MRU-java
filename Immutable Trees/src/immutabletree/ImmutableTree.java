/*
Name: Mack Bautista
Email: mbaut981@mtroyal.ca
Course: COMP2631-001
Instructor: Bita Sadeghi
Assignment: 4
Due Date: Mar. 28, 2024

*/

package immutabletree;

import java.util.NoSuchElementException;

public final class ImmutableTree<T> implements ImmutableDictionary<T> {
	private final String key;
	private final T value;
	private final ImmutableTree<T> left;
	private final ImmutableTree<T> right;
	
	public ImmutableTree() {
		this.key = null;
		this.value = null;
		this.left = null;
		this.right = null;
	}
	
	private ImmutableTree(String key, T value, ImmutableTree<T> left, ImmutableTree<T> right) {
	    this.key = key;
	    this.value = value;
	    this.left = left;
	    this.right = right;
	}
	
	public ImmutableTree<T> put(String key, T value) {
	    ImmutableTree<T> result;

	    if (this.key == null) {
	        result = new ImmutableTree<T>(key, value, new ImmutableTree<T>(), new ImmutableTree<T>());
	    } else {
	        int cmp = key.compareTo(this.key);
	        if (cmp < 0) {
	            result = new ImmutableTree<T>(this.key, this.value, (ImmutableTree<T>) this.left.put(key, value), this.right);
	        } else if (cmp > 0) {
	            result = new ImmutableTree<T>(this.key, this.value, this.left, (ImmutableTree<T>) this.right.put(key, value));
	        } else {
	            result = new ImmutableTree<T>(this.key, value, this.left, this.right);
	        }
	    }

	    return result;
	}

	public ImmutableDictionary<T> remove(String key) {
	    ImmutableTree<T> result;

	    if (this.key == null) {
	        result = this;
	    } else {
	        int cmp = key.compareTo(this.key);
	        if (cmp < 0) {
	            result = new ImmutableTree<T>(this.key, this.value, (ImmutableTree<T>)this.left.remove(key), this.right);
	        } else if (cmp > 0) {
	            result = new ImmutableTree<T>(this.key, this.value, this.left, (ImmutableTree<T>)this.right.remove(key));
	        } else {
	            if (this.left.key == null) {
	                result = this.right;
	            } else if (this.right.key == null) {
	                result = this.left;
	            } else {
	                String minKey = this.right.min();
	                result = new ImmutableTree<T>(minKey, this.right.get(minKey), this.left, (ImmutableTree<T>)this.right.remove(minKey));
	            }
	        }
	    }

	    return result;
	}

	private String min() {
	    String result;
	    if (this.left.key == null) {
	        result = this.key;
	    } else {
	        result = this.left.min();
	    }
	    return result;
	}

	public T get(String key) throws NoSuchElementException {
	    T result;
	    if (this.key == null) {
	        throw new NoSuchElementException("Key not found: " + key);
	    }
	    int cmp = key.compareTo(this.key);
	    if (cmp < 0) {
	        result = this.left.get(key);
	    } else if (cmp > 0) {
	        result = this.right.get(key);
	    } else {
	        result = this.value;
	    }
	    return result;
	}

	public boolean contains(String key) {
	    boolean result;
	    if (this.key == null) {
	        result = false;
	    } else {
	        int cmp = key.compareTo(this.key);
	        if (cmp < 0) {
	            result = this.left.contains(key);
	        } else if (cmp > 0) {
	            result = this.right.contains(key);
	        } else {
	            result = true;
	        }
	    }
	    return result;
	}

	public int size() {
	    int result;
	    if (this.key == null) {
	        result = 0;
	    } else {
	        result = 1 + this.left.size() + this.right.size();
	    }
	    return result;
	}

	public String[] keys() {
	    String[] keys;
	    if (this.key == null) {
	        keys = new String[0];
	    } else {
	        String[] leftKeys = this.left.keys();
	        String[] rightKeys = this.right.keys();
	        keys = new String[leftKeys.length + 1 + rightKeys.length];
	        System.arraycopy(leftKeys, 0, keys, 0, leftKeys.length);
	        keys[leftKeys.length] = this.key;
	        System.arraycopy(rightKeys, 0, keys, leftKeys.length + 1, rightKeys.length);
	    }
	    return keys;
	}

	public String[] preOrder() {
	    String[] keys;
	    if (this.key == null) {
	        keys = new String[0];
	    } else {
	        String[] leftKeys = this.left.preOrder();
	        String[] rightKeys = this.right.preOrder();
	        keys = new String[leftKeys.length + 1 + rightKeys.length];
	        keys[0] = this.key;
	        System.arraycopy(leftKeys, 0, keys, 1, leftKeys.length);
	        System.arraycopy(rightKeys, 0, keys, leftKeys.length + 1, rightKeys.length);
	    }
	    return keys;
	}

}
