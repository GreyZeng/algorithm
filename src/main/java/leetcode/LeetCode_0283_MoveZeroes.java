package leetcode;

// 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
// 示例:
// 输入: [0,1,0,3,12]
// 输出: [1,3,12,0,0]
// 说明:
// 必须在原数组上操作，不能拷贝额外的数组。
// 尽量减少操作次数。
// Leetcode题目 : https://leetcode.com/problems/move-zeroes/
public class LeetCode_0283_MoveZeroes {
    // 双指针
    // 一个在-1位置，一个在0位置
    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return;
        }
        int N = nums.length;
        int base = -1;
        for (int i = 0; i < N; i++) {
            if (nums[i] != 0) {
                swap(nums, i, ++base);
            }
        }
    }

    public static void swap(int[] nums, int i, int j) {
        if (nums == null || nums.length < 2 || i == j) {
            return;
        }
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    public static void main(String[] args) {
        int[] nums = {5, 0, 3, 2, 0, 4, 0};
        moveZeroes(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }

}
