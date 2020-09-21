package maps;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @see AbstractIterableMap
 * @see Map
 */
public class ChainedHashMap<K, V> extends AbstractIterableMap<K, V> {
    // TODO: define reasonable default values for each of the following three fields
    private static final double DEFAULT_RESIZING_LOAD_FACTOR_THRESHOLD = 5;
    private static final int DEFAULT_INITIAL_CHAIN_COUNT = 5;
    private static final int DEFAULT_INITIAL_CHAIN_CAPACITY = 3;

    /*
    Warning:
    DO NOT rename this `chains` field or change its type.
    We will be inspecting it in our Gradescope-only tests.

    An explanation of this field:
    - `chains` is the main array where you're going to store all of your data (see the [] square bracket notation)
    - The other part of the type is the AbstractIterableMap<K, V> -- this is saying that `chains` will be an
    array that can store an AbstractIterableMap<K, V> object at each index.
       - AbstractIterableMap represents an abstract/generalized Map. The ArrayMap you wrote in the earlier part
       of this project qualifies as one, as it extends the AbstractIterableMap class.  This means you can
       and should be creating ArrayMap objects to go inside your `chains` array as necessary. See the instructions on
       the website for diagrams and more details.
        (To jump to its details, middle-click or control/command-click on AbstractIterableMap below)
     */
    AbstractIterableMap<K, V>[] chains;
    private int numChains = 0;
    private int size = 0;

    // You're encouraged to add extra fields (and helper methods) though!

    public ChainedHashMap() {
        this(DEFAULT_RESIZING_LOAD_FACTOR_THRESHOLD, DEFAULT_INITIAL_CHAIN_COUNT, DEFAULT_INITIAL_CHAIN_CAPACITY);
    }

    public ChainedHashMap(double resizingLoadFactorThreshold, int initialChainCount, int chainInitialCapacity) {
        this.chains = createArrayOfChains(initialChainCount);
        this.numChains = initialChainCount;
    }

    /**
     * This method will return a new, empty array of the given size that can contain
     * {@code AbstractIterableMap<K, V>} objects.
     *
     * Note that each element in the array will initially be null.
     *
     * Note: You do not need to modify this method.
     * @see ArrayMap createArrayOfEntries method for more background on why we need this method
     */
    @SuppressWarnings("unchecked")
    private AbstractIterableMap<K, V>[] createArrayOfChains(int arraySize) {
        return (AbstractIterableMap<K, V>[]) new AbstractIterableMap[arraySize];
    }

    /**
     * Returns a new chain.
     *
     * This method will be overridden by the grader so that your ChainedHashMap implementation
     * is graded using our solution ArrayMaps.
     *
     * Note: You do not need to modify this method.
     */

    protected AbstractIterableMap<K, V> createChain(int initialSize) {
        return new ArrayMap<>(initialSize);
    }

    @Override
    public V get(Object key) {
        Iterator<Map.Entry<K, V>> iter = this.iterator();
        if (key == null) {
            while (iter.hasNext()) {
                Map.Entry<K, V> entry = iter.next();
                if (entry.getKey() == null) {
                    return entry.getValue();
                }
            }
        } else {
            while (iter.hasNext()) {
                Map.Entry<K, V> entry = iter.next();
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        //  when dict doesn't contain input key
        return null;
    }

    @Override
    public V put(K key, V value) {
        Iterator<Map.Entry<K, V>> iter = this.iterator();
        while (iter.hasNext()) {
            Map.Entry<K, V> entry = iter.next();
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        int hashcode = key.hashCode();
        AbstractIterableMap<K, V> addMap = this.createChain(DEFAULT_INITIAL_CHAIN_CAPACITY);
        if (this.numChains == DEFAULT_RESIZING_LOAD_FACTOR_THRESHOLD) {
            return null;
        }
        addMap.put(key, value);
        this.chains[hashcode] = addMap;
        return null;

    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void clear() {
        this.chains = createArrayOfChains(DEFAULT_INITIAL_CHAIN_COUNT);
    }

    @Override
    public boolean containsKey(Object key) {
        Iterator<Map.Entry<K, V>> iter = this.iterator();
        if (key == null) {
            while (iter.hasNext()) {
                Map.Entry<K, V> entry = iter.next();
                if (entry.getKey() == null) {
                    return true;
                }
            }
        } else {
            while (iter.hasNext()) {
                Map.Entry<K, V> entry = iter.next();
                if (entry.getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        // Note: you won't need to change this method (unless you add more constructor parameters)
        return new ChainedHashMapIterator<>(this.chains);
    }

    /*
    See the assignment webpage for tips and restrictions on implementing this iterator.
     */
    private static class ChainedHashMapIterator<K, V> implements Iterator<Map.Entry<K, V>> {
        private AbstractIterableMap<K, V>[] chains;
        private int chainIndex = 0;
        Iterator<Map.Entry<K, V>> iter;
        // You may add more fields and constructor parameters

        public ChainedHashMapIterator(AbstractIterableMap<K, V>[] chains) {
            this.chains = chains;
        }

        @Override
        public boolean hasNext() {
            if (this.chains[chainIndex] != null) {
                this.iter = this.chains[chainIndex].iterator();
            }
            return this.iter.hasNext();
        }

        @Override
        public Map.Entry<K, V> next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("no such element has been found");
            }
            if (this.iter.hasNext()) {
                return this.iter.next();
            } else if (!this.iter.hasNext()) {
                if (this.chains[chainIndex + 1] != null) {
                    chainIndex++;
                    this.iter = this.chains[chainIndex].iterator();
                }
            }
            return this.iter.next();
        }
    }
}
