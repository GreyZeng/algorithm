package grey.algorithm;

import java.io.*;

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
public class Code_0024_NowCoder_SmallSum {

    private static final int MAXN = 100001;
    private static final int[] arr = new int[MAXN];
    private static final int[] help = new int[MAXN];
    private static int n;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StreamTokenizer in = new StreamTokenizer(br);
            try (PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out))) {
                in.nextToken();
                n = (int) in.nval;
                for (int i = 0; i < n; i++) {
                    in.nextToken();
                    arr[i] = (int) in.nval;
                }
                out.println(smallSum(0, n - 1));
                out.flush();
            }
        }
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
        int i = l; // 卡左边界
        int j = m + 1; // 卡右边界
        long sum = 0L;
        while (j <= r) {
            // 滑动窗口，不回退
            while (i <= m && arr[i] <= arr[j]) {
                sum += arr[i++];
            }
            ans += sum;
            j++;
        }
        int s = l;
        int e = m + 1;
        i = l;
        while (s <= m && e <= r) {
            if (arr[s] <= arr[e]) {
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
        for (i = l; i <= r; i++) {
            arr[i] = help[i];
        }
        return ans;
    }
}
