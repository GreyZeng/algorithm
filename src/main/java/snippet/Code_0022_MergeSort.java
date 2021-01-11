package snippet;

/**
 * 归并排序 1）整体是递归，左边排好序+右边排好序+merge让整体有序 2）让其整体有序的过程里用了排外序方法 3）利用master公式来求解时间复杂度
 * 4）当然可以用非递归实现
 * <p>
 * <p>
 * T(N) = 2*T(N/2) + O(N^1)
 * <p>
 * 根据master可知时间复杂度为O(N*logN)
 * <p>
 * merge过程需要辅助数组，所以额外空间复杂度为O(N)
 * <p>
 * 归并排序的实质是把比较行为变成了有序信息并传递，比O(N^2)的排序快
 */
public class Code_0022_MergeSort {
	public static void sort(int[] arr) {
		if (arr == null || arr.length <= 1) {
			return;
		}
		process(arr, 0, arr.length - 1);
	}

	private static void process(int[] arr, int L, int R) {
		if (L == R) {
			return;
		}
		int M = L + ((R - L) >> 1);
		process(arr, L, M);
		process(arr, M + 1, R);
		merge(arr, L, M, R);
	}

	private static void merge(int[] arr, int L, int M, int R) {
		int len = R - L + 1;
		int l = L;
		int mid = M + 1;
		int[] help = new int[len];
		int i = 0;
		while (l <= M && mid <= R) {
			help[i++] = arr[l] > arr[mid] ? arr[mid++] : arr[l++];
		}
		while (l <= M) {
			help[i++] = arr[l++];
		}
		while (mid <= R) {
			help[i++] = arr[mid++];
		}
		i = 0;
		for (int t : help) {
			arr[L + (i++)] = t;
		}
	}

	public static void main(String[] args) {
		int[] nums = { 1, 3, 2, 4, -4, 9, 7, -1 };
		sort(nums);
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
	}

}
