package git.snippet.tree;

import java.util.*;

// 笔记：https://www.cnblogs.com/greyzeng/p/16356829.html
// 树的按层遍历
// 1. hash表+LinkedList
// 2. 仅用LinkedList
// 3. 自定义队列
// https://leetcode.com/problems/binary-tree-level-order-traversal/
public class LeetCode_0102_BinaryTreeLevelOrderTraversal {

    // 哈希表结合Java自带的LinkedList
    public List<List<Integer>> levelOrder3(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }
        // 存每个节点在哪一层
        Map<TreeNode, Integer> map = new HashMap<>();
        // 用于遍历树的节点
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        map.put(root, 0); // 根节点在第0层
        queue.offer(root);
        int curLevel = 0;
        List<Integer> everyLevel = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode c = queue.poll();
            // 弹出节点在第几层
            int nodeLevel = map.get(c);
            if (c.left != null) {
                queue.offer(c.left);
                map.put(c.left, nodeLevel + 1);
            }
            if (c.right != null) {
                queue.offer(c.right);
                map.put(c.right, nodeLevel + 1);
            }
            if (curLevel == nodeLevel) {
                // 弹出节点就是当前节点所在的层，说明没有收集完毕，继续收集
                everyLevel.add(c.val);
            } else {
                // 弹出节点不是当前节点所在层，则一定是下一层的节点（因为每次map进入的节点层数只加一）所在层
                // 此时说明当前层已经遍历结束
                ans.add(everyLevel);// 可以收集当前层的完整信息
                everyLevel = new ArrayList<>(); // 继续为下一层开辟空间
                everyLevel.add(c.val);
                curLevel = nodeLevel;
            }
        }
        ans.add(everyLevel);
        return ans;
    }

    // 不用Hash表，只用LinkedList和若干个变量
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }
        // 用于遍历树的节点
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        queue.offer(root);
        List<Integer> everyLevel = new ArrayList<>();
        TreeNode nextEnd = null;
        TreeNode curEnd = root;
        while (!queue.isEmpty()) {
            TreeNode c = queue.poll();
            if (c.left != null) {
                nextEnd = c.left;
                queue.offer(c.left);
            }
            if (c.right != null) {
                nextEnd = c.right;
                queue.offer(c.right);
            }
            if (curEnd == c) {
                // 当前层结束了
                everyLevel.add(c.val);
                ans.add(everyLevel);
                everyLevel = new ArrayList<>();
                curEnd = nextEnd;
            } else {
                // 当前层还没结束
                everyLevel.add(c.val);
            }
        }
        return ans;
    }


    // 用自定义Queue
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        MyNode head = new MyNode(root);
        MyQueue queue = new MyQueue();
        queue.offer(head);
        MyNode curEnd = head;
        MyNode nextEnd = null;
        List<Integer> everyLevel = new ArrayList<>();
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
            everyLevel.add(c.data.val);
            if (curEnd.data == c.data) {
                ans.add(everyLevel);
                everyLevel = new ArrayList<>();
                curEnd = nextEnd;
            }
        }
        return ans;
    }

    public class MyNode {

        public TreeNode data;
        public MyNode next;

        public MyNode(TreeNode node) {
            data = node;
        }
    }

    public class MyQueue {

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
}
