package leetcode;

//Given an integer n, return the number of trailing zeroes in n!.
//
//        Follow up: Could you write a solution that works in logarithmic time complexity?
//
//
//
//        Example 1:
//
//        Input: n = 3
//        Output: 0
//        Explanation: 3! = 6, no trailing zero.
//        Example 2:
//
//        Input: n = 5
//        Output: 1
//        Explanation: 5! = 120, one trailing zero.
//        Example 3:
//
//        Input: n = 0
//        Output: 0
//
//
//        Constraints:
//
//        1 <= n <= 10^4
public class LeetCode_0172_FactorialTrailingZeroes {

    // 5的因子有多少个
    // 25, 125... 不止一个5，所以不能单纯n/5，应该把n/5的结果一直/5,直到n<5为止。
    public static int trailingZeroes(int n) {
        int re = 0;
        while (n >= 5) {
            n = n / 5;
            re += n;
        }
        return re;
    }

    public static void main(String[] args) {
        System.out.println(trailingZeroes(25));
    }

}
