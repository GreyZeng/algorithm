package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 方法0. 大根堆 O(N*logK)
 * 方法1. 快排改进 (概率收敛到O(N), 空间复杂度O(1))
 * 方法2. bfprt算法 (严格收敛到O(N),但是空间复杂度O(N))
 */
public class LeetCode_0215_KthLargestElementInAnArray {
    // bfprt算法
    public static int findKthLargest(int[] nums, int k) {
        return bfprt(nums, 0, nums.length - 1, k - 1);
    }

    private static int bfprt(int[] nums, int L, int R, int index) {
        if (L == R) {
            return nums[L];
        }
        int pivot = medianOfMedians(nums, L, R);
        int[] range = partition(nums, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return nums[index];
        } else if (index < range[0]) {
            return bfprt(nums, L, range[0] - 1, index);
        } else {
            return bfprt(nums, range[1] + 1, R, index);
        }
    }

    // 将arr分成每五个元素一组，不足一组的补齐一组
    // 对每组进行排序
    // 取出每组的中位数，组成一个新的数组
    // 对新的数组求其中位数，这个中位数就是我们需要的值
    public static int medianOfMedians(int[] arr, int L, int R) {
        int size = R - L + 1;
        int offSize = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + offSize];
        for (int i = 0; i < mArr.length; i++) {
            // 每一组的第一个位置
            int teamFirst = L + i * 5;
            int median = getMedian(arr, teamFirst, Math.min(R, teamFirst + 4));
            mArr[i] = median;
        }
        return bfprt(mArr, 0, mArr.length - 1, (mArr.length - 1) / 2);
    }

    public static int getMedian(int[] arr, int L, int R) {
        Arrays.sort(arr, L, R);
        return arr[(R + L) / 2];

    }
    // 快排改进算法
    // 第K小 == 第 nums.length - k 大
    public static int findKthLargest2(int[] nums, int k) {
        return p(nums, 0, nums.length - 1, k - 1);
    }

    // nums在L...R范围上，如果要排序的话，请返回index位置的值
    public static int p(int[] nums, int L, int R, int index) {
        if (L == R) {
            return nums[L];
        }
        int pivot = nums[L + (int) (Math.random() * (R - L + 1))];
        int[] range = partition(nums, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return nums[index];
        } else if (index < range[0]) {
            return p(nums, L, range[0] - 1, index);
        } else {
            return p(nums, range[1] + 1, R, index);
        }
    }

    private static int[] partition(int[] nums, int l, int r, int pivot) {
        int i = l;
        int more = l - 1;//大于区域
        int less = r + 1; // 小于区域
        while (i < less) {
            if (nums[i] > pivot) {
                swap(nums, i++, ++more);
            } else if (nums[i] < pivot) {
                swap(nums, i, --less);
            } else {
                i++;
            }
        }
        return new int[]{more + 1, less - 1};
    }

    public static void swap(int[] nums, int t, int m) {
        int tmp = nums[m];
        nums[m] = nums[t];
        nums[t] = tmp;
    }
    // 第K大 --> 第 (len - K + 1) 小
    public static int findKthLargest3(int[] nums, int k) {
        PriorityQueue<Integer> h = new PriorityQueue<>();
        int i = 0;
        while (i < k) {
            h.offer(nums[i++]);
        }
        while (i < nums.length) {
            h.offer(nums[i++]);
            h.poll();
        }
        return h.peek();
    }
}
