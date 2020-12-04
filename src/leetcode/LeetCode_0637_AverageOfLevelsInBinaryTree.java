/*
Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
Example 1:
Input:
    3
   / \
  9  20
    /  \
   15   7
Output: [3, 14.5, 11]
Explanation:
The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].*/
package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode_0637_AverageOfLevelsInBinaryTree {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	public static List<Double> averageOfLevels(TreeNode root) {
		List<Double> ans = new ArrayList<>();
		if (root == null) {
			return ans;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);

		TreeNode curEnd = root;
		TreeNode nextEnd = null;
		int countOfLevel = 0;
		Double sumOfLevel = 0d;
		while (!queue.isEmpty()) {
			TreeNode c = queue.poll();
			if (c.left != null) {
				queue.offer(c.left);
				nextEnd = c.left;

			}
			if (c.right != null) {
				queue.offer(c.right);
				nextEnd = c.right;

			}
			sumOfLevel += Double.valueOf(c.val);
			countOfLevel++;
			if (c == curEnd) {
				ans.add(sumOfLevel / Double.valueOf(countOfLevel));
				curEnd = nextEnd;
				sumOfLevel = 0d;
				countOfLevel = 0;
			}
		}
		return ans;
	}
}
