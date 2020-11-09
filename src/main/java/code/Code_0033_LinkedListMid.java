package code;

//1）输入链表头节点，奇数长度返回中点，偶数长度返回上中点 
//2）输入链表头节点，奇数长度返回中点，偶数长度返回下中点 
//3）输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个 
//4）输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
// 可以使用ArrayList方式
// 也可以通过双指针的方式
public class Code_0033_LinkedListMid {
	public static class Node {
		public int value;
		public Node next;

		public Node(int v) {
			value = v;
		}
	}

	/**
	 * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点 1个节点 则返回 第0个节点 2个节点 则返回第0个节点 3个节点 则返回第1个节点
	 */
	public static Node midOrUpMidNode(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return head;
		}
		// 链表有3个点或以上
		Node slow = head.next;
		Node fast = head.next.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	/**
	 * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
	 */
	public static Node midOrDownMidNode(Node head) {
		if (head == null || head.next == null) {
			return head;
		}
		Node slow = head.next;
		Node fast = head.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	/**
	 * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
	 */
	public static Node midOrUpMidPreNode(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		Node slow = head;
		Node fast = head.next.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	/**
	 * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
	 */
	public static Node midOrDownMidPreNode(Node head) {
		if (head == null || head.next == null) {
			return null;
		}
		if (head.next.next == null) {
			return head;
		}
		Node slow = head;
		Node fast = head.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
}
