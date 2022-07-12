/*
Given a non-empty array of integers, return the k most frequent elements.

        Example 1:

        Input: nums = [1,1,1,2,2,3], k = 2
        Output: [1,2]
        Example 2:

        Input: nums = [1], k = 1
        Output: [1]
        Note:

        You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
        Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
        It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
        You can return the answer in any order.
*/
package leetcode.medium;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// https://leetcode.cn/problems/top-k-frequent-elements/
// 笔记：https://www.cnblogs.com/greyzeng/p/16469101.html
// Hash表词频 + 小根堆（拿次数排序，大小是K就开始收集）
public class LeetCode_0347_TopKFrequentElements {

    public static class Node {
        // 值
        public int v;
        // 次数
        public int t;

        public Node(int value, int times) {
            v = value;
            t = times;
        }
    }

    public static int[] topKFrequent(int[] arr, int k) {
        if (arr == null || arr.length == 0 || arr.length < k) {
            return null;
        }
        Map<Integer, Node> freqMap = new HashMap<>();
        for (int n : arr) {
            if (freqMap.containsKey(n)) {
                freqMap.get(n).t++;
            } else {
                freqMap.put(n, new Node(n, 1));
            }
        }
        // 字符种类没有k个，无法得到结果
        if (freqMap.size() < k) {
            return null;
        }
        int[] ans = new int[k];
        PriorityQueue<Node> topK = new PriorityQueue<>(k, Comparator.comparingInt(o -> o.t));
        for (Map.Entry<Integer, Node> entry : freqMap.entrySet()) {
            if (topK.size() <= k || topK.peek().t < entry.getValue().t) {
                topK.offer(entry.getValue());
            }
            if (topK.size() > k) {
                topK.poll();
            }
        }
        int i = 0;
        while (!topK.isEmpty()) {
            ans[i++] = topK.poll().v;
        }
        return ans;
    }
}
