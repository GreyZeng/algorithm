package git.snippet.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

// 笔记：https://www.cnblogs.com/greyzeng/p/16933830.html
// 已知一个几乎有序的数组。
// 几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的。
// 请选择一个合适的排序策略，对这个数组进行排序。(从小到大)
// tips: 加k个数进堆，然后再加入一个，弹出一个，最后堆里面剩下的继续弹出即可 时间复杂度：O(N*logK)
public class DistanceLessK {
	public static void sortedArrDistanceLessK(int[] arr, int k) {
		k = Math.min(arr.length - 1, k);
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		int i = 0;
		for (; i < k + 1; i++) {
			heap.offer(arr[i]);
		}
		int index = 0;
		// O(N*logK)
		for (; i < arr.length; i++) {
			heap.offer(arr[i]);
			// 移动一定不会超过K次
			if (!heap.isEmpty()) {
				arr[index++] = heap.poll();
			}
		}
		while (!heap.isEmpty()) {
			arr[index++] = heap.poll();
		}
	}

	public static void main(String[] args) {
		System.out.println("test begin");
		sortedArrDistanceLessK();
		System.out.println("test end");
	}

	public static void sortedArrDistanceLessK() {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		for (int i = 0; i < testTime; i++) {
			int k = (int) (Math.random() * maxSize) + 1;
			int[] arr = randomArrayNoMoveMoreK(maxSize, maxValue, k);
			int[] arr1 = copyArray(arr);
			int[] arr2 = copyArray(arr);
			DistanceLessK.sortedArrDistanceLessK(arr1, k);
			Arrays.sort(arr2);
			if (!assertArrayEquals(arr1, arr2)) {
				System.out.println("出错了...");
				break;
			}
		}
	}

	public static boolean assertArrayEquals(int[] arr1, int[] arr2) {
		if (arr1 == null) {
			return arr2 == null;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;

	}

	// for test
	// 几乎有序的数组排序测试
	public static int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int K) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		// 先排个序
		Arrays.sort(arr);
		// 然后开始随意交换，但是保证每个数距离不超过K
		// swap[i] == true, 表示i位置已经参与过交换
		// swap[i] == false, 表示i位置没有参与过交换
		boolean[] isSwap = new boolean[arr.length];
		for (int i = 0; i < arr.length; i++) {
			int j = Math.min(i + (int) (Math.random() * (K + 1)), arr.length - 1);
			if (!isSwap[i] && !isSwap[j]) {
				isSwap[i] = true;
				isSwap[j] = true;
				int tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
			}
		}
		return arr;
	}

	public static int[] copyArray(int[] arr1) {
		if (arr1 == null) {
			return null;
		}
		int[] arr2 = new int[arr1.length];
		System.arraycopy(arr1, 0, arr2, 0, arr1.length);
		return arr2;
	}
}
