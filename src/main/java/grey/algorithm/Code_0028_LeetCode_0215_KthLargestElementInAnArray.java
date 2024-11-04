package grey.algorithm;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 无序数组中求第K大的数，K从1开始算
 * <p>
 * 测试链接 : https://leetcode.com/problems/kth-largest-element-in-an-array/
 * <p>
 * <p>
 * 方法0. 大根堆 O(N*logK)
 *
 * <p>
 * 方法1. 快排改进 (概率收敛到O(N), 空间复杂度O(1))
 *
 * <p>
 * 方法2. bfprt算法 (严格收敛到O(N),但是空间复杂度O(N))
 */
public class Code_0028_LeetCode_0215_KthLargestElementInAnArray {

    // 快排改进算法(时间复杂度O(N))
    public static int findKthLargest(int[] arr, int k) {
        // ！！！不要陷入如下几个边界的纠结之中
        // 1. 这里是 k - 1 还是 k ，
        // 2. 从 0 开始还是 从 1 开始，
        // 3. 第 k 大还是第 k 小
        // 先考虑思路：
        // 先不要纠结第K小还是第K大，
        // 先把递归函数和partion方法写出来，再用几个例子去判断一下，
        // 最后再定主方法是 调用 k 还是 k - 1
        return p(arr, 0, arr.length - 1, k - 1);
    }

    public static int p(int[] arr, int l, int r, int k) {
        if (l >= r) {
            return arr[l];
        }
        int p = arr[l + (int) (Math.random() * (r - l + 1))];
        int[] range = p1(arr, l, r, p);
        if (k >= range[0] && k <= range[1]) {
            return p;
        } else if (k < range[0]) {
            return p(arr, l, range[0] - 1, k);
        } else {
            // k > range[1]
            return p(arr, range[1] + 1, r, k);
        }
    }

    public static int[] p1(int[] arr, int l, int r, int p) {
        int s = l - 1;
        int e = r + 1;
        int i = l;
        while (i < e) {
            if (arr[i] > p) {
                swap(arr, i++, ++s);
            } else if (arr[i] < p) {
                swap(arr, i, --e);
            } else {
                i++;
            }
        }
        return new int[]{s + 1, e - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        while (L < more) {
            if (arr[L] > pivot) {
                swap(arr, L++, ++less);
            } else if (arr[L] == pivot) {
                L++;
            } else {
                swap(arr, L, --more);
            }
        }
        return new int[]{less + 1, more - 1};
    }


    // bfprt算法
    public static int findKthLargest1(int[] nums, int k) {
        return bfprt(nums, 0, nums.length - 1, k - 1);
    }

    private static int bfprt(int[] nums, int L, int R, int index) {
        if (L == R) {
            return nums[L];
        }
        int pivot = medianOfMedians(nums, L, R);
        int[] range = partition(nums, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return pivot;
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

    // 第K大 --> 第 (len - K + 1) 小
    // k需要小于nums.length ，否则没有意义
    public static int findKthLargest3(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            heap.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            heap.offer(nums[i]);
            heap.poll();
        }
        return heap.peek();
    }
}
