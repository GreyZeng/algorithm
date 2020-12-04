/*You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

 

Example 1:

Input: nums = [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
 

Constraints:

0 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4*/
package leetcode;

import java.util.ArrayList;
import java.util.List;

// 小和问题 改归并
// 改有序表
public class LeetCode_0315_CountOfSmallerNumbersAfterSelf {
	public static class Node {
		public int value;
		public int index;

		public Node(int v, int i) {
			value = v;
			index = i;
		}
	}

	public static List<Integer> countSmaller(int[] nums) {
		List<Integer> ans = new ArrayList<>();
		if (nums == null) {
			return ans;
		}
		for (int i = 0; i < nums.length; i++) {
			ans.add(0);
		}
		if (nums.length < 2) {
			return ans;
		}
		int N = nums.length;
		Node[] arr = new Node[N];
		for (int i = 0; i < N; i++) {
			arr[i] = new Node(nums[i], i);
		}
		process(arr, 0, N - 1, ans);
		return ans;
	}

	private static void process(Node[] nums, int L, int R, List<Integer> ans) {
		if (L == R) {
			return;
		}
		int M = L + ((R - L) >> 1);
		process(nums, L, M, ans);
		process(nums, M + 1, R, ans);
		merge(nums, L, M, R, ans);
	}

	private static void merge(Node[] nums, int L, int M, int R, List<Integer> ans) {
		int len = R - L + 1;
		int mid = M + 1;
		int l = L;
		Node[] help = new Node[len];
		int i = 0;
		while (l <= M && mid <= R) {
			if (nums[l].value > nums[mid].value) {
				ans.set(nums[l].index, ans.get(nums[l].index) + (R - mid) + 1);
			}
			help[i++] = nums[l].value > nums[mid].value ? nums[l++] : nums[mid++];

		}
		while (l <= M) {
			help[i++] = nums[l++];
		}
		while (mid <= R) {
			help[i++] = nums[mid++];
		}
		i = 0;
		for (Node t : help) {
			nums[L + (i++)] = t;
		}
	}

	public static void main(String[] args) {
		int[] nums = { 0, 2, 1 };
		List<Integer> list = countSmaller(nums);
		for (int i : list) {
			System.out.print(i + " ");
		}
	}
}
