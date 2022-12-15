package 练习题.leetcode.medium;

import java.util.HashMap;

//Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
//
//        Implement the LRUCache class:
//
//        LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
//        int get(int key) Return the value of the key if the key exists, otherwise return -1.
//        void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache.
//        If the number of keys exceeds the capacity from this operation, evict the least recently used key.
//        Follow up:
//        Could you do get and put in O(1) time complexity?
//
//
//
//        Example 1:
//
//        Input
//        ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//        [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//        Output
//        [null, null, null, 1, null, -1, null, -1, 3, 4]
//
//        Explanation
//        LRUCache lRUCache = new LRUCache(2);
//        lRUCache.put(1, 1); // cache is {1=1}
//        lRUCache.put(2, 2); // cache is {1=1, 2=2}
//        lRUCache.get(1);    // return 1
//        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
//        lRUCache.get(2);    // returns -1 (not found)
//        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
//        lRUCache.get(1);    // return -1 (not found)
//        lRUCache.get(3);    // return 3
//        lRUCache.get(4);    // return 4
//
//
//        Constraints:
//
//        1 <= capacity <= 3000
//        0 <= key <= 3000
//        0 <= value <= 104
//        At most 3 * 104 calls will be made to get and put.
// https://www.cnblogs.com/greyzeng/p/14413345.html
public class LeetCode_0146_LRUCache {

    public static class LRUCache {
        public static class Node {
            public int key;
            public int value;
            public Node last;
            public Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        public static class DoubleLinkedList {
            public Node head;
            public Node tail;

            public DoubleLinkedList() {
                head = null;
                tail = null;
            }

            public void moveToLast(Node node) {
                if (tail == node) {
                    return;
                }
                if (head == node) {
                    head = node.next;
                    head.last = null;
                } else {
                    node.last.next = node.next;
                    node.next.last = node.last;
                }
                node.last = tail;
                node.next = null;
                tail.next = node;
                tail = node;
            }

            public void addLast(Node node) {
                if (head == null) {
                    head = node;
                    tail = node;
                }
                if (tail != null) {
                    tail.next = node;
                    node.last = tail;
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
/*["LRUCache","put","put","get","put","get","put","get","get","get"]
        [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]

        [null,null,null,1,null,2,null,1,3,4]*/

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

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);
        cache.put(3, 3);
        cache.get(2);
    }


}
