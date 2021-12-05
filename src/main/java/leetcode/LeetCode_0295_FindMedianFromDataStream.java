/*Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
So the median is the mean of the two middle value.

        For example,
        [2,3,4], the median is 3

        [2,3], the median is (2 + 3) / 2 = 2.5

        Design a data structure that supports the following two operations:

        void addNum(int num) - Add a integer number from the data stream to the data structure.
        double findMedian() - Return the median of all elements so far.


        Example:

        addNum(1)
        addNum(2)
        findMedian() -> 1.5
        addNum(3)
        findMedian() -> 2


        Follow up:

        If all integer numbers from the stream are between 0 and 100, how would you optimize it?
        If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?*/
package leetcode;


import java.util.Comparator;
import java.util.PriorityQueue;

// 一个大根堆，一个小根堆  size维持差值<2
public class LeetCode_0295_FindMedianFromDataStream {
    public static class MedianFinder {
        public static class MinHeap implements Comparator<Integer> {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        }

        public static class MaxHeap implements Comparator<Integer> {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        }

        private PriorityQueue<Integer> minHeap;
        private PriorityQueue<Integer> maxHeap;

        public MedianFinder() {
            minHeap = new PriorityQueue<>(new MinHeap());
            maxHeap = new PriorityQueue<>(new MaxHeap());
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