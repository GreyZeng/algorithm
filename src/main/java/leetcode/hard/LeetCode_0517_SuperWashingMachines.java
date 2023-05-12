package leetcode.hard;

// 假设有 n台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的
// 在每一步操作中，你可以选择任意 m（1 ≤ m ≤ n）台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机
// 给定一个非负整数数组代表从左至右每台洗衣机中的衣物数量，请给出能让所有洗衣机中剩下的衣物的数量相等的最少的操作步数
// 如果不能使每台洗衣机中衣物的数量相等，则返回-1
// Leetcode题目：https://leetcode.com/problems/super-washing-machines/
// 笔记：https://www.cnblogs.com/greyzeng/p/16335452.html
public class LeetCode_0517_SuperWashingMachines {
    public static int findMinMoves(int[] arr) {
        if (null == arr || 0 == arr.length) {
            return 0;
        }
        int sum = 0;
        int size = arr.length;
        for (int item : arr) {
            sum += item;
        }
        if (sum % size != 0) {
            return -1;
        }
        int avg = sum / size;
        int leftSum = 0;
        int ans = 0;
        for (int i = 0; i < size; i++) {
            // 左侧还差多少
            int leftRest = leftSum - i * avg;
            // 右侧还差多少
            int rightRest = (sum - leftSum - arr[i]) - (size - i - 1) * avg;
            if (leftRest < 0 && rightRest < 0) {
                // 左侧右侧都差一些才到平均值
                // 此时就需要中间位置向左边和右边都丢一些衣服
                ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
            } else {
                // 左侧多，右侧少，多的通过中间丢一些到左侧
                // 右侧少，左侧多，多的通过中间丢一些到右侧
                // 左右侧都多，则可以**同时**向中间丢衣服
                ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
            }
            leftSum += arr[i];
        }
        return ans;
    }
}
