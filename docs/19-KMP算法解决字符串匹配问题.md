<meta name = "referrer" content = "no-referrer" />

## 要解决的问题

假设字符串str长度为N，字符串match长度为M，`M <= N`, 想确定str中是否有某个子串是等于match的。返回和match匹配的字符串的首字母在str的位置，如果不匹配，则返回-1

OJ可参考：[LeetCode 28. 实现 strStr()](https://leetcode-cn.com/problems/implement-strstr/)

<!--more-->

## 暴力方法

从str串中每个位置开始匹配match串，时间复杂度`O(M*N)`

## KMP算法

KMP算法可以用`O(N)`时间复杂度解决上述问题。

### 流程

我们规定数组中每个位置的一个指标，这个指标定义为

> 这个位置之前的字符前缀和后缀的匹配长度，不要取得整体。

例如: `ababk` 这个字符串，`k`位置的指标为2, 因为`k`之前位置的字符串为`abab`

前缀`ab` 等于 后缀`ab`，长度为2，下标为3的`b`的指标为1，因为`b`之前的字符串`aba` ,前缀`a` 等于后缀`a`, 长度为1。

人为规定：**`0`位置的指标是-1，`1`位置的指标0**

假设match串中每个位置我们都已经求得了这个指标值，放在了一个`next`数组中，这个数组有助于我们加速整个匹配过程。

我们假设在某个时刻，匹配的到的字符如下

![image](https://img2020.cnblogs.com/blog/683206/202109/683206-20210921164226549-574919006.png)

其中str的`i..j`一直可以匹配上match串的`0...m`, str中的`x`位置和match串中的`y`位置第一次匹配不上。如果使用暴力方法，此时我们需要从str的`i+1`位置重新开始匹配match串的`k`位置，而KMP算法，利用next数组，可以加速这一匹配过程，具体流程是，依据上例，我们可以得到`y`位置的`next`数组信息，假设`y`的`next`数组信息是2，如下图

![image](https://img2020.cnblogs.com/blog/683206/202109/683206-20210921170052706-1273956464.png)

如果`y`的`next`数组信息是2，那么`0...k` 这一段完全等于`f...m`这一段，那么对于match来说，当`y`位置匹配不上`x`位置以后, **可以直接让`x`位置匹配`y`的`next`数组位置`p`上的值**，如下图

![image](https://img2020.cnblogs.com/blog/683206/202109/683206-20210921170800794-736729936.png)

如果匹配上了，则`x`来到下一个位置，`p`来到下一个位置继续匹配，如果再次匹配不上，假设`p`位置的next数组值为0, 则继续用`x`匹配`p`的`next`数组位置`0`位置上的值，如下图

![image](https://img2020.cnblogs.com/blog/683206/202109/683206-20210921174429904-497316473.png)

如果`x`位置的值依旧不等于`0`位置的值，则宣告本次匹配失败，str串来到`x`下一个位置，match串从`0`位置开始继续匹配。

### next数组求解

`next`数组的求解是KMP算法中最关键的一步，要快速求解`next`数组，需要做到当我们求`i`位置的`next`信息时，能通过`i-1`的`next`数组信息加速求得,如下图

![image](https://img2020.cnblogs.com/blog/683206/202109/683206-20210921174730767-1151362186.png)

当我们求`i`位置的`next`信息时，假设`j`位置的`next`信息为6，则表示

![image](https://img2020.cnblogs.com/blog/683206/202109/683206-20210921175033187-1367280841.png)

`m...n`这一段字符串等于`s...t`这一段字符，此时可以得出一个结论，如果：

`x`位置上的字符等于`j`位置上的字符，那么`i`位置上的`next`信息为`j`位置上的`next`信息加1，即为7。如果不等，则继续看`x`位置上的`next`信息，假设为2，则有：

![image](https://img2020.cnblogs.com/blog/683206/202109/683206-20210921175545356-41593909.png)

此时，判断`q`位置的值是否等于`j`位置的值，如果相等，那么`i`位置上的`next`信息为`x`位置上的`next`信息加1，即为3，如果不等，则继续看`q`位置上的`next`信息，假设为1，那么有

![image](https://img2020.cnblogs.com/blog/683206/202109/683206-20210921175922714-1780250808.png)

此时，判断`p`位置的值是否等于`j`位置的值，如果相等，那么`i`位置上的`next`信息为`q`位置上的`next`信息加1，即为2，如果不等，则继续如上逻辑，如果都没有匹配上`j`位置的值，则`i`位置的`next`信息为0。

## 主流程代码复杂度估计

```java
public class LeetCode_0028_ImplementStrStr {
    public static int strStr(String str, String match) {
        if (str == null || match == null || match.length() > str.length()) {
            return -1;
        }
        if (match.length() < 1) {
            return 0;
        }
        char[] s = str.toCharArray();
        char[] m = match.toCharArray();
        int l = m.length;
        int[] next = getNextArr(m, l);
        int x = 0;
        int y = 0;
        while (y < s.length && x < l) {
            if (s[y] == m[x]) {
                y++;
                x++;
            } else if (x != 0) {
                x = next[x];
            } else {
                y++;
            }
        }
        return x == l ? y - x : -1;
    }

    // 求解next数组逻辑
    private static int[] getNextArr(char[] str, int l) {
        if (l == 1) {
            return new int[]{-1};
        }
        int[] next = new int[l];
        next[0] = -1;
        next[1] = 0;
        int i = 2; // 目前在哪个位置上求next数组值
        int cn = 0; // 前后缀最长字符的长度，也表示下一个要比的信息位置
        while (i < next.length) {
            if (str[i - 1] == str[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
}
```

`next`数组的求解流程时间复杂度显然为`O(N)`，现在估计主流程的复杂度，主流程中，`x`能取得的最大值为str字符串的长度N，定义一个变量`x-y`,能取得的最大值不可能超过N（即当x = N，y=0时候），在主流程的wile循环中，有三个分支

```text
        while (y < s.length && x < l) {
            if (s[y] == m[x]) {
                y++;
                x++;
            } else if (x != 0) {
                x = next[x];
            } else {
                y++;
            }
        }
```

我们考虑这三个分支对于`y`和`y - x`变化范围的影响

| 分支        | y    | y  - x |
| ----------- | ---- | ------ |
| x++; y++    | 推高 | 不变   |
| x = next[x] | 不变 | 推高   |
| y++         | 推高 | 推高   |

如上分析，`y`和`y-x`都不可能降低，且三个分支只能中一个，所以，而`y`和`y-x`的最大值均为N，所有分支执行总推高的次数不可能超过2N。即得出主流程的复杂度`O(N)`

## KMP算法应用

### 求一个字符串的旋转词（详见：LeetCode 796）

思路

> 将这个字符串拼接一下, 比如原始串为：123456，拼接成：123456123456

如果匹配的字符串是这个拼接的字符串的子串，则互为旋转词。

### 一棵二叉树是否为另外一棵二叉树的子树(详见：LeetCode 572)

思路

> 先将两棵树分别序列化为数组A和数组B，如果B是A的子串，那么A对应的二叉树中一定有某个子树的结构和B对应的二叉树完全一样。

## 更多

[算法和数据结构笔记](https://github.com/GreyZeng/algorithm)

## 参考资料

- [程序员代码面试指南（第2版）](https://book.douban.com/subject/30422021/)
- [算法和数据结构体系班-左程云](https://ke.qq.com/course/3067253)
