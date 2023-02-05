package 练习题.leetcode.medium;

// 给定n个非负整数a1，a2，...an，每个数代表坐标中的一个点(i,ai)。在坐标内画n条垂直线
// 垂直线i的两个端点分别为(i,ai)和(i, 0)，找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水
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
                max = Math.max(max, arr[j] * (j - i));
                j--;
            } else {
                max = Math.max(max, arr[i] * (j - i));
                i++;
            }
        }
        return max;
    }
}
