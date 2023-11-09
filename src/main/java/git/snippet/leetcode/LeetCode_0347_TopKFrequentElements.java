package git.snippet.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
// 示例 1:
// 输入: nums = [1,1,1,2,2,3], k = 2
// 输出: [1,2]
// 示例 2:
// 输入: nums = [1], k = 1
// 输出: [1]
// 提示：
// 1 <= nums.length <= 10^5
// k 的取值范围是 [1, 数组中不相同的元素的个数]
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
// 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n是数组大小。
// Leetcode题目 : https://leetcode.com/problems/top-k-frequent-elements/
// https://leetcode.cn/problems/top-k-frequent-elements/
// 笔记：https://www.cnblogs.com/greyzeng/p/16469101.html
// Hash表词频 + 小根堆（拿次数排序，大小是K就开始收集）
public class LeetCode_0347_TopKFrequentElements {

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
}
