package 数据结构.LRU;

import java.util.HashMap;

// LRU 设计和实现
// 最近最少使用
// 双向链表结合哈希表
// https://leetcode.com/problems/lru-cache/
// https://www.cnblogs.com/greyzeng/p/14413345.html
public class LeetCode_0146_LRUCache {

  static class LRUCache {
    static class Node {
      public int key;
      public int value;
      public Node last;
      public Node next;

      public Node(int key, int value) {
        this.key = key;
        this.value = value;
      }
    }

    static class DoubleLinkedList {
      public Node head;
      public Node tail;

      public DoubleLinkedList() {
        head = null;
        tail = null;
      }

      public void moveToLast(Node node) {
        if (tail == node) {
          // 移动的节点就是尾部节点，不需要动
          return;
        }
        if (head == node) {
          head = node.next;
          head.last = null;
        } else {
          // 把 node 先和原先链表断连
          node.last.next = node.next;
          node.next.last = node.last;
        }
        // 统一把node加到尾部
        addLast(node);
      }

      public void addLast(Node node) {
        if (head == null) {
          head = node;
          tail = node;
        }
        if (tail != null) {
          node.last = tail;
          node.next = null;
          tail.next = node;
          tail = node;
        }
      }
    }

    private HashMap<Integer, Node> map;
    private DoubleLinkedList list;
    private final int capacity;

    public LRUCache(int capacity) {
      this.map = new HashMap<>();
      this.list = new DoubleLinkedList();
      this.capacity = capacity;
    }
    // note get值后，需要把这个值设置为最新的未使用的过的值
    public int get(int key) {
      if (map.containsKey(key)) {
        Node node = map.get(key);
        list.moveToLast(node);
        return node.value;
      }
      return -1;
    }

    public void put(int key, int value) {
      if (map.containsKey(key)) {
        Node old = map.get(key);
        old.value = value;
        list.moveToLast(old);
      } else {
        if (map.size() == capacity) {
          map.remove(list.head.key);
          list.head.value = value;
          list.head.key = key;
          map.put(key, list.head);
          list.moveToLast(list.head);
        } else {
          Node node = new Node(key, value);
          map.put(key, node);
          list.addLast(node);
        }
      }
    }
  }
}
