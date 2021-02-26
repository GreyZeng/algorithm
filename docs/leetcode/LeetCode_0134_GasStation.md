## 题目描述

[题目链接](https://leetcode.com/problems/gas-station/)

## 思路

### 暴力解法 O(N^2)

我们可以通过生成辅助数组来验证良好出发点

```java
int[] h
```

这个数组的长度和cost数组长度一致，且这个数组的每个元素的生成逻辑是：

```java
h[i] = gas[i] - cost[i];
``` 

h(i) 往后累加，并回到i位置，不出现负数，就是良好出发点 ，这个i位置就是良好出发点

```java
public static int canCompleteCircuit3(int[] gas, int[] cost) {
    int n = gas.length;

    int[] h = new int[n];
    for (int i = 0; i < n; i++) {
        h[i] = gas[i] - cost[i];
    }
    // 标记良好出发点的位置，开始是-1，说明没有找到良好出发点
    int good = -1;
    // h[i] 一直往后累加，累加和记录在preSum中，回到本身，如果不出现负数，i位置就是良好出发点
    int preSum;
    for (int i = 0; i < n; i++) {
        preSum = h[i];
        for (int j = i + 1; j < n + i + 1; j++) {
            if (preSum < 0) {
                break;
            }
            int index =  j % n  ;
            preSum += h[index];
        }
        if (preSum >= 0) {
            good = i;
        }
    }
    return good;
}
```

### 滑动窗口 时间复杂度 O(N) 空间复杂度 O(N)


oil(i) - dis(i) -> h(i) 纯能职数组

生成h(i) 的累加和数组
纯能职数组[1,-1,0,3,-1]
--> 累加和数组 [1,0,0,3,2]
---> 再累加一次 [1,0,0,3,2,3,2,2,5,4]
然后滑动窗口最小值，减去L-1位置的数，如果<0,则L不是良好出发点


### 时间复杂度 O(N) 空间复杂度 O(1) 的解法

时间O(n) 空间O(1)
1. 复用oil数组
2. int need  接上头需要多少
   int rest 剩余油多少
   int start 联通区开始
   int end 联通区结束下一个位置
   [start, end)
   进入联通区，还没有满足的，联通区里面的点都不满足
   找到良好出发点以后，只要能连上良好出发点的都满足条件（只需要关注need）

## 更多


[算法和数据结构笔记](https://github.com/GreyZeng/algorithm)


## 参考资料


- [程序员代码面试指南（第2版）](https://book.douban.com/subject/30422021/)
- [算法和数据结构基础班-左程云](https://ke.qq.com/course/2145184)
- [极客时间-数据结构与算法之美-王争](https://time.geekbang.org/column/intro/126)
- [算法(第四版)](https://book.douban.com/subject/19952400/)
