package leetcode.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
public class LeetCode_0144_BinaryTreePreorderTraversal {

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

    public static TreeNode buildTree() {
        TreeNode N1 = new TreeNode(1);
        TreeNode N2 = new TreeNode(2);
        TreeNode N3 = new TreeNode(3);
        TreeNode N4 = new TreeNode(4);
        TreeNode N5 = new TreeNode(5);
        TreeNode N6 = new TreeNode(6);
        TreeNode N7 = new TreeNode(7);
        TreeNode N8 = new TreeNode(8);
        TreeNode N9 = new TreeNode(9);
        TreeNode N10 = new TreeNode(10);
        TreeNode N11 = new TreeNode(11);
        TreeNode N12 = new TreeNode(12);
        TreeNode N13 = new TreeNode(13);
        N1.left = N2;
        N1.right = N3;
        N2.right = N4;
        N3.left = N5;
        N3.right = N6;
        N4.left = N7;
        N4.right = N8;
        N6.left = N9;
        N6.right = N10;
        N7.left = N11;
        N8.left = N12;
        N9.right = N13;
        return N1;
    }

    public static void printList(List<Integer> ans) {
        StringBuilder sb = new StringBuilder();
        for (int i : ans) {
            sb.append(i).append("-->");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        TreeNode root = buildTree();
        List<Integer> ans = preorderTraversal3(root);
        printList(ans);
    }

    // 递归方式
    public static List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        p(root, ans);
        return ans;
    }

    public static void p(TreeNode node, List<Integer> ans) {
        if (node == null) {
            return;
        }
        ans.add(node.val);
        p(node.left, ans);
        p(node.right, ans);
    }

    // 【非递归】先序遍历
    // 0. 使用栈
    // 1. 弹出就打印
    // 2. 如果有右孩子，先压右孩子
    // 3. 如果有左孩子，就压左孩子
    // 然后重复1， 2， 3步（栈不为空循环执行）
    public static List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            ans.add(pop.val);
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
        return ans;
    }

    // Morris遍历实现先序遍历，空间复杂度O(1)
    // 来到两次的节点第一次来到时候打印，其他节点正常打印
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        TreeNode mostRight;
        TreeNode cur = root;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    ans.add(cur.val);
                    mostRight.right = cur;
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
