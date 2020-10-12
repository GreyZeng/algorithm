package leetcode;

import java.util.ArrayList;
import java.util.List;


// leetcode加锁，可以看lintcode对应的题目：https://www.lintcode.com/problem/inorder-successor-in-bst/description
public class LeetCode_0285_InorderSuccessorInBST {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    // 递归方式
    // 中序遍历的后继节点
    public TreeNode inorderSuccessor(TreeNode head, TreeNode p) {
        if (head == null) {
            return null;
        }
        List<TreeNode> dp = new ArrayList<>();
        in(head, dp);
        boolean tag = false;
        for (TreeNode t : dp) {
            if (t == p) {
                tag = true;
                continue;
            }
            if (tag) {
                return t;
            }
        }
        return null;
    }

    public static void in(TreeNode head, List<TreeNode> dp) {
        if (head == null) {
            return;
        }
        in(head.left, dp);
        dp.add(head);
        in(head.right, dp);
    }

// TODO
// 用morris方式来做
}
