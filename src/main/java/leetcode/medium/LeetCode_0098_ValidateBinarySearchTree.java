package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

//Given a binary tree, determine if it is a valid binary search tree (BST).
//
//		Assume a BST is defined as follows:
//
//		The left subtree of a node contains only nodes with keys less than the node's key.
//		The right subtree of a node contains only nodes with keys greater than the node's key.
//		Both the left and right subtrees must also be binary search trees.
//
//
//		Example 1:
//
//		2
//		/ \
//		1   3
//
//		Input: [2,1,3]
//		Output: true
//		Example 2:
//
//		5
//		/ \
//		1   4
//		/ \
//		3   6
//
//		Input: [5,1,4,null,null,3,6]
//		Output: false
//		Explanation: The root node's value is 5 but its right child's value is 4.
// 是否为二叉搜索树
// 1. 中序遍历严格递增（递归或者Morris遍历实现中序遍历）
// 2. 二叉树递归套路

public class LeetCode_0098_ValidateBinarySearchTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public boolean isValidBST(TreeNode head) {
        if (null == head) {
            return true;
        }
        return process(head).isBST;
    }

    public Info process(TreeNode head) {
        if (head == null) {
            return null;
        }

        Info left = process(head.left);
        Info right = process(head.right);
        int max = head.val;
        int min = head.val;
        boolean isBST = true;

        if (left != null) {
            max = Math.max(left.max, max);
            min = Math.min(left.min, min);
            isBST = left.isBST && (head.val > left.max);
        }
        if (right != null) {
            max = Math.max(right.max, max);
            min = Math.min(right.min, min);
            isBST = isBST && right.isBST && (head.val < right.min);
        }
        return new Info(isBST, max, min);
    }

    public class Info {
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    // 中序遍历递增
    public boolean isValidBST3(TreeNode head) {
        if (head == null) {
            return true;
        }
        List<Integer> result = new ArrayList<>();
        in(head, result);
        int start = result.get(0);
        for (int i = 1; i < result.size(); i++) {
            if (result.get(i) <= start) {
                return false;
            }
            start = result.get(i);
        }
        return true;
    }

    public void in(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        in(root.left, list);
        list.add(root.val);
        in(root.right, list);
    }

    // morris遍历
    public boolean isValidBST2(TreeNode head) {
        if (head == null) {
            return true;
        }
        TreeNode cur = head;
        TreeNode mostRight;
        TreeNode pre = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    if (pre != null && pre.val >= cur.val) {
                        return false;
                    }
                    pre = cur;
                    cur = cur.right;
                }
            } else {
                if (pre != null && pre.val >= cur.val) {
                    return false;
                }
                pre = cur;
                cur = cur.right;
            }
        }
        return true;
    }

}
