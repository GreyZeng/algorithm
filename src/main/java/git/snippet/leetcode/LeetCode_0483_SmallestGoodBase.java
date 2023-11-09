package git.snippet.leetcode;

// TODO
// 对于给定的整数 n, 如果n的k（k>=2）进制数的所有数位全为1，则称 k（k>=2）是 n 的一个好进制。
// 以字符串的形式给出 n, 以字符串的形式返回 n 的最小好进制。
// 示例 1：
// 输入："13"
// 输出："3"
// 解释：13 的 3 进制是 111。
// 示例 2：
// 输入："4681"
// 输出："8"
// 解释：4681 的 8 进制是 11111。
// 示例 3：
// 输入："1000000000000000000"
// 输出："999999999999999999"
// 解释：1000000000000000000 的 999999999999999999 进制是 11。
// leetcode题目：https://leetcode.com/problems/smallest-good-base/
// tips: x相对x-1进制，必为好进制
// 明显是以2作为好进制的数先排除掉
// 某个数，2进制是多少位？以后不能超过这个位
// 定好位数以后，再定一个下限和上限的访问，
// 把x开某次位根号，就不能再低了（左边界）。
// 把x开某次位根号，就不能再高了（右边界）。
// 可以定出二分的范围
public class LeetCode_0483_SmallestGoodBase {

    // ""4651" -> 4651
    public static String smallestGoodBase(String n) {
        long num = Long.parseLong(n);
        // n这个数，需要从m位开始试，固定位数，一定要有m位！
        for (int m = (int) (Math.log(num + 1) / Math.log(2)); m > 2; m--) {
            // num开m次方
            long l = (long) (Math.pow(num, 1.0 / m));
            long r = (long) (Math.pow(num, 1.0 / (m - 1))) + 1L;
            while (l <= r) {
                long k = l + ((r - l) >> 1);
                long sum = 0L;
                long base = 1L;
                for (int i = 0; i < m && sum <= num; i++) {
                    sum += base;
                    base *= k;
                }
                if (sum < num) {
                    l = k + 1;
                } else if (sum > num) {
                    r = k - 1;
                } else {
                    return String.valueOf(k);
                }
            }
        }
        return String.valueOf(num - 1);
    }
}
