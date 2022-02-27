package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

//树的按层遍历
//1. hash表+LinkedList
//2. 仅用LinkedList
//3. 自定义队列
// https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
public class LeetCode_0102_BinaryTreeLevelOrderTraversal {

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

    public static void printList(List<List<Integer>> ans) {
        StringBuilder sb = new StringBuilder();
        for (List<Integer> n : ans) {
            for (int i : n) {
                sb.append(i).append("-->");
            }
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        TreeNode root = buildTree();
        List<List<Integer>> ans = levelOrder2(root);
        printList(ans);
    }

    // 用LinkedList
    public static List<List<Integer>> levelOrder2(TreeNode head) {
        if (head == null) {
            return new LinkedList<>();
        }
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> level = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode curEnd = head;
        TreeNode nextEnd = null;
        queue.offer(curEnd);
        while (!queue.isEmpty()) {
            TreeNode c = queue.poll();
            level.add(c.val);
            if (c.left != null) {
                queue.offer(c.left);
                nextEnd = c.left;
            }
            if (c.right != null) {
                queue.offer(c.right);
                nextEnd = c.right;
            }
            if (c == curEnd) {
                ans.add(level);
                level = new LinkedList<>();
                curEnd = nextEnd;
            }
        }
        return ans;
    }

    public static class MyNode {
        public TreeNode data;
        public MyNode next;

        public MyNode(TreeNode node) {
            data = node;
        }
    }

    public static class MyQueue {
        public MyNode front;
        public MyNode end;
        public int size;

        public MyQueue() {
            front = null;
            end = null;
        }

        public void offer(MyNode c) {
            size++;
            if (front == null) {
                front = c;
            } else {
                end.next = c;
            }
            end = c;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public MyNode poll() {
            size--;
            MyNode ans = front;
            front = front.next;
            ans.next = null;
            return ans;
        }

    }

    // 用自定义Queue
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        MyNode head = new MyNode(root);
        MyQueue queue = new MyQueue();
        queue.offer(head);
        MyNode curEnd = head;
        MyNode nextEnd = null;
        List<Integer> item = new ArrayList<>();
        MyNode t;
        while (!queue.isEmpty()) {
            MyNode c = queue.poll();
            if (c.data.left != null) {
                t = new MyNode(c.data.left);
                queue.offer(t);
                nextEnd = t;
            }
            if (c.data.right != null) {
                t = new MyNode(c.data.right);
                queue.offer(t);
                nextEnd = t;
            }
            item.add(c.data.val);
            if (curEnd.data == c.data) {
                ans.add(item);
                item = new ArrayList<>();
                curEnd = nextEnd;
            }
        }
        return ans;
    }

    // 用LinkedList和HashMap
    public static List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        Map<TreeNode, Integer> map = new HashMap<>();
        map.put(root, 0);
        int currentLevel = 0;
        List<Integer> item = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode c = queue.poll();
            int level = map.get(c);
            if (c.left != null) {
                queue.offer(c.left);
                map.put(c.left, level + 1);
            }
            if (c.right != null) {
                queue.offer(c.right);
                map.put(c.right, level + 1);
            }

            if (level == currentLevel) {
                item.add(c.val);
            } else {
                ans.add(item);
                item = new ArrayList<>();
                item.add(c.val);
                currentLevel = level;
            }
        }
        ans.add(item);
        return ans;
    }

}
