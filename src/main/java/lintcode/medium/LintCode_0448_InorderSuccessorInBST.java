package lintcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 笔记：https://www.cnblogs.com/greyzeng/p/16863345.html
// 二叉搜索树中查找后继节点
// leetcode 285
// https://www.lintcode.com/problem/inorder-successor-in-bst/description
public class LintCode_0448_InorderSuccessorInBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p == null) {
            return null;
        }
        if (p.right != null) {
            return rightLeftMost(p.right);
        }
        TreeNode successor = null;
        while (root != null) {
            if (root.val > p.val) {
                successor = root;
                root = root.left;
            } else if (root.val < p.val) {
                root = root.right;
            } else {
                break;
            }
        }
        return successor;
    }

    private static TreeNode rightLeftMost(TreeNode p) {
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    public static TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        List<TreeNode> ans = new ArrayList<>();
        if (root == null) {
            return null;
        }
        in2(root, ans);
        boolean find = false;
        for (TreeNode c : ans) {
            if (c == p) {
                find = true;
            } else if (find) {
                return c;
            }
        }
        return null;
    }

    private static void in2(TreeNode root, List<TreeNode> ans) {
        if (root == null) {
            return;
        }
        in2(root.left, ans);
        ans.add(root);
        in2(root.right, ans);
    }

    public TreeNode inorderSuccessor4(TreeNode head, TreeNode p) {
        if (head == null) {
            return null;
        }
        TreeNode ans = null;
        TreeNode cur = head;
        TreeNode mostRight;
        boolean find = false;
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
                    mostRight.right = null;
                }
            }
            if (find) {
                ans = cur;
                find = false;
            }
            if (cur == p) {
                find = true;
            }
            cur = cur.right;
        }
        return ans;
    }

    public TreeNode inorderSuccessor3(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        boolean flag = false;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (cur == p) {
                    flag = true;
                } else if (flag) {
                    return cur;
                }
                cur = cur.right;
            }
        }
        return null;
    }
}
