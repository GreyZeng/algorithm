// Given a binary tree, flatten it to a linked list in-place.

// For example, given the following tree:

// 1
// / \
// 2 5
// / \ \
// 3 4 6
// The flattened tree should look like:

// 1
// \
// 2
// \
// 3
// \
// 4
// \
// 5
// \
// 6
package leetcode.medium;


import java.util.LinkedList;

// https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/
public class LeetCode_0114_FlattenBinaryTreeToLinkedList {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            val = value;
        }
    }

    public static class Info {
        public TreeNode head;
        public TreeNode tail;

        public Info(TreeNode h, TreeNode t) {
            head = h;
            tail = t;
        }
    }

    // 二叉树递归套路
    public static void flatten1(TreeNode root) {
        p(root);
    }

    public static Info p(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info leftInfo = p(root.left);
        Info rightInfo = p(root.right);
        root.left = null;
        root.right = leftInfo == null ? null : leftInfo.head;
        TreeNode tail = leftInfo == null ? root : leftInfo.tail;
        tail.right = rightInfo == null ? null : rightInfo.head;
        tail = rightInfo == null ? tail : rightInfo.tail;
        return new Info(root, tail);
    }

    // TODO 时间O(N) 空间O(1) Morris遍历解法
    public static void flatten3(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode pre = null;
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
                    if (pre != null) {
                        pre.left = cur;
                    }
                    pre = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                if (pre != null) {
                    pre.left = cur;
                }
                pre = cur;
            }
            cur = cur.right;
        }
        cur = root;
        TreeNode next;
        while (cur != null) {
            next = cur.left;
            cur.left = null;
            cur.right = next;
            cur = next;
        }
    }

    // 普通做法：先序遍历+List
    public static void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        pre(list, root);
        TreeNode c = root;
        while (!list.isEmpty()) {
            c.right = list.pollFirst();
            c.left = null;
            if (!list.isEmpty()) {
                c = c.right;
            } else {
                break;
            }
        }
    }


    public static void pre(LinkedList<TreeNode> list, TreeNode root) {
        if (root == null) {
            return;
        }

        list.addLast(root);
        pre(list, root.left);
        pre(list, root.right);
    }


    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        flatten3(head);
        System.out.println(head);
        // head.left = new TreeNode(2);
        // head.left.left = new TreeNode(3);
        // head.left.right = new TreeNode(4);
        // head.right = new TreeNode(5);
        // head.right.left = new TreeNode(6);
        // head.right.right = new TreeNode(7);
        //
        // flatten2(head);

        // while (head != null) {
        // System.out.println(head.val);
        // System.out.println(head.left);
        // head = head.right;
        // }

    }


}
