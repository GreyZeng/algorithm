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
import java.util.PriorityQueue;
import java.util.Set;

// Hash表词频    + 小根堆（拿次数排序，大小是K）
public class LeetCode_0347_TopKFrequentElements {

    public static class Node {
        public int value;
        public int times;

        public Node(int value, int times) {
            this.value = value;
            this.times = times;
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) {
            return null;
        }
        HashMap<Integer, Node> freq = new HashMap<>();
        for (int i : nums) {
            if (!freq.containsKey(i)) {
                freq.put(i, new Node(i, 1));
            } else {
                freq.put(i, new Node(i, freq.get(i).times + 1));
            }
        }
        PriorityQueue<Node> topK = new PriorityQueue<>(Comparator.comparingInt(o -> o.times));
        Set<Integer> keys = freq.keySet();
        for (Integer key : keys) {
            Node t = freq.get(key);
            // 没形成k个元素，或者达到了门槛要求，进入堆
            if (topK.size() <= k || topK.peek().times < t.times) {
                topK.add(t);
            }
            // 已经要超过k了，就每次弹出一个
            // 确保堆中保留k个元素
            if (topK.size() > k) {
                topK.poll();
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = topK.poll().value;
        }
        return res;
    }

}
