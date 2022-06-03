package leetcode.hard;


// https://leetcode-cn.com/problems/super-washing-machines/
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
