package 练习题.leetcode.medium;

import java.util.ArrayList;

// 笔记：https://www.cnblogs.com/greyzeng/p/16703346.html
// 是否为二叉搜索树
// 1. 中序遍历严格递增
// 2. 二叉树递归套路
// 3. Morris遍历
// https://leetcode-cn.com/problems/validate-binary-search-tree/
public class LeetCode_0098_ValidateBinarySearchTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int data) {
            this.val = data;
        }
    }

    // Morris遍历，O(1)空间复杂度
    public static boolean isValidBST3(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean ans = true;
        TreeNode pre = null;
        TreeNode mostRight;
        TreeNode cur = root;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    if (pre != null && pre.val >= cur.val) {
                        ans = false;
                    }
                    pre = cur;
                    mostRight.right = null;
                }
            } else {
                if (pre != null && pre.val >= cur.val) {
                    ans = false;
                }
                pre = cur;
            }
            cur = cur.right;
        }
        return ans;
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
            return new Info(Math.max(head.val, right.max), Math.min(head.val, right.min),
                    right.isBST && head.val < right.min);
        }
        if (right == null) {
            // left != null
            return new Info(Math.max(head.val, left.max), Math.min(head.val, left.min),
                    left.isBST && head.val > left.max);
        }
        return new Info(Math.max(head.val, Math.max(left.max, right.max)),
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
