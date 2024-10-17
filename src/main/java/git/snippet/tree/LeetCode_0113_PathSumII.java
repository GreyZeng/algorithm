package git.snippet.tree;

import java.util.ArrayList;
import java.util.List;

// 笔记：https://www.cnblogs.com/greyzeng/articles/15700243.html
// https://leetcode.com/problems/path-sum-ii
public class LeetCode_0113_PathSumII {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        process(root, 0, path, targetSum, result);
        return result;
    }

    public void process(TreeNode node, int preSum, List<Integer> path, int targetSum, List<List<Integer>> result) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            // 叶子节点
            if (preSum + node.val == targetSum) {
                path.add(node.val);
                result.add(path);
            }
            return;
        }
        List<Integer> copy1 = new ArrayList<>(path);
        List<Integer> copy2 = new ArrayList<>(path);
        copy1.add(node.val);
        copy2.add(node.val);
        process(node.left, preSum + node.val, copy1, targetSum, result);
        process(node.right, preSum + node.val, copy2, targetSum, result);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
