package 数据结构.二叉树;

// 二叉树是的最大深度问题
// 笔记：https://www.cnblogs.com/greyzeng/p/16963808.html
// https://leetcode.cn/problems/maximum-depth-of-binary-tree/
// ref:https://www.lintcode.com/problem/maximum-depth-of-binary-tree/description
public class LeetCode_0104_MaximumDepthOfBinaryTree {

    /*
     * 注意最小高度比这个复杂，要额外小心判断空
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    // morris遍历可以更优化
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    // by morris
    public static int maxDepth2(TreeNode head) {
        if (head == null) {
            return 0;
        }
        TreeNode cur = head;
        TreeNode mostRight;
        int curLevel = 0;
        int maxHeight = Integer.MIN_VALUE;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                int rightBoardSize = 1;
                while (mostRight.right != null && mostRight.right != cur) {
                    rightBoardSize++;
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) { // 第一次到达
                    curLevel++;
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // 第二次到达
                    if (mostRight.left == null) {
                        maxHeight = Math.max(maxHeight, curLevel);
                    }
                    curLevel -= rightBoardSize;
                    mostRight.right = null;
                }
            } else { // 只有一次到达
                curLevel++;
            }
            cur = cur.right;
        }
        int finalRight = 1;
        cur = head;
        while (cur.right != null) {
            finalRight++;
            cur = cur.right;
        }
        if (cur.left == null) {
            maxHeight = Math.max(maxHeight, finalRight);
        }
        return maxHeight;
    }
}
