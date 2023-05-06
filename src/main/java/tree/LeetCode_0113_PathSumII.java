package tree;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/path-sum-ii
public class LeetCode_0113_PathSumII {
    private static boolean isSum = false;
    private static int sum = 0;
    private static List<List<Integer>> ans = new ArrayList<>();
    // FIXME
    // TODO
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return ans;
        }
        sum = targetSum;
        return ans;
    }
    public void p(TreeNode root, int preSum, List<Integer> preList) {
        if (root.left == null && root.right == null) {
            // leaf note
            if (preSum + root.val == sum) {
                preList.add(root.val);
                ans.add(preList);
                return;
            } 
        }
    }
    public class TreeNode {

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
}
