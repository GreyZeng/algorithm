## 排序

### 选择排序

```
arr[0～N-1]范围上，找到最小值所在的位置，然后把最小值交换到0位置。

arr[1～N-1]范围上，找到最小值所在的位置，然后把最小值交换到1位置。

arr[2～N-1]范围上，找到最小值所在的位置，然后把最小值交换到2位置。

…

arr[N-1～N-1]范围上，找到最小值位置，然后把最小值交换到N-1位置。

所以选择排序的时间复杂度为O(N^2)。

Code_0002_SelectionSort.java

单链表的选择排序
LeetCode_0148_SortList.java
```

### 冒泡排序

```
在arr[0～N-1]范围上：

arr[0]和arr[1]，谁大谁来到1位置；

arr[1]和arr[2]，谁大谁来到2位置

…

arr[N-2]和arr[N-1]，谁大谁来到N-1位置

在arr[0～N-2]范围上，重复上面的过程，但最后一步是arr[N-3]和arr[N-2]
，谁大谁来到N-2位置

在arr[0～N-3]范围上，重复上面的过程，但最后一步是arr[N-4]和arr[N-3]，谁大谁来到N-3位置

…

最后在arr[0～1]范围上，重复上面的过程，但最后一步是arr[0]和arr[1]，谁大谁来到1位置

Code_0003_BubbleSort.java
```

### 插入排序


```
想让arr[0~0]上有序，这个范围只有一个数，当然是有序的。

想让arr[0~1]上有序，所以从arr[1]开始往前看，如果arr[1] < arr[0]，就交换。否则什么也不做。

…

想让arr[0~i]上有序，所以从arr[i]开始往前看，arr[i]这个数不停向左移动，一直移动到左边的数字不再比自己大，停止移动。

最后一步，想让arr[0~N-1]上有序， arr[N-1]这个数不停向左移动，一直移动到左边的数字不再比自己大，停止移动。


估算时发现这个算法流程的复杂程度，会因为数据状况的不同而不同。

Code_0001_InsertionSort.java
```

## 二分

### 在一个有序数组中，找某个数是否存在 
```
LeetCode_0704_BinarySearch.java
```
### 在一个有序数组中，找>=某个数最左侧的位置 
```
Code_0019_BinarySearch.java
```
### 在一个有序数组中，找<=某个数最右侧的位置 
```
Code_0019_BinarySearch.java
```
### 相关练习
```
LeetCode_0035_SearchInsertPosition.java
```
### 局部最大值问题 
```
LeetCode_0162_FindPeakElement.java
```

## 异或运算

### 不用额外变量交换两个值

```
a = a^b;
b = a^b;
a = a^b;
```


### 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数 
```
NowCoder_EvenOddTimes.java

LeetCode_0136_SingleNumber.java
```
### 怎么把一个int类型的数，提取出最右侧的1来

```
i & ((~i) + 1)
```

### 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
```
NowCoder_EvenOddTimesPlus.java
LeetCode_0260_SingleNumberIII.java
```
### 一个数字中有多少个1
```
LeetCode_0191_NumberOfOneBits.java
```
### 一个数组中有一种数出现K次，其他数都出现了M次，M > 1,  K < M, 找到出现了K次的数，要求，额外空间复杂度O(1)，时间复杂度O(N)
```
LeetCode_0137_SingleNumberII.java
```

## 单链表和双向链表

### 单链表和双链表如何反转(递归/迭代版本)

```
Code_0008_ReverseList.java
LeetCode_0206_ReverseLinkedList.java
LeetCode_0092_ReverseLinkedListII.java
LintCode_0035_ReverseLinkedList.java
```

### 链表中删除给定值的节点

```
为了防止头节点就是要删除的节点，所以一开始要先找到不需要删除的第一个节点

LeetCode_0203_RemoveLinkedListElements.java
LintCode_0452_RemoveLinkedListElements.java
```

## 队列和栈

### 双向链表实现栈和队列
```
Code_0011_DoubleEndsToStackAndQueue.java
```
### 数组实现栈和队列
```
Code_0006_ArrayToStackAndQueue.java
```
### 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能

**pop、push、getMin操作的时间复杂度都是 O(1)**

方法1. 使用两个栈 空间复杂度O(N)
```
LintCode_0012_MinStack.java
LeetCode_0155_MinStack.java
```

方法2. 空间复杂度O(1) 但是需要限定值的范围

```
Code_0013_MinStackO1
```

### 怎么用数组实现不超过固定大小的队列和栈？

```
LeetCode_0622_DesignCircularQueue.java
```

### 队列和栈的互相转换

```
LeetCode_0232_ImplementQueueUsingStacks.java
LeetCode_0225_ImplementStackUsingQueues.java
LintCode_0494_ImplementStackByTwoQueues.java
LintCode_0040_ImplementQueueByTwoStacks.java
```
**使用队列实现栈的三个原则**

pop stack + push stack

- 一次性导完

- 只有pop stack空了才能导数据

- pop stack不为空不用导数据


## 递归

### master公式

```
子问题规模等量的情况下：
master公式  
T(N) = a * T(N/b) + O(N^d)(其中的a、b、d都是常数)

T(N)为父过程的数据规模
T(N / b)为子过程的数据规模
a为子过程的调用次数
O(N ^ d)为除了递归过程之外其他调用的时间复杂度

如果 log(b,a) < d，复杂度为O(N^d)
如果 log(b,a) > d，复杂度为O(N^log(b,a))
如果 log(b,a) == d，复杂度为O(N^d  * logN)

该公式只适用子过程的调用都是数据规模相同的情况，如这个例子中两个子过程的数据规模都是T(N/2)。如果一个递归过程有多个子过程数据规模不一样，那么它不能用该公式进行时间复杂度的计算。

```

## 归并排序

递归方式: 左部分排序 + 右部分排序 + merge

申请一个额外数组，进行merge操作，谁小拷贝谁进新的数组

复杂度估计, 使用master公式 O(N*logN)


非递归方式
分组进行排序，组的长度从2开始，下一个是4..8..直到N

```
Code_0022_MergeSort.java
LintCode_0006_MergeTwoSortedArrays.java
LintCode_0064_MergeTwoSortedArrays.java
LeetCode_0088_MergeSortedArray.java
NowCoder_MergeSort.java
```

小和问题

LeetCode_0315_CountOfSmallerNumbersAfterSelf.java

一个数组中所有的降序对

LeetCodeCN_0051_ReversePairs.java
LeetCode_0493_ReversePairs.java

```
在一个数组中，
对于每个数num，求有多少个后面的数 * 2 依然<num，求总个数
比如：[3,1,7,0,2]
3的后面有：1，0
1的后面有：0
7的后面有：0，2
0的后面没有
2的后面没有
所以总共有5个




给定一个数组arr，两个整数lower和upper，

返回arr中有多少个子数组的累加和在[lower,upper]范围上
LeetCode_0327_CountOfRangeSum

方法1：归并排序
1. 前缀和加速求区间和
2. 必须以i结尾的达标子数组有多少个

方法2：有序表方式

```


## 随机快排

- Partition
- 荷兰国旗问题：LeetCode_0075_SortColors.java
- 快排实现 Code_0025_QuickSort.java 非递归版本
- 时间复杂度O(N*logN),空间复杂度O(logN)

找第K大/小的数
- 堆解法
- 快排解法
- bfprt算法

LeetCode_0215_KthLargestElementInAnArray.java
NowCoder_0088_FindKth.java

## 堆

[堆](堆.md)