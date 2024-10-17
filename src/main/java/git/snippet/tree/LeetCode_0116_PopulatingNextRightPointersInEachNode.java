package git.snippet.tree;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
// 本质是树的按层遍历
// 1. hash表+LinkedList
// 2. 仅用LinkedList
// 3. 自定义队列
// 本题有特殊要求：
// You may only use constant extra space.
// Recursive approach is fine, you may assume implicit stack space does not count as extra space for
// this problem.
// 所以可以采用自定义队列的方式
// 笔记：https://www.cnblogs.com/greyzeng/p/16356829.html
public class LeetCode_0116_PopulatingNextRightPointersInEachNode {
    // 使用LinkedList作为队列,空间O(N)
    public Node connect(Node root) {
        if (null == root) {
            return null;
        }
        Node pre = null;
        int size;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            size = queue.size();
            // 把每一层用链表串好，pre记录每一层的最后那个位置
            // 用于和下一层的开头连接
            // 每次把一层搞定
            for (; size > 0; size--) {
                Node p = queue.poll();
                if (pre != null) {
                    pre.next = p;
                }
                pre = p;
                if (null != p.left) {
                    queue.offer(p.left);
                }
                if (null != p.right) {
                    queue.offer(p.right);
                }
            }
            pre = null;
        }
        return root;
    }

    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        Node cur = root;
        MyQueue queue = new MyQueue();
        queue.offer(cur);
        int size;
        while (!queue.isEmpty()) {
            size = queue.size;
            Node pre = null;
            for (; size > 0; size--) {
                cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                if (pre != null) {
                    pre.next = cur;
                }
                pre = cur;
            }
        }
        return root;
    }

    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node(int v) {
            val = v;
        }
    }

    // 为了省空间，自定义Queue替代LinkedList
    public static class MyQueue {
        public Node head;
        public Node tail;
        public int size;

        public void offer(Node node) {
            size++;
            if (null == head) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public Node poll() {
            size--;
            Node ans = head;
            head = head.next;
            ans.next = null;
            return ans;
        }
    }
}
