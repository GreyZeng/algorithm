## 题目描述

[题目链接](https://leetcode.com/problems/gas-station/)

## 思路
tips:
方法1：
oil(i) - dis(i) -> h(i) 纯能职数组
h(i) 往后累加，不出现负数，就是良好出发点 O(N^2)
生成h(i) 的累加和数组
纯能职数组[1,-1,0,3,-1]
--> 累加和数组 [1,0,0,3,2]
---> 再累加一次 [1,0,0,3,2,3,2,2,5,4]
然后滑动窗口最小值，减去L-1位置的数，如果<0,则L不是良好出发点

方法2：
时间O(n) 空间O(1)
1. 复用oil数组
2. int need  接上头需要多少
   int rest 剩余油多少
   int start 联通区开始
   int end 联通区结束下一个位置
   [start, end)
   进入联通区，还没有满足的，联通区里面的点都不满足
   找到良好出发点以后，只要能连上良好出发点的都满足条件（只需要关注need）

## 更多


[算法和数据结构笔记](https://github.com/GreyZeng/algorithm)


## 参考资料


- [程序员代码面试指南（第2版）](https://book.douban.com/subject/30422021/)
- [算法和数据结构基础班-左程云](https://ke.qq.com/course/2145184)
- [极客时间-数据结构与算法之美-王争](https://time.geekbang.org/column/intro/126)
- [算法(第四版)](https://book.douban.com/subject/19952400/)
