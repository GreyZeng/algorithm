package leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

// 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径
//        至少包含一个 节点，且不一定经过根节点。
//        路径和 是路径中各节点值的总和。
//        给你一个二叉树的根节点 root ，返回其 最大路径和。
//        进阶：
//        如果返回最大路径和上的所有节点，该怎么做？
//        LeetCode题目 : https://leetcode.com/problems/binary-tree-maximum-path-sum/
// 笔记：https://www.cnblogs.com/greyzeng/p/16960465.html
// ref LintCode : https://www.lintcode.com/problem/binary-tree-maximum-path-sum/description
public class LeetCode_0124_BinaryTreeMaximumPathSum {

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int v) {
      val = v;
    }
  }

  public static int maxPathSum(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return process(root).maxPathSum;
  }

  // 任何一棵树，必须汇报上来的信息
  public static class Info {
    public int maxPathSum;
    public int maxPathSumFromHead;

    public Info(int path, int head) {
      maxPathSum = path;
      maxPathSumFromHead = head;
    }
  }

  public static Info process(TreeNode head) {
    if (null == head) {
      return new Info(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }
    Info leftInfo = process(head.left);
    Info rightInfo = process(head.right);
    int maxPathSumFromHead =
        head.val + Math.max(Math.max(leftInfo.maxPathSumFromHead, rightInfo.maxPathSumFromHead), 0);
    int maxPathSum =
        Math.max(
            Math.max(leftInfo.maxPathSum, rightInfo.maxPathSum),
            head.val
                + Math.max(0, leftInfo.maxPathSumFromHead)
                + Math.max(0, rightInfo.maxPathSumFromHead));
    return new Info(maxPathSum, maxPathSumFromHead);
  }

  // 如果要返回路径的做法
  // TODO
  public static List<TreeNode> getMaxSumPath(TreeNode head) {
    List<TreeNode> ans = new ArrayList<>();
    if (head != null) {
      Data data = f(head);
      HashMap<TreeNode, TreeNode> fmap = new HashMap<>();
      fmap.put(head, head);
      fatherMap(head, fmap);
      fillPath(fmap, data.from, data.to, ans);
    }
    return ans;
  }

  public static class Data {
    public int maxAllSum;
    public TreeNode from;
    public TreeNode to;
    public int maxHeadSum;
    public TreeNode end;

    public Data(int a, TreeNode b, TreeNode c, int d, TreeNode e) {
      maxAllSum = a;
      from = b;
      to = c;
      maxHeadSum = d;
      end = e;
    }
  }

  public static Data f(TreeNode x) {
    if (x == null) {
      return null;
    }
    Data l = f(x.left);
    Data r = f(x.right);
    int maxHeadSum = x.val;
    TreeNode end = x;
    if (l != null && l.maxHeadSum > 0 && (r == null || l.maxHeadSum > r.maxHeadSum)) {
      maxHeadSum += l.maxHeadSum;
      end = l.end;
    }
    if (r != null && r.maxHeadSum > 0 && (l == null || r.maxHeadSum > l.maxHeadSum)) {
      maxHeadSum += r.maxHeadSum;
      end = r.end;
    }
    int maxAllSum = Integer.MIN_VALUE;
    TreeNode from = null;
    TreeNode to = null;
    if (l != null) {
      maxAllSum = l.maxAllSum;
      from = l.from;
      to = l.to;
    }
    if (r != null && r.maxAllSum > maxAllSum) {
      maxAllSum = r.maxAllSum;
      from = r.from;
      to = r.to;
    }
    int p3 =
        x.val
            + (l != null && l.maxHeadSum > 0 ? l.maxHeadSum : 0)
            + (r != null && r.maxHeadSum > 0 ? r.maxHeadSum : 0);
    if (p3 > maxAllSum) {
      maxAllSum = p3;
      from = (l != null && l.maxHeadSum > 0) ? l.end : x;
      to = (r != null && r.maxHeadSum > 0) ? r.end : x;
    }
    return new Data(maxAllSum, from, to, maxHeadSum, end);
  }

  public static void fatherMap(TreeNode h, HashMap<TreeNode, TreeNode> map) {
    if (h.left == null && h.right == null) {
      return;
    }
    if (h.left != null) {
      map.put(h.left, h);
      fatherMap(h.left, map);
    }
    if (h.right != null) {
      map.put(h.right, h);
      fatherMap(h.right, map);
    }
  }

  public static void fillPath(
      HashMap<TreeNode, TreeNode> fmap, TreeNode a, TreeNode b, List<TreeNode> ans) {
    if (a == b) {
      ans.add(a);
    } else {
      HashSet<TreeNode> ap = new HashSet<>();
      TreeNode cur = a;
      while (cur != fmap.get(cur)) {
        ap.add(cur);
        cur = fmap.get(cur);
      }
      ap.add(cur);
      cur = b;
      TreeNode lca = null;
      while (lca == null) {
        if (ap.contains(cur)) {
          lca = cur;
        } else {
          cur = fmap.get(cur);
        }
      }
      while (a != lca) {
        ans.add(a);
        a = fmap.get(a);
      }
      ans.add(lca);
      ArrayList<TreeNode> right = new ArrayList<>();
      while (b != lca) {
        right.add(b);
        b = fmap.get(b);
      }
      for (int i = right.size() - 1; i >= 0; i--) {
        ans.add(right.get(i));
      }
    }
  }

  public static void main(String[] args) {
    TreeNode head = new TreeNode(4);
    head.left = new TreeNode(-7);
    head.right = new TreeNode(-5);
    head.left.left = new TreeNode(9);
    head.left.right = new TreeNode(9);
    head.right.left = new TreeNode(4);
    head.right.right = new TreeNode(3);

    List<TreeNode> maxPath = getMaxSumPath(head);

    for (TreeNode n : maxPath) {
      System.out.println(n.val);
    }
  }
}
