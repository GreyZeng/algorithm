package leetcode.hard;

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
// 用对数器准确，leetcode和lintcode用例不准确
// leetcode ： https://leetcode.com/problems/split-array-largest-sum
// lintcode: 
public class LeetCode_0410_SplitArrayLargestSum {
    // 不优化枚举的动态规划方法，O(N^2 * K)
    public static int splitArray1(int[] nums, int m) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        long[] sum = new long[n + 1];
        for (int i = 1; i < n + 1; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        // dp[i][j] 0....i 由 j个人来处理，最优处理是多少
        long[][] dp = new long[n][m + 1];
        dp[0][1] = nums[0];
        for (int i = 1; i < n; i++) {
            // 数组分一个部分，就是累加和
            dp[i][1] = dp[i - 1][1] + nums[i];
        }
        for (int i = 2; i < m + 1; i++) {
            // i个元素分i份, 每个元素一份
            dp[i - 1][i] = Math.max(nums[i - 1], dp[i - 2][i - 1]);
        }
        for (int j = 2; j < m + 1; j++) {
            for (int i = n - 1; i >= j; i--) {
                // dp[i][j] 至少可以是这个值。
                dp[i][j] = Math.max(dp[i - 1][j - 1], nums[i]);
                for (int k = i - 1; k >= j - 2; k--) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], sum(sum, k + 1, i)));
                }
            }
        }
        return (int) dp[n - 1][m];
    }

    // 求原数组arr[L...R]的累加和
    // 其中sum为前缀和数组
    public static long sum(long[] sum, int L, int R) {
        return sum[R + 1] - sum[L];
    }

    // 用了四边形不等式优化，O(N * K)
    // FIXME
    public static int splitArray2(int[] nums, int m) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        long[] sum = new long[n + 1];
        for (int i = 1; i < n + 1; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        // dp[i][j] 0....i 由 j个人来处理，最优处理是多少
        long[][] dp = new long[n][m + 1];
        int[][] best = new int[n][m + 1];
        dp[0][1] = nums[0];
        // best[0][1] = 0;
        for (int i = 1; i < n; i++) {
            // 数组分一个部分，就是累加和
            dp[i][1] = dp[i - 1][1] + nums[i];
            // best[i][1] = 0
        }
        long max = dp[0][1];
        for (int i = 2; i < m + 1; i++) {
            // i个元素分i份, 每个元素一份
            max = Math.max(max, nums[i - 1]);
            dp[i - 1][i] = max;
            best[i - 1][i] = i - 1;
        }
        for (int j = 2; j < m + 1; j++) {
            for (int i = j; i < n; i++) {
                // dp[i][j] 至少可以是这个值。
                // TODO 上界？
                dp[i][j] = Math.max(dp[i - 1][j - 1], nums[i]);
                best[i][j] = i - 1;
                for (int k = i - 2; k >= Math.max(best[i - 1][j] - 1, 0); k--) {
                    long next = Math.max(dp[k][j - 1], sum(sum, k + 1, i));
                    if (next < dp[i][j]) {
                        dp[i][j] = next;
                        best[i][j] = k;
                    }
                }
            }
        }
        return (int) dp[n - 1][m];
    }

    // 最优解
    public static int splitArray3(int[] nums, int m) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int l = 0;
        int r = sum;
        int ans = 0;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            int parts = getParts(nums, mid);
            if (parts > m) {
                // mid越大，parts才会越小
                l = mid + 1;
            } else {
                ans = mid;
                r = mid - 1;
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
