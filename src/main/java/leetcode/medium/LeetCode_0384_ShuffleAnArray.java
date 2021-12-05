/*
Shuffle a set of numbers without duplicates.

        Example:

// Init an array with set 1, 2, and 3.
        int[] nums = {1,2,3};
        Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
        solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
        solution.reset();

// Returns the random shuffling of array [1,2,3].
        solution.shuffle();*/
package leetcode.medium;

public class LeetCode_0384_ShuffleAnArray {
    public class Solution {
        private int[] origin;
        private int[] shuffle;
        private int N;

        public Solution(int[] nums) {
            origin = nums;
            N = nums.length;
            shuffle = new int[N];
            System.arraycopy(nums, 0, shuffle, 0, N);
        }

        /**
         * Resets the array to its original configuration and return it.
         */
        public int[] reset() {
            return origin;
        }

        /**
         * Returns a random shuffling of the array.
         */
        public int[] shuffle() {
            for (int i = N; i >= 1; i--) {
                swap(shuffle, i - 1, f(i));
            }
            return shuffle;
        }

        public void swap(int[] arr, int i, int j) {
            if (arr == null || arr.length <= 1 || i == j) {
                return;
            }
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];

        }

        // [0,N-1]
        private int f(int N) {
            return (int) (Math.random() * N);
        }
    }


}
