package leetcode.medium;

public class LeetCode_0236_LowestCommonAncestorOfBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static TreeNode lowestCommonAncestor(TreeNode head, TreeNode o1, TreeNode o2) {
        if (o1 == null) {
            return o2;
        }
        if (o2 == null) {
            return o1;
        }
        // o1和o2都不为null
        return p(head, o1, o2).ancestor;
    }

    public static Info p(TreeNode head, TreeNode o1, TreeNode o2) {
        if (head == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = p(head.left, o1, o2);
        Info rightInfo = p(head.right, o1, o2);
        boolean findA = leftInfo.findA || rightInfo.findA || head == o1;
        boolean findB = leftInfo.findB || rightInfo.findB || head == o2;
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
        public Info(boolean findA, boolean findB, TreeNode ancestor) {
            this.findA = findA;
            this.findB = findB;
            this.ancestor = ancestor;
        }

        private boolean findA;
        private boolean findB;
        private TreeNode ancestor;

    }
}
