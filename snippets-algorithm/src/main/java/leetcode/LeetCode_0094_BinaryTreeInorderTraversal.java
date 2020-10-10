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

    // morris
    public static List<Integer> inorderTIntegers(TreeNode head) {
        List<Integer> tree = new ArrayList<>();
        if (head == null) {
            return tree;
        }
        TreeNode cur = head;
        TreeNode mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    tree.add(cur.val);
                    cur = cur.right;
                }
            } else {
                tree.add(cur.val);
                cur = cur.right;
            }
        }
        return tree;
    }

    // 递归
    public static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> tree = new ArrayList<>();
        process(root, tree);
        return tree;
    }

    public static void process(TreeNode root, List<Integer> tree) {
        if (root != null) {
            process(root.left, tree);
            tree.add(root.val);
            process(root.right, tree);
        }
    }

    // 非递归
    // 1。整条左边界入栈
    // 2。弹出就打印
    // 3。来到右树上继续执行1
    public static List<Integer> inorderTIntegers3(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                list.add(root.val);
                root = root.right;

            }
        }
        return list;
    }


}
