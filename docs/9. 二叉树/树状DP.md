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


二叉树的序列化和反序列化 
- 补空节点 
- 中序遍历无法序列化 比如：
```
1
  2

和

2
  1
```
LeetCode_0297_SerializeAndDeserializeBinaryTree

LeetCode 431 Encode N-ary Tree to Binary Tree
tips: 每个子节点转换成自己左树的右边界 + 深度优先遍历

打印二叉树

求树的最大宽度 
LeetCode_0662_MaximumWidthOfBinaryTree
Code_0039_TreeMaxWidth.java




重建二叉树 动态规划
LeetCode_0106_ConstructBinaryTreeFromInorderAndPostorderTraversal.java
LeetCode_0105_ConstructBinaryTreeFromPreorderAndInorderTraversal.java

后继节点
【特殊的树结构，包含父节点的指针】
Code_0040_SuccessorNode.java
二叉搜索树中查找后继节点
LintCode_0448_InorderSuccessorInBST.java

折痕问题：

Code_0041_PaperFolding

### 二叉树递归套路 O(N)

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








