package leetcode.easy;

//给定一个二叉树，找出其最小深度。
//
//        最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
//
//        说明：叶子节点是指没有子节点的节点。
// 二叉树递归套路
// Morris遍历
public class LeetCode_0111_MinimumDepthOfBinaryTree {

    public class TreeNode {
        TreeNode left;
        TreeNode right;
    }

    // 二叉树的递归套路
    public static class Info {
        public int minHeight;

        public Info(int h) {
            minHeight = h;
        }
    }

    public static int minDepth1(TreeNode head) {
        return p(head).minHeight;
    }

    public static Info p(TreeNode head) {
        if (head == null) {
            return new Info(0);
        }
        if (head.left == null) {
            return new Info(p(head.right).minHeight + 1);
        }
        if (head.right == null) {
            return new Info(p(head.left).minHeight + 1);
        }
        Info left = p(head.left);
        Info right = p(head.right);
        return new Info(Math.min(left.minHeight, right.minHeight) + 1);
    }

    // morris遍历方式
    public static int minDepth(TreeNode head) {
        if (head == null) {
            return 0;
        }
        TreeNode cur = head;
        TreeNode mostRight;
        int curHeight = 0;
        int min = Integer.MAX_VALUE;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                int dulplicate = 1;
                while (mostRight.right != null && mostRight.right != cur) {
                    dulplicate++;
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    curHeight++;
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    if (mostRight.left == null) {
                        min = Math.min(min, curHeight);
                    }
                    curHeight -= dulplicate;
                    mostRight.right = null;
                }
            } else {
                curHeight++;
            }
            cur = cur.right;
        }
        int rightMostHeight = 1;
        TreeNode c = head;
        while (c.right != null) {
            rightMostHeight++;
            c = c.right;
        }
        if (c.left == null) {
            min = Math.min(min, rightMostHeight);
        }
        return min;
    }

//    public static int minDepth2(TreeNode head) {
//        if (head == null) {
//            return 0;
//        }
//        return p(head).minH;
//    }
//
//    public static Info p(TreeNode head) {
//        if (head == null) {
//            return new Info(0);
//        }
//        if (head.left == null && head.right == null) {
//            return new Info(1);
//        }
//        if (head.left == null) {
//            return new Info(p(head.right).minH + 1);
//        }
//        if (head.right == null) {
//            return new Info(p(head.left).minH + 1);
//        }
//        return new Info(Math.min(p(head.left).minH, p(head.right).minH) + 1);
//    }
//
//    public static class Info {
//        public int minH;
//
//        public Info(int minH) {
//            this.minH = minH;
//        }
//    }
}
