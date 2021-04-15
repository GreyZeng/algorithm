## 题目描述

[题目链接](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

## 思路

1. 假设必须以i位置字符结尾的字符串最大不重复字串的长度是x，那么所有位置x值中最大的值就是答案。

2. i位置的x的值取决于两个因素。
    - 第一个因素是i-1向左边能扩到最左位置（即：i-1位置上的x值）。
    - 第二个因素是i位置的值上一次出现的位置。

这两个因素取最大值即可算出i位置的x的值。

比如

```
[b,c,1,a,a,x,3,4,b,4]
```

这个字符串,我们要求第4个位置a这个字符的x值是多少？ 

此时：i=4

先看上面说的第一个因素：i-1位置（即3号位置上的a）能向左边扩到的最左位置是0位置（赋给变量mostLeft），

第二个因素：i位置的a字符上一次出现的位置是3位置(赋给变量prePos)，

所以i位置的x值就是：

```
i - max(mostLeft, prePos) = 4 - max(0, 3) = 4 - 3 = 1
```

3. 如何记录i位置上一次出现的位置呢？ 
   
可以用一个整型数组arr来表示

比如：

a 的 ascii码是97， arr[97] = 3,表示a上次出现的位置是3。

4. 如何记录i向左扩能扩到最左的位置是哪里？ 可以用一个pre变量来记录，pre初始是-1。

## 更多

[算法和数据结构笔记](https://github.com/GreyZeng/algorithm)

## 参考资料

- [程序员代码面试指南（第2版）](https://book.douban.com/subject/30422021/)
- [算法和数据结构基础班-左程云](https://ke.qq.com/course/2145184)
- [极客时间-数据结构与算法之美-王争](https://time.geekbang.org/column/intro/126)
- [算法(第四版)](https://book.douban.com/subject/19952400/)
