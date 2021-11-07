## 要解决的问题

> 求一个字符串最长回文子串是什么。且时间复杂度 `O(N)`

具体描述可参考：

[LeetCode_5_最长回文子串](https://leetcode-cn.com/problems/longest-palindromic-substring/)

[LintCode_200_最长回文子串](https://www.lintcode.com/problem/200/)

## 暴力解法

以每个字符为中心向左右两边扩，直到扩不动为止，记录下每个字符对应能扩的范围大小。因为有每个位置左右两边能扩的最大范围，我们可以很方便还原出最长回文子串是什么。

比如：`AB1234321CD` 这个字符串，以`4`字符为中心向左右两边能扩的位置最大，`1234321` 为最长回文子串。

如上解法有个问题，即针对类似`1ABBA2`这样的字符串，如上算法会错过最长回文子串`ABBA`, 因为`ABBA`不是以任何一个字符串向左右两边扩散得到的。所以，需要预处理一下原始字符串，预处理的方式如下：

在字符串的每两个位置之间插入一个特殊字符，变成一个预处理后的字符串，比如我们可以以`#`作为特殊字符（特殊字符选哪个无所谓，不必非要是原始串中不含有的字符），将`1ABBA2`这个字符串预处理成`1#A#B#B#A#2`，用预处理串来跑这个暴力解法，会得到`#A#B#B#A#`这个是预处理串的最长回文子串，我们可以很方便把这个串还原成原始串的最长回文子串。

暴力解法时间复杂度为`O(N^2)`。

暴力方法的示例代码如下：

```java
public class LeetCode_0005_LongestPalindromicSubstring {
    // 暴力解法
    public static String longestPalindrome1(String s) {
        if (s.length() == 1) {
            return s;
        }
        char[] str = s.toCharArray();
        char[] mStr = manacherStr(str);
        int max = 1; // 最大回文长度至少是1
        int lM = 0; // 记录最长回文的左边界的上一个位置
        int rM = 0; // 记录最长回文的有边界的下一个位置
        for (int i = 1; i < mStr.length; i++) {
            int curMax = 1; // 当前的最大回文长度至少是1
            int l = i - 1;
            int r = i + 1;
            while (l >= 0 && r < mStr.length) {
                if (mStr[l] == mStr[r]) {
                    // 暴力扩充
                    l--;
                    r++;
                } else {
                    break;
                }
            }
            curMax = r - l - 1;
            if (curMax > max) {
                // 当前最长回文长度已经超过了max了
                // 更新中心值
                // 更新max值
                max = curMax;
                lM = l;
                rM = r;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = lM + 2; i < rM; i += 2) {
            sb.append(mStr[i]);
        }
        return sb.toString();
    }

    public static char[] manacherStr(char[] str) {
        final char c = '#';
        char[] mStr = new char[(str.length << 1) | 1];
        mStr[0] = c;
        mStr[1] = str[0];
        int index = 1;
        for (int i = 2; i < mStr.length; i++) {
            if ((i & 1) != 1) {
                mStr[i] = c;
            } else {
                mStr[i] = str[index++];
            }
        }
        return mStr;
    }
}
```

## Manacher算法

**Manacher算法**可以用`O(N)`时间复杂度解决这个问题。同样的，Manacher算法也需要对原始字符串进行上述的预处理过程。

### 相关变量说明

`pArr`

> 一个整型数组，长度和预处理串一样，存每个位置的最长回文半径是多少。

比如`#A#B#B#A#`这个字符串，

位于数组2号位置的`A`的回文半径是`A#`或者`#A`, 长度为2，则`pArr[2] = 2`，

位于数组4号位置的`#`的回文半径是`#B#A#`或者`#A#B#`, 长度为5， 则`pArr[4] = 5`

其他位置以此类推。

通过`pArr`的定义，我们显然可以得到如下结论

`pArr[0] = 1`

`i`

> 整型，当前遍历到的位置，因为`pArr[0]=1`, 所以i可以从1开始遍历。

`r`

> 整型，回文最右边界，只要某个位置能扩到超过这个位置，就更新r这个值，初始值为0，因为一个字符串回文字符串至少是1，可以以第0个字符为中心且以0为最右边界(即：第0个字符本身作为一个回文串)

`c`

> 整型，就是扩到r位置的的中心点，即`pArr[c] = r - c + 1`，初始值为0，与r的初始值定为0一样的考虑。

### 流程

考虑`i`, `r`, `c`三个变量之间的位置关系，无非有以下两种情况

情况1. `i`在`r`外，比如初始状态下：`i=1, r,c = 0`

情况2. `i`在`r`内或者`i==r`

关于情况1，流程如暴力解法一样，以`i`位置为中心，左右两边扩到不能再扩的位置，更新`pArr[i]，c, r`的值。

关于情况2，我们假设`i'`为`i`关于`c`对称的点，`r'`为`r`关于`c`对称的点，示例图如下：

![image](https://img2020.cnblogs.com/blog/683206/202109/683206-20210920125517266-138475304.png)

细分如下几种情况：

情况2-1

> `i'`自己的回文区域都在`[r'...r]`内。

例如下图中`[6...10]`为`i'`的最长回文区域，左边界并未超过`r'`

![image](https://img2020.cnblogs.com/blog/683206/202109/683206-20210920125647186-1704116291.png)

由此可以推出，由于`i`位置和`i'`位置是关于`c`位置对称的，则`i`位置的回文区域至少包括`[14...19]`这一段，如下图

![image](https://img2020.cnblogs.com/blog/683206/202109/683206-20210920130033640-1802022520.png)

即`pArr[i']`**至少等于**`pArr[i]`，接下来考虑`i`能否继续扩散，即考虑`19`位置的值是否等于`13`位置的值，

我们可以假设`19`位置的值和`13`位置的值相等，同时，有如下两个显而易见的结论

1. `19`位置的值等于`5`位置的值。

2. `13`位置的值等于`11`位置的值。

推出**`5`位置的值和`11`位置的值相等**，那么由于我们前面假设i'只能扩散到最左`6`位置以及最右`10`位置，所以，推出的结论和我们的假设矛盾，所以，**`19`位置的值不等于`13`位置的值**

所以情况2-1的结论是：**`i`的最长回文区域长度和`i'`的答案一样, 即：`pArr[i'] = pArr[i]`**

情况2-2

> `i'`自己的回文区域在`[r'...r]`外

如下图

![image](https://img2020.cnblogs.com/blog/683206/202109/683206-20210920131107874-1860156959.png)

其中`[2...14]`范围是以`i'`为中心的最长回文区域。

在情况2-2下，我们可以得到如下几个结论：

1. 根据`i`和`i'`的关系，以`i`为中心，从`[13...19]`至少是回文的。

2. 根据`i'`的回文区域，`12`位置的值等于`4`位置的值，以`c`为中心，`4`位置的值又等于`20`位置的值，所以`12`位置的值等于`20`位置的值，即以`i`为中心，最长回文区域还可以扩展到`[12...20]`。

3. 根据`i'`的回文区域，`13`位置的值等于`3`位置的值，以`c`为中心，`13`位置的值又等于`11`位置的值，`3`位置的值等于`21`位置上的值，所以`11`位置的值等于`21`位置的值，即以`i`为中心，最长回文区域还可以扩展到`[11...21]`。

4. 继续判断以`i`为中心，是否可以继续扩散，即要继续判断`10`位置的值是否等于`22`位置的值，我们假设`10`位置的值等于`22`位置的值，以`c`为中心，`10`位置的值等于`14`位置的值，以`i'`为中心，`14`位置的值等于`2`位置的值，所以`10`位置的值等于`2`位置的值，根据我们的假设，`2`位置的值会等于`22`位置的值。这个与我们的前提矛盾了，因为我们的前提是`c`只能扩展到`[3...21]`这个区域，即：`2`位置的值不可能等于`22`位置的值，所以我们的假设不成立，所以`10`位置的值不等于`22`位置的值。

所以，情况2-2的结论是：**`i`到`r`的距离就是`i`的回文半径，即：`pArr[i] = r - i + 1`**

情况2-3

> `i'`自己的回文区域左边界和`r'`压线

如下图

![image](https://img2020.cnblogs.com/blog/683206/202109/683206-20210920135507571-1807519563.png)

其中`[3...13]`区域为以`i'`为中心能扩的最大回文区域。

有了情况2-2的铺垫，`i`在情况2-3条件下**至少可以扩充的范围是`[11...21]`**, 但是接下来是否可以继续扩充，还需要逐个判断。

自此，所有情况考虑完毕。

由于i在遍历过程中，始终不回退，所以，Manacher算法时间复杂度`O(N)`

## 完整代码

```java
public class LeetCode_0005_LongestPalindromicSubstring {

    public static String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] str = s.toCharArray();
        char[] strs = manacherStr(str);
        int[] pArr = new int[strs.length];
        int c = 0;
        int r = 0;
        int i = 1;
        int len = strs.length;
        int max = 1;
        while (i < len) {
            // pArr[i] 至少不需要扩的大小
            pArr[i] = i < r ? Math.min(r - i, pArr[c - (i - c)]) : 1;
            // 暴力扩
            while (i + pArr[i] < len && i - pArr[i] >= 0) {
                if (strs[i + pArr[i]] == strs[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            // 扩散的位置能否更新回文有边界R
            // 如果可以更新，则更新R，且把C置于当前的i，因为是当前的i让回文右边界扩散的
            if (i + pArr[i] > r) {
                r = i + pArr[i];
                c = i;
            }
            max = Math.max(pArr[i++], max);
        }

        // 定位最大回文有边界的回文中心是哪个
        int n = 0;
        for (; n < len; n++) {
            if (pArr[n] == max) {
                break;
            }
        }

        // 构造最大回文子串
        StringBuilder sb = new StringBuilder();
        for (i = n - max + 2; i < n + max; i += 2) {
            sb.append(strs[i]);
        }
        return sb.toString();
    }

    public static char[] manacherStr(char[] str) {
        char[] strs = new char[str.length << 1 | 1];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = ((i & 1) == 1) ? str[i >> 1] : '#';
        }
        return strs;
    }
}
```

## 相关习题

LeetCode_0005_LongestPalindromicSubstring

LintCode_0200_LongestPalindromicSubstring

LeetCode_0647_PalindromicSubstrings

LeetCode_0214_ShortestPalindrome

## 更多

[算法和数据结构笔记](https://github.com/GreyZeng/algorithm)

## 参考资料

- [程序员代码面试指南（第2版）](https://book.douban.com/subject/30422021/)
- [算法和数据结构体系班-左程云](https://ke.qq.com/course/3067253)
