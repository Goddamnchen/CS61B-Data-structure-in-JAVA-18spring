package lab9;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Guanting Chen
 * @since  07/20/2018
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }


    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) return null;
        int cmp = p.key.compareTo(key);
        if (cmp < 0) return getHelper(key, p.right);
        else if (cmp > 0) return getHelper(key, p.left);
        else return p.value;

    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        return getHelper(key, this.root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            this.size++;
            return new Node(key, value);
        }
        int cmp = p.key.compareTo(key);
        if (cmp < 0)  p.right = putHelper(key, value, p.right);
        else if (cmp > 0)  p.left = putHelper(key, value, p.left);
        else {
            p.value = value;
        }
        return p;

    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("put get() with a null key");
        if (value == null) throw new IllegalArgumentException("put get() with a null value");
        this.root = putHelper(key, value, this.root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return this.size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    private Node removeHelper(Node p, K key) {
        int cmp = key.compareTo(p.key);
        if      (cmp < 0) p.left  = removeHelper(p.left, key);
        else if (cmp > 0) p.right = removeHelper(p.right, key);
        else
        {
            //if p is not a 2-child node, directly connect its child to p's parent
            if (p.right == null) return p.left;
            if (p.left == null) return p.right;
            //otherwise, find and store p's successor
            //dealing with successor's link, including parent and right child
            //then replace the p with the stored successor
            Node successor = min(p.right);  // See page 407.
            successor.right = deleteMin(p.right);
            successor.left = p.left;
            p = successor;
        }
        return p;

    }
    private Node deleteMin(Node p) {
        if (p.left == null) return p.right;
        p.left = deleteMin(p.left);
        return p;
    }
    private Node min(Node p) {
        if (p.left == null) return p;
        return min(p.left);
    }
    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException("calls remove() with a null key");
        V value = get(key);
        if (size == 0 || value == null) return null;
        else {
            root = removeHelper(root, key);
            size--;
            return value;
        }

    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new TreeIterator();
    }
    private class TreeIterator implements Iterator<K> {

        public boolean hasNext() {
            return true;
        }
        public K next(){
            return null;
        }
    }
}
