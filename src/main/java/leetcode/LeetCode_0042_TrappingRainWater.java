package leetcode;

/**
 * @author Grey
 */
//Constraints:
// n == height.length
// 0 <= n <= 3 * 10^4
// 0 <= height[i] <= 10^5
public class LeetCode_0042_TrappingRainWater {

    // 双指针
    // TODO
    public static int trap(int[] arr) {
        if (null == arr || arr.length <= 2) {
            return 0;
        }
        int n = arr.length;
        // 考虑每个位置上的水能留下多少
        int leftMax = arr[0];
        int rightMax = arr[n - 1];
        int l = 1;
        int r = n - 2;
        int water = 0;
        while (l <= r) {
            if (leftMax > rightMax) {
                if (arr[l] < rightMax) {
                    water += (rightMax - arr[l]);
                } else {
                    leftMax = Math.max(arr[l], leftMax);
                }
                l++;
            } else {
                if (arr[r] < leftMax) {
                    water += (leftMax - arr[r]);
                } else {
                    rightMax = Math.max(arr[r], rightMax);
                }
                r--;
            }
        }
        return water;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(arr));
    }

}
