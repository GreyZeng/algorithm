# LeetCode_0435_NonOverlappingIntervals

## 题目描述

[题目链接](https://leetcode.com/problems/non-overlapping-intervals/)

## 思路

题目要求至少移除多少个线段可以保证线段不出现重叠区域，比如以下情况：

![image](https://img2020.cnblogs.com/blog/683206/202109/683206-20210924161153594-1933723265.png)

我们至少需要移走四条线段才能让剩余线段不重叠。移动后的线段如下图

![image](https://img2020.cnblogs.com/blog/683206/202109/683206-20210924161549588-1705521250.png)

我们可以反过来考虑，即**求所有非重叠区域的个数**，假设是M，线段数量假设是N，那么`N-M`即为需要移除的线段数量。

主要流程如下，先把所有线段按结尾位置排序，结尾小的排前面，结尾大的排后面。

然后遍历每个线段，如果线段的开始位置超过了前一个线段的结尾位置，说明这两个线段是非重叠的，非重叠个数加1，遍历完毕，用所有线段个数减去非重叠个数，即为需要移除的线段个数。

## 完整代码

```java
import java.util.Arrays;
import java.util.Comparator;

// 至少移除多少个区间后可以保证线段不重合
// 转换成非重叠区域的个数
public class LeetCode_0435_NonOverlappingIntervals {
    public static int eraseOverlapIntervals(int[][] intervals) {
        if (null == intervals || intervals.length <= 1) {
            return 0;
        }
        Interval[] arr = new Interval[intervals.length];
        int i = 0;
        for (int[] interval : intervals) {
            arr[i++] = new Interval(interval[0], interval[1]);
        }
        Arrays.sort(arr, new MyComparator());
        // 非重叠区域个数
        int notOverlap = 1;
        int b = arr[0].end;
        for (i = 1; i < arr.length; i++) {
            if (arr[i].start >= b) {
                notOverlap++;
                b = arr[i].end;
            }
        }
        return arr.length - notOverlap;
    }

    public static class MyComparator implements Comparator<Interval> {

        @Override
        public int compare(Interval o1, Interval o2) {
            return o1.end - o2.end;
        }
    }

    public static class Interval {
        public int start;
        public int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
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
