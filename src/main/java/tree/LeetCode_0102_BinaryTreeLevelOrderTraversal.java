package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// 笔记：https://www.cnblogs.com/greyzeng/p/16356829.html
// 树的按层遍历
// 1. hash表+LinkedList
// 2. 仅用LinkedList
// 3. 自定义队列
// https://leetcode.com/problems/binary-tree-level-order-traversal/
public class LeetCode_0102_BinaryTreeLevelOrderTraversal {
    // 哈希表结合Java自带的LinkedList
    public List<List<Integer>> levelOrder3(TreeNode head) {
        if (head == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        // 记录某个节点在第几层
        Map<TreeNode, Integer> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        // 当前是第几层
        int curLevel = 0;
        TreeNode cur = head;
        queue.offer(cur);
        map.put(cur, curLevel);
        List<Integer> levelRecords = new ArrayList<>();
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
            if (curLevel == level) {
                levelRecords.add(c.val);
            } else {
                ans.add(levelRecords);
                levelRecords = new ArrayList<>();
                levelRecords.add(c.val);
                curLevel = level;
            }
        }
        // 记得要存最后一层的数据
        ans.add(levelRecords);
        return ans;
    }

    // 用LinkedList
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        TreeNode cur = root;
        TreeNode curEnd = root;
        TreeNode nextEnd = null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(cur);
        List<Integer> items = new LinkedList<>();
        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur.left != null) {
                queue.offer(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                nextEnd = cur.right;
            }
            items.add(cur.val);
            if (curEnd == cur) {
                curEnd = nextEnd;
                ans.add(items);
                items = new LinkedList<>();
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
