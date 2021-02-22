# 牛客 汉诺塔II

## 题目描述


[题目链接](https://www.nowcoder.com/questionTerminal/b2d552cd60b7415fad2612a32e799812)


### 思路


对于三层汉诺塔问题，流程是：

第一大步：0-N-1 号圆盘 从from位置到other位置，给第N号圆盘腾出位置

第二大步：N号圆盘从from位置到to位置

第三大步：0-N-1 号圆盘从other位置到to位置 


所以：

N位置没有必要去other位置！所以，如果arr[N] = other 直接返回-1

定义递归函数：

```
int f(arr, N, from, to, other) 
```

其中

N 表示0...N个圆盘 

from 从哪个位置

to 到哪个位置

other 其他位置

返回值，这个是最优解的第几步

比如：

f(arr, 8,1,3,2): arr中表示9个圆盘目前的状态，从左边移动到右边，是最优解的第几步


所以：

如果某一步 arr[i] = other 直接返回-1

如果arr[i] = from 说明 第一大步没走完，

调用 f(arr, i-1, from ,other, to)即可得到这是第几步。

如果arr[i] = to 说明在第三大步，

因为N层汉诺塔问题的步数是 : 2^N - 1

第一大步走了 2^(i-1) - 1

第二大步走了 1步

所以：

第一大步和第二大步一共走了 ：2^(i-1)

剩下第三大步走了 2^(i-1) + f(arr, i-1, other, to, from)


完整代码

```java
// O(N)
public static int chkStep(int[] arr, int N) {
    return f(arr, N - 1, 1, 3, 2);
}
// n 表示0...n个圆盘 n+1
// from 从哪个位置
// to 到哪个位置
// other 其他位置
// 返回，这个是最优解的第几步
// eg: f(arr, 8,1,3,2): arr中表示9个圆盘目前的状态，从左边移动到右边，是最优解的第几步

// 三层汉诺塔问题
// 0~i-1 from -> other
// i from -> to
// 0~i-1 other -> to
// 所以i位置没有必要去other位置
// 如果某一步 arr[i] = other 直接返回-1
// 如果arr[i] = from 说明 第一步没走完
// 调用 f(arr, i-1, from ,other, to)
// 如果arr[i] = to 说明在第三步
// 因为N层汉诺塔问题的步数是 2^N - 1
// 第一步走了 2^(i-1) - 1
// 第二步走了 1步
// 所以第一步+ 第二步 = 2^(i-1)
// 剩下第三步走了 2^(i-1) + f(arr, i-1,other,to,from)
public static int f(int[] arr, int N, int from, int to, int other) {
    if (N == -1) {
        return 0;
    }
    if (arr[N] == other) {
        return -1;
    }
    if (arr[N] == from) {
        return f(arr, N - 1, from, other, to);
    } else {
        int rest = f(arr, N - 1, other, to, from);
        if (rest == -1) {
            return -1;
        }
        return (1 << N) + rest;
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
