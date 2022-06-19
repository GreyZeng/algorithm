package leetcode.medium;

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
// 阶乘后有几个零
// 测评链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes/
public class LeetCode_0172_FactorialTrailingZeroes {

    // 5的因子有多少个
    public static int trailingZeroes(int n) {
		int ans = 0;
		while (n != 0) {
			n /= 5;
			ans += n;
		}
		return ans;
	}


    public static void main(String[] args) {
        System.out.println(trailingZeroes(25));
    }

}
