/*
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 *
 * Example 1:
 *
 * Input: nums = [1, 5, 1, 1, 6, 4] Output: One possible answer is [1, 4, 1, 5, 1, 6]. Example 2:
 *
 * Input: nums = [1, 3, 2, 2, 3, 1] Output: One possible answer is [2, 3, 1, 3, 1, 2]. Note: You may
 * assume all input has valid answer.
 *
 * Follow Up: Can you do it in O(n) time and/or in-place with O(1) extra space?
 */
package git.snippet.leetcode;

// 时间复杂度O(N)，额外空间复杂度O(1)
// 找中位数 荷兰国旗问题
// 完美洗牌问题
public class LeetCode_0324_WiggleSortII {

    public static void wiggleSort(int[] nums) {

        if (nums == null || nums.length < 2) {
            return;
        }
        int N = nums.length;
        findMedian(nums, 0, N - 1, N / 2);
        if ((N & 1) == 0) {
            shuffle(nums, 0, N - 1);
            reverse(nums, 0, N - 1);
        } else {
            shuffle(nums, 1, N - 1);
        }
    }

    public static void findMedian(int[] nums, int L, int R, int index) {
        while (L < R) {
            int pivot = nums[L + (int) (Math.random() * (R - L + 1))];
            int[] range = partition(nums, L, R, pivot);
            if (index >= range[0] && index <= range[1]) {
                return;
            } else if (index > range[1]) {
                L = range[1] + 1;
            } else {
                R = range[0] - 1;
            }
        }
    }

    public static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, cur++, ++less);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] nums, int L, int R) {
        if (nums == null || nums.length <= 1 || R == L) {
            return;
        }
        nums[L] = nums[L] ^ nums[R];
        nums[R] = nums[L] ^ nums[R];
        nums[L] = nums[L] ^ nums[R];
    }

    public static void shuffle(int[] arr, int L, int R) {

        while (R - L + 1 > 0) {
            int len = R - L + 1;
            int base = 3;
            int k = 1;
            while (base <= (len + 1) / 3) {
                base *= 3;
                k++;
            }
            int half = (base - 1) / 2;
            int mid = (L + R) / 2;
            rotate(arr, L + half, mid, mid + half);
            toNext(arr, L, base - 1, k);
            L = L + base - 1;
        }
    }

    // i位置下一个位置应该去哪里
    private static int findNextIndex(int i, int N) {
        if (i <= N / 2) {
            return 2 * i;
        }
        return (i - N / 2) * 2 - 1;
    }

    private static void toNext(int[] arr, int start, int len, int k) {
        for (int i = 0, trigger = 1; i < k; i++, trigger *= 3) {
            int pre = arr[start + trigger - 1];
            int next = findNextIndex(trigger, len);
            while (next != trigger) {
                int t = arr[next + start - 1];
                arr[next + start - 1] = pre;
                pre = t;
                next = findNextIndex(next, len);
            }
            arr[next + start - 1] = pre;
        }
    }

    // L..R做逆序调整
    public static void reverse(int[] arr, int L, int R) {
        while (L < R) {
            swap(arr, L++, R--);
        }
    }

    // L..M部分和M+1..R部分互换
    public static void rotate(int[] arr, int L, int M, int R) {
        reverse(arr, L, M);
        reverse(arr, M + 1, R);
        reverse(arr, L, R);
    }
}
