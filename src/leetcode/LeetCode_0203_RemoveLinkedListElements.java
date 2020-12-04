//Remove all elements from a linked list of integers that have value val.
//
//Example:
//
//Input:  1->2->6->3->4->5->6, val = 6
//Output: 1->2->3->4->5
package leetcode;


public class LeetCode_0203_RemoveLinkedListElements {
	public static class ListNode {
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

	public static ListNode removeElements(ListNode head, int val) {
		if (head == null) {
            return null;
        }
        // 找到第一个不需要删除的节点
        // 防止要删的节点就是头节点以及头节点下面的一批节点
        while (head != null) {
            if (head.val != val) {
                break;
            }
            head = head.next;
        }
        ListNode pre = head;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next; 
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
	}

	public static void main(String[] args) {
		System.out.println("begin test");
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(6);
		ListNode n = removeElements(head, 6);
		while (n != null) {
			System.out.println(n.val + ":");
			n = n.next;
		}
	}
}
