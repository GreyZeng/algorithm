## 题目描述

[题目链接](https://leetcode.com/problems/regular-expression-matching/)

## 思路

### 判断合法字符

题目的约束条件是:

```text
1 <= s.length <= 20
1 <= p.length <= 30
s contains only lowercase English letters.
p contains only lowercase English letters, '.', and '*'.
It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
```

所以合法的`p`串和`s`串满足如下条件:

1. `s`串中不能含有`.`和`*` 

2. `p`串中，`*`不能开头，且两个`*`不能连在一起



### 暴力递归

定义递归函数

```java
process0(char[] s, char[] p, int si, int pi)
```

递归含义是：**`s`串从`si`开始到最后，是否可以匹配`p`串从`pi`位置到最后的字符串**

所以主函数只需要做如下调用即可得到答案

``java
process0(s,p,0,0)
``

表示：**`s`从0位置开始，一直到最后，是否匹配`p`从0开始，一直到最后的字符串。**

递归函数的base case是

// TODO

```java

```


## 更多

[算法和数据结构笔记](https://github.com/GreyZeng/algorithm)

