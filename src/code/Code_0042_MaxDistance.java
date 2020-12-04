package code;

/**
 * 给定一棵二叉树的头节点head，任何两个节点之间都存在距离(经过的节点数！）， 返回整棵二叉树的最大距离
 */
public class Code_0042_MaxDistance {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int maxDistance(Node head) {
		if (head == null) {
			return 0;
		}
		return process(head).max;
	}

	public static class Info {
		public int maxHeight; // 从当前节点插到最底部最大高度
		public int max; // 当前树的最大距离

		public Info(int max, int maxHeight) {
			this.max = max;
			this.maxHeight = maxHeight;
		}
	}

	private static Info process(Node head) {
		if (head == null) {
			return new Info(0, 0);
		}
		Info left = process(head.left);
		Info right = process(head.right);
		int max = Math.max(left.maxHeight + right.maxHeight + 1, Math.max(left.max, right.max));
		int maxHeight = Math.max(left.maxHeight, right.maxHeight) + 1;
		return new Info(max, maxHeight);
	}
}
