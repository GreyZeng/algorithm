---
title: LeetCode 162. Find Peak Element
date: 2021-07-27 22:58:08
tags:
  - 算法与数据结构
  - LeetCode
  - 二分
categories:
  - - 技术基础
    - 算法与数据结构
description: '二分法可以用于求局部最大值问题'
---

## 题目描述

[题目链接](https://leetcode.com/problems/find-peak-element/)

## 思路

假设数组长度为`N`，首先判断`0`号位置的数和`N-1`位置的数是不是峰值位置。

`0`号位置只需要和`1`号位置比较，如果`0`号位置大，`0`号位置就是峰值位置。

`N-1`号位置只需要和`N-2`号位置比较，如果`N-1`号位置大，`N-1`号位置就是峰值位置。

如果`0`号位置和`N-1`在上轮比较中均是最小值，那么数组的样子必然是如下情况：

![image](https://img2020.cnblogs.com/blog/683206/202107/683206-20210727230900379-2118521445.png)

`[0..1]`区间内是增长, `[N-2...N-1]`区间内是下降

那么峰值位置必在`[1...N-2]`之间出现

此时可以通过**二分**来找峰值位置，先来到中点位置，假设为`mid`，如果:

```java
arr[mid] > arr[mid+1] && arr[mid] > arr[mid-1]
```

则`mid`位置即峰值位置

否则，有如下两种情况：

情况一，趋势是：

![image](https://img2020.cnblogs.com/blog/683206/202107/683206-20210727231447044-1007027123.png)

则在`[1...(mid-1)]`区间内继续上述二分。

情况二，趋势是：

![image](https://img2020.cnblogs.com/blog/683206/202107/683206-20210727231814706-386657533.png)

则在`[(mid+1)...(N-2)]`区间内继续上述二分。

## 完整代码

```java
public class LeetCode_0162_FindPeakElement {

    public int findPeakElement(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return nums[0] == Math.max(nums[0], nums[1]) ? 0 : 1;
        }
        if (nums[n - 1] > nums[n - 2]) {
            return n - 1;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        int L = 1;
        int R = n - 2;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]) {
                return mid;
            } else if (nums[mid] < nums[mid + 1]) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return -1;
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
