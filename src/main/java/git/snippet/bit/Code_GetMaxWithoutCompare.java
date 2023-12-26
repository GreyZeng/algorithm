package git.snippet.bit;

// 笔记：https://www.cnblogs.com/greyzeng/p/16641111.html
// 不要用任何比较判断，返回两个数中较大的数
public class Code_GetMaxWithoutCompare {
    // 1 -> 0
    // 0 -> 1
    public static int flip(int n) {
        return n ^ 1;
    }

    // 如果n是负数，则返回 1
    // 如果n是正数，则返回 0
    public static int sign(int n) {
        return flip((n >> 31) & 1);
    }

    public static int getMax1(int a, int b) {
        // 如果 c 是负数，则 b 大，返回 b
        // 如果 c 是正数，则 a 大，返回 a
        int c = a - b;
        // 如果 c 是负数，则返回 1
        // 如果 c 是正数，则返回 0
        int scA = sign(c);
        int scB = flip(scA);
        return a * scA + b * scB;
    }

    public static int getMax2(int a, int b) {
        int c = a - b;
        int sa = sign(a);
        int sb = sign(b);
        int sc = sign(c);
        int difSab = sa ^ sb;
        int sameSab = flip(difSab);
        int returnA = difSab * sa + sameSab * sc;
        int returnB = flip(returnA);
        return a * returnA + b * returnB;
    }
}
