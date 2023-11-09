package git.snippet.leetcode;

// 二叉树中，寻找两个节点的最低公共祖先
// https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree
// 笔记：https://www.cnblogs.com/greyzeng/p/16757504.html
public class LeetCode_0236_LowestCommonAncestorOfBinaryTree {
    public static TreeNode lowestCommonAncestor(TreeNode head, TreeNode a, TreeNode b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        // o1和o2都不为null
        return p(head, a, b).ancestor;
    }

    public static Info p(TreeNode head, TreeNode a, TreeNode b) {
        if (head == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = p(head.left, a, b);
        Info rightInfo = p(head.right, a, b);
        boolean findA = leftInfo.findA || rightInfo.findA || head == a;
        boolean findB = leftInfo.findB || rightInfo.findB || head == b;
        if (findA && findB) {
            if (leftInfo.findA && leftInfo.findB) {
                return new Info(true, true, leftInfo.ancestor);
            } else if (rightInfo.findA && rightInfo.findB) {
                return new Info(true, true, rightInfo.ancestor);
            }
            return new Info(true, true, head);
        }
        return new Info(findA, findB, null);
    }

    public static class Info {
        private boolean findA;
        private boolean findB;
        private TreeNode ancestor;
        public Info(boolean findA, boolean findB, TreeNode ancestor) {
            this.findA = findA;
            this.findB = findB;
            this.ancestor = ancestor;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
