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
//tips:
//        从左往右的尝试模型，按最后一个画家负责的情况枚举情况
//        k块
//        min(max(s1,s2...sk))
//        四边形不等式不是最优解
//        O(N * K)
//
//        目标定，至少需要几个画家？
//        累加和--》二分--》几个画家
//
//        有一个最优解
//
//        1. 先定一个目标（比如定成累加和 为 0 ~ sum 之间的一个值） 看下需要划分几块
//        2. 二分 达标位置（取最左的位置）
//        O(N*log2Sum)
public class LeetCode_0410_SplitArrayLargestSum {
    // 不优化枚举的动态规划方法，O(N^2 * K)
    public static int splitArray1(int[] nums, int m) {
        int n = nums.length;
        // 前缀和加速
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }

        //dp[i][j] 表示0...i分成j份的最好结果
        int[][] dp = new int[n][m + 1];
        // 第0列无意义
        // 从第一列开始, 只分一份的情况下，就是累加和
        dp[0][1] = nums[0];
        //System.out.println("dp[0][1] = " + dp[0][1]);
        for (int i = 1; i < n; i++) {
            dp[i][1] = dp[i - 1][1] + nums[i];
            //System.out.println("dp[" + i + "][" + 1 + "] = " + dp[i][1]);
        }

        // dp[i - 1][i] 表示 i个元素分成i份，只有唯一分法，
        // dp[i - 1][i] 就是 0...i - 1的最大值
        int max = dp[0][1];
        for (int i = 2; i <= m; i++) {
            max = Math.max(max, nums[i - 1]);
            dp[i - 1][i] = max;

        }
        // 普遍位置
        for (int j = 2; j <= m; j++) {
            for (int i = j; i < n; i++) {
                // 最后一份只有一个元素, 0...i, 最后一个元素的位置是i，最后一份留给最后一个元素
                // 留最后一个元素给最后一堆数组
                dp[i][j] = Math.max(nums[i], dp[i - 1][j - 1]);
                //System.out.println("dp[" + (i) + "][" + j + "] 至少= " + dp[i][j]);
                // 依次考察留2个元素给最后一个数组
                // 留3个元素给最后一个数组
                // ...
                for (int k = 2; i + 1 - k >= j - 1; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[i - k][j - 1], sum(sum, i - k + 1, i)));
                }
                //System.out.println("dp[" + (i) + "][" + j + "] = " + dp[i][j]);
//                System.out.println("dp[" + (i ) + "][" + j + "] = " + dp[i ][j]);
            }
        }
        return dp[n - 1][m];
    }

    // 求原数组arr[L...R]的累加和
    // 其中sum为前缀和数组
    public static int sum(int[] sum, int L, int R) {
        return sum[R + 1] - sum[L];
    }

    // 用了枚举优化，O(N * K)
    public static int splitArray2(int[] nums, int m) {
        int n = nums.length;
        // 前缀和加速
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }

        //dp[i][j] 表示0...i分成j份的最好结果
        int[][] dp = new int[n][m + 1];
        int[][] best = new int[n][m + 1];
        // 第0列无意义
        // 从第一列开始, 只分一份的情况下，就是累加和
        dp[0][1] = nums[0];
        //System.out.println("best[0][1] = " + best[0][1]);
        for (int i = 1; i < n; i++) {
            dp[i][1] = dp[i - 1][1] + nums[i];

            // best可以认为以0号位置为分割点
            //System.out.println("best[" + i + "][" + 1 + "] = " + best[i][1]);
        }

        // dp[i - 1][i] 表示 i个元素分成i份，只有唯一分法，
        // dp[i - 1][i] 就是 0...i - 1的最大值
        int max = dp[0][1];
        for (int i = 2; i <= m; i++) {
            max = Math.max(max, nums[i - 1]);
            dp[i - 1][i] = max;
            best[i - 1][i] = i - 1;
            //System.out.println("best[" + (i - 1) + "][" + i + "] = " + best[i - 1][i]);
        }
        // 普遍位置
        for (int j = 2; j <= m; j++) {
            for (int i = j; i < n; i++) {
                // 最后一份只有一个元素, 0...i, 最后一个元素的位置是i，最后一份留给最后一个元素
                // 留最后一个元素给最后一堆数组
                dp[i][j] = Math.max(nums[i], dp[i - 1][j - 1]);
                best[i][j] = i - 1;
                //System.out.println("dp[" + (i) + "][" + j + "] 至少= " + dp[i][j]);
                // 依次考察留2个元素给最后一个数组
                // 留3个元素给最后一个数组
                // ...
//                for (int k = 2; i + 1 - k >= j - 1; k++) {
//                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[i - k][j - 1], sum(sum, i - k + 1, i)));
//                }
//                if (i == 2 && j == 2) {
//                    System.out.println("");
//                }
                for (int k = i - 2; k >= Math.max(best[i - 1][j] - 1, 0); k--) {
                    int next = Math.max(dp[k][j - 1], sum(sum, k + 1, i));
                    if (next < dp[i][j]) {
                        dp[i][j] = next;
                        best[i][j] = k;
                    }
                }
//                System.out.println("best[" + (i) + "][" + j + "] = " + best[i][j]);
//                System.out.println("dp[" + (i) + "][" + j + "] = " + dp[i][j]);
            }
        }
        return dp[n - 1][m];
    }

    // 最优解
    public static int splitArray3(int[] nums, int M) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        long l = 0;
        long r = sum;
        long ans = 0;
        while (l <= r) {
            long mid = (l + r) / 2;
            long cur = getNeedParts(nums, mid);
            if (cur <= M) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) ans;
    }

    public static int getNeedParts(int[] arr, long aim) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > aim) {
                return Integer.MAX_VALUE;
            }
        }
        int parts = 1;
        int all = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (all + arr[i] > aim) {
                parts++;
                all = arr[i];
            } else {
                all += arr[i];
            }
        }
        return parts;
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
        int maxValue = 100;
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
//        int[] arr = {2, 3, 1, 2, 4, 3};
//        int k = 5;
//        System.out.println(splitArray2(arr, k));
    }
}
