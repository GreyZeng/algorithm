package 练习题.snippet;

// 给定一个非负数组arr，长度为N，那么有N-1种方案可以把arr切成左右两部分,
// 每一种方案都有，min{左部分累加和，右部分累加和},
// 求这么多方案中，min{左部分累加和，右部分累加和}的最大值是多少？
// O(N)复杂度
public class Code_0065_BestSplitForAll {
    // 暴力方法
    public static int bestSplit1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int ans = 0;
        for (int s = 0; s < N - 1; s++) {
            int sumL = 0;
            for (int L = 0; L <= s; L++) {
                sumL += arr[L];
            }
            int sumR = 0;
            for (int R = s + 1; R < N; R++) {
                sumR += arr[R];
            }
            ans = Math.max(ans, Math.min(sumL, sumR));
        }
        return ans;
    }

    // O(N)
    // 求累加和，两边扩散
    public static int bestSplit2(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }
        int ans = Math.min(arr[0], sum - arr[0]);
        int left = arr[0];
        int right = 0;
        for (int i = 1; i < n - 1; i++) {
            left += arr[i];
            right = sum - left;
            ans = Math.max(ans, Math.min(left, right));
        }
        return ans;
    }

    public static int[] randomArray(int len, int max) {
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 20;
        int max = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        int[] arr = null;
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            arr = randomArray(len, max);
            int ans1 = bestSplit1(arr);
            int ans2 = bestSplit2(arr);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
