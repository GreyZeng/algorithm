package class_2022_08_3_week;

// 给你一个数组 nums，我们可以将它按一个非负整数 k 进行轮调，
// 例如，数组为 nums = [2,4,1,3,0]，
// 我们按 k = 2 进行轮调后，它将变成 [1,3,0,2,4]。
// 这将记为 3 分，
// 因为 1 > 0 [不计分]、3 > 1 [不计分]、0 <= 2 [计 1 分]、
// 2 <= 3 [计 1 分]，4 <= 4 [计 1 分]。
// 在所有可能的轮调中，返回我们所能得到的最高分数对应的轮调下标 k 。
// 如果有多个答案，返回满足条件的最小的下标 k 。
// 测试链接 : 
// https://leetcode.cn/problems/smallest-rotation-with-highest-score/
public class Code05_SmallestRotationWithHighestScore {

	public static int bestRotation(int[] nums) {
		int n = nums.length;
		
		// cnt : 差分数组
		// cnt最后进行前缀和的加工！
		// 加工完了的cnt[0] :  整体向右移动0的距离, 一共能得多少分
		// 加工完了的cnt[i] :  整体向右移动i的距离, 一共能得多少分
		int[] cnt = new int[n + 1];
		for (int i = 0; i < n; i++) {
			// 遍历每个数！
			// 看看每个数，对差分数组哪些范围，会产生影响!
			if (nums[i] < n) {
				if (i <= nums[i]) {
					add(cnt, nums[i] - i, n - i - 1);
				} else {
					add(cnt, 0, n - i - 1);
					add(cnt, n - i + nums[i], n - 1);
				}
			}
		}
		for (int i = 1; i <= n; i++) {
			cnt[i] += cnt[i - 1];
		}
		// 最大得分是啥！已经求出来了
		int max = cnt[0];
		int ans = 0;
		for (int i = n - 1; i >= 1; i--) {
			// 整体移动的i 0 n-1 n-2 n-3 1
			//         k 0  1   2   3   n-1
 			if (cnt[i] > max) {
				max = cnt[i];
				ans = i;
			}
		}
		return ans == 0 ? 0 : (n - ans);
	}

	public static void add(int[] cnt, int l, int r) {
		cnt[l]++;
		cnt[r + 1]--;
	}

}
