## 题目描述

[题目链接](https://leetcode.com/problems/merge-two-sorted-lists/)


## 思路 

设置两个指针，一个指针（t1）指向l1链表头，另外一个指针（t2）指向l2链表头。

首先判断l1和l2的第一个元素，谁小，谁就是最后要返回的链表的头节点，如果l1和l2的第一个元素相等，随便取哪个都可以。

这样，我们就设置好了要返回链表的头节点，假设头节点是head，

依次移动t1和t2指针，谁小，谁就接入进来。依次操作，直到两个链表都遍历完毕为止。

完整代码和注释：

```java
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null) {
    		// 如果任何一个链表为空，那么直接返回另外一个链表即可
			return l1 == null ? l2 : l1;
		}
    	// 谁小谁作为头
		ListNode head = l1.val > l2.val ? l2 : l1;
		// t1 和 t2 表示l1和l2下一个要遍历的位置
		ListNode t1 = head == l1 ? l1.next : l1;
		ListNode t2 = head == l2 ? l2.next : l2;
		ListNode cur = head;
		while (t1 != null || t2 != null) {
			if (t1 == null) {
				// l1链表已经到头，剩下只需要把l2链表接入进来即可
				cur.next = t2;
				t2 = t2.next;
				cur = cur.next;
				continue;
			}
			if (t2 == null) {
				// l2链表已经到头，剩下只需要把l2链表接入进来即可
				cur.next = t1;
				t1 = t1.next;
				cur = cur.next;
				continue;
			}
			// l1和l2都没有到头，那么谁小谁接入进来即可。
			if (t1.val > t2.val) {
				cur.next = t2;
				t2 = t2.next;
			} else {
				cur.next = t1;
				t1 = t1.next;
			}
			cur = cur.next;
		}
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
