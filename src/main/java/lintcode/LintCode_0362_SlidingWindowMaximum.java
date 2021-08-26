package lintcode;

import java.util.LinkedList;
import java.util.List;

// https://www.lintcode.com/problem/362/
// 给出一个可能包含重复的整数数组，和一个大小为 k 的滑动窗口, 
// 从左到右在数组中滑动这个窗口，找到数组中每个窗口内的最大值。
public class LintCode_0362_SlidingWindowMaximum {
	/**
	 * @param nums: A list of integers.
	 * @param k:    An integer
	 * @return: The maximum number inside the window at each moving.
	 */
	public List<Integer> maxSlidingWindow(int[] nums, int k) {
		List<Integer> list = new LinkedList<>();
		if (null == nums || 0 == nums.length) {
			return list;
		}
		if (k == 1) {
			for (int i = 0; i < nums.length; i++) {
				list.add(nums[i]);
			}
			return list;
		}

		if (k >= nums.length) {
			int max = nums[0];
			for (int i = 1; i < nums.length; i++) {
				max = Math.max(max, nums[i]);
			}
			list.add(max);
			return list;
		}
		int i = 0;
		int len = nums.length; 
		// 头到尾是从大到小
		LinkedList<Integer> qMax = new LinkedList<>();
		while (i < len) {
			while (!qMax.isEmpty() && nums[qMax.peekFirst()] <= nums[i]) {
				qMax.pollFirst();
			}
			qMax.addFirst(i);
			if (qMax.peekLast() == i - k) {
				qMax.pollLast();
			}

			if (i >= k - 1) {
				list.add(nums[qMax.peekLast()]);
			}
			i++;
		}
		return list;
	}
}
