package 练习题.leetcode.medium;

// 和116题一样
// https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii/
// 笔记：https://www.cnblogs.com/greyzeng/p/16356829.html
public class LeetCode_0117_PopulatingNextRightPointersInEachNodeII {
  public static class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;
  }

  public static class MyQueue {
    public Node head;
    public Node tail;
    public int size;

    public void offer(Node data) {
      size++;
      if (head == null) {
        head = data;
        tail = data;
      } else {
        tail.next = data;
        tail = data;
      }
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

  public static Node connect(Node root) {
    if (root == null) {
      return null;
    }
    MyQueue queue = new MyQueue();
    queue.offer(root);
    Node pre;
    Node cur;
    int size = 0;
    while (!queue.isEmpty()) {
      size = queue.size;
      pre = null;
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
}
