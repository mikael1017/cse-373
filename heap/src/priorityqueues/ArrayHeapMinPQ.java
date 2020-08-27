package priorityqueues;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T extends Comparable<T>> implements ExtrinsicMinPQ<T> {
    // IMPORTANT: Do not rename these fields or change their visibility.
    // We access these during grading to test your code.
    static final int START_INDEX = 1;
    List<PriorityNode<T>> items;
    private int size;

    public ArrayHeapMinPQ() {
        items = new ArrayList<>();
        items.add(null);
        this.size = 0;
    }

    // Here's a method stub that may be useful. Feel free to change or remove it, if you wish.
    // You'll probably want to add more helper methods like this one to make your code easier to read.
    /**
     * A helper method for swapping the items at two indices of the array heap.
     */
    private void swap(int a, int b) {
        PriorityNode<T> temp = this.items.get(b);
        this.items.add(a, temp);
        this.items.add(b, this.items.remove(a + 1));
    }

    @Override
    public void add(T item, double priority) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        this.items.add(new PriorityNode<>(item, priority));
        this.size++;
        if (this.size > 1) {
            helperAdd(item, priority);
        }

    }

    private void helperAdd(T item, double priority) {
        int currIndex = this.size;
        while (this.items.get(currIndex / 2).getPriority() > this.items.get(currIndex).getPriority()) {
            this.swap(currIndex, currIndex / 2);
            currIndex = currIndex / 2;
        }
    }

    @Override
    public boolean contains(T item) {
        for (int i = START_INDEX; i < this.size + START_INDEX; i++) {
            if (this.items.get(i).getItem().equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T peekMin() {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        return this.items.get(START_INDEX).getItem();
    }

    @Override
    public T removeMin() {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        T minNode = this.items.get(START_INDEX).getItem();
        //  System.out.println(this.size + " = size ");
        //  System.out.println(this.items.get(this.size()));
        this.swap(START_INDEX, this.size);
        this.items.remove(this.size);
        this.size--;
        removeHelper();
        return minNode;
    }

    private void removeHelper() {
        int currIndex = START_INDEX;
        while ((currIndex * 2) + 1 <= this.size) {
            double curr = this.items.get(currIndex).getPriority();
            double left = this.items.get(currIndex * 2).getPriority();
            if ((currIndex * 2) + 1 <= this.size) {
                double right = this.items.get((currIndex * 2) + 1).getPriority();
                double minPriority = Math.min(left, right);
                if (curr > minPriority) {
                    if (left < right) {
                        this.swap(currIndex, currIndex * 2);
                        currIndex = currIndex * 2;
                    } else {
                        this.swap(currIndex, (currIndex * 2) + 1);
                        currIndex = (currIndex * 2) + 1;
                    }
                }
            } else {
                if (curr > left) {
                    this.swap(currIndex, currIndex *2);
                    currIndex = currIndex * 2;
                }
            }
        }
    }

    @Override
    public void changePriority(T item, double priority) {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public int size() {
        return this.size;
    }
}
