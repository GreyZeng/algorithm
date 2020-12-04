package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeetCode_0145_BinaryTreePostorderTraversal {

   
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
    // morris遍历实现后续遍历
    public static List<Integer> postorderTraversal(TreeNode head) {
        List<Integer> ans = new ArrayList<>();
        if (null == head) {
            return ans;
        }
        TreeNode cur = head;
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
                    // 有左树的点第二次到达自己的时候
                    mostRight.right = null;
                    collectEdge(cur.left, ans);
                }
            } 
            cur = cur.right;
        }
        collectEdge(head, ans);
        return ans;
    }
    public static void collectEdge(TreeNode node, List<Integer> ans) {
        TreeNode tail = reverse(node);
        TreeNode c = tail;
        while (c!=null) {
            ans.add(c.val);
            c = c.right;
        }
        reverse(tail);
    }
    
    private static TreeNode reverse(TreeNode n) {
        TreeNode pre = null; 
        TreeNode c = n;
        while (c != null) {
            TreeNode t = c.right;
            c.right = pre;
            pre = c;
            c = t;
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
            // 如果c的左孩子和有孩子都不为空，且上一个节点不是c的有孩子，说明是c是新加入 的节点，把左孩子压栈
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

    // 【非递归】【双栈】后序遍历
    // 先序遍历是，头，左，右
    // 改造一下，变成：头，右，左
    // 然后：逆序一下，就变成了后序遍历
    // 所以用两个栈即可实现
    public static List<Integer> postorderTraversal2(TreeNode head) {
        List<Integer> ans = new ArrayList<>();
        if (head == null) {
            return ans;
        }
        TreeNode cur = head;
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> helper = new Stack<>();
        stack.push(cur);
        while (!stack.isEmpty()) {
            cur = stack.pop();
            helper.push(cur);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        while (!helper.isEmpty()) {
            ans.add(helper.pop().val);
        }
        return ans;
    }

    // 递归方式
    public static List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        post(root, ans);
        return ans;
    }

    private static void post(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        post(root.left, ans);
        post(root.right, ans);
        ans.add(root.val);
    }
}
