# 题目描述

[题目链接](https://leetcode.com/problems/fibonacci-number/)

## 思路

斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：

> F(0) = 0，F(1) = 1
> F(n) = F(n - 1) + F(n - 2)，其中 n > 1

### 暴力解法：递归版本

```java
    public static int fib(int N) {
        if (N <= 0) {
            return 0;
        }
        if (N == 1 || N == 2) {
            return 1;
        }
        return fib(N - 1) + fib(N - 2);
    }
```

### 暴力解法：迭代版本

```java
    public static int fib2(int N) {
        if (N <= 0) {
            return 0;
        }
        if (N == 1 || N == 2) {
            return 1;
        }
        int first = 1;
        int second = 1;
        int result = 0;
        for (int i = 3; i <= N; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        return result;
    }

```

### 最优解

如果某个递归，除了初始项之外，具有如下的形式

```text
F(N) = C1 * F(N) + C2 * F(N-1) + ... + Ck * F(N-k) ( C1...Ck 和k都是常数)
```

并且这个递归的表达式是严格的、不随条件转移的, 那么都存在类似斐波那契数列的优化，时间复杂度都能优化成O(logN),

斐波那契数列的通项公式

```text
F(N) = F(N - 1) + F(N - 2)
```

斐波那契数列的任意项（以F2，F3，F4为例），都有如下公式：

```text
|F2,F3| * | a,b| = |F3,F4|
          | c,d|
```

其中，矩阵中a = 0, b = 1, c = 1, d = 1

所以针对斐波那契第N项，有

```text
|F(N),F(N-1)|  = |F1,F2| * | 0,1| ^ (N - 2)
                           | 1,1| 
```
// TODO
例如：

```text
F(N) = 6 * F(N-1) + 3 * F(N-5)
```

```text
|Fn,Fn-1,Fn-2,Fn-3,Fn-4| = |F5,F4,F3,F2,F1|x|5x5|^(N-5)
```

O(logN)

## 更多

[算法和数据结构笔记](https://github.com/GreyZeng/algorithm)

## 参考资料


- [程序员代码面试指南（第2版）](https://book.douban.com/subject/30422021/)
- [算法和数据结构基础班-左程云](https://ke.qq.com/course/2145184)
- [极客时间-数据结构与算法之美-王争](https://time.geekbang.org/column/intro/126)
- [算法(第四版)](https://book.douban.com/subject/19952400/)
