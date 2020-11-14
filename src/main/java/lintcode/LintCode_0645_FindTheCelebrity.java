/*描述
假设你和 n 个人在一个聚会中(标记为 0 到 n - 1)，其中可能存在一个名人。名人的定义是所有其他 n - 1 人都认识他/她，但他/她不知道任何一个。
现在你想要找出这个名人是谁或者验证这个名人不存在。你唯一可以做的事情就是提出如下问题：“你好，A，你认识B吗？” 来获取A是否认识B。您需要通过询问尽可能少的问题(以渐近的意义)来找出名人是谁(或验证其不存在)。
你得到一个辅助函数 bool know(a，b)，它会告诉你A是否知道B.实现一个函数 int findCelebrity(n)，你的函数应该使 knows 的调用次数最少。

如果在这个聚会中有名人， 那么有且只有一个。如果有名人在聚会中则返回名人的标签，如果没有名人，返回 -1。

 
样例
样例1

输入：
2 // 接下来n*(n-1)行
0 knows 1
1 does not know 0
输出： 1
解释：
所有人都认识1，而且1不认识其他人。
样例2

输入：
3 // 接下来n*(n-1)行
0 does not know 1
0 does not know 2
1 knows 0
1 does not know 2
2 knows 0
2 knows 1
输出：0
解释：
所有人都认识0，而且0不认识其他人。
0不认识1，同时1认识0。
2认识所有人，但是1不认识2。*/
package lintcode;

// leetcode加锁 277
// lintcode : https://www.lintcode.com/problem/find-the-celebrity/description
public class LintCode_0645_FindTheCelebrity {
    // 它会告诉你A是否知道B
    public static boolean knows(int a, int b) {
        if (a == 0 && b == 1) {
            return true;
        }
        return false;
    }

    // tips:明星一开始等于0，如果0认识了i，把i改为明星的候选
    public static int findCelebrity(int n) {
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return 0;
        }
        int s = 0;
        int i = 1;
        while (i < n) {
            if (knows(s, i)) {
                s = i;
            }
            i++;
        }
        i = 0;
        while (i < n) {
            if (knows(s, i) && i != s) {
                return -1;
            }
            i++;
        }
        i = 0;
        while (i < n) {
            if (!knows(i, s) && i != s) {
                return -1;
            }
            i++;
        }
        return s;
    }


}
