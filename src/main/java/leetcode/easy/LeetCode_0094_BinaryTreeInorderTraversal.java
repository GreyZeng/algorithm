package leetcode.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
public class LeetCode_0094_BinaryTreeInorderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    // 递归方式
    public static List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        p(root, ans);
        return ans;
    }

    public static void p(TreeNode root, List<Integer> ans) {
        if (root != null) {
            p(root.left, ans);
            ans.add(root.val);
            p(root.right, ans);
        }
    }

    // 【非递归】中序遍历
    // 0. 使用栈
    // 1.整条左边界入栈
    // 2.弹出就打印
    // 3.来到右树上继续执行1
    public static List<Integer> inorderTraversal2(TreeNode head) {
        List<Integer> ans = new ArrayList<>();
        if (head == null) {
            return ans;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = head;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode pop = stack.pop();
                ans.add(pop.val);
                cur = pop.right;
            }
        }
        return ans;
    }

    // morris遍历方式
    // 第二次来到自己的时候收集
    public static List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        TreeNode mostRight;
        TreeNode cur = root;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    ans.add(cur.val);
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
