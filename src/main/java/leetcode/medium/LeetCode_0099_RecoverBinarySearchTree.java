/*You are given the root of a binary search tree (BST), where exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.

        Follow up: A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?*/
package leetcode.medium;

import java.util.Stack;
// https://leetcode.cn/problems/recover-binary-search-tree/
public class LeetCode_0099_RecoverBinarySearchTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    // 中序遍历 第一次降序的头节点，第二次降序的尾节点
    // 这里只需要交换两个节点的val就可以AC，实际上更好的处理方式应该是节点真实的进行交换
    public static void recoverTree(TreeNode root) {
        TreeNode[] twoErrNodes = getTwoErrNodes(root);
        twoErrNodes[0].val = twoErrNodes[0].val ^ twoErrNodes[1].val;
        twoErrNodes[1].val = twoErrNodes[0].val ^ twoErrNodes[1].val;
        twoErrNodes[0].val = twoErrNodes[0].val ^ twoErrNodes[1].val;
    }

    // 中序遍历
    public static TreeNode[] getTwoErrNodes(TreeNode head) {
        TreeNode[] errs = new TreeNode[2];
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        TreeNode cur = head;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (pre != null && pre.val > cur.val) {
                    // 第一次降序的头节点
                    errs[0] = errs[0] == null ? pre : errs[0];
                    // 第二次降序的尾节点
                    errs[1] = cur;
                }
                pre = cur;
                cur = cur.right;
            }
        }
        return errs;
    }
}
