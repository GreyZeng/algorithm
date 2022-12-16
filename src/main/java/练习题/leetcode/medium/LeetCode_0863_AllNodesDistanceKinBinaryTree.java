package 练习题.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// 给定三个参数，二叉树的头节点head，树上某个节点target，正数K。从target开始，可以向上走或者向下走，返回与target的距离是K的所有节点
// 给定三个参数：
// 二叉树的头节点head，树上某个节点target，正数K
// 从target开始，可以向上走或者向下走
// 返回与target的距离是K的所有节点
// https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree/
// tips:
// 宽度优先遍历，一次处理一批
public class LeetCode_0863_AllNodesDistanceKinBinaryTree {

  public static class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int v) {
      val = v;
    }
  }

  // 生成每个节点的父节点的映射表
  // 宽度优先遍历，一次处理一批
  public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
    Map<TreeNode, TreeNode> parents = new HashMap<>();
    parents.put(root, null);
    parents(root, parents);
    Set<TreeNode> visited = new HashSet<>();
    Queue<TreeNode> queue = new LinkedList<>();
    visited.add(target);
    queue.offer(target);
    List<Integer> ans = new ArrayList<>();
    int curLevel = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size > 0) {
        TreeNode cur = queue.poll();
        if (curLevel == k) {
          ans.add(cur.val);
        }
        if (cur.left != null && !visited.contains(cur.left)) {
          queue.offer(cur.left);
          visited.add(cur.left);
        }
        if (cur.right != null && !visited.contains(cur.right)) {
          queue.offer(cur.right);
          visited.add(cur.right);
        }
        if (parents.get(cur) != null && !visited.contains(parents.get(cur))) {
          queue.offer(parents.get(cur));
          visited.add(parents.get(cur));
        }
        size--;
      }
      curLevel++;
      if (curLevel > k) {
        break;
      }
    }
    return ans;
  }

  private static void parents(TreeNode root, Map<TreeNode, TreeNode> parents) {
    if (root == null) {
      return;
    }
    if (root.left != null) {
      parents.put(root.left, root);
      parents(root.left, parents);
    }
    if (root.right != null) {
      parents.put(root.right, root);
      parents(root.right, parents);
    }
  }
}
