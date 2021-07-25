## 题目描述

[题目链接](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)


## 思路 

由于题目要求： Could you do this in one pass?

所以这题的难点在于：

- 我们不通过获取链表长度的方式来拿需要删除的节点。

要定位到需要删除的点，我们可以这样操作，假设我们要删除的点是X，会有如下关系

![image](https://img2020.cnblogs.com/blog/683206/202104/683206-20210419000053918-93772141.png)

其中：

- X'点是X关于中点的对称点

- Y是X前一个点

- Y'是X’后一个点


所以，可以很容易的得到一个结论：
如果我设置两个指针，假设一个叫pre，一个叫cur，

1. cur从头开始走，当cur走到Y'位置的时候（即走了n+1步），pre开始从头走;

2. 接下来cur和pre都继续向下一个位置移动，当cur移动到结尾（cur == null）的时候，pre应该正好在Y位置;

3. pre.next = pre.next.next;

这样就把X位置给删除掉了。

这道题另外一个需要注意的点是：

- 需要被删除的节点如果不是头节点，那么返回的链表节点应该是头节点。
- 如果需要删除的节点正好是头节点，那么返回的节点应该是头节点的下一个节点。

所以，如果上述两个指针移动的第一步：

 cur从头开始走，当cur走到Y'位置的时候（即走了n+1步），正好是cur == null的位置。那pre不用走了，头节点即为需要删除的节点。
 
 直接返回 head.next即可。

完整代码：

```java
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            n--;
            if (n == -1) {
                pre = head;
            }
            if (n < -1) {
                pre = pre.next;
            }
            cur = cur.next;
        } 
        if (pre == null) {
            return head.next;
        }
        pre.next = pre.next.next;
        return head;
    }
```
 
## 更多


[算法和数据结构笔记](https://github.com/GreyZeng/algorithm)


## 参考资料


- [程序员代码面试指南（第2版）](https://book.douban.com/subject/30422021/)
- [算法和数据结构基础班-左程云](https://ke.qq.com/course/2145184)
- [极客时间-数据结构与算法之美-王争](https://time.geekbang.org/column/intro/126)
- [算法(第四版)](https://book.douban.com/subject/19952400/)
