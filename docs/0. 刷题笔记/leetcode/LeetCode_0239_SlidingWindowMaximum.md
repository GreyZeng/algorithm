## 题目描述

[题目链接](https://leetcode.com/problems/sliding-window-maximum/)

### 思路

数组为num, 滑动窗口大小是k，首先两个极端情况：

1. 如果k=1，则直接返回原数组即可（滑动窗口大小为1，那么每个窗口的最小值/最大值就是其本身）

2. 如果k == num.length, 那么返回一个数组大小为1的数组，这个数组里面的唯一的数就是整个数组的最大值

然后讨论普遍情况： 1 < k < num.length

如果窗口大小为k，数组长度为n，那么滑动窗口最大值数组的长度通过观察可知, 结果数组的长度为：

```
n - k + 1
```

接着，我们定义一个双端队列，这个双端队列存的是数组下标，且下标对应的原元素头到尾部数据是从大到小的

我们定义一个r变量来遍历数组， 整个流程分为如下三步：

1. nums[r] 的数据和双端队列尾部下标(假设是inx)对应的数据元素数据,即:nums[inx], 进行比较，如果nums[inx]小于nums[r] 则，双端队列把尾部的inx值弹出, 继续比较双端队列尾部的对应的数组值和nums[r]的大小，直到num[r]<num[双端队列尾部存的下标值]。

2. 当双端队列头部的元素==r-k时候，说明双端队列头部的元素需要被淘汰了，双端队列中头部的值已经过期下标了，需要弹出这个过期下标 比如：窗口k为3
    - r来到3位置，那么过期下标就是0
    - r来到4位置，那么过期下标就是1
    - r来到5位置，那么过期下标就是2
    - r来到6位置，那么过期下标就是3
    - r来到7位置，那么过期下标就是4 ...

3. 当r>=k-1的时候，说明窗口已经形成了，此时，每次进入一个数，就要收集一次答案。

完整代码

```java
public static int[] maxSlidingWindow(int[] nums, int k) {
  int n = nums.length;
  if (k == 1) {
      return nums;
  }
  if (k == n) {
      return new int[]{maxOfArr(nums, n)};
  }
  // 数组长度是n，窗口是k，则结果数组长度为n - k + 1
  int[] ans = new int[n - k + 1];
  // 头部进，尾部出
  // 从头到尾依次从大到小
  LinkedList<Integer> qMax = new LinkedList<>();
  int r = 0;
  int index = 0;
  while (r < n) {
      while (!qMax.isEmpty() && nums[qMax.peekLast()] <= nums[r]) {
          // 给nums[r] 腾出位置
          qMax.pollLast();
      }
      // 现在qMax尾部的值一定大于r
      // 所以可以放心的把r加入到尾部中去
      qMax.addLast(r);
      // 如果此时双端队列中头部的值是过期下标
      // 比如r来到3位置，窗口k为3，那么过期下标就是0
      // 比如r来到4位置，窗口k为3，那么过期下标就是1
      // 比如r来到5位置，窗口k为3，那么过期下标就是2
      // 比如r来到6位置，窗口k为3，那么过期下标就是3
      // 比如r来到7位置，窗口k为3，那么过期下标就是4
      // ...
      if (qMax.peekFirst() == r - k) {
          // 弹出过期下标
          qMax.pollFirst();
      }

      // 窗口形成了，每次加入一个数收集一个答案
      if (r >= k - 1) {
          ans[index++] = nums[qMax.peekFirst()];
      }
      r++;
  }
  return ans;
}

private static int maxOfArr(int[] nums, int n) {
  int max = nums[0];
  for (int i = 1; i < n; i++) {
      max = Math.max(max, nums[i]);
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
