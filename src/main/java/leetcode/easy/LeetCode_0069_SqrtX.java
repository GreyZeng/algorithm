package leetcode.easy;

@Deprecated
public class LeetCode_0069_SqrtX {

    // x一定非负，输入可以保证
    public static int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x < 3) {
            return 1;
        }
        long res = 0;
        long t;
        long L = 1;
        long R = x;
        while (L <= R) {
            t = (L + R) >> 1;
            if (t * t <= x) {
                res = t;
                L = t + 1;
            } else {
                R = t - 1;
            }
        }
        return (int) res;
    }

}
