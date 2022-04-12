# Morris遍历

[笔记](https://www.cnblogs.com/greyzeng/articles/15941957.html)

## 代码

Code_0047_Morris.java

## 相关习题

### 是否是搜索二叉树

中序遍历一下，如果数一直递增，说明是搜索二叉树，第二次回到自己的时候，恢复指针后，看下是否是叶子节点

LeetCode_0098_ValidateBinarySearchTree.java

### 最小高度（以叶子节点来算）

LeetCode_0111_MinimumDepthOfBinaryTree.java

tips：

morris发现当前层？

> 上一个节点是X，在第8层，下一个遍历的节点是Y，如何判断Y在第几层， 如果Y左树的最右节点是A（非X），Y必定是第9层，如果Y左树的最右节点是X，那Y在第 X层数-Y的左树的右节点的个数

morris发现叶节点？

> 第二次回到自己的时候，恢复指针后，看下是否是叶子节点, 最后要单独遍历一下左树的最右节点。

### 什么时候用二叉树的递归套路，什么时候用Morris遍历

如果你需要你的左树给你一些信息，右树给你一些信息，然后整合，这个时候就用二叉树的递归套路
如果你用完左树信息后，可以不用再管左树的信息了，那么就可以用Morris遍历

如果需要用Hash表，且题目中固定了数据的范围，这个时候可以用数组来替代Hash表

-----

给定一个二叉树的头节点head，路径的规定有以下三种不同的规定：

1）路径必须是头节点出发，到叶节点为止，返回最大路径和

LeetCode_0104_MaximumDepthOfBinaryTree.java

既可以用二叉树递归套路，也可以用morris遍历

[TODO]
给定一个二叉树的头节点head
2）路径可以从任何节点出发，但必须往下走到达任何节点，返回最大路径和

3）路径可以从任何节点出发，到任何节点，返回最大路径和
LeetCode_0124_BinaryTreeMaximumPathSum.java



