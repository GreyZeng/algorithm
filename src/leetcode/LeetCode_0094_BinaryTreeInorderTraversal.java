package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeetCode_0094_BinaryTreeInorderTraversal {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;
    }

    // morris遍历方式
    public static List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        TreeNode cur = root;
        TreeNode mostRight = null;
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

    // 【非递归】中序遍历
    // 1.整条左边界入栈
    // 2.弹出就打印
    // 3.来到右树上继续执行1
    public static List<Integer> inorderTraversal2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                ans.add(cur.val);
                cur = cur.right;
            }
        }
        return ans;
    }

    // 递归方式
    public static List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        in(root, ans);
        return ans;
    }

    private static void in(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        in(root.left, ans);
        ans.add(root.val);
        in(root.right, ans);
    }

}
