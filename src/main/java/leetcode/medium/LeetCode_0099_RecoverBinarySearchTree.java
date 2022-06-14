package leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.cn/problems/recover-binary-search-tree/
public class LeetCode_0099_RecoverBinarySearchTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    // Morris遍历，最优解，空间复杂度O(1)
    public static void recoverTree(TreeNode root) {
        // TODO
    }

    // 空间复杂度O(N)，非最优解
    // 中序遍历
    // 第一个错误节点：第一次降序的头节点
    // 第二个错误节点：第二次降序的尾节点
    // 这里只需要交换两个节点的val就可以AC，
    // 实际上更好的处理方式应该是节点真实的进行交换
    public static void recoverTree2(TreeNode root) {
        TreeNode[] twoErrNodes = getTwoErrNodes(root);
        twoErrNodes[0].val = twoErrNodes[0].val ^ twoErrNodes[1].val;
        twoErrNodes[1].val = twoErrNodes[0].val ^ twoErrNodes[1].val;
        twoErrNodes[0].val = twoErrNodes[0].val ^ twoErrNodes[1].val;
    }

    private static TreeNode[] getTwoErrNodes(TreeNode root) {
        TreeNode[] errorNode = new TreeNode[2];
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                if (pre != null) {
                    if (pre.val > node.val) {
                        // 第一次降序的头节点
                        errorNode[0] = errorNode[0] == null ? pre : errorNode[0];
                        // 第二次降序的尾节点
                        errorNode[1] = node;
                    }
                }
                pre = node;
                cur = node.right;
            }
        }
        return errorNode;
    }
}
