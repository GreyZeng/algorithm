package 练习题.leetcode.hard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// 有序表的使用
//tips:
//		最大高度的变化
//		开始变化的时候，最高高度开始变化，结算前一次的轮廓线
//		建立一组记录：
//		每个大楼的开始和结束点对应的最大高度
//		离散化处理
//
//		封装对象：
//		[3,7,6]  -> 3 + 6 , 7 - 6
//
//		第一个有序表 记录次数
//		第二个有序表 记录轮廓
//		为了防止纸片大楼，在排序的时候，把+放在-之前
// https://leetcode.com/problems/the-skyline-problem/
// lintcode上的用例存在纸片大楼，输出结果的要求有点差别
// https://www.lintcode.com/problem/131/
public class LeetCode_0218_TheSkylineProblem {
    public static class Node {
        public int x;
        public int height;

        public Node(int x, int height) {
            this.x = x;
            this.height = height;
        }
    }

    // 轮廓线只出现在最大高度变化的位置
    // 所以需要记录某个大楼从哪个位置开始增加了一个高度
    // 从哪个位置减少了一个高度
    public static List<List<Integer>> getSkyline(int[][] buildings) {
        List<Node> sortedNodes = init(buildings);
        TreeMap<Integer, Integer> heights = new TreeMap<>();
        TreeMap<Integer, Integer> result = new TreeMap<>();
        for (Node node : sortedNodes) {
            if (node.height > 0) {
                if (heights.containsKey(node.height)) {
                    heights.put(node.height, heights.get(node.height) + 1);
                } else {
                    heights.put(node.height, 1);
                }
            } else {
                if (heights.containsKey(-node.height)) {
                    if (heights.get(-node.height) == 1) {
                        heights.remove(-node.height);
                    } else {
                        heights.put(-node.height, heights.get(-node.height) - 1);
                    }
                }
            }
            if (heights.isEmpty()) {
                result.put(node.x, 0);
            } else {
                // 取最高的那个
                result.put(node.x, heights.lastKey());
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            int x = entry.getKey();
            int height = entry.getValue();
            if (ans.isEmpty() || ans.get(ans.size() - 1).get(1) != height) {
                List<Integer> item = new ArrayList<>();
                item.add(x);
                item.add(height);
                ans.add(item);
            }
        }
        return ans;
    }

    private static List<Node> init(int[][] buildings) {
        List<Node> list = new ArrayList<>();
        for (int[] building : buildings) {
            list.add(new Node(building[0], building[2]));
            list.add(new Node(building[1], -building[2]));
        }
        list.sort(Comparator.comparingInt((Node o) -> o.x));
        return list;
    }
}
