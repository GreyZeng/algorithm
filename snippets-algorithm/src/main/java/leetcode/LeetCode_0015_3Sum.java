package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 复习二元组相加等于某个值的算法
// 1. 排序
// 2. L指针往右动 R往左动
// L + R
// < K L++
// > K R--
// == K 收集答案，L不等于左边的数 L++
// 以上方案比较适合顺序数组
// 如果是不顺序数组，hash表也可以

// 三元组
// 1. 排序
// 遍历每个元素，然后取出这个元素，然后剩下的数调用二元组的算法 算 其余元素相加 和这个元素之和为目标元素的答案
// 从右往左 ，可以优化效率     因为arraylist
public class LeetCode_0015_3Sum {

	public static List<List<Integer>> threeSum(int[] nums) {
		if (null == nums) {
			return null;
		}
		if (nums.length < 3) {
			return new ArrayList<>();
		}
		return threeSum(nums, 0);
	}

	public static List<List<Integer>> threeSum(int[] nums, int target) {
		if (null == nums) {
			return null;
		}
		if (nums.length < 3) {
			return new ArrayList<>();
		}
		return threeSum(nums, 0, nums.length - 1, target);
	}

	public static List<List<Integer>> threeSum(int[] nums, int start, int end, int target) {
		if (null == nums) {
			return null;
		}
		if (nums.length < 3 || start < 0 || end > nums.length - 1 || start >= end) {
			return new ArrayList<>();
		}

		Arrays.sort(nums, start, end + 1);
		List<List<Integer>> res = new ArrayList<>();
		for (int i = end; i >= start + 2; i--) {
			if (nums[i] < target / 3) {
				return res;
			}
			if (i == end || nums[i] != nums[i + 1]) {
				List<List<Integer>> rest = twoSum(nums, start, i - 1, target - nums[i]);
				if (null != rest) {
					for (List<Integer> item : rest) {
						item.add(nums[i]);
						res.add(item);
					}
				}
			}
		}

		return res;
	}

	// 数组中，求两个元素之和是target的所有序列（需要去重）
	public static List<List<Integer>> twoSum(int[] nums, int target) {
		if (null == nums) {
			return null;
		}
		if (nums.length < 2) {
			return new ArrayList<>();
		}
		return twoSum(nums, 0, nums.length - 1, target);
	}

	public static List<List<Integer>> twoSum(int[] nums, int start, int end, int target) {
		if (null == nums) {
			return null;
		}
		if (nums.length == 1 || start >= end || start < 0 || end > nums.length - 1) {
			return new ArrayList<>();
		}

		// 先排序数组
		Arrays.sort(nums, start, end + 1);
		List<List<Integer>> res = new ArrayList<>();
		int L = start;
		int R = end;
		while (L != R) {
			if (nums[L] + nums[R] < target) {
				L++;
			} else if (nums[L] + nums[R] > target) {
				R--;
			} else {
				if (L == start || nums[L - 1] != nums[L]) {
					List<Integer> item = new ArrayList<>();
					item.add(nums[L]);
					item.add(nums[R]);
					res.add(item);
				}
				L++;
				R--;
				if (R <= L) {
					break;
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int[] nums = new int[] { -4, -1, -1, 0, 1, 2 };
		Arrays.sort(nums);
		// System.out.println(twoSum(nums,1,5, 0));
		System.out.println(threeSum(nums, 0));
	}
}
