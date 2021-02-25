## 题目描述

[题目链接](https://leetcode.com/problems/scramble-string/)

## 思路  

初步过滤

如果两个字符串的字符种类和数量不太一样，那么肯定不互为扰乱字符串


### 暴力递归方式

```java
f(str1,str2, L1, L2, k)
```

表示: str1 从L1开始往后推k个长度字符，和str2从L2往后推k个长度字符，是否互为扰乱字符串

那么主函数调用：

```java
f(str1, str2, 0, 0, str1.length) 
```

暴力方法中，我们枚举第一刀切str1的位置，切完第一刀后，分成的两个字符串可以旋转也可以不旋转，枚举出所有情况后，如果str2和str1互为扰动字符串，那么str2必在其中。






## 更多


[算法和数据结构笔记](https://github.com/GreyZeng/algorithm)


## 参考资料


- [程序员代码面试指南（第2版）](https://book.douban.com/subject/30422021/)
- [算法和数据结构基础班-左程云](https://ke.qq.com/course/2145184)
- [极客时间-数据结构与算法之美-王争](https://time.geekbang.org/column/intro/126)
- [算法(第四版)](https://book.douban.com/subject/19952400/)
