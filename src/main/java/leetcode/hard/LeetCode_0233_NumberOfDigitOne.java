/*Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.



        Example 1:

        Input: n = 13
        Output: 6
        Example 2:

        Input: n = 0
        Output: 0


        Constraints:

        0 <= n <= 2 * 10^9*/
package leetcode.hard;

// TODO
// https://leetcode.cn/problems/number-of-digit-one/
// tips:
//打表法不行
//
//        数位dp的问题 最高位是1 不是1 最高位开始有几个1 其次高位置上有几个1
//
//        f(13625) -> 3626 ~ f(3625)
//        N = 1到k位 最高位是1 N%10^(k-1) + 1
//
//        10^(k-2) * (k - 1)
//
//        最高位不是1 10^(k-1) + 10^(k-2) *a* (k - 1)
//
//        复杂度 O(log10(N) * log10(N))
//
//        数位dp
public class LeetCode_0233_NumberOfDigitOne {
    // 数位dp
    // 递归方法：
    // 假设要求的数是：134556
    // 非递归部分，求34557 ~ 134556中有几个1， 然后+递归调用 countDigitOne(34556)的结果即可
    // base case f(n) n<10时候，f(n) = 1

    // 非递归情况，要分情况
    //是1 [N % 10^(k-1) + 1]   + [ (k-1) * 10 ^ (k-2)]
    //不是1 ,假设最高位是a， 10^(k-1) + a * 10^(k-2) * (k-1)
    public static int countDigitOne(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n < 10) {
            return 1;
        }
        // 数字的长度
        int len = getLen(n);
        int base = getBase(len - 1);
        // 最高位的数字
        int first = n / base;
        // 最高位为1的数字的总数
        int firstOne = first == 1 ? n % base + 1 : base;
        // 除去最高位，其他位置为1的数字总数
        int otherOne = first * (len - 1) * (base / 10);
        return firstOne + otherOne + countDigitOne(n % base);
    }

    public static int getLen(int num) {
        int len = 0;
        while (num != 0) {
            len++;
            num /= 10;
        }
        return len;
    }

    public static int getBase(int base) {
        return (int) Math.pow(10, base);
    }

}
