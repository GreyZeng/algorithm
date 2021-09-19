## 题目描述

[题目链接](https://leetcode.com/problems/minimum-cost-to-merge-stones/)

## 思路


首先，K和石子数组的长度有关系，通过观察可知，假设石子的长度是n，如果


```
(n - 1) % (K - 1) > 0
```


则无法最后合并成一个石子


定义递归函数


```java
f(L, R, part, K)
```


L..R范围上通过每次合并K个数，一定要合成出part个部分，最小代价是多少


所以主函数调用的时候


```java
f(0, n - 1, 1, K)
```


0到n-1范围上，通过每次合并K个数，一定要合成一部分，最小代价是多少


base case是, L...R范围内只剩下一个数了，那么如果part为1，则返回最小代价是0（无须合并），如果part非1，则无法合并(因为只有一个数了),返回-1


```java
if (L == R) {
   return part == 1 ? 0 : -1;
}
```


如果L...R范围内不止一个数，那么就看part的取值，


假如part等于1, 说明需要合并成一个数，那么最后一次合并必然是分成了K部分（因为分成K部分，才能在下一次的合并过程中合成一个数）


```java
int pre = f(arr, L, R, K, K);
if (pre == -1) {
    return -1;
}
return pre + L..R 累加和;
```


其中的L到R的累加和, 我们先放一边，再看下面一种情况：

假如part的值不等于1，则需要考虑将L...R分两部分来考虑，一部分生成part = 1的最小代价cost1，另外一部分生成part = part - 1的最小代价cost2,

则cost1 + cost2 就是最小代价，代码如下：


```java
int ans = Integer.MAX_VALUE;
for (int i = L; i < R; i += (K - 1)) {
    int cost1 = f(arr, L, i, K, 1);
    int cost2 = f(arr, i + 1, R, K, part - 1);
    if (cost1 != -1 && cost2 != -1) {
        ans = Math.min(ans, cost2 + cost1);
    }
}
return ans;
```


接下来，我们解决前面提到的问题：快速得到L...R的累加和，我们可以通过前缀和数组来加速L..R的累加和计算, 生成前缀和数组的方式如下


```java
// 前缀和用来加速求L..R范围内的累加和
int[] preSum = new int[n];
preSum[0] = stones[0];
for (int i = 1; i < n; i++) {
    preSum[i] = preSum[i - 1] + stones[i];
}
```


这样我们就可以很方便以O(1)求出L...R的累加和


```java
L..R累加和 = preSum[R] - (L == 0?0:preSum[L-1])
```


所以，暴力递归的代码如下：


```java
// 暴力解法
public static int mergeStones(int[] stones, int K) {
    // k和数组长度先做一次过滤
    int n = stones.length;
    if ((n - 1) % (K - 1) > 0) {
        return -1;
    }
    // 前缀和用来加速求L..R范围内的累加和
    int[] preSum = new int[n];
    preSum[0] = stones[0];
    for (int i = 1; i < n; i++) {
        preSum[i] = preSum[i - 1] + stones[i];
    }
    return f(stones, 0, n - 1, K, 1, preSum);
}

// f(L,R,part) -> L..R范围上一定要合成出part个数，最小代价是多少
public static int f(int[] arr, int L, int R, int K, int part, int[] preSum) {
    if (L == R) {
        return part == 1 ? 0 : -1;
    }
    if (part == 1) {
        // part只有1块的时候
        // 需要算出当part是K份的时候，最小代价
        int pre = f(arr, L, R, K, K, preSum);
        if (pre == -1) {
            return -1;
        }
        return pre + preSum[R] - (L == 0 ? 0 : preSum[L - 1]);
    }
    // part不止一块
    // 则可以让 L..i 得到1块
    // i+1...R得到part-1块
    // 然后合并即可
    int ans = Integer.MAX_VALUE;
    for (int i = L; i < R; i += (K - 1)) {
        int cost1 = f(arr, L, i, K, 1, preSum);
        int cost2 = f(arr, i + 1, R, K, part - 1, preSum);
        if (cost1 != -1 && cost2 != -1) {
            ans = Math.min(ans, cost2 + cost1);
        }
    }
    return ans;
}
```


这个解法在LeetCode上超时，我们可以增加记忆化搜索来优化，如暴力尝试中提到的，有三个可变参数：L,R,part, 其中：


L的变化范围是：0...n-1


R的变化范围是：0...n-1


part的变化范围是：1...K


我们可以定义一个三维数组来存递归结果


```java
int[][][] dp = new int[n][n][K+1]
```


只需要在每次暴力递归的时候，用这个数组存下当时的记录即可, 优化后的代码如下：


```java
public static int mergeStones(int[] stones, int K) {
    // k和数组长度先做一次过滤
    int n = stones.length;
    if ((n - 1) % (K - 1) > 0) {
        return -1;
    }
    // 前缀和用来加速求L..R范围内的累加和
    int[] preSum = new int[n];
    preSum[0] = stones[0];
    for (int i = 1; i < n; i++) {
        preSum[i] = preSum[i - 1] + stones[i];
    }
    int[][][] dp = new int[n][n][K + 1];
    return f2(stones, 0, n - 1, K, 1, preSum, dp);
}

// f(L,R,part) -> L..R范围上一定要合成出part个数，最小代价是多少
public static int f2(int[] arr, int L, int R, int K, int part, int[] preSum, int[][][] dp) {
    if (dp[L][R][part] != 0) {
        return dp[L][R][part];
    }
    if (L == R) {
        dp[L][R][part] = (part == 1 ? 0 : -1);
        return dp[L][R][part];
    }
    if (part == 1) {
        // part只有1块的时候
        // 需要算出当part是K份的时候，最小代价
        int pre = f2(arr, L, R, K, K, preSum, dp);
        if (pre == -1) {
            dp[L][R][part] = -1;
            return -1;
        }
        dp[L][R][part] = pre + preSum[R] - (L == 0 ? 0 : preSum[L - 1]);
        return dp[L][R][part];
    }
    // part不止一块
    // 则可以让 L..i 得到1块
    // i+1...R得到part-1块
    // 然后合并即可
    int ans = Integer.MAX_VALUE;
    for (int i = L; i < R; i += (K - 1)) {
        int left = f2(arr, L, i, K, 1, preSum, dp);
        int right = f2(arr, i + 1, R, K, part - 1, preSum, dp);
        if (left != -1 && right != -1) {
            ans = Math.min(ans, right + left);
        } else {
            dp[L][R][part] = -1;
        }
    }
    dp[L][R][part] = ans;
    return ans;
}
```


![微信截图_20210222001100.png](https://cdn.nlark.com/yuque/0/2021/png/757806/1613923956439-ff39a73d-139d-4219-80e2-d5d276ff63e1.png#align=left&display=inline&height=467&margin=%5Bobject%20Object%5D&name=%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20210222001100.png&originHeight=467&originWidth=614&size=23650&status=done&style=none&width=614)

## 更多


[算法和数据结构笔记](https://github.com/GreyZeng/algorithm)


## 参考资料


- [程序员代码面试指南（第2版）](https://book.douban.com/subject/30422021/)
- [算法和数据结构基础班-左程云](https://ke.qq.com/course/2145184)
- [极客时间-数据结构与算法之美-王争](https://time.geekbang.org/column/intro/126)
- [算法(第四版)](https://book.douban.com/subject/19952400/)
