package 算法.二分;

// TODO
// 给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
// 提示：
// 1 <= nums.length <= 1000
// 0 <= nums[i] <= 10^6
// 1 <= m <= min(50, nums.length)
//
// tips:
// 方法1：四边形不等式(非最优解)
// 方法2：二分法（最优解）时间复杂度O(N)
// 用对数器验证:Code_0071_SplitArrayLargestSum，leetcode和lintcode用例不准确
// https://www.cnblogs.com/greyzeng/p/16810872.html
// leetcode ： https://leetcode.cn/problems/split-array-largest-sum
// lintcode:
public class LeetCode_0410_SplitArrayLargestSum {
    // 最优解
    // O(N)
    public static int splitArray3(int[] nums, int m) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int l = 0;
        int r = sum;
        int ans = 0;
        while (l <= r) {
            int max = l + ((r - l) >> 1);
            int k = getParts(nums, max);
            if (k > m) {
                // max 越大，k 才会越小
                l = max + 1;
            } else {
                ans = max;
                r = max - 1;
            }
        }
        return ans;
    }

    // 达到aim要分几部分
    public static int getParts(int[] nums, int aim) {
        for (int num : nums) {
            if (num > aim) {
                return Integer.MAX_VALUE;
            }
        }
        int part = 1;
        int all = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (all + nums[i] > aim) {
                part++;
                all = nums[i];
            } else {
                all += nums[i];
            }
        }
        return part;
    }

    // 不优化枚举的动态规划方法，O(N^2 * K)
    // 不优化枚举的动态规划方法，O(N^2 * K)
    public static int splitArray1(int[] nums, int K) {
        int N = nums.length;
        int[] sum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        int[][] dp = new int[N][K + 1];
        for (int j = 1; j <= K; j++) {
            dp[0][j] = nums[0];
        }
        for (int i = 1; i < N; i++) {
            dp[i][1] = sum(sum, 0, i);
        }
        // 每一行从上往下
        // 每一列从左往右
        // 根本不去凑优化位置对儿！
        for (int i = 1; i < N; i++) {
            for (int j = 2; j <= K; j++) {
                int ans = Integer.MAX_VALUE;
                // 枚举是完全不优化的！
                for (int leftEnd = 0; leftEnd <= i; leftEnd++) {
                    int leftCost = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
                    int rightCost = leftEnd == i ? 0 : sum(sum, leftEnd + 1, i);
                    int cur = Math.max(leftCost, rightCost);
                    if (cur < ans) {
                        ans = cur;
                    }
                }
                dp[i][j] = ans;
            }
        }
        return dp[N - 1][K];
    }

    // 求原数组arr[L...R]的累加和
    // 其中sum为前缀和数组
    public static int sum(int[] sum, int L, int R) {
        return sum[R + 1] - sum[L];
    }

    // 用了四边形不等式优化，O(N * K)
    // 课上现场写的方法，用了枚举优化，O(N * K)
    public static int splitArray2(int[] nums, int K) {
        int N = nums.length;
        int[] sum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        int[][] dp = new int[N][K + 1];
        int[][] best = new int[N][K + 1];
        for (int j = 1; j <= K; j++) {
            dp[0][j] = nums[0];
            best[0][j] = -1;
        }
        for (int i = 1; i < N; i++) {
            dp[i][1] = sum(sum, 0, i);
            best[i][1] = -1;
        }
        // 从第2列开始，从左往右
        // 每一列，从下往上
        // 为什么这样的顺序？因为要去凑（左，下）优化位置对儿！
        for (int j = 2; j <= K; j++) {
            for (int i = N - 1; i >= 1; i--) {
                int down = best[i][j - 1];
                // 如果i==N-1，则不优化上限
                int up = i == N - 1 ? N - 1 : best[i + 1][j];
                int ans = Integer.MAX_VALUE;
                int bestChoose = -1;
                for (int leftEnd = down; leftEnd <= up; leftEnd++) {
                    int leftCost = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
                    int rightCost = leftEnd == i ? 0 : sum(sum, leftEnd + 1, i);
                    int cur = Math.max(leftCost, rightCost);
                    if (cur < ans) {
                        ans = cur;
                        bestChoose = leftEnd;
                    }
                }
                dp[i][j] = ans;
                best[i][j] = bestChoose;
            }
        }
        return dp[N - 1][K];
    }


    public static int[] randomArray(int len, int maxValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int N = 100;
        // int N = 5;
        int maxValue = 100;
        // int maxValue = 7;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int M = Math.min((int) (Math.random() * N) + 1, len);
            int[] arr = randomArray(len, maxValue);
            int ans1 = splitArray1(arr, M);
            int ans2 = splitArray2(arr, M);
            int ans3 = splitArray3(arr, M);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.print("arr : ");
                printArray(arr);
                System.out.println("M : " + M);
                System.out.println("ans1 : " + ans1);
                System.out.println("ans2 : " + ans2);
                System.out.println("ans3 : " + ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
