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



给定一个二叉树的头节点head，路径的规定有以下三种不同的规定：

1）路径必须是头节点出发，到叶节点为止，返回最大路径和 
LeetCode_0104_MaximumDepthOfBinaryTree.java 既可以用二叉树递归套路，也可以用morris遍历

[TODO]
给定一个二叉树的头节点head
2）路径可以从任何节点出发，但必须往下走到达任何节点，返回最大路径和

3）路径可以从任何节点出发，到任何节点，返回最大路径和
LeetCode_0124_BinaryTreeMaximumPathSum.java



完全二叉树节点的个数 ，要求复杂度低于O(N)
tips:
求总深度，左边扎到最深
右树的最左节点可以扎到最深处，则左树一定是满的
右树的最左节点不能扎到最深处，则右树一定是满的 
O(h^2) -> O((logN)^2)
LeetCode_0222_CountCompleteTreeNodes.java


已知一棵二叉树中没有重复节点，并且给定了这棵树的中序遍历数组和先序遍历 数组，返回后序遍历数组。
比如给定:
int[] pre = { 1, 2, 4, 5, 3, 6, 7 };
int[] in = { 4, 2, 5, 1, 6, 3, 7 }; 返回:
{4,5,2,6,7,3,1}
NowCoder_PreAndMidToPos.java

双向链表节点结构和二叉树节点结构是一样的，如果你把last认为是left，next认为是next的话。
给定一个搜索二叉树的头节点head，请转化成一条有序的双向链表，并返回链表的头节点。
LintCode_1534_CovertBSTToDoubleLinkedList.java

tips:
case1 中序遍历
case2 二叉树的递归套路
case3 morris遍历



给定一棵二叉树的头节点head，已知所有节点的值都不一样，返回其中最大的且符 合搜索二叉树条件的最大拓扑结构的大小。
拓扑结构：不是子树，只要能连起来的结构都算。
NowCoder_BiggestTopology.java
tips:

二叉树的递归套路，每个位置的贡献记录，左树的最右节点，右树的最左节点
复杂度 O(N)


一棵二叉树原本是搜索二叉树，但是其中有两个节点调换了位置，使得这棵二叉树不再 是搜索二叉树，请找到这两个错误节点并返回。
已知二叉树中所有节点的值都不一样，给定二叉树的头节点 head，
返回一个长度为2的 二叉树节点类型的数组errs，errs[0]表示一个错误节点， errs[1]表示另一个错误节点。

进阶: 如果在原问题中得到了这两个错误节点，我们当然可以通过交换两个节点的节点值的方式让整棵二叉树重新成为搜索二叉树。
但现在要求你不能这么做，而是在结构上完全交换两个节点的位置，请实现调整的函数

tips:
中序遍历 第一次降序的头节点，第二次降序的尾节点
e1,e2 可能是同一个头
e1,e2与其父是什么关系
e1,e2是否挨着
LeetCode_0099_RecoverBinarySearchTree