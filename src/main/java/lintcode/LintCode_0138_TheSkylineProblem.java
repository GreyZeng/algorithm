package lintcode;

import java.util.*;

// 有序表的使用
// TODO
public class LintCode_0138_TheSkylineProblem {
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
    public static List<List<Integer>> buildingOutline(int[][] buildings) {
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
        // FIXME
        // TODO
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
    public static void main(String[] args) {
        int[][] building = { { 1, 3, 3 }, { 2, 4, 4 }, { 5, 6, 1 } };
        List<List<Integer>> list = buildingOutline(building);
        System.out.println(list);
    }
}
