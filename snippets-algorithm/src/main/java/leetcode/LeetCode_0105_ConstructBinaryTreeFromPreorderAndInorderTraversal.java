package leetcode;

import java.util.HashMap;

//Given preorder and inorder traversal of a tree, construct the binary tree.
//
//Note:
//You may assume that duplicates do not exist in the tree.
//
//For example, given
//
//preorder = [3,9,20,15,7]
//inorder = [9,3,15,20,7]
//Return the following binary tree:
//
//    3
//   / \
//  9  20
//    /  \
//   15   7
public class LeetCode_0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	public static TreeNode buildTree(int[] preorder, int[] inorder) {
		HashMap<Integer, Integer> m = new HashMap<>();
		for (int i = 0; i < inorder.length; i++) {
			m.put(inorder[i], i);
		}
		return process(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, m);
	}

	public static TreeNode process(int[] preorder, int s1, int e1, int[] inorder, int s2, int e2,
			HashMap<Integer, Integer> m) {
		if (s1 > e1) {
			return null;
		}
		// 构造头
		TreeNode head = new TreeNode(preorder[s1]);
//	      3
		// \
		// 20
//	         \
		// 7
		if (s1 == e1) {
			return head;
		}
		int index = m.get(preorder[s1]);

		// 构造左树
		head.left = process(preorder, s1 + 1, s1 + index - s2, inorder, s2, index - 1, m);
		// 构造右树
		head.right = process(preorder, s1 + index - s2 + 1, e1, inorder, index + 1, e2, m);
		return head;
	}

}
