package deques;

/** A buggy array implementation of the Deque interface. */
public class ArrayDeque<T> extends AbstractDeque<T> {
    private T[] data;
    private int front;
    private int back;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayDeque() {
        //  default : size 8 array
        data = (T[]) new Object[8];
        front = 0;
        back = 1;
        size = 0;
    }

        //  increase index of i by one unit to the right
    private static int increment(int i, int length) {
        if (i == length - 1) {
            return 0;
        } else {
            return i + 1;
        }
    }
        //  decrease index of i by one unit to the left
    private static int decrement(int i, int length) {
        if (i == 0) {
            return length - 1;
        } else {
            return i - 1;
        }
    }
        //  add given item into the front of the array
    public void addFirst(T item) {
        if (size == data.length) {
            resize(data.length * 2);
        }
        data[front] = item;

        front = decrement(front, data.length);
        size += 1;

    }
        //  add given item into the back of the array
    public void addLast(T item) {
        if (size == data.length) {
            resize(data.length * 2);
        }
        data[back] = item;
        back = increment(back, data.length);
        size += 1;
    }

        //  remove the first element of the array
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        front = increment(front, data.length);
        T result = data[front];
        data[front] = null;
        size -= 1;
        if (needsDownsize()) {
            resize(data.length / 2);
        }
        return result;
    }

        //  remove the last element of the array
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        back = decrement(back, data.length);
        T result = data[back];
        data[back] = null;
        size -= 1;
        if (needsDownsize()) {
            resize(data.length / 2);
        }
        return result;
    }

        //  return the element of the array at given index
    public T get(int index) {
        if (index >= size) {
            return null;
        } else {
            int place = front + 1 + index;
            return data[place % data.length];
        }
    }
        //  return string representation of the array
    public String toString() {
        // We use a StringBuilder since it concatenates strings more efficiently
        // than using += in a loop
        StringBuilder output = new StringBuilder();
        if (size >= 0) {
            int counter = 0;
            int i = increment(front, data.length);
            while (counter < size) {
                output.append(data[i]).append(" ");
                i = increment(i, data.length);
                counter += 1;
            }
        }
        return output.toString();
    }
        //  return size of the deque
    public int size() {
        return size;
    }

        //  resize the array to given capacity
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        //  make new array with given capacity
        T[] newData = (T[]) new Object[capacity];
        int i = increment(front, data.length);
        for (int newIndex = 0; newIndex < size; newIndex += 1) {
            newData[newIndex] = data[i];
            i = increment(i, size);
        }
        front = newData.length - 1;
        back = size;
        data = newData;
    }

    private boolean needsDownsize() {
        return ((double) size) / data.length < 0.25 && data.length >= 16;
    }
}
