package 练习题.leetcode.hard;

// 给定一棵二叉树的头节点head，如果在某一个节点x上放置相机，那么x的父节点、x的所有子节点以及x都可以被覆盖。返回如果要把所有数都覆盖，至少需要多少个相机。
// tips:
// 二叉树递归套路
// 1. x位置有相机
// 2. x没相机，但是被覆盖
// 3. x没相机，也没被覆盖
// 也可以贪心
// https://leetcode-cn.com/problems/binary-tree-cameras/
public class LeetCode_0968_BinaryTreeCameras {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  public enum Status {
    UNCOVER, COVER_WITH_CAMERA, COVER_NO_CAMERA
  }

  public static class Info {
    public Status status;
    public int camera;

    public Info(Status s, int c) {
      status = s;
      camera = c;
    }
  }

  public static int minCameraCover(TreeNode root) {
    Info info = p(root);
    return info.status == Status.UNCOVER ? 1 + info.camera : info.camera;
  }

  public static Info p(TreeNode root) {
    if (root == null) {
      return new Info(Status.COVER_NO_CAMERA, 0);
    }
    Info left = p(root.left);
    Info right = p(root.right);
    // if1
    if (left.status == Status.UNCOVER || right.status == Status.UNCOVER) {
      return new Info(Status.COVER_WITH_CAMERA, left.camera + right.camera + 1);
    }
    // if2
    // if1和if2的顺序不能调换！
    if (left.status == Status.COVER_WITH_CAMERA || right.status == Status.COVER_WITH_CAMERA) {
      return new Info(Status.COVER_NO_CAMERA, left.camera + right.camera);
    }
    return new Info(Status.UNCOVER, left.camera + right.camera);
  }
}
