# 加油站问题

## 题目描述

[题目链接](https://leetcode.com/problems/gas-station/)

## 思路

### 暴力解法 O(N^2)

我们可以通过生成辅助数组来验证良好出发点

```java
int[]h
```

这个数组的长度和cost数组长度一致，且这个数组的每个元素的生成逻辑是：

```java
h[i]=gas[i]-cost[i];
```

我们可以很容易得到一个结论：**h(i) 往后累加，并回到i位置，不出现负数，就是良好出发点 ，这个i位置就是良好出发点。**

以每个位置作为i位置，依次走这个逻辑，所以这个解法的复杂度是 O(N^2)，代码如下：

```java
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = cost.length;
        int[] helper = new int[len];
        for (int i = 0; i < helper.length; i++) {
            helper[i] = gas[i] - cost[i];
        }
        int pre = 0;
        for (int i = 0; i < len; i++) {
            pre = helper[i];
            if (pre < 0) {
                continue;
            }
            for (int j = i + 1; j < len + i + 1; j++) {
                pre += helper[j < len ? j : (j - len)];
                if (pre < 0) {
                    break;
                }
            }
            if (pre >= 0) {
                return i;
            }
        }
        return -1;
    }
```

### 滑动窗口 时间复杂度 O(N) 空间复杂度 O(N)

首先，我们还是需要生成h[i]数组

```java
h[i]=gas[i]-cost[i];
```

假设生成的h[i]数组如下：

```java
[1,-1,0,3,-1]
```

我们生成其累加和数组preSum[i]

```java
[1,0,0,3,2]
```

用这个累加和数组再和h[i]数组相加，得到一个两倍长度的数组

```java
[1,0,0,3,2,3,2,2,5,4]
```

求针对这个数组，滑动窗口为n（n为原数组长度）的最小值，如果第i个窗口内的最小值减去窗口前一个位置的值，如果小于0，则i号位置不是良好出发点

比如

L...L + n - 1 是第x个窗口，最小值m，

如果:

```java
m - h[L-1] >= 0
```

则x是良好出发点

反之，则x不是良好出发点, 完整代码：

```java
public static int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int doubleLen = len << 1;
        int[] h = new int[doubleLen];
        h[0] = gas[0] - cost[0];
        for (int i = 1; i < doubleLen; i++) {
            if (i < len) {
                h[i] = gas[i] - cost[i];
                h[i] += h[i - 1];
            }
            if (i >= len) {
                h[i] = h[len - 1] + h[i - len];
            }
        }
        LinkedList<Integer> qMin = new LinkedList<>();
        int r = 0;
        int index = 0;
        while (r < doubleLen) {
            while (!qMin.isEmpty() && h[qMin.peekLast()] >= h[r]) {
                qMin.pollLast();
            }
            qMin.addLast(r);
            if (qMin.peekFirst() == r - len) {
                qMin.pollFirst();
            }
            if (r >= len - 1) {
                if (r == len - 1) {
                    if (h[qMin.peekFirst()] >= 0) {
                        return index;
                    }
                } else {
                    if (h[qMin.peekFirst()] - h[r - len] >= 0) {
                        return index;
                    }
                }
                index++;
            }
            r++;
        }
        return -1;
    }
```

### 时间复杂度 O(N) 空间复杂度 O(1) 的解法

TODO

## 更多

[算法和数据结构笔记](https://github.com/GreyZeng/algorithm)

## 参考资料

- [程序员代码面试指南（第2版）](https://book.douban.com/subject/30422021/)
- [算法和数据结构基础班-左程云](https://ke.qq.com/course/2145184)
- [极客时间-数据结构与算法之美-王争](https://time.geekbang.org/column/intro/126)
- [算法(第四版)](https://book.douban.com/subject/19952400/)
