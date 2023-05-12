package snippet;

import java.util.TreeSet;

// 给定一个数组arr，再给定一个k值, 返回累加和小于等于k，但是离k最近的子数组累加和
// arr中求子数组的累加和是<=K的并且是最大的，返回这个最大的累加和
public class Code_0024_MaxSubArraySumLessOrEqualsK {
    // tips:
    // TreeSet
    // 前缀和 加入有序表
    // 某个位置结尾的情况下，收集一下答案
    // 如果数组全是正数，可以用窗口
    public static int sum(int[] arr, int K) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);

        int max = Integer.MIN_VALUE;
        int sum = 0;
        // 求子数组必须以i结尾的情况下，求个子数组的累加和，是<=K的，并且是最大的
        for (int j : arr) {
            sum += j; // sum -> arr[0..i];
            if (set.ceiling(sum - K) != null) {
                max = Math.max(max, sum - set.ceiling(sum - K));
            }
            set.add(sum);
        }
        return max;
    }


}
