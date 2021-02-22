## 题目描述

[题目链接](https://leetcode.com/problems/minimum-window-substring/)


## 思路 滑动窗口 + 欠账表

将目标字符串加入到一个欠账表中，这个欠帐表记录了目标字符串中每个字符出现的次数，因为题目说到字符串都是英文字母，所以定义一个256大小的字符数组即可

```java
// 初始化欠帐表
// 如果不止ASCII码的字符，则可以用Hash表来实现欠账表
int[] owe = new int[256];
for (char c : target) {
    owe[c]++;
}
```

假设目标字符串有c这个字符, 那么

```java
char[c] 
```

的值就是目标字符串中c出现的次数

定义变量all，表示目标字符串中的字符数量之和

欠账表初始化完成后，开始实现滑动窗口，定义窗口初始大小win=0，定义l和r分别在原始串的0位置，定义finalL为最后要求的窗口位置。

滑动窗口的移动逻辑是：

r从原始串0位置一直往右边移动，在移动的过程中，加入的字符如果在欠账表中存在，则为有效还款，all--，继续移动，一直到all = 0 ，此时形成的窗口一定是覆盖目标串的所有字符，

此时开始移动l位置，l从0位置一直往右边移动，进入的字符如果在欠帐表中的记录 < 0 (说明r往右边移动的时候，这些字符还多了)，这个时候，l位置在欠账表中的记录++，l一直移动到对应位置的欠账表记录正好为0就停下来

此时，l 位置和 r位置固定，形成了一个有效的窗口，如果窗口win=0，则更新win = r -l + 1， 如果win > r - l + 1 说明原来更新的窗口比现在的窗口大，那么也要更新win = r - l + 1，

并标记 finalL = l，这个过程的代码如下：

```java
// all等于0说明找到一个符合条件的窗口，开始结算
if (all == 0) {
    // 开始移动L，缩小窗口
    while (owe[str[l]] < 0) {
        // owe[str[l]] < 0 都是无效的还款
        owe[str[l++]]++;
    }
    // 窗口没有形成或者窗口当前形成的窗口小于上一次的窗口大小，则更新
    if (win == 0 || win > r - l + 1) {
        win = r - l + 1;
        finalL = l;
    }
    owe[str[l++]]++;
    all++;
}
```

以上过程不断滑动，直到r到原始串最后一个位置停止

那么 原始串的 [finalL, finalL + win] 区间，即为要求的字符串。

```java
s.substring(finalL, finalL + win);
```

完整代码：

```java
// 欠账表 + all
// 滑动窗口
public static String minWindow(String s, String t) {
    // 如果目标串比原始串还大，则原始串无论如何都无法找到包含目标串所有字符的子串
    if (s.length() < t.length()) {
        return "";
    }
    char[] str = s.toCharArray();
    char[] target = t.toCharArray();
    // 初始化欠帐表
    // 如果不止ASCII码的字符，则可以用Hash表来实现欠账表
    int[] owe = new int[256];
    for (char c : target) {
        owe[c]++;
    }
    int all = target.length;
    int win = 0;
    int l = 0;
    int r = 0;
    // [finalL, finalL + win] 就是对应的结果字符串
    int finalL = 0;
    while (r != str.length) {
        owe[str[r]]--;
        if (owe[str[r]] >= 0) {
            // 有效还款
            all--;
        }
        // all等于0说明找到一个符合条件的窗口，开始结算
        if (all == 0) {
            // 开始移动L，缩小窗口
            while (owe[str[l]] < 0) {
                // owe[str[l]] < 0 都是无效的还款
                owe[str[l++]]++;
            }
            // 窗口没有形成或者窗口当前形成的窗口小于上一次的窗口大小，则更新
            if (win == 0 || win > r - l + 1) {
                win = r - l + 1;
                finalL = l;
            }
            owe[str[l++]]++;
            all++;
        }
        r++;
    }
    return s.substring(finalL, finalL + win);
}
```


## 更多


[算法和数据结构笔记](https://github.com/GreyZeng/algorithm)


## 参考资料


- [程序员代码面试指南（第2版）](https://book.douban.com/subject/30422021/)
- [算法和数据结构基础班-左程云](https://ke.qq.com/course/2145184)
- [极客时间-数据结构与算法之美-王争](https://time.geekbang.org/column/intro/126)
- [算法(第四版)](https://book.douban.com/subject/19952400/)
