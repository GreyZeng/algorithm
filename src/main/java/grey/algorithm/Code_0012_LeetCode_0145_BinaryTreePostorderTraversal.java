package grey.algorithm;

import java.util.*;

// https://leetcode.com/problems/binary-tree-postorder-traversal/
// 二叉树的后序遍历
// 笔记：https://www.cnblogs.com/greyzeng/articles/15941957.html
public class Code_0012_LeetCode_0145_BinaryTreePostorderTraversal {

	// 递归方法
	public List<Integer> postorderTraversal3(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		pos(root, ans);
		return ans;
	}

	public void pos(TreeNode root, List<Integer> ans) {
		if (null != root) {
			pos(root.left, ans);
			pos(root.right, ans);
			ans.add(root.val);
		}
	}

	// 非递归 双栈或者一栈+一链表方式
	// 改造先序遍历
	// 先序遍历是，头，左，右
	// 改造一下，变成：头，右，左
	// 然后：逆序一下，就变成了后序遍历
	// 所以用两个栈即可实现
	public List<Integer> postorderTraversal2(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		if (null == root) {
			return ans;
		}
		Stack<TreeNode> stack = new Stack<>();
		Stack<TreeNode> helper = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			helper.push(node);
			if (node.left != null) {
				stack.push(node.left);
			}
			if (node.right != null) {
				stack.push(node.right);
			}
		}
		while (!helper.isEmpty()) {
			ans.add(helper.pop().val);
		}
		return ans;
	}

	// TODO
	// 【非递归】【单栈】后序遍历
	public static List<Integer> postorderTraversal1(TreeNode h) {
		// 创建一个列表用于存储后序遍历结果
		List<Integer> ans = new ArrayList<>();

		// 如果根节点不为空，开始遍历
		if (h != null) {
			Stack<TreeNode> stack = new Stack<>();
			stack.push(h); // 将根节点压入栈

			// h 的含义：上一次被处理（打印）的节点
			while (!stack.isEmpty()) {
				TreeNode cur = stack.peek(); // 查看栈顶节点但不弹出

				// 如果当前节点有左子树，且左子树还没被处理过
				if (cur.left != null && h != cur.left && h != cur.right) {
					stack.push(cur.left); // 继续深入左子树
				}
				// 如果左子树处理过了，现在检查右子树是否需要处理
				else if (cur.right != null && h != cur.right) {
					stack.push(cur.right); // 继续深入右子树
				}
				// 左右子树都处理过了，可以处理当前节点
				else {
					ans.add(cur.val); // 加入结果列表
					h = stack.pop(); // 弹出当前节点，并标记为已处理
				}
			}
		}

		// 返回后序遍历结果
		return ans;
	}

	// morris遍历实现后序遍历
	// 处理时机放在能回到自己两次的点，且第二次回到自己的时刻,第二次回到他自己的时候，
	// 不打印他自己，而是逆序打印他左树的右边界, 整个遍历结束后，单独逆序打印整棵树的右边界
	public List<Integer> postorderTraversal(TreeNode root) {
		if (root == null) {
			return new ArrayList<>();
		}
		List<Integer> ans = new ArrayList<>();
		TreeNode cur = root;
		TreeNode mostRight;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
					// 第二次来到自己的时候，收集自己的左树的右边界
					collect(cur.left, ans);
				}
			}
			cur = cur.right;
		}
		collect(root, ans);
		return ans;
	}

	private void collect(TreeNode root, List<Integer> ans) {
		TreeNode node = reverse(root);
		TreeNode c = node;
		while (c != null) {
			ans.add(c.val);
			c = c.right;
		}
		reverse(node);
	}

	private TreeNode reverse(TreeNode node) {
		TreeNode pre = null;
		TreeNode cur = node;
		while (cur != null) {
			TreeNode t = cur.right;
			cur.right = pre;
			pre = cur;
			cur = t;
		}
		return pre;
	}

	public class TreeNode {

		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
}
