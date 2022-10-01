package leetcode.medium;

import java.util.ArrayList;
import java.util.TreeMap;
// TODO
//给定一个未排序的整数数组，找到最长递增子序列的个数。
//		示例 1:
//		输入: [1,3,5,4,7]
//		输出: 2
//		解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
//		示例 2:
//		输入: [2,2,2,2,2]
//		输出: 5
//		解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
//		注意:给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
//		Leetcode题目 : https://leetcode.com/problems/number-of-longest-increasing-subsequence/
public class LeetCode_0673_NumberOfLongestIncreasingSubsequence {

	// 好理解的方法，时间复杂度O(N^2)
	public static int findNumberOfLIS1(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int n = nums.length;
		int[] lens = new int[n];
		int[] cnts = new int[n];
		lens[0] = 1;
		cnts[0] = 1;
		int maxLen = 1;
		int allCnt = 1;
		for (int i = 1; i < n; i++) {
			int preLen = 0;
			int preCnt = 1;
			for (int j = 0; j < i; j++) {
				if (nums[j] >= nums[i] || preLen > lens[j]) {
					continue;
				}
				if (preLen < lens[j]) {
					preLen = lens[j];
					preCnt = cnts[j];
				} else {
					preCnt += cnts[j];
				}
			}
			lens[i] = preLen + 1;
			cnts[i] = preCnt;
			if (maxLen < lens[i]) {
				maxLen = lens[i];
				allCnt = cnts[i];
			} else if (maxLen == lens[i]) {
				allCnt += cnts[i];
			}
		}
		return allCnt;
	}

	// 优化后的最优解，时间复杂度O(N*logN)
	public static int findNumberOfLIS2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		ArrayList<TreeMap<Integer, Integer>> dp = new ArrayList<>();
		int len = 0;
		int cnt = 0;
		for (int num : nums) {
			// num之前的长度，num到哪个长度len+1
			len = search(dp, num);
			// cnt : 最终要去加底下的记录，才是应该填入的value
			if (len == 0) {
				cnt = 1;
			} else {
				TreeMap<Integer, Integer> p = dp.get(len - 1);
				cnt = p.firstEntry().getValue() - (p.ceilingKey(num) != null ? p.get(p.ceilingKey(num)) : 0);
			}
			if (len == dp.size()) {
				dp.add(new TreeMap<Integer, Integer>());
				dp.get(len).put(num, cnt);
			} else {
				dp.get(len).put(num, dp.get(len).firstEntry().getValue() + cnt);
			}
		}
		return dp.get(dp.size() - 1).firstEntry().getValue();
	}

	// 二分查找，返回>=num最左的位置
	public static int search(ArrayList<TreeMap<Integer, Integer>> dp, int num) {
		int l = 0, r = dp.size() - 1, m = 0;
		int ans = dp.size();
		while (l <= r) {
			m = (l + r) / 2;
			if (dp.get(m).firstKey() >= num) {
				ans = m;
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return ans;
	}

}
