// Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from
// left to right, level by level from leaf to root).
//
// For example:
// Given binary tree [3,9,20,null,null,15,7],
// 3
// / \
// 9 20
// / \
// 15 7
// return its bottom-up level order traversal as:
// [
// [15,7],
// [9,20],
// [3]
// ]
package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
 

// 笔记：https://www.cnblogs.com/greyzeng/p/16356829.html
// https://leetcode.com/problems/binary-tree-level-order-traversal-ii
// 按层遍历进阶
public class LeetCode_0107_BinaryTreeLevelOrderTraversalII {

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

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // TODO
    	return null;
    }
}
