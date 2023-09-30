package leetcode;

// 笔记：https://www.cnblogs.com/greyzeng/p/16753978.html
// 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径（边）长度中的最大值。
// https://leetcode.cn/problems/diameter-of-binary-tree/
public class LeetCode_0543_DiameterOfBinaryTree {

  public static int diameterOfBinaryTree(TreeNode head) {
    if (head == null) {
      return 0;
    }
    return process(head).max;
  }

  private static Info process(TreeNode head) {
    if (head == null) {
      return new Info(0, 0);
    }
    Info left = process(head.left);
    Info right = process(head.right);
    int max = Math.max(left.maxHeight + right.maxHeight, Math.max(left.max, right.max));
    int maxHeight = Math.max(left.maxHeight, right.maxHeight) + 1;
    return new Info(max, maxHeight);
  }

  public static class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
  }

  public static class Info {
    public int maxHeight; // 从当前节点插到最底部最大高度
    public int max; // 当前树的最大距离

    public Info(int max, int maxHeight) {
      this.max = max;
      this.maxHeight = maxHeight;
    }
  }
}
