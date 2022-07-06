//给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
//
//        如果反转后整数超过 32 位的有符号整数的范围[−231, 231− 1] ，就返回 0。
//
//        假设环境不允许存储 64 位整数（有符号或无符号）。
//        示例 1：
//        输入：x = 123
//        输出：321
//        示例 2：
//        输入：x = -123
//        输出：-321
//        示例 3：
//        输入：x = 120
//        输出：21
//        示例 4：
//        输入：x = 0
//        输出：0
//        提示：
//
//        -2^31 <= x <= 2^31 - 1
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode-cn.com/problems/reverse-integer
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
package leetcode.easy;

// https://leetcode-cn.com/problems/reverse-integer/
public class LeetCode_0007_ReverseInteger {

    public static int reverse(int x) {
        // 无论如何，把x先转为负数来处理
        // 因为负数比整数的值域多表示一个
        boolean neg = ((x >>> 31) & 1) == 1;
        x = neg ? x : -x;
        int m = Integer.MIN_VALUE / 10;
        int o = Integer.MIN_VALUE % 10;
        int res = 0;
        while (x != 0) {
            if (/*即将越界*/ res < m || (res * 10 == m && x < o)) {
                return 0;
            }
            res = res * 10 + (x % 10);

            x = x / 10;
        }
        return neg ? res : -res;
    }
}
