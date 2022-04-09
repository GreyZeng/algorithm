package leetcode.medium;


// https://leetcode.com/problems/container-with-most-water/
// https://www.cnblogs.com/greyzeng/p/5918205.html
// lintcode：https://www.lintcode.com/problem/383/
public class LeetCode_0011_ContainerWithMostWater {
    public int maxArea(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int i = 0;
        int j = arr.length - 1;
        int max = 0;
        while (i < j) {
            if (arr[i] > arr[j]) {
                max = Math.max(max, arr[j--] * (j - i));
            } else {
                max = Math.max(max, arr[i++] * (j - i));
            }
        }
        return max;
    }
}
