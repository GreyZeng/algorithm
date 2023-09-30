package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 笔记：https://www.cnblogs.com/greyzeng/p/16356829.html
// https://leetcode.com/problems/binary-tree-level-order-traversal-ii
// 按层遍历进阶：从底部开始遍历
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
public class LeetCode_0107_BinaryTreeLevelOrderTraversalII {

  public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

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
    List<List<Integer>> ans = new ArrayList<>();
    if (root == null) {
      return ans;
    }
    TreeNode cur = root;
    TreeNode curEnd = root;
    TreeNode nextEnd = null;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(cur);
    List<Integer> items = new LinkedList<>();
    while (!queue.isEmpty()) {
      cur = queue.poll();
      if (cur.left != null) {
        queue.offer(cur.left);
        nextEnd = cur.left;
      }
      if (cur.right != null) {
        queue.offer(cur.right);
        nextEnd = cur.right;
      }
      items.add(0, cur.val);
      if (curEnd == cur) {
        curEnd = nextEnd;
        // 如果是顺序按层遍历，这段代码只需要改成
        // ans.add(items);
        ans.add(0, items);
        items = new LinkedList<>();
      }
    }
    return ans;
  }
}
