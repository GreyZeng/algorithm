package 练习题.leetcode.easy;

// 判断一棵树是否是平衡二叉树
// 平衡二叉树要么是一棵空树.
// 要么保证左右子树的高度之差不大于 1.
// 子树也必须是一颗平衡二叉树
// https://leetcode.cn/problems/balanced-binary-tree/
// 笔记：https://www.cnblogs.com/greyzeng/p/16703346.html
public class LeetCode_0110_BalancedBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public static boolean isBalanced(TreeNode head) {
        if (null == head) {
            return true;
        }
        return p(head).isBalanced;
    }

    private static Info p(TreeNode head) {
        if (head == null) {
            return new Info(0, true);
        }
        Info left = p(head.left);
        Info right = p(head.right);
        int height = Math.max(left.height, right.height) + 1;
        boolean isBalanced =
                (Math.abs(left.height - right.height) <= 1) && left.isBalanced && right.isBalanced;
        return new Info(height, isBalanced);
    }

    public static class Info {
        private int height;
        private boolean isBalanced;

        public Info(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }

    public static boolean isBalanced1(TreeNode head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(TreeNode head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
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
        TreeNode head = new TreeNode();
        head.val = (int) (Math.random() * maxValue);
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(head) != isBalanced(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
