package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 方法0. 大根堆 O(N*logK)
 * 方法1. 快排改进 (概率收敛到O(N), 空间复杂度O(1))
 * 方法2. bfprt算法 (严格收敛到O(N),但是空间复杂度O(N))
 */
public class LeetCode_0215_KthLargestElementInAnArray {
    // bfprt算法
    public static int findKthLargest(int[] nums, int k) {
        // TODO
        return -1;
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


    public static class MyComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

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

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println(findKthLargest(nums, 1));
        System.out.println(findKthLargest(nums, 2));
        System.out.println(findKthLargest(nums, 3));
        System.out.println(findKthLargest(nums, 4));
        System.out.println(findKthLargest(nums, 5));
    }


}
