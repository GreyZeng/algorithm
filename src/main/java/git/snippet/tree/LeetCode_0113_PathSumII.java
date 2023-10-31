package git.snippet.tree;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/path-sum-ii
public class LeetCode_0113_PathSumII {
  private static int SUM = 0;

  public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    if (root == null) {
      return new ArrayList<>();
    }
    SUM = targetSum;
    List<List<Integer>> ans = new ArrayList<>();
    p(root, 0, new ArrayList<>(), ans);
    return ans;
  }

  public void p(TreeNode root, int preSum, List<Integer> preList, List<List<Integer>> ans) {
    if (root.left == null && root.right == null) {
      // leaf note
      if (preSum + root.val == SUM) {
        preList.add(root.val);
        ans.add(copy(preList));
        // 清理现场
        preList.remove(preList.size() - 1);
      }
      return;
    }
    preList.add(root.val);
    preSum += root.val;
    if (root.left != null) {
      p(root.left, preSum, preList, ans);
    }
    if (root.right != null) {
      p(root.right, preSum, preList, ans);
    }
    preList.remove(preList.size() - 1);
  }

  public List<Integer> copy(List<Integer> list) {
    List<Integer> copy = new ArrayList<>();
    for (Integer i : list) {
      copy.add(i);
    }
    return copy;
  }

  public class TreeNode {

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
}
