package leetcode.medium;

import java.util.LinkedList;

// 笔记：https://www.cnblogs.com/greyzeng/p/16703346.html
// 什么是完全二叉树：每一层都是满的，或者即便不满，也是从左到右依次变满的
// https://leetcode-cn.com/problems/check-completeness-of-a-binary-tree/
public class LeetCode_0958_CheckCompletenessOfABinaryTree {
    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int data) {
            this.value = data;
        }
    }

    // 解法一
    // 1. 某个节点没有左孩子但是有右孩子，一定不是完全二叉树
    // 2. 第一次出现左右孩子不双全的时候，接下来的节点都是叶子节点
    public static boolean isCompleteTree(TreeNode head) {
        if (head == null) {
            return true;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        TreeNode l = null;
        TreeNode r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if (
                // 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
                    (leaf && (l != null || r != null)) || (l == null && r != null)

            ) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

    // 二叉树的递归套路
    public static boolean isCompleteTree2(TreeNode head) {
        if (null == head) {
            return true;
        }
        return p(head).isCBT;
    }

    private static Info p(TreeNode head) {
        if (head == null) {
            return new Info(true, true, 0);
        }
        Info left = p(head.left);
        Info right = p(head.right);
        int height = Math.max(left.height, right.height) + 1;
        boolean isFull = left.isFull && right.isFull && (left.height == right.height);
        if (isFull) {
            // 是满二叉树，肯定是完全二叉树
            return new Info(true, true, height);
        }
        // 不是满二叉树
        if (left.height == right.height) {
            boolean isCBT = left.isFull && right.isCBT;
            return new Info(false, isCBT, height);
        }
        if (left.height - right.height == 1) {
            boolean isCBT = left.isCBT && right.isFull;
            return new Info(false, isCBT, height);
        }
        return new Info(false, false, height);
    }

    public static class Info {
        private boolean isFull;
        private boolean isCBT;
        private int height;

        public Info(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
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
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (isCompleteTree(head) != isCompleteTree2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
