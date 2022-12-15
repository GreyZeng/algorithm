package 练习题.leetcode.medium;

import java.util.ArrayList;

//Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
//
//
//
//        Example 1:
//
//        Input: root = [3,1,4,null,2], k = 1
//        3
//        / \
//        1   4
//        \
//        2
//        Output: 1
//        Example 2:
//
//        Input: root = [5,3,6,2,4,null,null,1], k = 3
//        5
//        / \
//        3   6
//        / \
//        2   4
//        /
//        1
//        Output: 3
//        Follow up:
//        What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
//
//
//
//        Constraints:
//
//        The number of elements of the BST is between 1 to 10^4.
//        You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
public class LeetCode_0230_KthSmallestElementInBST {

    public static class TreeNode {
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

    // 中序遍历第K个节点
    public static int kthSmallest(TreeNode root, int k) {
        ArrayList<TreeNode> t = new ArrayList<>();
        p(root, t);
        return t.get(k - 1).val;
    }

    public static void p(TreeNode r, ArrayList<TreeNode> res) {
        if (r == null) {
            return;
        }
        if (r.left == null && r.right == null) {
            res.add(r);
            return;
        }
        p(r.left, res);
        res.add(r);
        p(r.right, res);
    }

    // TODO morris遍历解

}
