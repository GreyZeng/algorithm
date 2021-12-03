package leetcode;

public class LeetCode_0958_CheckCompletenessOfABinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }


    public boolean isCompleteTree(TreeNode root) {
        if (null == root) {
            return true;
        }
        return process(root).isCBT;
    }

    public Info process(TreeNode root) {
        if (root == null) {
            return new Info(true, true, 0);
        }
        Info left = process(root.left);
        Info right = process(root.right);
        int height = Math.max(left.height, right.height) + 1;
        boolean isCBT = (left.isFull && right.isFull
                && ((left.height - right.height) == 1 || left.height == right.height))
                || (left.isCBT && right.isFull && (left.height - right.height) == 1)
                || (left.isFull && right.isCBT && (left.height == right.height));
        boolean isFull = left.isFull && right.isFull && (right.height == left.height);
        return new Info(isCBT, isFull, height);
    }

    public static class Info {
        public boolean isCBT; // 是否完全二叉树
        public boolean isFull; // 是否满二叉树
        public int height; // 高度

        public Info(boolean isCBT, boolean isFull, int height) {
            this.isCBT = isCBT;
            this.isFull = isFull;
            this.height = height;
        }
    }


}
