package leetcode;

//Example 2:
//
//        Input: height = [1,1]
//        Output: 1
//        Example 3:
//
//        Input: height = [4,3,2,1,4]
//        Output: 16
//        Example 4:
//
//        Input: height = [1,2,1]
//        Output: 2
//Constraints:
//
//        n == height.length
//        2 <= n <= 10^5
//        0 <= height[i] <= 10^4
// https://leetcode.com/problems/container-with-most-water/
public class LeetCode_0011_ContainerWithMostWater {
    public static int maxArea(int[] height) {
        if (height.length <= 1) {
            return 0;
        }
        int len = height.length;
        int max = 0;
        int l = 0;
        int r = len - 1;
        while (l < r) {
            if (height[l] < height[r]) {
                // 左边为基准，因为右边有兜底
                max = Math.max(max, (r - l) * (height[l]));
                l++;
            } else {
                // 右边为基准，因为左边有兜底
                max = Math.max(max, (r - l) * height[r]);
                r--;
            }
        }
        return max;
    }
}
