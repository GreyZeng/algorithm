package leetcode;

import java.util.*;

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
public class LeetCode_0218_TheSkylineProblem {
    public static class Node {
        public int x;
        public int height;

        public Node(int x, int height) {
            this.x = x;
            this.height = height;
        }
    }

    public static class MyComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.x - o2.x;
        }
    }

    // 轮廓线只出现在最大高度变化的位置
    // 所以需要记录某个大楼从哪个位置开始增加了一个高度
    // 从哪个位置减少了一个高度
    public static List<List<Integer>> getSkyline(int[][] buildings) {
        List<Node> nodes = buildNodes(buildings);
        TreeMap<Integer, Integer> heightTimesMap = new TreeMap<>();
        TreeMap<Integer, Integer> result = new TreeMap<>();
        for (Node node : nodes) {
            int height = node.height;
            if (height > 0) {
                if (heightTimesMap.containsKey(height)) {
                    heightTimesMap.put(height, heightTimesMap.get(height) +1 );
                } else {
                    heightTimesMap.put(height, 1);
                }
            } else {
                if (heightTimesMap.get(-height) == 1) {
                    heightTimesMap.remove(-height);
                } else {
                    heightTimesMap.put(-height, heightTimesMap.get(-height) - 1);
                }
            }
            if (heightTimesMap.isEmpty()) {
                result.put(node.x, 0);
            } else {
                result.put(node.x, heightTimesMap.lastKey());
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
           int key =  entry.getKey();
           int value = entry.getValue();
           if (ans.isEmpty() || ans.get(ans.size() - 1).get(1) != value) {
               ans.add(new ArrayList<>(Arrays.asList(key, value)));
           }
        }
        return ans;
    }

    private static List<Node> buildNodes(int[][] buildings) {
        List<Node> list = new ArrayList<>();
        for (int[] building : buildings) {
            // 在某个位置增加了一个高度
            list.add(new Node(building[0], building[2]));
            // 在某个位置减少了一个高度
            list.add(new Node(building[1], -building[2]));
        }
        list.sort(new MyComparator());
        return list;
    }
}
