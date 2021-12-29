package lintcode;

/**
 * 描述 Two numbers in the array, if the previous number is greater than the
 * following number, then the two numbers form a reverse order pair. Give you an
 * array, find out the total number of reverse order pairs in this array.
 * Summary: if a [i] > a [j] and i < j, a [i] and a [j] form a reverse order
 * pair.
 * <p>
 * 样例
 * <p>
 * Example1
 * <p>
 * Input: A = [2, 4, 1, 3, 5] Output: 3 Explanation: (2, 1), (4, 1), (4, 3) are
 * reverse pairs
 * <p>
 * Example2
 * <p>
 * Input: A = [1, 2, 3, 4] Output: 0 Explanation: No reverse pair
 *
 * @author Grey
 * @date 2021年7月22日 下午1:33:22
 * @since
 */
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
