package grey.algorithm;

// 笔记：https://www.cnblogs.com/greyzeng/p/16739515.html
// 荷兰国旗问题
// 仅0，1，2，所有的0放左边，所有的1放中间，所有的2放右边
// https://leetcode.com/problems/sort-colors/
public class Code_0026_LeetCode_0075_SortColors {
	public static void sortColors(int[] arr) {
		int l = -1;
		int r = arr.length;
		int i = 0;
		while (i < r) {
			if (arr[i] == 0) {
				// 小于区域
				swap(arr, i++, ++l);
			} else if (arr[i] == 1) {
				// 等于区域
				i++;
			} else {
				// 大于区域
				swap(arr, i, --r);
			}
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void main(String[] args) {
		int[] arr = { 0, 0, 1, 2, 2, 2, 1, 0 };
		sortColors(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

}
