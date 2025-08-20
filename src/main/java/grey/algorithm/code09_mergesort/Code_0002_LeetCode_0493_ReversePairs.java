package grey.algorithm.code09_mergesort;

// 逆序对问题
// Given an integer array nums,
// return the number of reverse pairs in the array.
// A reverse pair is a pair (i, j) where:
// 0 <= i < j < nums.length and
// nums[i] > 2 * nums[j].
// 笔记：https://www.cnblogs.com/greyzeng/p/16653063.html
// https://leetcode.com/problems/reverse-pairs/
// https://www.lintcode.com/problem/532/
// 方法1： 归并排序
// TODO 方法2： 树状数组
// 小和问题是一个数右边有多少个数比它自己大
// 降序对问题就是求一个数右边有多少个数比它小，可以从右往左来算
public class Code_0002_LeetCode_0493_ReversePairs {

    public static int reversePairs(int[] arr) {
        if (null == arr || arr.length <= 1) {
            return 0;
        }
        return count(arr, 0, arr.length - 1);
    }

    public static int count(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = l + ((r - l) >> 1);
        return count(arr, l, m) + count(arr, m + 1, r) + merge(arr, l, m, r);
    }

    public static int merge(int[] arr, int l, int m, int r) {
        int sum = 0;
        int s = l;
        int e = m + 1;
        while (e <= r) {
            while (s <= m && (long) arr[s] > (long) arr[e] * 2) {
                s++;
            }
            // 因为整个merge sort改成从大到小排序，所以
            // s位置左侧都是比arr[e]两倍还大的数
            sum += (s - l);
            e++;
        }
        int[] help = new int[r - l + 1];
        s = l;
        e = m + 1;
        int i = 0;
        while (s <= m && e <= r) {
            // 改成从大到小排序会更好算
            if (arr[s] > arr[e]) {
                help[i++] = arr[s++];
            } else {
                help[i++] = arr[e++];
            }
        }
        while (s <= m) {
            help[i++] = arr[s++];
        }
        while (e <= r) {
            help[i++] = arr[e++];
        }
        int index = 0;
        for (i = l; i <= r; i++) {
            arr[i] = help[index++];
        }
        return sum;
    }

}
