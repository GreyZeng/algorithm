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
|F2,F3| * |a,b| = |F3,F4|
          |c,d|
```

其中，矩阵中a = 0, b = 1, c = 1, d = 1

所以针对斐波那契第N项，有

```text
|F(N),F(N-1)|  = |F1,F2| * |0,1| ^ (N - 2)
                           |1,1| 
```

所以优化的关键在于，求一个矩阵的(N - 2)次方如何更快，我们可以参考求一个整数的N次方如何最快，可以通过快速幂方式来计算。

比如：求6的5次方, 可以这样来求，先把5转换成二进制`0101`, 准备一个变量`t`，初始等于6， 准备一个变量`ans`, 初始等于1，从右到左遍历5的二进制位,如果遇到1则：`ans *= t` 且 `t *= t`, 遇到0则不仅需要处理ans，只需要`t *= t`, 直到遍历完成5的二进制位，ans即为答案，整个复杂度为 `O(logN)`, 详细可以参考： [x的n次幂](https://www.lintcode.com/problem/428/), 代码为：

```java
public class LintCode_0428_PowXN {

    // 类fabanacci问题
    // pow X N   ( N 转成2进制）
    // 复杂度 log（N）
    public static double myPow(double x, int n) { 
        int pow = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
        double ans = 1D;
        double t = x;
        while (pow != 0) {
            if ((pow & 1) != 0) {
                ans *= t;
            }
            pow >>= 1;
            t *= t;
        }
        if (n == Integer.MIN_VALUE) {
            ans *= x;
        }
        if (n < 0) {
            ans = 1D / ans;
        }
        return ans;
    }
}
```

回到斐波那契数列问题，一个矩阵的N次方，也可以优化成`O(logN)`的解法, 在斐波那契问题中， `ans`变量初始为单位矩阵，即：

```text
|1,0| 
|0,1| 
```

`t` 在斐波那契问题下初始为

```text
|0,1| 
|1,1| 
```

逻辑和求N的X次幂一样，只不过N的X次幂中 `t` 和 `ans` 变量都是数字相乘，而斐波那契问题是矩阵相乘，矩阵相乘的规则请参考线性代数的知识, 完整代码如下

```java
    // 最优解 O(log^N)
    public static int fib3(int N) {
        if (N <= 0) {
            return 0;
        }
        if (N == 1 || N == 2) {
            return 1;
        }
        int[][] matrix = matrixPow(new int[][]{{0, 1}, {1, 1}}, N - 2);
        return matrix[0][1] + matrix[1][1];
    }

    public static int[][] matrixPow(int[][] matrix, int n) {
        int[][] ans = new int[][]{{1, 0}, {0, 1}};
        int[][] t = matrix;
        while (n != 0) {
            if ((n & 1) != 0) {
                ans = matrix(t, ans);
            }
            n >>= 1;
            t = matrix(t, t);
        }
        return ans;
    }

    public static int[][] matrix(int[][] A, int[][] B) {
        int[][] result = new int[2][2];
        result[0][0] = A[0][0] * B[0][0] + A[0][1] * B[1][0];
        result[0][1] = A[0][0] * B[0][1] + A[0][1] * B[1][1];
        result[1][0] = A[1][0] * B[0][0] + A[1][1] * B[1][0];
        result[1][1] = A[1][0] * B[0][1] + A[1][1] * B[1][1];
        return result;
    }
```

类斐波那契问题都可以用如上的优化方法来计算，

例如，某个问题的第N项的通项公式是：

```text
F(N) = 6 * F(N-1) + 3 * F(N-5)
```

那么，要求其第N项的值，可以转换成如下矩阵公式，

```text
|Fn,Fn-1,Fn-2,Fn-3,Fn-4| = |F5,F4,F3,F2,F1|x|5x5|^(N-5)
```

列出其中前几个项并带入求出`|5x5|` 这个5 乘以 5的矩阵中每个位置的数字，然后参考快速幂的算法，即可解答。

## 更多

[算法和数据结构笔记](https://github.com/GreyZeng/algorithm)

## 参考资料


- [程序员代码面试指南（第2版）](https://book.douban.com/subject/30422021/)
- [算法和数据结构基础班-左程云](https://ke.qq.com/course/2145184)
- [极客时间-数据结构与算法之美-王争](https://time.geekbang.org/column/intro/126)
- [算法(第四版)](https://book.douban.com/subject/19952400/)