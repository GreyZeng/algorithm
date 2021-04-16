## 题目描述

[题目链接](https://leetcode.com/problems/container-with-most-water/)

## 思路

左指针l从0位置开始，右指针r从len-1开始， height[l] 和 height[r] 无非有如下几种情况：

情况1：

```java
height[r] < height[l]
```

情况2：

```java
height[r] == height[l]
```

情况3：

```java
height[r] > height[l]
```

对于情况1，我们可以这样考虑，不管l...r之间的值有哪些，我至少可以确保如果横线划在height[r]位置，可以收获

```java
(r - l) * height[r]
```
这么多雨水，然后r指针向右移动

情况3同理，不管l...r之间的值有哪些，我至少可以确保如果横线划在height[l]位置，可以收获

```java
(r - l) * height[l]
```
这么多雨水，然后l指针向左移动

对于情况2，其实也可以归结到上面任何一种情况中，假设我归结在情况3中，那么对于情况2和情况3，统一可以获得的最大雨水量是：

```java
(r - l) * height[l]
```
然后l指针向左移动

结束的条件是当l和r相等的时候，每一次移动l或者r都收集一遍，并且和最大值(max)比较，最后返回max即可

## 完整代码

```java
    public static int maxArea(int[] heights) {
        if (null == heights || heights.length == 1) {
            return 0;
        }
        int l = 0;
        int r = heights.length - 1;
        int w;
        int max = 0;
        while (l < r) {
            if (heights[r] < heights[l]) {
                w = (r - l) * heights[r];
                r--;
            } else {
                w = (r - l) * heights[l];
                l++;
            }
            max = Math.max(max, w);
        }
        return max;
    }
```

## 更多

[算法和数据结构笔记](https://github.com/GreyZeng/algorithm)

## 参考资料

- [程序员代码面试指南（第2版）](https://book.douban.com/subject/30422021/)
- [算法和数据结构基础班-左程云](https://ke.qq.com/course/2145184)
- [极客时间-数据结构与算法之美-王争](https://time.geekbang.org/column/intro/126)
- [算法(第四版)](https://book.douban.com/subject/19952400/)
