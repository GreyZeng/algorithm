package leetcode;

/**
 * 一个数组中有一种数出现K次，其他数都出现了M次，M > 1, K < M, 找到出现了K次的数，要求，额外空间复杂度O(1)，时间复杂度O(N)
 * @author Grey
 * @date 2021年7月18日 下午11:53:52
 * @since
 */
public class LeetCode_0137_SingleNumberII {
	public int singleNumber(int[] nums) {
		return km(nums, 1, 3);
	}

	public static int km(int[] arr, int k, int m) {
		int[] helper = new int[32];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < 32; j++) {
				helper[j] += ((arr[i] >> j) & 1);
			}
		}
		int ans = 0;
		for (int i = 0; i < 32; i++) {
			if (helper[i] % m == k) {
				ans |= (1 << i);
			}
		}
		return ans;
	}
}
