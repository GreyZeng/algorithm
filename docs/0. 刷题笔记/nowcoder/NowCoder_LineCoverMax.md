# 牛客_线段重合问题

## 题目描述

[题目链接](https://www.nowcoder.com/questionTerminal/f8fa4b67dd054892966d280790c42ba3)

### 暴力解

1. 首先得到线段的最大值和最小值
2. 最大值和最小值按单位1等分，看每条线覆盖了多少，抓一下全局max
时间复杂度 O((max-min)*N)：

### 堆解法 O(N*logN)

准备小根堆（之所以设置为堆，是因为要处理重复值，如果有序表的话，就无法接受重复值）
遍历每一个线段,得到其开始位置L和结束位置R，规则是：
0. 每个线段按开始位置L从小到大排序。
1. 如果堆为空，线段结束位置R进入堆。
2. 堆不为空，则遍历到线段L位置和堆顶元素(假设为M)比较，如果堆顶元素小于L，则结算一次堆中元素大小，记为size，然后弹出堆顶元素，直到堆顶元素小于L，然后把L加入
3. 每次生成的size取最大值即为最大重合了几个线段

## 关键代码

### 暴力解

```java
public static int maxCover3(int[][] lines) {
        int min = lines[0][0];
        int max = lines[0][1];
        for (int[] line : lines) {
            min = Math.min(min, Math.min(line[0], line[1]));
            max = Math.max(max, Math.max(line[0], line[1]));
        }
        int cover = 0;
        int maxCover = 0;
        for (int i = min; i <= max; i++) {
            for (int[] line : lines) {
                // 这里要注意 如果[1,2] ,[2, 3] 中2 算一个重合点的话，
                // 则条件为：line[0] <= i && line[1] >= i
                // 如果不算的话，line[0] <= i+0.5 && line[1] >= i + 0.5
                if (line[0] <= i && line[1] >= i) {
                    cover++;
                }
            }
            maxCover = Math.max(cover, maxCover);
            cover = 0;
        }
        return maxCover;
    }
```

### 堆解法

```java
public static int maxCover(int[][] m) {
        Line[] lines = new Line[m.length];
        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        // 按开始位置排序
        Arrays.sort(lines, new StartComparator());
        PriorityQueue<Line> heap = new PriorityQueue<>(new EndComparator());
        int max = 0;
        for (Line line : lines) {
            // 这里要注意 如果[1,2] ,[2, 3] 中2 算一个重合点的话，heap.peek().end < line.start，如果不算的话，heap.peek().end <= line.start
            while (!heap.isEmpty() && heap.peek().end < line.start) {
                heap.poll();
            }
            heap.add(line);
            max = Math.max(max, heap.size());
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
