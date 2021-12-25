/*You are given an integer array nums and you have to return a new counts array. 

The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

 

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
// TODO 
// 改有序表
public class LeetCode_0315_CountOfSmallerNumbersAfterSelf {
	public static class Node {
		public int value;
		public int index;

		public Node(int index, int value) {
			this.value = value;
			this.index = index;
		}
	}

	// 思路转换为：一个数的右边有多少个数比它小！
	// 改归并排序（从大到小）
	public static List<Integer> countSmaller(int[] nums) {
		int size = nums.length;
		List<Integer> result = new ArrayList<>(size);
		Node[] nodes = new Node[size];
		for (int i = 0; i < size; i++) {
			result.add(0);
			nodes[i] = new Node(i, nums[i]);
		}
		process(nodes, 0, size - 1, result);
		return result;
	}

	private static void process(Node[] nodes, int L, int R, List<Integer> result) {
		if (L == R) {
			return;
		}
		int mid = L + ((R - L) >> 1);
		process(nodes, L, mid, result);
		process(nodes, mid + 1, R, result);
		merge(nodes, L, mid, R, result);
	}

	private static void merge(Node[] nodes, int l, int mid, int r, List<Integer> result) {
		Node[] help = new Node[r - l + 1];
		int s1 = l;
		int s2 = mid + 1;
		int index = 0;
		while (s1 <= mid && s2 <= r) {
			if (nodes[s1].value < nodes[s2].value) {
				help[index++] = nodes[s2++];
			} else if (nodes[s1].value > nodes[s2].value) {
				result.set(nodes[s1].index, result.get(nodes[s1].index) + 1 + r - s2);
				help[index++] = nodes[s1++];
			} else {
				help[index++] = nodes[s2++];
			}
		}
		while (s1 <= mid) {
			help[index++] = nodes[s1++];
		}
		while (s2 <= r) {
			help[index++] = nodes[s2++];
		}
		index = 0;
		for (Node v : help) {
			nodes[l + (index++)] = v;
		}
	}

	public static void main(String[] args) {
		int[] nums = { 5, 2, 6, 1 };
		List<Integer> result = countSmaller(nums);
		for (int num : result) {
			System.out.print(num);
		}
		System.out.println();
	}
}
