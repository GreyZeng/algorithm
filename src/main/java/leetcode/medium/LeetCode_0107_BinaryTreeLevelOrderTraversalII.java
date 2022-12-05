// Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from
// left to right, level by level from leaf to root).
//
// For example:
// Given binary tree [3,9,20,null,null,15,7],
//    3
//   / \
//  9  20
//    /  \
//   15   7
// return its bottom-up level order traversal as:
// [
//  [15,7],
//  [9,20],
//  [3]
// ]
package leetcode.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 笔记：https://www.cnblogs.com/greyzeng/p/16356829.html
// 按层遍历进阶
public class LeetCode_0107_BinaryTreeLevelOrderTraversalII {

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
  }

  public List<List<Integer>> levelOrderBottom(TreeNode root) {
    if (null == root) {
      return new ArrayList<>();
    }
    Deque<List<Integer>> stack = new ArrayDeque<>();
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> levelRecords = new ArrayList<>();
    TreeNode curEnd = root;
    TreeNode nextEnd = null;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      TreeNode poll = queue.poll();
      levelRecords.add(poll.val);
      if (null != poll.left) {
        queue.offer(poll.left);
        nextEnd = poll.left;
      }
      if (null != poll.right) {
        queue.offer(poll.right);
        nextEnd = poll.right;
      }
      if (poll == curEnd) {
        curEnd = nextEnd;
        stack.push(levelRecords);
        levelRecords = new ArrayList<>();
      }
    }
    while (!stack.isEmpty()) {
      result.add(stack.poll());
    }
    return result;
  }
}
