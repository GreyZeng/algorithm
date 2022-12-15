package 练习题.leetcode.hard;

// TODO
//给定一个正整数 n，找出小于或等于 n 的非负整数中，其二进制表示不包含 连续的1 的个数。
//		示例 1:
//		输入: 5
//		输出: 5
//		解释:
//		下面是带有相应二进制表示的非负整数<= 5：
//		0 : 0
//		1 : 1
//		2 : 10
//		3 : 11
//		4 : 100
//		5 : 101
//		其中，只有整数3违反规则（有两个连续的1），其他5个满足规则。
//		说明：1 <= n <= 10^9
//		leetcode题目：https://leetcode-cn.com/problems/non-negative-integers-without-consecutive-ones/
// tips： 数位dp
public class LeetCode_0600_NonnegativeIntegersWithoutConsecutiveOnes {

//	// f(0, false, 5,n)
//	// 6 5 ... 0 -1 n
//	// 0 ? 停
//
//	//     5 4 3 2 1 0 -1
//	// n : 1 0 1 1 1 0
//	// 0 1 i
//	// pre : 第i+1位做的决定，0还是1
//	// 在 第i+1位做的决定 是pre的情况下，从index位开始，往后都做决定！
//	// 但是，不能有相邻的1，请问有多少决定！返回！
//	// alreadyLess : 之前的决定，是不是已经导致你到index的时候，已经比n小了！
//	// pre -> 0 1
//	// alreadyLess -> true false
//	// index -> n的位数，（logN）
//	// 2 * 2 * logN
//	// dp[2][]
//	// int alreadyLess  0  1
//	public int f(int pre, boolean alreadyLess, int index, int n) {
//		if (index == -1) {
//			return 1;
//		}
//		if (pre == 1) {
//			// 只能做0的决定，然后去往i-1位置
//			boolean curLessOrMore = alreadyLess | ((n & 1 << index) != 0);
//			return f(0, curLessOrMore, index - 1, n);
//		} else { // pre == 0的决定
//			// 可能性1，继续做0的决定
//			boolean curLessOrMore = alreadyLess | ((n & 1 << index) != 0);
//			int p1 = f(0, curLessOrMore, index - 1, n);
//			// 可能性2，做1的决定
//			// 1)pre == 0的决定, 2)
//			int p2 = 0;
//			if (alreadyLess || (n & 1 << index) != 0) {
//				p2 = f(1, alreadyLess, index - 1, n);
//			}
//			return p1 + p2;
//		}
//	}

    public static int findIntegers(int n) {
        int i = 31;
        // 尝试每个位置最左侧的1
        for (; i >= 0; i--) {
            if ((n & (1 << i)) != 0) {
                break;
            }
        }
        // for循环出来之后，i表示，n最高位的1，在哪？
        // 从这个位置，往右边低位上走！
        int[][][] dp = new int[2][2][i + 1];
        return f(0, 0, i, n, dp);
    }


    public static int f(int pre, int alreadyLess, int index, int num, int[][][] dp) {
        if (index == -1) {
            return 1;
        }
        if (dp[pre][alreadyLess][index] != 0) {
            return dp[pre][alreadyLess][index];
        }
        int ans = 0;
        if (pre == 1) {
            ans = f(0, Math.max(alreadyLess, (num & (1 << index)) != 0 ? 1 : 0), index - 1, num, dp);
        } else {
            if ((num & (1 << index)) == 0 && alreadyLess == 0) {
                ans = f(0, alreadyLess, index - 1, num, dp);
            } else {
                ans = f(1, alreadyLess, index - 1, num, dp)
                        + f(0, Math.max(alreadyLess, (num & (1 << index)) != 0 ? 1 : 0), index - 1, num, dp);
            }
        }
        dp[pre][alreadyLess][index] = ans;
        return ans;
    }

}
