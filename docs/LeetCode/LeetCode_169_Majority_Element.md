# LeetCode 169. Majority Element

# LeetCode 169. Majority Element


## 题目描述


[题目链接](https://leetcode.com/problems/majority-element/)


### 思路1 Hash表
很直接，代码略
由于题目的follow-up要求空间复杂度O(1)，所以，这个方法其实并不是最优解。


### 思路2 一次删除两个不同的数
一次删除两个不同的数，如果存在majority element，那么这个majority element一定会最后剩下来，但是，不能说一次删除两个不同的数，最后剩下了来的数就是majority element，比如{1，2，3，4，5} 这个数组，最后剩下5，5不是要求的值。所以，在执行完每次删除两个不同的数这个操作后，最后留下的数，还要过一遍数组，看下这个数是不是超过了半数，如果超过半数，才算是最后结果。代码见：
```java
    public static int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        
        int cand = nums[0];
        int HP = 1;
        int i = 1;
        int limit = nums.length >> 1;
        while (i < nums.length) {
            if (cand == nums[i]) {
                HP++;
                // 如果某时，HP已经大于一半的数了，直接返回
                if (HP > limit) {
                    return nums[i];
                }
            } else {
                if (HP == 0) {
                    cand = nums[i];
                    HP++;
                } else {
                    HP--;
                }
            }
            i++;
        }
        if (HP == 0) {
            return -1;
        }
        int c = 0;
        for (int num : nums) {
            if (cand == num) {
                c++;
            }
        }
        if (c > limit) {
            return cand;
        } else {
            return -1;
        }
    }
```


说明：
其中HP标识候选数出现的次数，我们可以假设侯选数一开始就是num[0]，只要遍历到的数和候选数一致，HP++，不一致，直接HP归零，侯选数置为当前数，这样就实现了一次删除两个数的目的。
此外，这里有一个小的贪心，就是当HP已经到达数组一半以上的大小时候，直接返回当前的候选数（因为HP就表示侯选数出现的次数）


## 更多


[算法和数据结构笔记](https://github.com/GreyZeng/algorithm)


## 参考资料


- [程序员代码面试指南（第2版）](https://book.douban.com/subject/30422021/)
- [算法和数据结构基础班-左程云](https://ke.qq.com/course/2145184)
- [极客时间-数据结构与算法之美-王争](https://time.geekbang.org/column/intro/126)
- [算法(第四版)](https://book.douban.com/subject/19952400/)
