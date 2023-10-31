package git.snippet.tree;

import java.util.ArrayList;

// Given the root of a binary tree, determine if it is a valid binary search tree (BST).
//
// A valid BST is defined as follows:
//
// The left  subtree of a node contains only nodes with keys less than the node's key.
// The right subtree of a node contains only nodes with keys greater than the node's key.
// Both the left and right subtrees must also be binary search trees.
// 笔记：https://www.cnblogs.com/greyzeng/p/16703346.html
// 是否为二叉搜索树
// 1. 中序遍历严格递增
// 2. 二叉树递归套路
// 3. Morris遍历
// https://leetcode.com/problems/validate-binary-search-tree/
public class LeetCode_0098_ValidateBinarySearchTree {

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

  // Morris遍历，O(1)空间复杂度
  public boolean isValidBST3(TreeNode root) {
    if (root == null) {
      return true;
    }
    boolean ans = true;
    TreeNode pre = null;
    TreeNode mostRight;
    TreeNode cur = root;
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
          if (pre != null && pre.val >= cur.val) {
            ans = false;
          }
          pre = cur;
          mostRight.right = null;
        }
      } else {
        if (pre != null && pre.val >= cur.val) {
          ans = false;
        }
        pre = cur;
      }
      cur = cur.right;
    }
    return ans;
  }

  public boolean isValidBST2(TreeNode head) {
    if (head == null) {
      return true;
    }
    ArrayList<TreeNode> arr = new ArrayList<>();
    in(head, arr);
    for (int i = 1; i < arr.size(); i++) {
      if (arr.get(i).val <= arr.get(i - 1).val) {
        return false;
      }
    }
    return true;
  }

  public void in(TreeNode head, ArrayList<TreeNode> arr) {
    if (head == null) {
      return;
    }
    in(head.left, arr);
    arr.add(head);
    in(head.right, arr);
  }

  public boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    return p(root).isBST;
  }

  public Info p(TreeNode root) {
    if (root == null) {
      return null;
    }
    int max = root.val;
    int min = root.val;
    boolean isBST = true;
    Info left = p(root.left);
    if (left != null) {
      max = Math.max(left.max, max);
      min = Math.min(left.min, min);
      isBST &= left.isBST;
      isBST &= (root.val > left.max);
    }
    Info right = p(root.right);
    if (right != null) {
      max = Math.max(right.max, max);
      min = Math.min(right.min, min);
      isBST &= right.isBST;
      isBST &= (root.val < right.min);
    }
    return new Info(max, min, isBST);
  }

  public class Info {
    public int max;
    public int min;
    public boolean isBST;

    public Info(int max, int min, boolean isBST) {
      this.max = max;
      this.min = min;
      this.isBST = isBST;
    }
  }
}
