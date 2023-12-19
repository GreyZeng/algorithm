package git.snippet.tree;
// 笔记：https://www.cnblogs.com/greyzeng/articles/16971977.html
// 判断两颗树是否结构相同
// Two binary trees are considered the same if they are structurally identical, and the nodes have
// the same value.
// https://leetcode.com/problems/same-tree
public class LeetCode_0100_SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            // 两个树同时为空才表示 same tree
            return q == null && p == null;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
