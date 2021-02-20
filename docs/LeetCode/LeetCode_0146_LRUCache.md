# LeetCode 146. LRU Cache

# LeetCode 146. LRU Cache


## 题目描述


[题目链接](https://leetcode.com/problems/lru-cache/)


### 思路
总体思路，是双向链表和哈希表搭配使用，同时要记录一个LRU的最大容量Capacity。
双向链表尾部记录最新使用过的记录，头部记录的是最久没有使用过的记录。
具体来说，对于put操作：

- 如果是未加入的元素
   - 未超过Capacity，则会加入到双向链表的尾部，同时存一份到哈希表中，
   - 超过了Capacity，则会最先淘汰双向链表头部的元素（因为这个元素是最久没有使用过的元素），然后把这个元素插入双向链表尾部，且存一份到哈希表中。
- 如果是已经加入的元素
   - 则直接把这个元素取出来，移动到双向链表的尾部

对于get操作：

- 如果未指定Key，则直接取尾部元素即可。
- 如果指定了Key，则从哈希表中取出这个元素后，同时把这个元素移动到双向链表的尾部。



完整代码见：

```java
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
```


## 更多


[算法和数据结构笔记](https://github.com/GreyZeng/algorithm)


## 参考资料


- [程序员代码面试指南（第2版）](https://book.douban.com/subject/30422021/)
- [算法和数据结构基础班-左程云](https://ke.qq.com/course/2145184)
- [极客时间-数据结构与算法之美-王争](https://time.geekbang.org/column/intro/126)
- [算法(第四版)](https://book.douban.com/subject/19952400/)
