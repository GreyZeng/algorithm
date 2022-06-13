package leetcode.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
// 二叉树的后序遍历
// 笔记：https://www.cnblogs.com/greyzeng/articles/15941957.html
public class LeetCode_0145_BinaryTreePostorderTraversal {

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
    public static List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        p(root, ans);
        return ans;
    }

    public static void p(TreeNode root, List<Integer> ans) {
        if (root != null) {
            p(root.left, ans);
            p(root.right, ans);
            ans.add(root.val);
        }
    }

    // 非递归 双栈方法
    // 先序遍历是，头，左，右
    // 改造一下，变成：头，右，左
    // 然后：逆序一下，就变成了后序遍历
    // 所以用两个栈即可实现
    public static List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> helper = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            helper.push(pop);
            if (pop.left != null) {
                stack.push(pop.left);
            }
            if (pop.right != null) {
                stack.push(pop.right);
            }
        }
        while (!helper.isEmpty()) {
            ans.add(helper.pop().val);
        }
        return ans;
    }


    // morris遍历实现后序遍历
    // 处理时机放在能回到自己两次的点，且第二次回到自己的时刻,第二次回到他自己的时候，
    // 不打印他自己，而是逆序打印他左树的右边界, 整个遍历结束后，单独逆序打印整棵树的右边界
    public static List<Integer> postorderTraversal(TreeNode head) {
        List<Integer> ans = new ArrayList<>();
        if (null == head) {
            return ans;
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
                    continue;
                } else {
                    // 有左树的点第二次到达自己的时候
                    mostRight.right = null;
                    collectLeftTreeRightEdge(cur.left, ans);
                }
            }
            cur = cur.right;
        }
        collectLeftTreeRightEdge(head, ans);
        return ans;
    }

    // 逆序收集左树的右边界
    private static void collectLeftTreeRightEdge(TreeNode head, List<Integer> ans) {
        TreeNode tail = reverse(head);
        TreeNode c = tail;
        while (c != null) {
            ans.add(c.val);
            c = c.right;
        }
        reverse(tail);
    }

    public static TreeNode reverse(TreeNode node) {
        TreeNode pre = null;
        TreeNode cur = node;
        while (cur != null) {
            TreeNode t = cur.right;
            cur.right = pre;
            pre = cur;
            cur = t;
        }
        return pre;
    }

    // 【非递归】【单栈】后序遍历
    public static List<Integer> postorderTraversal1(TreeNode head) {
        List<Integer> ans = new ArrayList<>();
        if (null == head) {
            return ans;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode c;
        TreeNode h = head;
        stack.push(h);
        while (!stack.isEmpty()) {
            c = stack.peek();
            // 如果c的左孩子和有孩子都不为空，且上一个节点不是c的右孩子，说明是c是新加入的节点，把左孩子压栈
            if (c.left != null && h != c.left && h != c.right) {
                stack.push(c.left);
            } else if (c.right != null && h != c.right) {
                stack.push(c.right);
            } else {
                ans.add(stack.pop().val);
                h = c;
            }
        }
        return ans;
    }
}
