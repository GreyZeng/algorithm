KMP算法
LeetCode_0028_ImplementStrStr.java

假设字符串str长度为N，字符串match长度为M，M <= N
想确定str中是否有某个子串是等于match的。
返回和match匹配的字符串的首字母在str的位置，如果不匹配，则返回-1

match每个位置的有个指标【这个位置之前的字符前缀和后缀的匹配长度，不要取得整体，人为规定，0位置的指标是-1，1位置的指标0】，存在next数组中
时间复杂度O(N)
1）如何理解next数组
2）如何利用next数组加速匹配过程，优化时的两个实质！
（为啥match可以直接匹配不上的时候跳到k位置而不是i+1位置？
为啥i~k之间任何一个值都不可以？）
暴力方法最差的情况 O（N*M）
aaaaaaaaaaab    aaab


KMP算法应用
一个字符串的旋转词
暴力方法：循环数组匹配 O（N^2）
长度一样的情况
将这个字符串拼接一下
如：123456 -》123456123456
如果匹配的字符串是这个拼接的字符串的子串，则互为旋转词

LeetCode_0796_RotateString.java


给定两棵二叉树的头节点head1和head2
想知道head1中是否有某个子树的结构和head2完全一样
解法：head1先序化成数组A，head2先序化成数组B，
如果B是A的子串，那么head1中一定有某个子树的结构和head2完全一样
 
LeetCode_0572_SubtreeOfAnotherTree.java