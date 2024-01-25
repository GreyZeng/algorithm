package git.snippet.heap;

import java.util.PriorityQueue;

// 笔记：https://www.cnblogs.com/greyzeng/p/16933830.html
// 已知一个几乎有序的数组。
// 几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的。
// 请选择一个合适的排序策略，对这个数组进行排序。(从小到大)
// tips: 加k个数进堆，然后再加入一个，弹出一个，最后堆里面剩下的继续弹出即可 时间复杂度：O(N*logK)
public class Code_DistanceLessK {
    public static void sortedArrDistanceLessK(int[] arr, int k) {
        k = Math.min(arr.length - 1, k);
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int i = 0;
        for (; i < k + 1; i++) {
            heap.offer(arr[i]);
        }
        int index = 0;
        for (; i < arr.length || !heap.isEmpty(); i++) {
            if (i < arr.length) {
                heap.offer(arr[i]);
            }
            arr[index++] = heap.poll();
        }
    }
}
