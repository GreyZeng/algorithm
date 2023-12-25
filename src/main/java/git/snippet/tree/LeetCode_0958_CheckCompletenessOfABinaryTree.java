package git.snippet.tree;

import java.util.LinkedList;

// 笔记：https://www.cnblogs.com/greyzeng/p/16703346.html
// 什么是完全二叉树：每一层都是满的，或者即便不满，也是从左到右依次变满的
// https://leetcode.com/problems/check-completeness-of-a-binary-tree/
public class LeetCode_0958_CheckCompletenessOfABinaryTree {
    public boolean isCompleteTree(TreeNode root) {
        return process(root).isComplete;
    }

    public Info process(TreeNode head) {
        if (head == null) {
            return new Info(true, true, 0);
        }
        Info left = process(head.left);
        Info right = process(head.right);
        int height = Math.max(left.height, right.height) + 1;
        boolean isFull = left.isFull && right.isFull && (left.height == right.height);
        boolean isComplete = isFull ||
                (left.isFull && right.isFull && (left.height - right.height == 1)) ||
                (left.isFull && right.isComplete && (left.height == right.height)) ||
                (left.isComplete && right.isFull && (left.height - right.height == 1));
        return new Info(isFull, isComplete, height);
    }

    public class Info {
        public boolean isFull; // 是否为满二叉树
        public boolean isComplete; // 是否为完全二叉树
        public int height;

        public Info(boolean isFull, boolean isComplete, int height) {
            this.isFull = isFull;
            this.isComplete = isComplete;
            this.height = height;
        }
    }

    public class TreeNode {
        public TreeNode left;
        public TreeNode right;
    }
}
