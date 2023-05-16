package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

// TODO
// 你的国家有无数个湖泊，所有湖泊一开始都是空的。当第 n 个湖泊下雨的时候，如果第 n 个湖泊是空的，那么它就会装满水，否则这个湖泊会发生洪水。你的目标是避免任意一个湖泊发生洪水。
// 给你一个整数数组 rains ，其中：
// rains[i] > 0 表示第 i 天时，第 rains[i] 个湖泊会下雨。
// rains[i] == 0 表示第 i 天没有湖泊会下雨，你可以选择 一个 湖泊并 抽干 这个湖泊的水。
// 请返回一个数组 ans ，满足：
// ans.length == rains.length
// 如果 rains[i] > 0 ，那么ans[i] == -1 。
// 如果 rains[i] == 0 ，ans[i] 是你第 i 天选择抽干的湖泊。
// 如果有多种可行解，请返回它们中的 任意一个 。如果没办法阻止洪水，请返回一个 空的数组 。
// leetcode题目：https://leetcode.cn/problems/avoid-flood-in-the-city/
// tips: 抽水取表里有，且离我最近的湖泊
public class LeetCode_1488_AvoidFloodInTheCity {

    // rains[i] = j 第i天轮到j号湖泊下雨
    // 规定，下雨日，干啥 : -1
    // 不下雨日，如果没有湖泊可抽 : 1
    public static int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        int[] invalid = new int[0];
        // key : 某个湖
        // value : 这个湖在哪些位置降雨
        // 4 : {3,7,19,21}
        // 1 : { 13 }
        // 2 : {4, 56}
        HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (rains[i] != 0) { // 第i天要下雨，rains[i]
                // 3天 9号
                // 9号 { 3 }
                // 9号 {1, 3}
                if (!map.containsKey(rains[i])) {
                    map.put(rains[i], new LinkedList<>());
                }
                map.get(rains[i]).addLast(i);
            }
        }
        // 没抽干的湖泊表
        // 某个湖如果满了，加入到set里
        // 某个湖被抽干了，从set中移除
        HashSet<Integer> set = new HashSet<>();
        // 这个堆的堆顶表示最先处理的湖是哪个
        PriorityQueue<Work> heap = new PriorityQueue<>();
        for (int i = 0; i < n; i++) { // 0 1 2 3 ...
            if (rains[i] != 0) {
                if (set.contains(rains[i])) {
                    return invalid;
                }
                // 放入到没抽干的表里
                set.add(rains[i]);
                map.get(rains[i]).pollFirst();
                if (!map.get(rains[i]).isEmpty()) {
                    heap.add(new Work(rains[i], map.get(rains[i]).peekFirst()));
                }
                // 题目规定
                ans[i] = -1;
            } else { // 今天干活！
                if (heap.isEmpty()) {
                    ans[i] = 1;
                } else {
                    Work cur = heap.poll();
                    set.remove(cur.lake);
                    ans[i] = cur.lake;
                }
            }
        }
        return ans;
    }

    public static class Work implements Comparable<Work> {
        public int lake;
        public int nextRain;

        public Work(int l, int p) {
            lake = l;
            nextRain = p;
        }

        @Override
        public int compareTo(Work o) {
            return nextRain - o.nextRain;
        }
    }

}
