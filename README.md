## 对数器

TODO

## >> 和 >>> 区别

`>>` 表示带符号右移
100011000
-> 110001100
最高位和符号位一致

`>>>` 表示不带符号右移
100011000
-> 010001100

不管符号位原来是什么，都用0来补





## 选择排序

arr[0～N-1]范围上，找到最小值所在的位置，然后把最小值交换到0位置。

arr[1～N-1]范围上，找到最小值所在的位置，然后把最小值交换到1位置。

arr[2～N-1]范围上，找到最小值所在的位置，然后把最小值交换到2位置。

…

arr[N-1～N-1]范围上，找到最小值位置，然后把最小值交换到N-1位置。

所以选择排序的时间复杂度为O(N^2)。

Code_0002_SelectionSort.java



## 冒泡排序

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



## 插入排序

想让arr[0~0]上有序，这个范围只有一个数，当然是有序的。

想让arr[0~1]上有序，所以从arr[1]开始往前看，如果arr[1] < arr[0]，就交换。否则什么也不做。

…

想让arr[0~i]上有序，所以从arr[i]开始往前看，arr[i]这个数不停向左移动，一直移动到左边的数字不再比自己大，停止移动。

最后一步，想让arr[0~N-1]上有序， arr[N-1]这个数不停向左移动，一直移动到左边的数字不再比自己大，停止移动。


估算时发现这个算法流程的复杂程度，会因为数据状况的不同而不同。

Code_0001_InsertionSort.java

## 二分

1. 在一个有序数组中，找某个数是否存在
2. 在一个有序数组中，找>=某个数的最左位置
3. 在一个有序数组中，找<=某个数的最右位置
4. 局部最小值问题
arr 无序数组，相邻的数都不相等，返回一个局部最小的位置即可
先看0位置是不是局部最小，
然后再看N-1位置是不是局部最小
然后来中点，然后二分（某个位置比左边小，又比右最小就是那个位置）

二分取中点的安全方式 int M = L + (R - L) >> 1
N * 2 => N << 1
N * 2 + 1 => (N << 1) | 1

Code_0019_BinarySearch.java

## 异或运算就是无进位相加

0^N = N
N^N = 0
异或满足交换律和结合律

```
a = a^b;
b = a^b;
a = a^b;
```

## 怎么把一个int类型的数，提取出最右侧的1来（二进制）

```
i & (~i + 1)
```



## 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数？

Code_0004_EvenOddTimes.java

## 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数

1. 假设出现了奇数次的数字为a和b，我们对数组所有数做异或操作，得到的最后结果一定是：a^b，记这个值为：m = a^b
2. 将m的二进制数的最右侧的1所代表的数找到，假设这个数为n，最右侧1所在的位置为r
3. 那么整个数组中，一定分为两类数：r位置上为1和r位置上为0的数，且a和b一定分别位于这两类数中（a和b不可能在r位置上同时为0或者同时为1）
4. 我们可以通过n和数组中每个数字做与操作，如果为0，说明这个数字中的r位置为0
5. 然后将这些r位置中为0的数字做异或操作，得到最后得结果就是a和b中的一个，假设为a
6. 然后将a和m做异或，得到b

Code_0004_EvenOddTimes.java

一个数字中有多少个1
LeetCode_0191_NumberOfOneBits.java

## 链表反转

- 迭代版本
- 递归版本

整体反转
部分反转
双链表反转
Code_0008_ReverseList
LeetCode_0206_ReverseLinkedList.java
LeetCode_0092_ReverseLinkedListII.java

## 链表中删除给定值的节点

> 为了防止头节点就是要删除的节点，所以一开始要先找到不需要删除的第一个节点 

LeetCode_0203_RemoveLinkedListElements.java


## 双向链表实现栈和队列

Code_0011_DoubleEndsToStackAndQueue.java

## 数组实现栈和队列

Code_0006_ArrayToStackAndQueue.java



## 怎么用数组实现不超过固定大小的队列和栈？

> 用环形数组来实现队列

LeetCode_0622_DesignCircularQueue.java


## 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能

1. pop、push、getMin操作的时间复杂度都是 O(1)。

2. 设计的栈类型可以使用现成的栈结构。

tips: 
方法1. 使用两个栈 空间复杂度O(N)
Code_0012_MinStack
LeetCode_0155_MinStack

方法2. 空间复杂度O(1) 但是需要限定值的范围
Code_0013_MinStackO1


## 队列和栈的互相转换
LeetCode_0232_ImplementQueueUsingStacks.java
LeetCode_0225_ImplementStackUsingQueues.java

## 递归复杂度的估计公式

子问题规模等量的情况下：
master公式  
T(N) = a * T(N/b) + O(N^d)(其中的a、b、d都是常数)

T(N)为父过程的数据规模
T(N / b)为子过程的数据规模
a为子过程的调用次数
O(N ^ b)为除了递归过程之外其他调用的时间复杂度

如果 log(b,a) < d，复杂度为O(N^d)
如果 log(b,a) > d，复杂度为O(N^log(b,a))
如果 log(b,a) == d，复杂度为O(N^d  * logN)

该公式只适用子过程的调用都是数据规模相同的情况，如这个例子中两个子过程的数据规模都是T(N/2)。如果一个递归过程有多个子过程数据规模不一样，那么它不能用该公式进行时间复杂度的计算。



## 归并排序

递归方式

左部分排序

右部分排序

申请一个额外数组，进行merge操作，谁小拷贝谁进新的数组

复杂度估计：


非递归方式
分组进行排序，组的长度从2开始，下一个是4..8..直到N


Code_0022_MergeSort.java

### 小和问题

LeetCode_0315_CountOfSmallerNumbersAfterSelf.java

### 一个数组中所有的降序对

LeetCodeCN_0051_ReversePairs.java
LeetCode_0493_ReversePairs.java


## 随机快排

- Partition
- 荷兰国旗问题：LeetCode_0075_SortColors.java
- 快排实现 Code_0025_QuickSort.java
- 时间复杂度O(N*logN),空间复杂度O(logN)


## 堆  

 1）堆结构就是用数组实现的完全二叉树结构
 2）完全二叉树中如果每棵子树的最大值都在顶部就是大根堆
 3）完全二叉树中如果每棵子树的最小值都在顶部就是小根堆
 4）堆结构的heapInsert与heapify操作
 5）堆结构的增大和减少
 6）优先级队列结构，就是堆结构

数组表示堆
// case 1 用0位置
// 左孩子 2 * i + 1
// 右孩子 2 * i + 2
// 父节点 （i - 1）/ 2
// case 2 不用0位置
// 左孩子 2 * i   i << 1
// 右孩子 2 * i + 1  i << 1 | 1
// 父节点 i / 2 i >> 1

Code_0026_MaxHeap.java
堆排序
Code_0027_HeapSort.java

heapify

LintCode_0130_Heapify.java

Code_0028_DistanceLessK.java

自己手写的堆结构
Code_0029_CustomHeap.java

## 前缀树

p: 某个字符经过几次
e: 以某个字符结尾有几次
头节点开始 

- 所有字符串有多少以某几个字符作为前缀
- 某个字符串是否包含在其中

Code_0030_TrieTree.java
两种实现，如果字符固定， 可以用数组，如果字符不固定Hash表实现


需要注意前缀树的删除节点操作，有可能删除的节点后续的路没用了。
ab
abcvdk
这两个字符串
如果要删掉abcvdk这个字符串，那么通向c v d k节点的路可以全部删除（p=0以下的节点都没有了）

LeetCode_0208_Trie
LeetCode_0139_WordBreak
LeetCode_0140_WordBreakII


## 不基于比较的排序
基于桶排序的两种排序
应用范围有限，需要样本的数据状况满足桶的划分 
- 计数排序
O(N) 词频表

- 基数排序
非负数，且每个数都是十进制的情况下
1. 找到最大值
2. 所有其他的值都补齐最大值的位数
3. 准备十个桶
4. 进桶的原则：按队列来 先根据个位数开始入桶，然后倒出来，然后十位数，继续倒出来....（从右向左）


## 排序总结

		时间复杂度	额外空间复杂度		稳定性
选择排序		O(N^2)			O(1)		无
冒泡排序		O(N^2)			O(1)		有
插入排序		O(N^2)			O(1)		有
归并排序		O(N*logN)			O(N)		有
随机快排		O(N*logN)			O(logN)		无
堆排序		O(N*logN)			O(1)		无
========================================================
计数排序		O(N)			O(M)		有
基数排序		O(N)			O(N)		有


## 链表题

快慢指针
1）输入链表头节点，奇数长度返回中点，偶数长度返回上中点 
2）输入链表头节点，奇数长度返回中点，偶数长度返回下中点 
3）输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个 
4）输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个

暴力方式：转成ArrayList,准确定位下标的方式
优化方式：快慢指针

Code_0033_LinkedListMid.java
LeetCode_0876_MiddleOfTheLinkedList.java



Partition链表

仅做Partition【保持相对秩序】
LeetCode_0086_PartitionList.java
奇偶版[算下标]
LeetCode_0328_OddEvenLinkedList.java
奇数放奇数位置，偶数放偶数位置


荷兰国旗版
Code_0038_PartitionList.java


Copy带Random指针的链表
LeetCode_0138_CopyListWithRandomPointer.java

找到相交节点

无环版本
LeetCode_0160_IntersectionOfTwoLinkedLists.java

无环有环结合版本
Code_0037_FindFirstIntersectNode.java

给定一个单链表的头节点head，请判断该链表是否为回文结构
LeetCode_0234_PalindromeLinkedList.java
简单方式：栈方式 （两种）
改原来链表的方式 记得要调整回来


## 二叉树

前中后序遍历（递归方式，非递归方式）
后序遍历：两个栈实现，一个栈实现
Morris遍历(空间复杂度O(1))
LeetCode_0144_BinaryTreePreorderTraversal
LeetCode_0094_BinaryTreeInorderTraversal
LeetCode_0145_BinaryTreePostorderTraversal

按层遍历  
 1. hash表+LinkedList
 2. 仅用LinkedList
 3. 自定义队列(空间复杂度O(1))
LeetCode_0102_BinaryTreeLevelOrderTraversal.java 
LeetCode_0107_BinaryTreeLevelOrderTraversalII.java
LeetCode_0637_AverageOfLevelsInBinaryTree.java
自定义Queue实现宽度优先遍历 （可以替换LinkedList，省空间）
LeetCode_0116_PopulatingNextRightPointersInEachNode.java 
LeetCode_0117_PopulatingNextRightPointersInEachNodeII.java

求树的最大宽度 
LeetCode_0662_MaximumWidthOfBinaryTree
Code_0039_TreeMaxWidth.java


二叉树的序列化和反序列化 
- 补空节点 
- 中序遍历无法序列化 
LeetCode_0297_SerializeAndDeserializeBinaryTree


重建二叉树 动态规划
LeetCode_0106_ConstructBinaryTreeFromInorderAndPostorderTraversal.java
LeetCode_0105_ConstructBinaryTreeFromPreorderAndInorderTraversal.java

后继节点
【特殊的树结构，包含父节点的指针】
Code_0040_SuccessorNode.java
二叉搜索树中查找后继节点
LintCode_0448_InorderSuccessorInBST.java


### 二叉树递归套路

是否为平衡二叉树
LeetCode_0110_BalancedBinaryTree
是否完全二叉树
LeetCode_0958_CheckCompletenessOfABinaryTree
是否为搜索二叉树
LeetCode_0098_ValidateBinarySearchTree
是否为满二叉树
Code_0046_IsFull.java
二叉树中最大的二叉搜索子树的大小
NowCoder_MaxSubBSTSize
二叉树中最大的二叉搜索子树的头节点
Code_0043_MaxSubBSTHead.java

给定一棵二叉树的头节点head，和另外两个节点a和b, 返回a和b的最低公共祖先
LeetCode_0236_LowestCommonAncestorOfBinaryTree.java
给定一棵二叉搜索树的头节点head,和另外两个节点a和b，返回a和b的最低公共祖先
LeetCode_0235_LowestCommonAncestorOfABinarySearchTree.java



派对最大快乐值问题
NowCoder_MaxHappy.java

二叉树的最大距离
- 边代表距离 LeetCode_0543_DiameterOfBinaryTree
- 节点数代表距离 Code_0042_MaxDistance
边 = 节点 - 1

### Morris遍历
Code_0047_Morris.java
当前是cur
1. cur无左树,cur = cur.right
2. cur有左树,找到左树最右节点mostRight
	a. mostRight的右指针指向null, mostRight.right = cur, cur = cur.right
	b. mostRight的右指针指向当前节点cur，mostRight.right = null, cur = cur.right
3. cur = null 停

Morris遍历实现前中后序遍历,空间 O（1）,时间 O（N） 递归和非递归的方式实现的复杂度是：空间：O（h）O（N）


第一次来到，则打印-> 先序遍历

打印两次的，第二次到达则打印 -> 中序遍历

最小高度（以叶子节点来算）
LeetCode_0111_MinimumDepthOfBinaryTree



是否是搜索二叉树
中序遍历一下，如果数一直递增，说明是搜索二叉树

二叉树的最小高度（二叉树的递归套路）

什么时候用二叉树的递归套路，什么时候用Morris遍历
如果你需要你的左树给你一些信息，右树给你一些信息，然后整合，这个时候就用二叉树的递归套路
如果你用完左数信息后，可以不用再管左树的信息了，那么就可以用Morris遍历

如果需要用Hash表，且题目中固定了数据的范围，这个时候用数组来替代Hash表


## 贪心

1. 分析业务
2. 根据业务逻辑找到不同的贪心策略
3. 对于能举出反例的策略直接跳过，不能举出反例的策略要证明有效性
使用对数器来验证贪心策略的正确性与否


拼接所有的字符串产生字典序最小的字符串
NowCoder_LowestString.java

会议室安排问题
Code_0048_BestArrange.java

放灯问题
NowCoder_Light.java


霍夫曼树应用
分金条问题 NowCoder_SplitGolden.java
LintCode_1872_MinimumCostToConnectSticks.java

项目花费/利润问题
LeetCode_0502_IPO.java


## 并查集
限定数据范围精简版的并查集
NowCoder_UnionFind.java
未限定范围支持泛型的并查集
Code_0049_UnionFind.java
  1) 有若干个样本a、b、c、d…类型假设是V
  2) 在并查集中一开始认为每个样本都在单独的集合里
  3) 用户可以在任何时候调用如下两个方法：
  boolean isSameSet(V x, V y) :查询样本x和样本y是否属于一个集合
  void union(V x, V y) : 把x和y各自所在集合的所有样本合并成一个集合
  4） isSameSet和union方法的代价越低越好
  数据结构设计
  1）每个节点都有一条往上指的指针
  2）节点a往上找到的头节点，叫做a所在集合的代表节点
  3）查询x和y是否属于同一个集合，就是看看找到的代表节点是不是一个
  4）把x和y各自所在集合的所有点合并成一个集合，只需要小集合的代表点挂 在大集合的代表点的下方即可
 
  并查集的优化
  1）节点往上找代表点的过程，把沿途的链变成扁平的
  2）小集合挂在大集合的下面
  3）如果方法调用很频繁，那么单次调用的代价为O(1)，两个方法都如此
 
  并查集的应用 解决两大块区域的合并问题 常用在图等领域中
 
LeetCode_0130_SurroundedRegions.java
LeetCode_0200_NumberOfIslands.java
LintCode_0178_GraphValidTree.java

## 图

邻接矩阵，邻接表
自定义图结构，以后的题目转换成自己熟悉的图结构
DFS 
Code_0051_DFS.java
BFS 
Code_0050_BFS.java
拓扑排序（有向无环图）
DFS方式和BFS方式
LintCode_0127_TopologicalSorting.java

最小生成树，不破坏连通性删掉某些边，使得整体的权重最小
NowCoder_MinimumSpanningTree.java
LeetCode_1489_FindCriticalAndPseudoCriticalEdgesInMinimumSpanningTree.java
K算法 （边权值排序，并查集）（如果无向图会少一侧的情况，按情况补充即可）
 最小生成树算法之Kruskal
  1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
  2）当前的边要么进入最小生成树的集合，要么丢弃
  3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
  4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
  5）考察完所有边之后，最小生成树的集合也得到了


P算法 
  1）可以从任意节点出发来寻找最小生成树
  2）某个点加入到被选取的点中后，解锁这个点出发的所有新的边
  3）在所有解锁的边中选最小的边，然后看看这个边会不会形成环
  4）如果会，不要当前边，继续考察剩下解锁的边中最小的边，重复3）
  5）如果不会，要当前边，将该边的指向点加入到被选取的点中，重复2）
  6）当所有点都被选取，最小生成树就得到了

Dijkstra算法
LeetCode_0743_NetworkDelayTime.java

  1）Dijkstra算法必须指定一个源点,每个边的权值均为非负数,求这个点到其他所有点的最短距离，到不了则为正无穷
  2）生成一个源点到各个点的最小距离表，一开始只有一条记录，即原点到自己的最小距离为0，
  源点到其他所有点的最小距离都为正无穷大
  3）从距离表中拿出没拿过记录里的最小记录，通过这个点发出的边，更新源 点到各个点的最小距离表，不断重复这一步
  4）源点到所有的点记录如果都被拿过一遍，过程停止，最小距离表得到了
给定出发点，出发点到所有点的距离之和最小是多少？
[TODO] Dijkstra算法优化：采用手写改进堆 复习手写堆结构！！！
[TODO]
505. The Maze II
787. Cheapest Flights Within K Stops
Word Ladder
Word Ladder II

## 暴力递归
1. 汉诺塔问题 2^N - 1 递归方法，[TODO]非递归方法
[TODO]2. 逆序一个栈（不申请额外的空间）
[TODO]3. 打印所有子序列
    - 重复
    - 不重复
[TODO]4. 打印字符串的全部排列
   LeetCode_0046_Permutations    
    - 重复
    - 不重复
    

[TODO]
### 从左往右的尝试模型 

规定1和A对应、2和B对应、3和C对应... 那么一个数字字符串比如"111"就可以转化为: "AAA"、"KA"和"AK"
给定一个只有数字字符组成的字符串str，
返回有多少种转化结果
LeetCode_0091_DecodeWays.java

给定两个长度都为N的数组weights和values，
 weights[i]和values[i]分别代表 i号物品的重量和价值。
 给定一个正数bag，表示一个载重bag的袋子，
 你装的物品不能超过这个重量。 返回你能装下最多的价值是多少?
 
### 范围上尝试的模型
arr[L...R] 规模缩小 
给定一个整型数组arr，代表数值不同的纸牌排成一条线，
玩家A和玩家B依次拿走每张纸牌，
规定玩家A先拿，玩家B后拿，
但是每个玩家每次只能拿走最左或最右的纸牌，
玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。

### 多样本位置全对应的尝试模型
一个样本做行，一个样本做列
两个字符串的最长公共子序列

str1做行，str2做列
dp[i][j] -> str1[0...i] str2[0..j] 最长公共子序列大小 -> dp[M][N] 就是我们要求的值
填第一行，第一列

普遍位置
dp[i][j] 
既不以str1的i字符结尾，也不以str2的j字符结尾 dp[i][j] = dp[i-1][j-1]
以str1的i字符结尾，不以str2的j字符结尾   dp[i][j] = dp[i][j-1]
不以str1的i字符结尾，以str2的j字符结尾 dp[i][j] = dp[i-1][j]
既以str1的i字符结尾，也以str2的j字符结尾 dp[i][j] = dp[i-1][j-1] + 1



### 寻找业务限制的尝试模型
洗咖啡杯问题

---
人过河问题，鱼吃人以后，会被其他鱼吃掉
【鱼是奇数的时候，吃，偶数的时候，不能吃】
 
 ABCDE海盗分硬币问题
 超过一半的同意才能通过
 未被通过的会被杀死
 人性本恶
 人性本善（利益受损才投死对方）
 刚好一半
 
 寄信问题（欧拉信封）
 每个人只能寄一封信，只收一封信
 f(5) = 4 * (f(3) + f(4))
 
 f(i) = (i-1)*(f(i-1) + f(i-2))
 f(1) = 0
 f(2) = 1
 f(3) = 2
 
N皇后问题
在N*N的棋盘上要摆N个皇后，
要求任何两个皇后不同行、不同列，
也不在同一条斜线上 给定一个整数n，返回n皇后的摆法有多少种。
n=1，返回1 n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
n=8，返回92
tip: 最右侧的1取出来
每一行放一个，放在一个结构(int[] records)里面(records[i] = j , i行j位置放了一个皇后)，接下来只要规避不同列和不同斜线
如果试到某一步后不能处理，则返回上一层再处理
位运算进行常数项优化！
列限制，左斜线限制，右斜线限制 这三个变量或运算以后，1位置不能放皇后

## 暴力递归到动态规划
所以的暴力过程，都是由于有重复计算（比如斐波那契数列问题）

题目一
假设有排成一行的N个位置，记为1~N，
N 一定大于或等于 2 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
如果机器人来到1位置，那么下一步只能往右来到2位置；
如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
如果机器人来到中间位置，那么下一步可以往左走或者往右走；
规定机器人必须走 K 步，
最终能来到P位置(P也是1~N中的一个)的方法有多少种 给定四个参数 N、M、K、P，返回方法数。

记忆化搜索

背包问题改动态规划

LeetCode_0091_DecodeWays 改动态规划

拿纸牌问题改动态规划

动态规划进一步优化（枚举行为） CoinWays

贴纸问题 无法进一步优化

动态规划的进一步优化主要是为了省略枚举行为
0）观察临近格子法
1）空间压缩
2）状态化简
3）四边形不等式


## 完美洗牌问题

给定一个长度为偶数的数组arr，长度记为2*N。
前N个为左部分，后N个为右部分。 arr就可以表示为{L1,L2,..,Ln,R1,R2,..,Rn}， 请将数组调整成{R1,L1,R2,L2,..,Rn,Ln}的样子。

- 一个结论：当arr长度S = 3^k - 1 (偶数)的时候，环的出发点1,3,9...3^(k-1)

前提：
 1) 数组从1开始计算
 2) 数组长度偶数


Code_0017_Shuffle.java

## 单调栈

 左边右边离它最近比它小的数 O（N）

 - 数组中有重复 Stack<List<Integer>>
 - 数组中无重复 Stack<Integer>

 栈底到栈顶从小到大
 弹出的时候，假设弹出的值是A，那么让它弹出的值就是它右边离它最近的最小值
 原先A压的是谁，那么谁就是A左边离它最近的最小值

Code_0018_MonoStack.java


## Manacher算法

用来解决回文问题，求一个字符串最长回文子串是什么 O(N)

暴力解法：
- 字符串每个字符之间用一个特殊字符插入，每个元素为中心，左边右边扩，直到扩不动为止
- 大小为：位置/2 = 代表原始串中的大小
- 复杂度：O（N^2）

基本概念：
- 回文半径
- 回文直径
- 回文区域
- 回文半径数组PARR[] ，每个位置得到的答案都放入PARR[]
- 回文最右边界 （int R) ，中心(int C) ---> C就是扩到R位置的的中心点

i当前位置，如果

    1）i在R外，同暴力方法
    
    2）i在R内或者和R同位置

 假设i'为i关于C对称的点

  i' 自己的回文区域都在L。。R内，所以i的答案和i'的答案一样，存入parr中

  i' 自己的回文区域在L。。。R外，i到R的距离就是i的回文半径

  i' 自己的回文区域左边界和L压线，需要继续验，R外的情况

LeetCode_0005_LongestPalindromicSubstring.java

LeetCode_0647_PalindromicSubstrings.java

LeetCode_0214_ShortestPalindrome.java



## 最长递增子序列问题的O(N*logN)解法

- 这里的递增指的是严格递增（相等都不算）
- 暴力解是 O(N^2)

经典解法：
- ends数组，ends[i] 找到的所有长度为i+1的递增子序列中最小结尾是什么
- dp[i]数组, 必须以i结尾的，最长递增子序列有多长

Code_0021_LIS.java
LeetCode_0300_LongestIncreasingSubsequence.java
LeetCode_0334_IncreasingTripletSubsequence.java
LeetCode_0354_RussianDollEnvelopes.java


