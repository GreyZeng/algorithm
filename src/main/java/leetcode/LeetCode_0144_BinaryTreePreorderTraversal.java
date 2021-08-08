package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LeetCode_0144_BinaryTreePreorderTraversal {
    public class TreeNode {
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

    // 递归方式
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> ans = new ArrayList<Integer>();
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
        List<Integer> ans = new ArrayList<>();
        if (head == null) {
            return ans;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = head;
        stack.push(cur);
        while (!stack.isEmpty()) {
            TreeNode c = stack.pop();
            ans.add(c.val);
            if (c.right != null) {
                stack.push(c.right);
            }
            if (c.left != null) {
                stack.push(c.left);
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
