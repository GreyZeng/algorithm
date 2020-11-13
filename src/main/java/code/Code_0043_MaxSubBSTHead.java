package code;

/**
 * 给定一棵二叉树的头节点head， 返回这颗二叉树中最大的二叉搜索子树的头节点
 */
public class Code_0043_MaxSubBSTHead {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	public static class Info {
		public int max;
		public int min;
		public boolean isBST;
		public Node maxSubBSTHead;
		public int maxSubBSTSize;

		public Info(int max, int min, boolean isBST, Node maxSubBSTHead, int maxSubBSTSize) {
			this.max = max;
			this.min = min;
			this.isBST = isBST;
			this.maxSubBSTHead = maxSubBSTHead;
			this.maxSubBSTSize = maxSubBSTSize;
		}
	}

	public static Node maxSubBSTHead(Node head) {
		if (null == head) {
			return head;
		}
		return p(head).maxSubBSTHead;
	}

	public static Info p(Node head) {
		if (head == null) {
			return null;
		}
		Info left = p(head.left);
		int max = head.value;
		int min = head.value;
		boolean isBST = true;
		Node maxSubHead = head;
		int maxSubBSTSize = 0;
		if (left != null) {
			max = Math.max(left.max, max);
			min = Math.min(left.min, min);
			isBST = left.isBST && (left.max < head.value);
		}
		Info right = p(head.right);
		if (right != null) {
			max = Math.max(right.max, max);
			min = Math.min(right.min, min);
			isBST = isBST && right.isBST && (right.min > head.value);
		}
		if (isBST) {
			maxSubBSTSize = (left != null ? left.maxSubBSTSize : 0) + (right != null ? right.maxSubBSTSize : 0) + 1;
		} else {
			maxSubBSTSize = Math.max((left != null ? left.maxSubBSTSize : 0),
					(right != null ? right.maxSubBSTSize : 0));
			if (left != null && maxSubBSTSize == left.maxSubBSTSize) {
				maxSubHead = left.maxSubBSTHead;
			} else if (right != null) {
				maxSubHead = right.maxSubBSTHead;
			}
		}
		return new Info(max, min, isBST, maxSubHead, maxSubBSTSize);
	}

}
