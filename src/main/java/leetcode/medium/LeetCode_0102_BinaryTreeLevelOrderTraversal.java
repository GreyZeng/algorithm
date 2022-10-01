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
    // 哈希表结合Java自带的LinkedList
    public static List<List<Integer>> levelOrder3(TreeNode head) {
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
    public static List<List<Integer>> levelOrder2(TreeNode head) {
        if (head == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> levelRecords = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode curEnd = head;
        TreeNode nextEnd = null;
        queue.offer(curEnd);
        while (!queue.isEmpty()) {
            TreeNode c = queue.poll();
            levelRecords.add(c.val);
            if (c.left != null) {
                queue.offer(c.left);
                nextEnd = c.left;
            }
            if (c.right != null) {
                queue.offer(c.right);
                nextEnd = c.right;
            }
            if (c == curEnd) {
                // 来到新的一层了
                curEnd = nextEnd;
                ans.add(levelRecords);
                levelRecords = new ArrayList<>();
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

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
