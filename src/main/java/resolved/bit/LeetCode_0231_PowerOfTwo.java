package resolved.bit;

// 判断一个数是否是2的某次幂
// https://leetcode.com/problems/power-of-two
public class LeetCode_0231_PowerOfTwo {
    // 借鉴 Netty 代码
    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & -n) == n;
    }

    public static void main(String[] args) {
        int i = 1024;
        System.out.println(isPowerOfTwo(i));
    }
}
