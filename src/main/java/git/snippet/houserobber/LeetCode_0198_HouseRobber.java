package git.snippet.houserobber;

// 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算在不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/house-robber
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
// 本质：返回一个数组中，选择的数字不能相邻的情况下，最大子序列累加和
// 笔记：https://www.cnblogs.com/greyzeng/p/16494637.html
public class LeetCode_0198_HouseRobber {
    // 动态规划
    public static int rob(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        if (arr.length == 2) {
            return Math.max(arr[0], arr[1]);
        }
        final int n = arr.length;
        int[] dp = new int[n];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], arr[i] + dp[i - 2]);
        }
        return dp[n - 1];
    }

    // 数组压缩
    public static int rob2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        if (arr.length == 2) {
            return Math.max(arr[0], arr[1]);
        }
        final int n = arr.length;
        int prePre = arr[0];
        int pre = Math.max(arr[0], arr[1]);
        int max = pre;
        for (int i = 2; i < n; i++) {
            int cur = Math.max(pre, prePre + arr[i]);
            prePre = pre;
            pre = cur;
            max = Math.max(cur, max);
        }
        return max;
    }
}
