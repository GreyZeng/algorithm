package git.snippet.tree;

import java.util.HashMap;

// 给定一个二叉树的根节点 root，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
// 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
// Leetcode题目 : https://leetcode.com/problems/path-sum-iii/
// 笔记：https://www.cnblogs.com/greyzeng/articles/15700243.html
// tips: 先序遍历
// 类似数组三连的第二问
public class LeetCode_0437_PathSumIII {

    public int pathSum(TreeNode root, int targetSum) {
        HashMap<Long, Integer> preSumMap = new HashMap<>();
        // 累加和为0的情况，默认就有一种了（空树）
        preSumMap.put(0L, 1);
        return process(root, targetSum, 0, preSumMap);
    }

    // 返回方法数
    // 从 node 到结尾，前面累计的结果是 preSum，前面组成的路径中，每个路径之和的结果有多少种，存在 preSumMap
    // preSum(i,j) 表示 前面路径的累加和为 i 的路径有 j 种
    public int process(TreeNode node, int targetSum, long preSum, HashMap<Long, Integer> preSumMap) {
        if (node == null) {
            return 0;
        }
        long all = preSum + node.val;
        int ans = 0;
        if (preSumMap.containsKey(all - targetSum)) {
            // 说明之前有路径可以得到
            ans = preSumMap.get(all - targetSum);
        }
        // 登记当前结果到preSumMap中
        if (!preSumMap.containsKey(all)) {
            preSumMap.put(all, 1);
        } else {
            preSumMap.put(all, preSumMap.get(all) + 1);
        }
        ans += process(node.left, targetSum, all, preSumMap);
        ans += process(node.right, targetSum, all, preSumMap);
        // 清理现场
        if (preSumMap.get(all) == 1) {
            preSumMap.remove(all);
        } else {
            preSumMap.put(all, preSumMap.get(all) - 1);
        }
        return ans;
    }

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;
    }
}
