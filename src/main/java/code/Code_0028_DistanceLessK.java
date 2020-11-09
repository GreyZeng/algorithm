package code;

import java.util.PriorityQueue;

/**
 * 与堆有关的题目 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的。
 * <p>
 * 请选择一个合适的排序策略，对这个数组进行排序。
 */
public class Code_0028_DistanceLessK {
	public static void sortedArrDistanceLessK(int[] arr, int k) {
		k = Math.min(arr.length - 1, k);
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		int i = 0;
		for (; i <= k; i++) {
			heap.add(arr[i]);
		}

		int index = 0;
		for (; i < arr.length && index < arr.length; i++, index++) {
			heap.offer(arr[i]);
			arr[index] = heap.poll();
		}
		while (!heap.isEmpty() ) {
			arr[index++] = heap.poll();
		}
	}

}
