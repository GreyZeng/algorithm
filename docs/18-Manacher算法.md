# Manacher 算法

> 用来解决回文问题，求一个字符串最长回文子串是什么 O(N)

## 暴力解法

> 字符串每个字符之间用一个特殊字符插入，每个元素为中心，左边右边扩，直到扩不动为止
> 大小为：位置/2 = 代表原始串中的大小
> 复杂度：O（N^2）

## Manacher算法

### 基本概念

回文半径

回文直径

回文区域

回文半径数组PARR[] ，每个位置得到的答案都放入PARR[]

回文最右边界 （int R) ，中心(int C) ---> C就是扩到R位置的的中心点

流程

设置变量i当前位置，如果

    1）i在R外，同暴力方法
    
    2）i在R内或者和R同位置

假设i'为i关于C对称的点

i' 自己的回文区域都在L。。R内，所以i的答案和i'的答案一样，存入parr中

i' 自己的回文区域在L。。。R外，i到R的距离就是i的回文半径

i' 自己的回文区域左边界和L压线，需要继续验，R外的情况

LeetCode_0005_LongestPalindromicSubstring.java
LintCode_0200_LongestPalindromicSubstring.java

LeetCode_0647_PalindromicSubstrings.java

LeetCode_0214_ShortestPalindrome.java
