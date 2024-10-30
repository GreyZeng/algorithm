package grey.algorithm;

// 笔记：https://www.cnblogs.com/greyzeng/p/16739515.html 快速排序
// partition过程
// 给定一个数组arr，和一个整数num。请把小于num的数放在数组的左边，等于num的数放在中间，大于num的数放在数组的右边。
// 要求额外空间复杂度O(1)，时间复杂度O(N)
// 快速排序3.0(随机快排+荷兰国旗技巧优化)
// 在arr[L..R]范围上，进行快速排序的过程：
// 0）在这个范围上，随机选一个数记为num，
// 1）用num对该范围做partition，< num的数在左部分，== num的数中间，>num的数在右部分。假设== num的数所在范围是[a,b]
// 2）对arr[L..a-1]进行快速排序(递归)
// 3）对arr[b+1..R]进行快速排序(递归) 因为每一次partition都会搞定一批数的位置且不会再变动，所以排序能完成
// 1）通过分析知道，划分值越靠近中间，性能越好；越靠近两边，性能越差
// 2）随机选一个数进行划分的目的就是让好情况和差情况都变成概率事件
// 3）把每一种情况都列出来，会有每种情况下的时间复杂度，但概率都是1/N
// 4）那么所有情况都考虑，时间复杂度就是这种概率模型下的长期期望！
// 时间复杂度O(N*logN)，额外空间复杂度O(logN)都是这么来的。
public class Code_0027_QuickSort {
	private static int first = 0;
	private static int last = 0;

	// 快速排序
	// 测评链接：https://www.lintcode.com/problem/464
	public static void sortIntegers2(int[] arr) {
		if (arr == null || arr.length <= 1) {
			return;
		}
		// [0,n)
		quickSort(arr, 0, arr.length - 1);
	}

	public static void quickSort(int[] arr, int l, int r) {
		if (l >= r) {
			return;
		}
		int i = l + (int) (Math.random() * (r - l + 1));
		sortColors(arr, l, r, arr[i]);
		int left = first;
		int right = last;
		quickSort(arr, l, left - 1);
		quickSort(arr, right + 1, r);
	}

	// 荷兰国旗问题
	// 在 s...e 范围内
	public static void sortColors(int[] arr, int l, int r, int p) {
		first = l ;
		last = r;
		int i = l;
		while (i <= last) {
			if (arr[i] < p) {
				// 小于区域
				swap(arr, i++, first++);
			} else if (arr[i] == p) {
				// 等于区域
				i++;
			} else {
				// 大于区域
				swap(arr, i, last--);
			}
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 9, 6, 7, 5, 3, 1, 3, 9 };
		sortIntegers2(arr);
		for (int v : arr) {
			System.out.println(v);
		}
	}

}
