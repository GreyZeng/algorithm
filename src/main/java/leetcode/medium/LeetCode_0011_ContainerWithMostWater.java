package leetcode.medium;

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
// https://www.cnblogs.com/greyzeng/p/5918205.html
public class LeetCode_0011_ContainerWithMostWater {
    public static int maxArea(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int l = 0;
        int r = height.length - 1;
        int max = Math.min(height[l], height[r]) * r;
        while (l < r) {
            if (height[l] < height[r]) {
                max = Math.max(max, height[l] * (r - l));
                l++;
            } else {
                max = Math.max(max, height[r] * (r - l));
                r--;
            }
        }
        return max;
    }
}
