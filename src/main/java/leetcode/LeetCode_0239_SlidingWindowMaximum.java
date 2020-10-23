package leetcode;

import java.util.LinkedList;

//You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
//
//        Return the max sliding window.
//
//
//
//        Example 1:
//
//        Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
//        Output: [3,3,5,5,6,7]
//        Explanation:
//        Window position                Max
//        ---------------               -----
//        [1  3  -1] -3  5  3  6  7       3
//        1 [3  -1  -3] 5  3  6  7       3
//        1  3 [-1  -3  5] 3  6  7       5
//        1  3  -1 [-3  5  3] 6  7       5
//        1  3  -1  -3 [5  3  6] 7       6
//        1  3  -1  -3  5 [3  6  7]      7
//        Example 2:
//
//        Input: nums = [1], k = 1
//        Output: [1]
//        Example 3:
//
//        Input: nums = [1,-1], k = 1
//        Output: [1,-1]
//        Example 4:
//
//        Input: nums = [9,11], k = 2
//        Output: [11]
//        Example 5:
//
//        Input: nums = [4,-2], k = 2
//        Output: [4]
//
//
//        Constraints:
//
//        1 <= nums.length <= 10^5
//        -10^4 <= nums[i] <= 10^4
//        1 <= k <= nums.length
public class LeetCode_0239_SlidingWindowMaximum {
    public static int[] maxSlidingWindow(int[] arr, int w) {
        if (null == arr) {
            return null;
        }
        int N = arr.length;
        if (w < 1 || N < w) {
            return null;
        }
        if (w == 1) {
            return arr;
        }
        LinkedList<Integer> q = new LinkedList<>();
        int[] res = new int[N - w + 1];
        // 双端队列的头部到尾部维持从大到小的顺序
        // 遍历数组，L。。R，其中0< L == (R - W) + 1
        // 移动R，如果arr【i】中的数字比双端队列队尾的数字大，
        // 那么则弹出双端队列尾部的元素，直到队尾元素不大于将要进来的元素
        // 移动L，如果对头数字是即将要过期的元素，则弹出队头元素
        // 遇到过期(i - w )下标要弹出，到第一次形成窗口的时候开始累加res
        int index = 0;
        for (int i = 0; i < N; i++) {
            while (!q.isEmpty() && arr[q.peekLast()] <= arr[i]) {
                q.pollLast();
            }
            q.addLast(i);
            if (q.peekFirst() == i - w) {
                q.pollFirst();
            }
            if (i >= w - 1) {
                res[index++] = arr[q.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] a = maxSlidingWindow(arr, k);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
