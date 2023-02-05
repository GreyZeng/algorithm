package 练习题.leetcode.medium;

// 笔记：https://www.cnblogs.com/greyzeng/p/16739515.html
// 荷兰国旗问题
// 仅0，1，2，所有的0放左边，所有的1放中间，所有的2放右边
// https://leetcode.com/problems/sort-colors/
public class LeetCode_0075_SortColors {

    public static void sortColors(int[] arr) {
        int l = -1;
        int r = arr.length;
        int i = 0;
        while (i < r) {
            if (arr[i] > 1) {
                swap(arr, i, --r);
            } else if (arr[i] < 1) {
                swap(arr, i++, ++l);
            } else {
                i++;
            }
        }
    }

    private static void swap(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        arr[l] = arr[l] ^ arr[r];
        arr[r] = arr[l] ^ arr[r];
        arr[l] = arr[l] ^ arr[r];
    }
}
