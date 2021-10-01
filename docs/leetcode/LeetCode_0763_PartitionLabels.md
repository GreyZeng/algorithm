# LeetCode_0763_PartitionLabels

## 题目描述

[题目链接](https://leetcode-cn.com/problems/partition-labels/)

## 思路

显然，如果整个字符串无重复值，那么字符串的字符个数就是最多划分的区间个数。

如果有重复值，假设a字符有重复，那么所有的a必须划分到同一个区间内，否则a分布不同区间的话，就不满足题目要求了。

同理，其他字符也是类似的逻辑。

我们的整体流程是从左往右遍历字符，当遍历到一个字符的时候，我们需要快速知道这个字符在字符串后续位置是哪里，这样至少知道这个字符串至少应该从哪里开始切。

例如：`abdtydfsdfdasbsssrr`这个字符串，当遍历到第一个`a`时候，我们需要马上知道整个字符串后续是否还有`a`这个字符，此例子中，在字符串的11位置确实还有一个字符`a`。

我们设置一个right变量，用于记录当前刀最少要切到的位置，

当遍历到第一个a字符以后，可以判断第一刀至少要切到11位置，right记录为11，

至于后续是否还需要继续扩散，要继续遍历下一个字符b，如果整个字符串中最右边的b字符没有超过11位置，则第一到继续可以保持到11位置来切，

如果整个字符串中的b的最右位置超过了11，如此例，最右边的b出现在13位置，那么第一刀位置就要从11扩散到13位置，right记录为13

如果遍历到某个字符串的最右边位置正好是right，则可以从这个位置切一刀。

以此类推，直到遍历完整个字符串。

根据以上流程，我们需要对数组进行一次预处理，即，记录每个字符串出现的最右位置，由于题目限定是小写字母，所以可以通过如下方式来保存每个字符的最右位置

```java
int[] help = new int[26];
for (int i = 0; i < str.length; i++) {
     help[str[i] - 'a'] = i;
}
```

主流程代码

```java
        int right = 0;
        int pre = right;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < str.length; i++) {
        	// 是否可以将right位置扩散
        	right = Math.max(right, help[str[i] - 'a']);
        	if(i == right) {
        		// 当前位置已经是能扩散的最右位置了
        		// 收集答案 
        		ans.add(right - pre + 1);
        		pre = right + 1;
        	}
        }
        return ans;
```

pre记录上一刀的位置，便于求每个区间的长度（题目要求）， 当前遍历到的位置i如果正好是目前区间能扩散的最右位置right，则结算。

## 更多

[算法和数据结构笔记](https://github.com/GreyZeng/algorithm)

## 参考资料

- [程序员代码面试指南（第2版）](https://book.douban.com/subject/30422021/)
- [算法和数据结构基础班-左程云](https://ke.qq.com/course/2145184)
- [极客时间-数据结构与算法之美-王争](https://time.geekbang.org/column/intro/126)
- [算法(第四版)](https://book.douban.com/subject/19952400/)