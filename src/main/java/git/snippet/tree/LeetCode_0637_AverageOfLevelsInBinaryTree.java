package git.snippet.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 笔记：https://www.cnblogs.com/greyzeng/p/16356829.html
// https://leetcode.com/problems/average-of-levels-in-binary-tree/description/
// 按层遍历，然后求每层的平均值
public class LeetCode_0637_AverageOfLevelsInBinaryTree {
    public List<Double> averageOfLevels(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode curEnd = root;
        TreeNode nextEnd = null;
        int numOfNodes = 0; // 记录每一层的节点个数
        double sumOfNodesVal = 0d; // 记录每一层的节点平均值（累计值除以个数）
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (null != cur.left) {
                queue.offer(cur.left);
                nextEnd = cur.left;
            }
            if (null != cur.right) {
                queue.offer(cur.right);
                nextEnd = cur.right;
            }
            numOfNodes++;
            sumOfNodesVal += cur.val;
            if (cur == curEnd) {
                result.add(sumOfNodesVal / numOfNodes);
                sumOfNodesVal = 0d;
                numOfNodes = 0;
                curEnd = nextEnd;
            }
        }
        return result;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
