package maps;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @see AbstractIterableMap
 * @see Map
 */
public class ArrayMap<K, V> extends AbstractIterableMap<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private int size = 0;
    //private int index = 0;

    /*
    Warning:
    DO NOT rename this `entries` field or change its type.
    We will be inspecting it in our Gradescope-only tests.

    An explanation of this field:
    - `entries` is the array where you're going to store all of your data (see the [] square bracket notation)
    - The other part of the type is the SimpleEntry<K, V> -- this is saying that `entries` will be an
    array that can store a SimpleEntry<K, V> object at each index.
       - SimpleEntry represents an object containing a key and a value.
        (To jump to its details, middle-click or control/command-click on SimpleEntry below)

    */
    SimpleEntry<K, V>[] entries;

    // You may add extra fields or helper methods though!

    public ArrayMap() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayMap(int initialCapacity) {
        this.entries = this.createArrayOfEntries(initialCapacity);

    }

    /**
     * This method will return a new, empty array of the given size that can contain
     * {@code Entry<K, V>} objects.
     *
     * Note that each element in the array will initially be null.
     *
     * Note: You do not need to modify this method.
     */
    @SuppressWarnings("unchecked")
    private SimpleEntry<K, V>[] createArrayOfEntries(int arraySize) {
        /*
        It turns out that creating arrays of generic objects in Java is complicated due to something
        known as "type erasure."

        We've given you this helper method to help simplify this part of your assignment. Use this
        helper method as appropriate when implementing the rest of this class.

        You are not required to understand how this method works, what type erasure is, or how
        arrays and generics interact.
        */
        return (SimpleEntry<K, V>[]) (new SimpleEntry[arraySize]);
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
        if (this.entries.length - 1 == this.size) {
            SimpleEntry<K, V>[] temp = this.createArrayOfEntries(this.size * 2);
            for (int i = 0; i < this.entries.length; i++) {
                temp[i] = this.entries[i];
            }
            this.entries = temp;
        }
        this.entries[this.size] = new SimpleEntry<>(key, value);
        this.size++;
        return null;
    }

    @Override
    public V remove(Object key) {
        if (!this.containsKey(key)) {
            return null;
        } else {
            this.size--;
            int index = 0;
            Iterator<Map.Entry<K, V>> iter = this.iterator();
            if (key == null) {
                while (iter.hasNext()) {
                    Map.Entry<K, V> entry = iter.next();
                    if (entry.getKey() == null) {
                        V result = entry.getValue();
                        this.entries[index] = this.entries[size];
                        this.entries[size] = null;
                        return result;
                    }
                    index++;
                }
            } else {
                while (iter.hasNext()) {
                    Map.Entry<K, V> entry = iter.next();
                    if (entry.getKey().equals(key)) {
                        V result = entry.getValue();
                        this.entries[index] = this.entries[size];
                        this.entries[size] = null;
                        return result;
                    }
                    index++;
                }
            }
            return null;
        }
    }

    @Override
    public void clear() {
        this.entries = this.createArrayOfEntries(DEFAULT_INITIAL_CAPACITY );
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
        return new ArrayMapIterator<>(this.entries);
    }

    // TODO: after you implement the iterator, remove this toString implementation
    // Doing so will give you a better string representation for assertion errors the debugger.
    @Override
    public String toString() {
        return super.toString();
    }

    private static class ArrayMapIterator<K, V> implements Iterator<Map.Entry<K, V>> {
        private final SimpleEntry<K, V>[] entries;
        private int index = 0;
        private int size = 0;
        // You may add more fields and constructor parameters

        public ArrayMapIterator(SimpleEntry<K, V>[] entries) {
            this.entries = entries;
        }

        @Override
        public boolean hasNext() {
            boolean result = this.entries[index] != null;
            return result;
        }

        @Override
        //  throw nosuch element if element is hot found
        public Map.Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("no such element has been found");
            }
            SimpleEntry<K, V> result = this.entries[index];
            index++;
            return result;
        }
    }
}
