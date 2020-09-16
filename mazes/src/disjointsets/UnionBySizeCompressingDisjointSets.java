package disjointsets;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * A quick-union-by-size data structure with path compression.
 * @see DisjointSets for more documentation.
 */
public class UnionBySizeCompressingDisjointSets<T> implements DisjointSets<T> {
    // Do NOT rename or delete this field. We will be inspecting it directly in our private tests.
    List<Integer> pointers;
    private int size;
    Map<T, Integer> dict;

    /*
    However, feel free to add more fields and private helper methods. You will probably need to
    add one or two more fields in order to successfully implement this class.
    */

    public UnionBySizeCompressingDisjointSets() {
        this.size = 0;
        this.pointers = new ArrayList<>();
        this.dict = new HashMap<>();
    }

    @Override
    public void makeSet(T item) {
        this.pointers.add(-1);
        this.dict.put(item, this.size);
        this.size++;
    }

    @Override
    public int findSet(T item) {
        int index = this.dict.get(item);
        int value = this.pointers.get(index);
        while (value >= 0) {
            index = value;
            value = this.pointers.get(index);
        }
        return index;
    }

    @Override
    public boolean union(T item1, T item2) {
        int index1 = findSet(item1);
        int index2 = findSet(item2);
        if (index1 == index2) {
            return false;
        }
        int value1 = this.pointers.get(index1);
        int value2 = this.pointers.get(index2);
        if (value1 < value2) {
            this.pointers.set(index2, index1);
            this.pointers.set(index1, value1 + value2);
        } else {
            this.pointers.set(index1, index2);
            this.pointers.set(index2, value1 + value2);
        }
        return true;
    }
}
