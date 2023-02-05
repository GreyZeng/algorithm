package 练习题.lintcode.medium;


// 笔记：https://www.cnblogs.com/greyzeng/p/16653063.html
// 逆序对问题 https://www.lintcode.com/problem/532/

public class LintCode_0532_ReversePairs {


    public static long reversePairs(int[] A) {
        if (null == A || A.length < 2) {
            return 0;
        }
        return process(A, 0, A.length - 1);
    }

    private static long process(int[] a, int l, int r) {
        if (l == r) {
            return 0L;
        }
        int m = l + ((r - l) >> 1);
        return process(a, l, m) + process(a, m + 1, r) + merge(a, l, m, r);
    }

    private static long merge(int[] a, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int index = 0;
        int s1 = l;
        int s2 = m + 1;
        long ans = 0L;
        while (s1 <= m && s2 <= r) {
            if (a[s1] < a[s2]) {
                help[index++] = a[s2++];
            } else if (a[s1] > a[s2]) {
                ans += (r - s2 + 1);
                help[index++] = a[s1++];
            } else {
                help[index++] = a[s2++];
            }
        }
        while (s1 <= m) {
            help[index++] = a[s1++];
        }
        while (s2 <= r) {
            help[index++] = a[s2++];
        }
        index = 0;
        for (int n : help) {
            a[l + (index++)] = n;
        }
        return ans;
    }
}
