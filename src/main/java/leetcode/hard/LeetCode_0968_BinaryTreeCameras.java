package leetcode.hard;

//给定一棵二叉树的头节点head，如果在某一个节点x上放置相机，那么x的父节点、x的所 有子节点以及x都可以被覆盖。返回如果要把所有数都覆盖，至少需要多少个相机。
//        tips:
//        二叉树递归套路
//        1. x位置有相机
//        2. x没相机，但是被覆盖
//        3. x没相机，也没被覆盖
//        也可以贪心
// https://leetcode-cn.com/problems/binary-tree-cameras/
public class LeetCode_0968_BinaryTreeCameras {
    public static class TreeNode {
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

    public static class Info {
        public int coverNoLight;
        public int coverWithLight;
        public int uncoverNoLight;

        public Info(int c, int u, int un) {
            coverNoLight = c;
            coverWithLight = u;
            uncoverNoLight = un;
        }
    }

    public static int minCameraCover(TreeNode root) {
        return -1;
    }
}
