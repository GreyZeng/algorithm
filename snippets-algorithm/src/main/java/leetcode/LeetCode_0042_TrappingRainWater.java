package leetcode;

public class LeetCode_0042_TrappingRainWater {

	// 双指针
	public static int trap(int[] arr) {
		if (arr == null || arr.length < 3) {
			return 0;
		}
		int N = arr.length - 1;
		int LMAX = arr[0];
		int RMAX = arr[N];
		int L = 1;
		int R = arr.length - 2;
		int res = 0;
		while (L <= R) {
			if (LMAX < RMAX) {
				if (arr[L] < LMAX) {
					res += (LMAX - arr[L++]);
				} else {
					LMAX = Math.max(LMAX, arr[L++]);
				}
			} else {
				if (arr[R] < RMAX) {
					res += (RMAX - arr[R--]);
				} else {
					RMAX = Math.max(RMAX, arr[R--]);
				}
			}
		}
		return res;
	}

}
