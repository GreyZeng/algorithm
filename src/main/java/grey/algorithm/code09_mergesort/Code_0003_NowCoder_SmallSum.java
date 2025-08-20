package grey.algorithm.code09_mergesort;

import java.util.Scanner;

//假设数组 s = [ 1, 3, 5, 2, 4, 6]
//在s[0]的左边所有 <= s[0]的数的总和为0
//在s[1]的左边所有 <= s[1]的数的总和为1
//在s[2]的左边所有 <= s[2]的数的总和为4
//在s[3]的左边所有 <= s[3]的数的总和为1
//在s[4]的左边所有 <= s[4]的数的总和为6
//在s[5]的左边所有 <= s[5]的数的总和为15
//所以s数组的“小和”为 : 0 + 1 + 4 + 1 + 6 + 15 = 27
//给定一个数组arr，实现函数返回arr的“小和”
//测试链接 : 
//https://www.nowcoder.com/practice/edfe05a1d45c4ea89101d936cac32469
public class Code_0003_NowCoder_SmallSum {

    private static final int MAXN = 100001;
    private static final int[] arr = new int[MAXN];
    private static final int[] help = new int[MAXN];
    private static int n;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(smallSum(0, n - 1));
    }
    // 结果比较大，用int会溢出的，所以返回long类型
    // 时间复杂度O(n * logn)

    public static long smallSum(int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = l + ((r - l) >> 1);
        return smallSum(l, m) + smallSum(m + 1, r) + merge(l, m, r);
    }

    // 返回跨左右产生的小和累加和，左侧有序、右侧有序，让左右两侧整体有序
    public static long merge(int l, int m, int r) {
        long ans = 0L;
        long sum = 0L;
        int s = l;
        int e = m + 1;
        while (e <= r) {
            while (s <= m && arr[s] <= arr[e]) {
                ans += arr[s++];
            }
            sum += ans;
            e++;
        }
        // help = new int[r - l + 1];
        s = l;
        e = m + 1;
        int i = 0;
        while (s <= m && e <= r) {
            if (arr[s] < arr[e]) {
                help[i++] = arr[s++];
            } else {
                help[i++] = arr[e++];
            }
        }
        while (s <= m) {
            help[i++] = arr[s++];
        }
        while (e <= r) {
            help[i++] = arr[e++];
        }
        int index = 0;
        for (i = l; i <= r; i++) {
            arr[i] = help[index++];
        }
        return sum;
    }
}
