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
        List<Node> items = buildNodes(buildings);
        // 最大高度出现的次数
        TreeMap<Integer, Integer> heightTimes = new TreeMap<>();
        TreeMap<Integer, Integer> result = new TreeMap<>();
        for (Node item : items) {
            if (item.height > 0) {
                if (!heightTimes.containsKey(item.height)) {
                    heightTimes.put(item.height, 1);
                } else {
                    heightTimes.put(item.height, heightTimes.get(item.height) + 1);
                }
            } else {
                if (heightTimes.get(-item.height) == 1) {
                    heightTimes.remove(-item.height);
                } else {
                    heightTimes.put(-item.height, heightTimes.get(-item.height) - 1);
                }
            }
            if (heightTimes.isEmpty()) {
                result.put(item.x, 0);
            } else {
                result.put(item.x, heightTimes.lastKey());
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            int curX = entry.getKey();
            int curMaxHeight = entry.getValue();
            if (ans.isEmpty() || ans.get(ans.size() - 1).get(1) != curMaxHeight) {
                ans.add(new ArrayList<>(Arrays.asList(curX, curMaxHeight)));
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

    public static void main(String[] args) {
        int[][] build = {{0,2,3},{2,5,3}};
        List<List<Integer>> skyline = getSkyline(build);
        System.out.println(skyline);
    }
}
