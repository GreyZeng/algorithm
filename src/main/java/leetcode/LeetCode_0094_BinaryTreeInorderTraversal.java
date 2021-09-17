package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LeetCode_0094_BinaryTreeInorderTraversal {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;
    }

    // 递归方式
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<Integer>();
        in(root, ans);
        return ans;
    }

    public void in(TreeNode cur, List<Integer> ans) {
        if (cur == null) {
            return;
        }
        in(cur.left, ans);
        ans.add(cur.val);
        in(cur.right, ans);
    }

    // 【非递归】中序遍历
    // 1.整条左边界入栈
    // 2.弹出就打印
    // 3.来到右树上继续执行1
    public static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.poll();
                ans.add(cur.val);
                cur = cur.right;
            }
        }
        return ans;
    }

    // morris遍历方式
    public static List<Integer> inorderTraversal3(TreeNode root) {
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
}
