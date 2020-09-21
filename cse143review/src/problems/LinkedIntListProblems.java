package problems;

import datastructures.LinkedIntList;
// Checkstyle will complain that this is an unused import until you use it in your code.
import datastructures.LinkedIntList.ListNode;

/**
 * See the spec on the website for example behavior.
 *
 * REMEMBER THE FOLLOWING RESTRICTIONS:
 * - do not call any methods on the `LinkedIntList` objects.
 * - do not construct new `ListNode` objects for `reverse3` or `firstToLast`
 *      (though you may have as many `ListNode` variables as you like).
 * - do not construct any external data structures such as arrays, queues, lists, etc.
 * - do not mutate the `data` field of any node; instead, change the list only by modifying
 *      links between nodes.
 */

public class LinkedIntListProblems {

    /**
     * Reverses the 3 elements in the `LinkedIntList` (assume there are exactly 3 elements).
     */
    public static void reverse3(LinkedIntList list) {
        ListNode temp = list.front;
        ListNode curr = list.front.next.next;
        curr.next = temp.next;
        temp.next = null;
        curr.next.next = temp;
        list.front = curr;
    }

    /**
     * Moves the first element of the input list to the back of the list.
     */
    public static void firstToLast(LinkedIntList list) {
        if (list.front != null && list.front.next != null) {
            ListNode root = list.front;
            ListNode first = list.front;
            root = root.next;
            first.next = null;
            ListNode curr = root;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = first;
            list.front = root;
        }
    }

    /**
     * Returns a list consisting of the integers of a followed by the integers
     * of n. Does not modify items of A or B.
     */
    public static LinkedIntList concatenate(LinkedIntList a, LinkedIntList b) {
        // Hint: you'll need to use the 'new' keyword to construct new objects.
        LinkedIntList sum = new LinkedIntList();
        if (a.front != null || b.front != null) {
            if (a.front == null) {
                sum = new LinkedIntList(b.front.data);
                ListNode curr = sum.front;
                ListNode temp = b.front;
                while (temp.next != null) {
                    curr.next = new ListNode(temp.next.data);
                    temp = temp.next;
                    curr = curr.next;
                }
            } else {
                sum = new LinkedIntList(a.front.data);
                ListNode curr = sum.front;
                ListNode temp = a.front;
                while (temp.next != null) {
                    curr.next = new ListNode(temp.next.data);
                    temp = temp.next;
                    curr = curr.next;
                }
                temp = b.front;
                while (temp != null) {
                    curr.next = new ListNode(temp.data);
                    temp = temp.next;
                    curr = curr.next;
                }
            }

        }
        return sum;
    }
}
