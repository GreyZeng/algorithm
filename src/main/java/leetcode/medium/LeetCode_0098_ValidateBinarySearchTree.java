package leetcode.medium;

import java.util.ArrayList;

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
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int data) {
            this.val = data;
        }
    }

    public static boolean isValidBST2(TreeNode head) {
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

    public static void in(TreeNode head, ArrayList<TreeNode> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static boolean isValidBST(TreeNode head) {
        if (null == head) {
            return true;
        }
        return p(head).isBST;
    }

    public static Info p(TreeNode head) {
        if (head == null) {
            return null;
        }
        Info left = p(head.left);
        Info right = p(head.right);
        if (left == null && right == null) {
            return new Info(head.val, head.val, true);
        }

        if (left == null) {
            // right != null
            return new Info(
                    Math.max(head.val,right.max), 
                    Math.min(head.val,right.min), 
                    right.isBST && head.val < right.min);
        }
        if (right == null) {
            // left != null
            return new Info(
                    Math.max(head.val,left.max), 
                    Math.min(head.val,left.min), 
                    left.isBST && head.val > left.max);
        }
        return new Info(
                Math.max(head.val, Math.max(left.max, right.max)), 
                Math.min(head.val, Math.min(left.min, right.min)), 
                left.isBST && right.isBST && head.val < right.min && head.val > left.max);
        
    }

    public static class Info {
        public Info(int max, int min, boolean isBST) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
        }

        private int max;
        private int min;
        private boolean isBST;

    }



    // for test
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (isValidBST2(head) != isValidBST(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
