package tree;
 
import java.util.ArrayList; 
import java.util.List;
import java.util.Stack;

// https://leetcode.com/problems/binary-tree-preorder-traversal/
// 二叉树的先序遍历
// 笔记：https://www.cnblogs.com/greyzeng/articles/15941957.html
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

    // 递归方法
    public List<Integer> preorderTraversal1(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
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
    // 第一步，申请一个栈，并把头节点压入。
    // 第二步，弹出就收集答案。
    // 第三步，第二步中弹出的节点，如果右孩子不为空，则右孩子入栈。
    // 第四步，第二步中弹出的节点，如果左孩子不为空，则左孩子入栈。
    // 第五步，循环执行第二步到第四步，直到栈为空。
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (null == root) {
            return ans;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
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
    // 来到两次的节点第一次来到时候打印，其他节点正常打印
    public static List<Integer> preorderTraversal3(TreeNode root) {
        if (null == root) {
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
                    ans.add(cur.val);
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    // mostRight.right = cur;
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
