package git.snippet.leetcode;

import java.util.LinkedList;
import java.util.Queue;

// 求树的最大宽度 注意：不是某层节点个数
// https://leetcode.cn/problems/maximum-width-of-binary-tree/
// 笔记：https://www.cnblogs.com/greyzeng/p/16860946.html
public class LeetCode_0662_MaximumWidthOfBinaryTree {

  public static class TreeNode {
    TreeNode left;
    TreeNode right;
  }

  public int widthOfBinaryTree(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int max = 1;
    Queue<AnnotateNode> queue = new LinkedList<>();
    queue.offer(new AnnotateNode(root, 0, 0));
    int curDepth = 0;
    int left = 0;
    while (!queue.isEmpty()) {
      AnnotateNode node = queue.poll();
      if (node.treeNode != null) {
        // 当作一个满二叉树来对待
        // 所以一个节点的左孩子等于2 * i
        // 一个节点右孩子的位置是：2*i+1
        queue.offer(new AnnotateNode(node.treeNode.left, node.depth + 1, node.pos * 2));
        queue.offer(new AnnotateNode(node.treeNode.right, node.depth + 1, node.pos * 2 + 1));
        if (curDepth != node.depth) {
          // 下一层开始结算上一层的结果
          curDepth = node.depth;
          left = node.pos;
        }
        int right = node.pos;
        max = Math.max(max, right - left + 1);
      }
    }
    return max;
  }

  static class AnnotateNode {
    TreeNode treeNode;
    int depth;
    int pos;

    public AnnotateNode(TreeNode treeNode, int depth, int pos) {
      this.treeNode = treeNode;
      this.depth = depth;
      this.pos = pos;
    }
  }
}
