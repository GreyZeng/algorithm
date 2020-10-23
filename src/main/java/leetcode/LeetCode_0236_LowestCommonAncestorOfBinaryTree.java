package leetcode;

public class LeetCode_0236_LowestCommonAncestorOfBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return p(root, p, q).ans;
    }

    public static A p(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return new A(null, false, false);
        }
        A left = p(root.left, p, q);
        A right = p(root.right, p, q);
        boolean findP = left.fP || right.fP || root == p;
        boolean findQ = left.fQ || right.fQ || root == q;
        TreeNode ans; // P和Q的最初交汇点
        if (left.fP && left.fQ) {
            ans = left.ans;
        } else if (right.fP && right.fQ) {
            ans = right.ans;
        } else {
            ans = root;
        }
        return new A(ans, findP, findQ);
    }

    public static class A {
        public TreeNode ans;
        public boolean fP; // 能否到达P
        public boolean fQ; // 能否到达Q

        public A(TreeNode ans, boolean fP, boolean fQ) {
            this.ans = ans;
            this.fP = fP;
            this.fQ = fQ;
        }
    }
}
