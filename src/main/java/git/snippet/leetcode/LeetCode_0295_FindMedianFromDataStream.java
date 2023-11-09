package git.snippet.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

// 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
// 例如，
// [2,3,4] 的中位数是 3
// [2,3] 的中位数是 (2 + 3) / 2 = 2.5
// 设计一个支持以下两种操作的数据结构：
// void addNum(int num) - 从数据流中添加一个整数到数据结构中。
// double findMedian() - 返回目前所有元素的中位数
// 一个大根堆，一个小根堆 size维持差值<2
// 笔记：https://www.cnblogs.com/greyzeng/p/16479520.html
public class LeetCode_0295_FindMedianFromDataStream {
    public static class MedianFinder {

        private final PriorityQueue<Integer> minHeap;
        private final PriorityQueue<Integer> maxHeap;

        public MedianFinder() {
            minHeap = new PriorityQueue<>(Comparator.comparingInt((Integer o) -> o));
            maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        }

        public void addNum(int num) {
            if (maxHeap.isEmpty()) {
                maxHeap.add(num);
            } else {
                if (maxHeap.peek() >= num) {
                    maxHeap.add(num);
                } else {
                    minHeap.add(num);
                }
            }
            int maxHeapSize = maxHeap.size();
            int minHeapSize = minHeap.size();
            if (maxHeapSize - minHeapSize == 2) {
                minHeap.add(maxHeap.poll());
            } else if (minHeapSize - maxHeapSize == 2) {
                maxHeap.add(minHeap.poll());
            }
        }

        public double findMedian() {
            if (maxHeap.size() == minHeap.size()) {
                return (maxHeap.peek() + minHeap.peek()) / 2d;
            }
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            }
            return minHeap.peek();
        }
    }
}
