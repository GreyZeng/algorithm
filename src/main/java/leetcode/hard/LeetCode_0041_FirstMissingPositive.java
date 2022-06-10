/**
 * Given an unsorted integer array, find the smallest missing positive integer.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,0] Output: 3 Example 2:
 * <p>
 * Input: [3,4,-1,1] Output: 2 Example 3:
 * <p>
 * Input: [7,8,9,11,12] Output: 1 Follow up:
 * <p>
 * Your algorithm should run in O(n) time and uses constant extra space.
 */
package leetcode.hard;

//有效区：i位置上一定要保证i上面放着i+1
//不再需要的数据，进入垃圾区
//R变量：垃圾区开始，期待返回的值就是R+1
// https://leetcode.cn/problems/first-missing-positive/
public class LeetCode_0041_FirstMissingPositive {
    // L 指针和R指针
    public static int firstMissingPositive(int[] arr) {
        int L = 0;
        int R = arr.length - 1;
        while (L <= R) {
            if (arr[L] == L + 1) {
                L++;
            } else if (arr[L] < L + 1 || arr[L] > R + 1 || arr[arr[L] - 1] == arr[L]) {
                swap(arr, R--, L);
            } else {
                swap(arr, L, arr[L] - 1);
            }
        }
        return L + 1;
    }

    public static void swap(int[] arr, int L, int R) {
        if (arr.length == 0 || L == R) {
            return;
        }
        arr[L] = arr[L] ^ arr[R];
        arr[R] = arr[L] ^ arr[R];
        arr[L] = arr[L] ^ arr[R];
    }
}
