package tree;

import java.util.HashMap;

// Constraints:
//1 <= preorder.length <= 3000
//inorder.length == preorder.length
//-3000 <= preorder[i], inorder[i] <= 3000
//preorder and inorder consist of unique values.
//Each value of inorder also appears in preorder.
//preorder is guaranteed to be the preorder traversal of the tree.
//inorder is guaranteed to be the inorder traversal of the tree.
public class LeetCode_0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

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

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}
		return p(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1, map);
	}

	// 给出左右树的范围，构造一棵树，返回头节点
	public TreeNode p(int[] preorder, int[] inorder, int p1, int p2, int i1, int i2, HashMap<Integer, Integer> map) {
		if (p1 == p2) {
			return new TreeNode(preorder[p1]);
		}
		// important！！！
		if (p1 > p2) {
			return null;
		}
		int index = map.get(preorder[p1]);
		TreeNode head = new TreeNode(preorder[p1]);
		head.left = p(preorder, inorder, p1 + 1, p1 + index - i1, i1, index - 1, map);
		head.right = p(preorder, inorder, p1 + index - i1 + 1, p2, index + 1, i2, map);
		return head;
	}

}
