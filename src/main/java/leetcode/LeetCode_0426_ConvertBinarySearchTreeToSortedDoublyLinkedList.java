package leetcode;

// 双向链表节点结构和二叉树节点结构是一样的，如果你把last认为是left，next认为是right的话。
// 给定一个搜索二叉树的头节点head，请转化成一条有序的双向链表，并返回链表的头节点。
//
// tips:
// case1 中序遍历
// case2 二叉树的递归套路
// case3 morris遍历
// https://leetcode-cn.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
// https://www.lintcode.com/problem/convert-binary-search-tree-to-sorted-doubly-linked-list/description
public class LeetCode_0426_ConvertBinarySearchTreeToSortedDoublyLinkedList {
  public class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
      val = _val;
      left = _left;
      right = _right;
    }
  }

  public Node treeToDoublyList(Node head) {
    if (head == null) {
      return null;
    }
    Info info = p(head);
    info.end.right = info.start;
    info.start.left = info.end;
    return info.start;
  }

  public Info p(Node head) {
    if (head == null) {
      return new Info(null, null);
    }
    Info left = p(head.left);
    if (left.end != null) {
      left.end.right = head;
    }
    head.left = left.end;

    Info right = p(head.right);
    if (right.start != null) {
      right.start.left = head;
    }
    head.right = right.start;
    Node start = left.start != null ? left.start : head;
    Node end = right.end != null ? right.end : head;
    return new Info(start, end);
  }

  public class Info {
    public Node start;
    public Node end;

    public Info(Node s, Node e) {
      start = s;
      end = e;
    }
  }
}
