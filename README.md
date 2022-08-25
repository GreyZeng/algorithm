# 算法与数据结构学习笔记

[源仓库：Github](https://github.com/GreyZeng/algorithm)

[镜像仓库：GitCode](https://gitcode.net/hotonyhui/algorithm)

## 二进制、位运算

笔记：[异或运算相关问题：找到数组中出现特定次数数字的问题](https://www.cnblogs.com/greyzeng/p/15385402.html)

| 题目                                                         | 代码                                                         | 备注                                 |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------ |
| 打印一个32位整数的二进制形式                                 | Code_0000_PrintBinary.java                                   |                                      |
| 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数? | NowCoder_EvenOddTimes.java<br/>LeetCode_0136_SingleNumber.java |                                      |
| 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数 | LeetCode_0260_SingleNumberIII                                |                                      |
| 一个整数的二进制中有几个 1                                   | LeetCode_0191_NumberOfOneBits.java                           | 涉及提取最右侧的 1：`x & ((~x) + 1)` |
| 一个数组中有一种数出现`k`次，其他数都出现了`m`次，`m > 1, k < m`, 找到出现了`k`次的数 | LeetCode_0137_SingleNumberII.java<br/>NowCoder_FindOneInK.java |                                      |

## 简单排序

笔记：[简单排序（冒泡排序，插入排序，选择排序）](https://www.cnblogs.com/greyzeng/p/15186769.html)

代码：  Code_0001_Sort.java

## 随机函数

笔记：  [随机函数变换示例](https://www.cnblogs.com/greyzeng/p/16618329.html)

| 题目                                        | 代码                                         | 备注 |
| ------------------------------------------- | -------------------------------------------- | ---- |
| 0 1 不等概率随机函数变成 0 1 等概率随机函数 | Code_0003_EqualProbabilityRandom.java        |      |
| `[0,x)`的概率调整为`x^2`                    | Code_0004_RandToPow2.java                    |      |
| 如何用`1~5`的随机函数加工出`1~7`的随机函数  | Code_0005_Rand5ToRand7.java                  |      |
| 如何用`a~b`的随机函数加工出`c~d`的随机函数  | LeetCode_0470_ImplementRand10UsingRand7.java |      |

## 二分

[二分法基本思路和实现](https://www.cnblogs.com/greyzeng/p/16622554.html)

[使用二分法来解决的问题](https://www.cnblogs.com/greyzeng/p/15690136.html)

| 题目                                           | 代码                                                         | 备注                                                         |
| ---------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 二分查找                                       | LeetCode_0704_BinarySearch.java                              |                                                              |
| 在一个有序数组中，找大于等于某个数最左侧的位置 | LeetCode_0035_SearchInsertPosition.java                      |                                                              |
| 在排序数组中查找元素的第一个和最后一个位置     | LeetCode_0034_FindFirstAndLastPositionOfElementInSortedArray.java |                                                              |
| 局部最大值问题                                 | LeetCode_0162_FindPeakElement.java                           |                                                              |
| 分割数组的最大值                               | LeetCode_0410_SplitArrayLargestSum.java                      | 给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。 |
| 判断一个数是否是Step Sum                       | Code_0111_IsStepSum.java                                     | 比如680，680 + 68 + 6 = 754，所以680的Step Sum是754，给定一个正数num，判断它是不是某个数的Step Sum。 |

[基础数据结构](docs/基础数据结构.md)

[归并排序](docs/归并排序.md)

[递归与快速排序](docs/递归与快速排序.md)

[堆和加强堆](docs/堆和加强堆.md)

[前缀树](docs/前缀树.md)

[基于桶的排序和排序总结](docs/基于桶的排序和排序总结.md)

[二叉树](docs/二叉树.md)

[贪心](docs/贪心.md)

[并查集](https://www.cnblogs.com/greyzeng/p/16340125.html)

[图](docs/图.md)

[动态规划](docs/动态规划.md)

[滑动窗口和双指针](docs/滑动窗口和双指针.md)

[单调栈](docs/单调栈.md)

[斐波那契数列问题和扩展](https://www.cnblogs.com/greyzeng/p/15388178.html)

[KMP算法](https://www.cnblogs.com/greyzeng/p/15317466.html)

[Manacher算法](https://www.cnblogs.com/greyzeng/p/15314213.html)

[bfprt算法](https://www.cnblogs.com/greyzeng/p/15320118.html)

[蓄水池算法](https://www.cnblogs.com/greyzeng/p/15311295.html)

[线段树](docs/线段树.md)

[树状数组](https://www.cnblogs.com/greyzeng/p/15343780.html)

[AC自动机](https://www.cnblogs.com/greyzeng/p/15347534.html)

[哈希表,布隆过滤器和一致性哈希](docs/哈希表,布隆过滤器和一致性哈希.md)

[资源限制类问题的常用解决方法](https://www.cnblogs.com/greyzeng/p/15371414.html)

[有序表和跳表](docs/有序表和跳表.md)

[打表法找规律以及根据数据量猜解法](docs/打表法找规律以及根据数据量猜解法.md)

[卡特兰数](docs/卡特兰数.md)

[数组三连](docs/数组三连.md)

[矩阵处理技巧](docs/矩阵处理技巧.md)

[四边形不等式优化技巧](docs/四边形不等式优化技巧.md)

[状态压缩的动态规划问题](docs/状态压缩的动态规划问题.md)

[DC3算法](docs/DC3算法.md)

[哈夫曼树](docs/哈夫曼树.md)

[动态规划猜法中和外部信息简化的相关问题](docs/动态规划猜法中和外部信息简化的相关问题.md)

[dinic算法](docs/dinic算法.md)

[已整理笔记](https://www.cnblogs.com/greyzeng/tag/%E7%AE%97%E6%B3%95/)

[待整理笔记](https://github.com/GreyZeng/algorithm/tree/master/docs)

## 题目清单

| 题目                         | 代码                                      | 备注 |
| ---------------------------- | ----------------------------------------- | ---- |
| 计算`1!+2!+3!+4!+…+N!`的结果 | Code_0002_SumOfFactorial.java             |      |
| 前缀和加速求区间和           | LeetCode_0303_RangeSumQueryImmutable.java |      |

