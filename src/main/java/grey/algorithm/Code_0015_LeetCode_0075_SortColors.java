package grey.algorithm;

// 笔记：https://www.cnblogs.com/greyzeng/p/16739515.html
// 荷兰国旗问题
// 仅0，1，2，所有的0放左边，所有的1放中间，所有的2放右边
// https://leetcode.com/problems/sort-colors/
public class Code_0015_LeetCode_0075_SortColors {
	public static void sortColors(int[] arr) {
		int l = -1; // 0 ~ l 是小于区域
		int r = arr.length; // l ~ r 是等于区域 ， r ~ arr.length - 1 是大于区域
		int i = 0;
		while (i < r) {
			if (arr[i] == 0) {
				swap(arr, ++l, i++);
			} else if (arr[i] == 1) {
				i++;
			} else {
				// i == 2
				swap(arr, --r, i);
			}
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

}
