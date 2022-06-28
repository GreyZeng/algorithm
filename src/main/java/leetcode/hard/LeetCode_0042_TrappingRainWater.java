package leetcode.hard;


//Constraints:
// n == height.length
// 0 <= n <= 3 * 10^4
// 0 <= height[i] <= 10^5
// https://leetcode.cn/problems/trapping-rain-water/
// https://www.lintcode.com/problem/363/
// 笔记：https://www.cnblogs.com/greyzeng/p/16418808.html
public class LeetCode_0042_TrappingRainWater {
    // 双指针
    public static int trap(int[] arr) {
        if (arr == null || arr.length <= 2) {
            return 0;
        }
        int result = 0;
        int l = 1;
        int r = arr.length - 2;
        int lMax = arr[0];
        int rMax = arr[arr.length - 1];
        while (l <= r) {
            if (lMax < rMax) {
                // 结算左侧
                result += Math.max(0, lMax - arr[l]);
                lMax = Math.max(lMax, arr[l++]);
            } else {
                // 结算右侧
                result += Math.max(0, rMax - arr[r]);
                rMax = Math.max(rMax, arr[r--]);
            }
        }
        return result;
    }
}
