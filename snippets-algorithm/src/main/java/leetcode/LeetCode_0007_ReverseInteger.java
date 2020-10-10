package leetcode;


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
