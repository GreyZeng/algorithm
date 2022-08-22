package leetcode.easy;

//Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
//
//        Example 1:
//
//        Input: [3,0,1]
//        Output: 2
//        Example 2:
//
//        Input: [9,6,4,2,3,5,7,0,1]
//        Output: 8
//        Note:
//        Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
public class LeetCode_0268_MissingNumber {

    // 求缺失的最小正数
    // L左侧都做到了i位置放的i
    // R假设0-N-1都出现了一次
    // 移动L，排除纯无用的数(<L, >=R) 放到垃圾区[R ....
    public static int missingNumber(int[] arr) {
        int L = 0;
        int R = arr.length;
        while (L != R) {
            if (arr[L] == L) {
                L++;
            } else if (arr[L] < L || arr[L] > R - 1 || arr[arr[L]] == arr[L]) {
                swap(arr, L, --R); // 移动到垃圾区
            } else {
                swap(arr, L, arr[L]);
            }
        }
        return L;
    }

    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


    public static void main(String[] args) {
        int[] a = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println(missingNumber(a));
    }

}
