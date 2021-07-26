package leetcode;

public class LeetCode_0034_FindFirstAndLastPositionOfElementInSortedArray {

    // 二分
    public static int[] searchRange(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return new int[]{-1, -1};
        }
        if (nums.length == 1) {
            return target == nums[0] ? new int[]{0, 0} : new int[]{-1, -1};
        }
        int first = findFirst(nums, target);
        if (first == -1) {
            return new int[]{-1, -1};
        }
        int second = findLast(nums, target);
        return new int[]{first, second};
    }

    private static int findFirst(int[] nums, int target) {
        int L = 0;
        int R = nums.length - 1;
        int M = (L + R) >> 1;
        int mostLeft = -1;
        while (L <= R) {
            if (target < nums[M]) {
                R = M - 1;
                M = (L + R) >> 1;
            } else if (target == nums[M]) {
                // 经典二分查找到这一步就可以直接返回M了，
                // 但是如果要求左边是不是还有满足条件的地方，则需要
                // 1. 记录一下当前的答案到mostLeft, 2. 继续在左边搜索
                mostLeft = M;
                R = M - 1;
                M = (L + R) >> 1;
            } else {
                L = M + 1;
                M = (L + R) >> 1;
            }
        }
        return mostLeft;
    }

    private static int findLast(int[] nums, int target) {
        int L = 0;
        int R = nums.length - 1;
        int M = (L + R) >> 1;
        int mostRight = -1;
        while (L <= R) {
            if (target < nums[M]) {
                R = M - 1;
                M = (L + R) >> 1;
            } else if (target == nums[M]) {
                mostRight = M;
                L = M + 1;
                M = (L + R) >> 1;
            } else {
                L = M + 1;
                M = (L + R) >> 1;
            }
        }
        return mostRight;
    }
}
