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

---


<meta name = "referrer" content = "no-referrer" />



## 题目描述

[题目链接](https://leetcode.com/problems/find-peak-element/)


## 思路 

假设数组长度为N，首先判断0号位置的数和N-1位置的数是不是峰值位置。

0号位置只需要和1号位置比较，如果0号位置大，0号位置就是峰值位置。
N-1号位置只需要和N-2号位置比较，如果N-1号位置大，N-1号位置就是峰值位置。

<!--more-->

如果0号位置和N-1在上轮比较中均是最小值，那么数组的样子必然是如下情况：

![image](https://img2020.cnblogs.com/blog/683206/202107/683206-20210727230900379-2118521445.png)


从0..1是增长
从N-2...N-1是下降

那么峰值位置必在1...N-2之间出现

此时可以通过**二分**来找峰值位置，先来到中点位置，假设为mid，如果:

```
arr[mid] > arr[mid+1] && arr[mid] > arr[mid-1]
```

mid位置即峰值位置

否则：

如果趋势是：

![image](https://img2020.cnblogs.com/blog/683206/202107/683206-20210727231447044-1007027123.png)

则在 1...(mid-1)区间内继续上述二分

如果趋势是：

![image](https://img2020.cnblogs.com/blog/683206/202107/683206-20210727231814706-386657533.png)

则在 (mid+1)...(N-2)区间内继续上述二分。


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
