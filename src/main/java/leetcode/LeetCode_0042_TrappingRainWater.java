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
                if (arr[r] < rightMax) {
                    water += (rightMax - arr[r]);
                } else {
                    rightMax = arr[r];
                }
                r--;
            } else { 
                if (arr[l] < leftMax) {
                    water += (leftMax - arr[l]);
                } else {
                    leftMax = arr[l];
                }
                l++;
            }
        }
        return water;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(arr));
    }

}
