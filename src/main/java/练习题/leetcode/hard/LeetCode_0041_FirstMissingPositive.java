package 练习题.leetcode.hard;

// 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
// 有效区：i位置上一定要保证i上面放着i+1
// 不再需要的数据，进入垃圾区
// R变量：垃圾区开始，期待返回的值就是R+1
// https://leetcode.cn/problems/first-missing-positive/
public class LeetCode_0041_FirstMissingPositive {
    public static int firstMissingPositive(int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int v = arr[l];
            if (v == l + 1) {
                l++;
            } else if (v < l + 1 || v > r + 1 || (arr[v - 1] == v)) {
                // 垃圾数据
                swap(arr, l, r--);
            } else {
                swap(arr, l, v - 1);
            }
        }
        return l + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }
}
