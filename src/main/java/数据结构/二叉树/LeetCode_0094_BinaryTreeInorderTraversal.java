package 数据结构.二叉树;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
// 二叉树的中序遍历
// 笔记：https://www.cnblogs.com/greyzeng/articles/15941957.html
public class LeetCode_0094_BinaryTreeInorderTraversal {

  public static class TreeNode {
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

  // 递归方式
  public List<Integer> inorderTraversal1(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    List<Integer> ans = new ArrayList<>();
    in(root, ans);
    return ans;
  }

  private void in(TreeNode root, List<Integer> ans) {
    if (root == null) {
      return;
    }
    in(root.left, ans);
    ans.add(root.val);
    in(root.right, ans);
  }

  // 【非递归】中序遍历
  // 第一步，整条左边界入栈。
  // 第二步，弹出就收集答案。
  // 第三步，来到右树上执行同第一步的操作。
  // 第四步，直到栈为空。
  public List<Integer> inorderTraversal2(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    List<Integer> ans = new ArrayList<>();
    Deque<TreeNode> stack = new ArrayDeque<>();
    TreeNode cur = root;
    while (!stack.isEmpty() || cur != null) {
      if (cur != null) {
        // 整条左边界入栈
        stack.push(cur);
        cur = cur.left;
      } else {
        TreeNode node = stack.pop();
        // 弹出收集答案
        ans.add(node.val);
        // 来到右树上做同样的操作
        cur = node.right;
      }
    }
    return ans;
  }


  // morris遍历方式
  // 第二次来到自己的时候收集
  public static List<Integer> inorderTraversal(TreeNode root) {
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
          // mostRight.right = cur;
          mostRight.right = null;
        }
      }
      ans.add(cur.val);
      cur = cur.right;
    }
    return ans;
  }
}
