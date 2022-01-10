package leetcode.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
public class LeetCode_0144_BinaryTreePreorderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    // 递归方式
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        pre(root, ans);
        return ans;
    }

    public void pre(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        ans.add(root.val);
        pre(root.left, ans);
        pre(root.right, ans);
    }

    // 【非递归】先序遍历
    // 1. 弹出就打印
    // 2. 如果有右孩子，先压右孩子
    // 3. 如果有左孩子，就压左孩子
    // 然后重复1， 2， 3步（栈不为空循环执行）
    public static List<Integer> preorderTraversal2(TreeNode head) {
        if (null == head) {
            return new LinkedList<>();
        }
        List<Integer> ans = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ans.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return ans;
    }

    // Morris遍历实现先序遍历，空间复杂度O(1)
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        TreeNode cur = root;
        TreeNode mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    ans.add(cur.val);
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                ans.add(cur.val);
            }
            cur = cur.right;
        }
        return ans;
    }

}
