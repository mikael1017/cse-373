package deques;

public class LinkedDeque<T> extends AbstractDeque<T> {
    private int size;
    // IMPORTANT: Do not rename these fields or change their visibility.
    // We access these during grading to test your code.
    Node<T> front;
    Node<T> back;
    private T sentinelValue;
    // Feel free to add any additional fields you may need, though.

    public LinkedDeque() {
        size = 0;
        front = new Node<T>(sentinelValue);
        back = new Node<T>(sentinelValue);
        front.next = back;
        back.prev = front;
    }

    public void addFirst(T item) {
        size += 1;
        Node<T> node = new Node<>(item, front, front.next);
        front.next.prev = node;
        front.next = node;
    }

    public void addLast(T item) {
        size += 1;
        Node<T> node = new Node<>(item, back.prev, back);
        back.prev.next = node;
        back.prev = node;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T value = front.next.value;
            size -= 1;
            front.next = front.next.next;
            front.next.prev = front;
            return value;
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            size -= 1;
            T value = back.prev.value;
            back.prev = back.prev.prev;
            back.prev.next = back;
            return value;
        }
    }

    //  must use iteration, not recursion
    public T get(int index) {
        if ((index >= size) || (index < 0)) {
            return null;
        }
        Node<T> curr = front.next;
        int i = 0;
        T value = null;
        while (curr != back) {
            if (i == index) {
                value = curr.value;
            }
            i++;
            curr = curr.next;
        }
        return value;
    }

    //  must take constant time
    public int size() {
        return size;
    }
}
