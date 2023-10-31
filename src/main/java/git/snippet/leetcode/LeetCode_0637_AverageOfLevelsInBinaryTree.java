/*
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of
 * an array. Example 1: Input: 3 / \ 9 20 / \ 15 7 Output: [3, 14.5, 11] Explanation: The average
 * value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5,
 * 11].
 */
package git.snippet.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 笔记：https://www.cnblogs.com/greyzeng/p/16356829.html
// https://leetcode.cn/problems/average-of-levels-in-binary-tree/description/
// 按层遍历，然后求每层的平均值
public class LeetCode_0637_AverageOfLevelsInBinaryTree {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
  }

  public static List<Double> averageOfLevels(TreeNode root) {
    if (null == root) {
      return new ArrayList<>();
    }
    List<Double> result = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    TreeNode curEnd = root;
    TreeNode nextEnd = null;
    int numOfNodes = 0;
    Double sumOfNodesVal = 0d;
    queue.offer(root);
    while (!queue.isEmpty()) {
      TreeNode poll = queue.poll();
      if (null != poll.left) {
        queue.offer(poll.left);
        nextEnd = poll.left;
      }
      if (null != poll.right) {
        queue.offer(poll.right);
        nextEnd = poll.right;
      }
      numOfNodes++;
      sumOfNodesVal += poll.val;
      if (poll == curEnd) {
        result.add(sumOfNodesVal / numOfNodes);
        sumOfNodesVal = 0d;
        numOfNodes = 0;
        curEnd = nextEnd;
      }
    }

    return result;
  }
}
