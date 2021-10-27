package leetcode;

//给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
//
//        设计一个算法使得这 m 个子数组各自和的最大值最小。
//
//         
//
//        示例 1：
//
//        输入：nums = [7,2,5,10,8], m = 2
//        输出：18
//        解释：
//        一共有四种方法将 nums 分割为 2 个子数组。 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
//        因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
//        示例 2：
//
//        输入：nums = [1,2,3,4,5], m = 2
//        输出：9
//        示例 3：
//
//        输入：nums = [1,4,4], m = 3
//        输出：4
//         
//
//        提示：
//
//        1 <= nums.length <= 1000
//        0 <= nums[i] <= 106
//        1 <= m <= min(50, nums.length)
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode-cn.com/problems/split-array-largest-sum
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
//       满足 min(max(s1,s2...sk))
//        但是四边形不等式不是最优解
// FIXME
public class LeetCode_0410_SplitArrayLargestSum {
    // 不优化枚举的动态规划方法，O(N^2 * K)
    public static int splitArray1(int[] nums, int m) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n == m) {
            // 要分的部分和数组长度一致，取全局最大值
            int max = nums[0];
            for (int i = 1; i < n; i++) {
                max = Math.max(nums[i], max);
            }
            return max;
        }
        long[] sum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        // dp[i][j] 表示 0....i分成j部分的答案是多少
        // 第0列没意义
        // 第0行除了dp[i][1]有意义，其他都没意义
        long[][] dp = new long[n][m + 1];
        // 如果只能分一个部分，答案就是累加和
        for (int i = 0; i < n; i++) {
            dp[i][1] = sum(sum, 0, i);
        }
        int max = nums[0];
        for (int i = 1; i < m + 1; i++) {
            max = Math.max(max, nums[i]);
            dp[i - 1][i] = max;

        }
        // 如果分的部分正好等于数组长度，则取全局最大值即可
        // dp[0][1] = nums[0];
        for (int i = 1; i < n; i++) {
            for (int j = 2; j < m + 1; j++) {
                // dp[i][j] 至少是这个值，最后一个位置分给最后一部分
                long ans = Integer.MAX_VALUE;
                for (int k = 0; k <= i; k++) {
                    long cur = Math.max(dp[k][j - 1], sum(sum, k + 1, i));
//                    System.out.println("dp[" + i + "][" + j + "]=" + dp[i][j]);
                    if (cur < ans) {
                        ans = cur;
                    }
                }
                dp[i][j] = ans;
            }
        }
        return (int) dp[n - 1][m];
    }

    // 求原数组arr[L...R]的累加和
    // 其中sum为前缀和数组
    public static long sum(long[] sum, int L, int R) {
        return sum[R + 1] - sum[L];
    }

    // 用了枚举优化，O(N * K)
    public static int splitArray2(int[] nums, int m) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n == m) {
            int max = nums[0];
            for (int i = 1; i < n; i++) {
                max = Math.max(nums[i], max);
            }
            return max;
        }
        long[] sum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        // dp[i][j] 表示 0....i分成j部分的答案是多少
        // 第0列没意义
        // 第0行除了dp[i][1]有意义，其他都没意义
        long[][] dp = new long[n][m + 1];
        // 记录dp[i][j]的最佳划分，最后一个部分位置负责到哪里
        int[][] best = new int[n][m + 1];
        // 如果只能分一个部分，答案就是累加和
        for (int i = 0; i < n; i++) {
            dp[i][1] = sum(sum, 0, i);
        }

        // 如果分的部分数量正好等于数组元素个数，那么每个部分对应一个数组元素
        for (int j = 2; j < m + 1; j++) {
            for (int i = n - 1; i >= 1; i--) {
                // dp[i][j] 至少是这个值，最后一个位置分给最后一部分
                dp[i][j] = Math.max(dp[i - 1][j - 1], nums[i]);
                best[i][j] = best[i - 1][j];
                int down = best[i - 1][j];
                int up = i + 1 < n ? best[i + 1][j] : n - 1;
                for (int k = down; k <= up; k++) {
                    long next = Math.max(dp[k][j - 1], sum(sum, k + 1, i));
                    if (next <= dp[i][j]) {
                        dp[i][j] = next;
                        best[i][j] = k;
                    }
                }
            }
        }
        return (int) dp[n - 1][m];
    }

    // 最优解
    // 1. 先定一个目标（比如定成累加和 为 0 ~ sum 之间的一个值） 看下需要划分几块
    // 2. 二分 达标位置（取最左的位置）
    // O(N*log2Sum)
    // sum如果很大，可以考虑上述四边形不等式优化解
    public static int splitArray3(int[] nums, int m) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 0...sum
        long l = 0;
        long r = sum;
        long ans = 0;
        while (l <= r) {
            long aim = (l + r) / 2;
            long part = getNeedParts(nums, aim);
            if (part <= m) {
                // 每一份分太大块了
                r = aim - 1;
                ans = aim;
            } else {
                // 每一份分小了
                l = aim + 1;

            }
        }
        return (int) ans;
    }

    public static int getNeedParts(int[] arr, long aim) {
        for (int n : arr) {
            if (n > aim) {
                return Integer.MAX_VALUE;
            }
        }
        int part = 1;
        int all = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if ((arr[i] + all) > aim) {
                part++;
                all = arr[i];
            } else {
                all += arr[i];
            }
        }
        return part;
    }

    public static int[] randomArray(int len, int maxValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int m = 3;
        int[] arr = {6, 1, 2, 2};
        System.out.println(splitArray1(arr, m));
//        int N = 4;
//        int maxValue = 10;
//        int testTime = 10000;
//        System.out.println("测试开始");
//        for (int i = 0; i < testTime; i++) {
//            int len = (int) (Math.random() * N) + 1;
//            int M = Math.min((int) (Math.random() * N) + 1, len);
//            int[] arr = randomArray(len, maxValue);
//            int ans1 = splitArray1(arr, M);
//            int ans2 = splitArray2(arr, M);
//            int ans3 = splitArray3(arr, M);
//            if (ans1 != ans2 || ans1 != ans3) {
//                System.out.print("arr : " + arr.length);
//                printArray(arr);
//                System.out.println("M : " + M);
//                System.out.println("ans1 : " + ans1);
//                System.out.println("ans2 : " + ans2);
//                System.out.println("ans3 : " + ans3);
//                System.out.println("Oops!");
//                break;
//            }
//        }
//        System.out.println("测试结束");

    }
}
