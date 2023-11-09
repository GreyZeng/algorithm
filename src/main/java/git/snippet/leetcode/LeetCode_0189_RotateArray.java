package git.snippet.leetcode;

// Given an array, rotate the array to the right by k steps, where k is non-negative.
//
// Follow up:
//
// Try to come up as many solutions as you can, there are at least 3 different ways to solve this
// problem.
// Could you do it in-place with O(1) extra space?
//
//
// Example 1:
//
// Input: nums = [1,2,3,4,5,6,7], k = 3
// Output: [5,6,7,1,2,3,4]
// Explanation:
// rotate 1 steps to the right: [7,1,2,3,4,5,6]
// rotate 2 steps to the right: [6,7,1,2,3,4,5]
// rotate 3 steps to the right: [5,6,7,1,2,3,4]
// Example 2:
//
// Input: nums = [-1,-100,3,99], k = 2
// Output: [3,99,-1,-100]
// Explanation:
// rotate 1 steps to the right: [99,-1,-100,3]
// rotate 2 steps to the right: [3,99,-1,-100]
//
//
// Constraints:
//
// 1 <= nums.length <= 2 * 10^4
// It's guaranteed that nums[i] fits in a 32 bit-signed integer.
// k >= 0
public class LeetCode_0189_RotateArray {

    // 方法1 左逆序，右逆序，整体逆序
    public static void rotate(int[] nums, int k) {
        int R = nums.length;
        k = k % R;
        if (k != 0) {
            reverse(nums, 0, R - k - 1);
            reverse(nums, R - k, R - 1);
            reverse(nums, 0, R - 1);
        }
    }

    private static void reverse(int[] nums, int L, int R) {
        while (L < R) {
            nums[L] = nums[L] ^ nums[R];
            nums[R] = nums[L] ^ nums[R];
            nums[L] = nums[L++] ^ nums[R--];
        }
    }

    // TODO 方法2 相等对半再相等对半分
    public static void rotate2(int[] nums, int k) {
        int N = nums.length;
        k = k % N;
        if (k == 0) {
            return;
        }
        int L = 0;
        int R = N - 1;
        int lpart = N - k;
        int rpart = k;
        int same = Math.min(lpart, rpart);
        int diff = lpart - rpart;
        exchange(nums, L, R, same);
        while (diff != 0) {
            if (diff > 0) {
                L += same;
                lpart = diff;
            } else {
                R -= same;
                rpart = -diff;
            }
            same = Math.min(lpart, rpart);
            diff = lpart - rpart;
            exchange(nums, L, R, same);
        }
    }

    public static void exchange(int[] nums, int start, int end, int size) {
        int i = end - size + 1;
        int tmp = 0;
        while (size-- != 0) {
            tmp = nums[start];
            nums[start] = nums[i];
            nums[i] = tmp;
            start++;
            i++;
        }
    }
}
