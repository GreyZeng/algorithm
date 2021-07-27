---
title: LeetCode 260. Single Number III date: 2021-07-27 22:58:08 tags:

- 算法与数据结构
- LeetCode
- 位运算 categories:
-
    - 技术基础
    - 算法与数据结构

---

<meta name = "referrer" content = "no-referrer" />

## 题目描述

[题目链接](https://leetcode.com/problems/single-number-iii/)

## 思路

原始题目其实可以扩展成：

> 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数

解法也是一样。

先看一个更简单的题目：

<!--more-->

> 如果一个数组中只有一个数出现了奇数次，其他数都是偶数次，如何找到这个出现了奇数次个数的数

解法是通过异或计算，把所有数求异或值，最后剩下的数就是那个出现了奇数次的数，因为出现偶数次的数通过异或计算都抵消了。

那么回到原题目：

> 一个数组中有两种数出现了奇数次，其他数都出现了偶数次

我们假设出现了奇数次的数是a和b，如果把数组所有数都异或一下，最后的结果一定是：

```
a^b
```

因为a和b是两种不同的数，所以 ```a^b```的结果一定不等于0。

所以：

```a^b```的结果如果转换成二进制的话，一定有某位是1。我们假设```a^b```转换成二进制后最右侧位置的1在i位置，由此可以得出一个结论：

**a和b的二进制在i位置一定一个为0，一个为1**

不妨假设a的i位置为0，b的i位置为1。

此外，容易得知，整个数组中的数，i位置为0的数除了a以外，其他数一定有偶数个， i位置为1的数除了b之外，其他数一定有偶数个。

那么我们可以只对i位置为1的数求异或，最后得到的值一定是b，然后通过

```
b^(a^b) = a
```

可以得到a的值。

最后只剩下一个问题，那么如何求一个数最右侧的1呢?

假设 某个数x二进制为：

```
00010010 
```

其最右侧的1是：

```
00000010
```

算法是：x & ((~x) + 1)  或者 x & (-x)

上述中

```
a^b
```

的最右侧1就是：

```
(a^b) & (~(a^b) + 1)
```

用这个值去 & 数组中每个值，如果为1，说明i位置是1，如果是0说明i位置是0

## 完整算法

```java
public class LeetCode_0260_SingleNumberIII {
    public static int[] singleNumber(int[] arr) {
        int eor = 0;
        for (int n : arr) {
            eor ^= n;
        }
        // 假设出现奇数次的两种数为 a和b
        // eor = a ^ b
        // 获取最右侧的1
        int a = 0;
        int rightOne = eor & ((~eor) + 1);
        for (int n : arr) {
            if ((n & rightOne) == 0) {
                a ^= n;
            }
        }
        int b = a ^ eor;
        return new int[]{a, b};
    }
}
```

## 推广

> 一个数组中有一种数出现K次，其他数都出现了M次，M > 1, K < M, 找到出现了K次的数，如果要求，额外空间复杂度O(1)，时间复杂度O(N)，假设数组中所有数都是int类型

我们可以这样考虑，设置一个32位的数组，

```
int[] help = new int[32];
```

遍历原始数组中每个数的每一个二进制位, 伪代码如下：

```
for (int num : arr) {
  for (int i = 0; i < 32; i++) {
    help[i] += num的二进制中i位置的值(只能是0或者1)
  }
}
```

经过以上循环，help数组就把数组中的所有数的二进制位的信息累加起来了。

```
help[0] 表示数组中所有数二进制中0位置的值之和
help[1] 表示数组中所有数二进制中1位置的值之和
...

help[31] 表示数组中所有数二进制中31位置的值之和
```

然后i从0开始拿出```help[i]```位置的值，假设i位置的值是x，用

```
x % m 

```
如果结果是k，说明出现k次的元素在这个位置上是1

遍历完help数组，出现k次元素的每一位信息都拿到了，然后还原出来即可。

## 完整代码

```
public static int km(int[] arr, int k, int m) {
		int[] helper = new int[32];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < 32; j++) {
				helper[j] += ((arr[i] >> j) & 1);
			}
		}
		int ans = 0;
		for (int i = 0; i < 32; i++) {
			if (helper[i] % m == k) {
				ans |= (1 << i);
			}
		}
		return ans;
	}
```


## 更多

[算法和数据结构笔记](https://github.com/GreyZeng/algorithm)

## 参考资料

- [程序员代码面试指南（第2版）](https://book.douban.com/subject/30422021/)
- [算法和数据结构基础班-左程云](https://ke.qq.com/course/2145184)
- [极客时间-数据结构与算法之美-王争](https://time.geekbang.org/column/intro/126)
- [算法(第四版)](https://book.douban.com/subject/19952400/)

