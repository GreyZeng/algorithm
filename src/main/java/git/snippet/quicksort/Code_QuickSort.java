package git.snippet.quicksort;

import java.util.Stack;

// 笔记：https://www.cnblogs.com/greyzeng/p/16739515.html 快速排序
// 测评：https://www.lintcode.com/problem/464
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
public class Code_QuickSort {

	// 递归方法
	public static void sort1(int[] a) {
		if (null == a || a.length <= 1) {
			return;
		}
		// quickSort(a, 0, a.length - 1);
		quickSort(a, 0, a.length - 1);
	}
	// 快排算法递归解法
	public static void quickSort(int[] arr, int l, int r) {
		if (null == arr || arr.length <= 1 || l >= r) {
			return;
		}
		// Math.random() -> [0,1) -> (int) (N * Math.random()) -> [0, N - 1]
		// [l, r] -> [l, r + 1) -> l + [0, r + 1 - l) -> l + (int)((r - l) *
		// Math.random())
		int pivot = l + (int) ((r - l) * Math.random());
		swap(arr, pivot, r);
		// 以arr[r]作为分界数
		int[] range = sortColor(arr, l, r);
		quickSort(arr, l, range[0] - 1);
		quickSort(arr, range[1] + 1, r);
	}

	public static int[] sortColor(int[] arr, int l, int r) {
		int smaller = l - 1;
		int bigger = r + 1;
		int cur = l;
		int pivot = arr[r];
		while (cur < bigger) {
			if (arr[cur] > pivot) {
				swap(arr, cur, --bigger);
			} else if (arr[cur] == pivot) {
				cur++;
			} else {
				swap(arr, cur++, ++smaller);
			}
		}
		return new int[] { smaller + 1, bigger - 1 };
	}

	// TODO
	// 快速排序非递归版本
	public static void sort2(int[] a) {
		if (null == a || a.length <= 1) {
			return;
		}
		int l = 0;
		int r = a.length - 1;
		int p = l + (int) (Math.random() * (r - l + 1));
		swap(a, p, r);
		int[] range = sortColor(a, l, r);
		Stack<Op> stack = new Stack<>();
		stack.push(new Op(l, range[0] - 1));
		stack.push(new Op(range[1] + 1, r));
		while (!stack.isEmpty()) {
			Op op = stack.pop();
			if (op.l < op.r) {
				swap(a, op.r, op.l + (int) (Math.random() * (op.r - op.l)));
				range = sortColor(a, op.l, op.r);
				stack.push(new Op(op.l, range[0] - 1));
				stack.push(new Op(range[1] + 1, op.r));
			}
		}
	}

	public static class Op {
		public int l;
		public int r;

		public Op(int l, int r) {
			this.l = l;
			this.r = r;
		}
	}

	public static void swap(int[] arr, int l, int r) {
		if (l != r) {
			arr[l] = arr[l] ^ arr[r];
			arr[r] = arr[l] ^ arr[r];
			arr[l] = arr[l] ^ arr[r];
		}
	}

}
