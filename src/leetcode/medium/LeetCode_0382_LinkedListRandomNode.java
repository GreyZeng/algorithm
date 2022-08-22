//Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.
//
//        Follow up:
//        What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?
//
//        Example:
//
//// Init a singly linked list [1,2,3].
//        ListNode head = new ListNode(1);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        Solution solution = new Solution(head);
//
//// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
//        solution.getRandom();
package leetcode.medium;

import java.util.Random;

public class LeetCode_0382_LinkedListRandomNode {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class Solution {
        ListNode head;

        /**
         * @param head The linked list's head.
         *             Note that the head is guaranteed to be not null, so it contains at least one node.
         */
        public Solution(ListNode head) {
            this.head = head;
        }

        /**
         * Returns a random node's value.
         */
        public int getRandom() {
            int count = 0;
            Random random = new Random();
            int index = 0;
            int i = 0;
            ListNode c = head;
            while (c != null) {
                count++;
                if (random.nextInt(count) == 0) {
                    index = i;
                }
                c = c.next;
                i++;
            }
            c = head;
            while (index != 0) {
                index--;
                c = c.next;
            }
            return c.val;
        }
    }
}
