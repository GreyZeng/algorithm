package code;

/**
 * 计数排序 一般来讲，计数排序要求，样本是整数，且范围比较窄 桶排序思想下的排序：计数排序 & 基数排序
 * <p>
 * 1)桶排序思想下的排序都是不基于比较的排序
 * <p>
 * 2)时间复杂度为O(N)，额外空间负载度O(M)
 * <p>
 * 3)应用范围有限，需要样本的数据状况满足桶的划分
 */
public class Code_0031_CountSort {
	public static void countSort(int[] arr) {
		if (arr == null || arr.length <= 1) {
			return;
		}
		int max = Integer.MIN_VALUE;
		for (int v : arr) {
			max = Math.max(max, v);
		}
		int[] bullet = new int[max + 1];

		for (int v : arr) {
			bullet[v]++;
		}
		int i = 0;
		int index = 0;
		for (; i < max + 1; i++) {
			int times = bullet[i];
			while (times > 0) {
                arr[index++] = i;
                times--;
			}
		}
	}

}
