package leetcode;

public class LeetCode_0108_ConvertSortedArrayToBinarySearchTree {

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public static TreeNode sortedArrayToBST(int[] nums) {
    return p(nums, 0, nums.length - 1);
  }

  private static TreeNode p(int[] nums, int i, int j) {
    if (i > j) {
      return null;
    }
    if (i == j) {
      return new TreeNode(nums[i]);
    }
    int M = (i + j) >> 1;
    TreeNode h = new TreeNode(nums[M]);
    h.left = p(nums, i, M - 1);
    h.right = p(nums, M + 1, j);
    return h;
  }
}
