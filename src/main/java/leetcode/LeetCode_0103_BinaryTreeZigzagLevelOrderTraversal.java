package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeetCode_0103_BinaryTreeZigzagLevelOrderTraversal {

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
  }

  public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    if (null == root) {
      return new ArrayList<>();
    }
    LinkedList<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    int size = 1;
    boolean p = true;
    int c = 0;
    TreeNode cur = null;
    List<List<Integer>> ans = new ArrayList<>();
    while (!queue.isEmpty()) {
      size = queue.size();
      List<Integer> list = new ArrayList<>();
      for (c = 0; c < size; c++) {
        cur = p ? queue.pollFirst() : queue.pollLast();
        list.add(cur.val);
        if (p) {
          if (cur.left != null) {
            queue.addLast(cur.left);
          }
          if (cur.right != null) {
            queue.addLast(cur.right);
          }
        } else {
          if (cur.right != null) {
            queue.addFirst(cur.right);
          }
          if (cur.left != null) {
            queue.addFirst(cur.left);
          }
        }
      }
      ans.add(list);
      p = !p;
    }
    return ans;
  }
}
