package lintcode;

import java.util.*;

// https://www.lintcode.com/problem/topological-sorting/description
// DFS方式和BFS方式
// 用点次来判断拓扑序列
// 用某个点能延申的最大深度来判断拓扑序
public class LintCode_0127_TopologicalSorting {

    public static class DirectedGraphNode {
        int label;
        ArrayList<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }

    // DFS方式
    // 出度从大到小
    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {

        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        for (DirectedGraphNode cur : graph) {
            f(cur, order);
        }
        ArrayList<Record> recordArr = new ArrayList<>();
        for (Record r : order.values()) {
            recordArr.add(r);
        }
        recordArr.sort(new MyComparator());
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (Record r : recordArr) {
            ans.add(r.node);
        }
        return ans;
    }

    public static Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
        if (order.containsKey(cur)) {
            return order.get(cur);
        }
        long out = 1;
        for (DirectedGraphNode next : cur.neighbors) {
            out += f(next, order).out;
        }
        Record ans = new Record(cur, out);
        order.put(cur, ans);
        return ans;
    }

    // 使用BFS实现，
    // 入度为0
    // 已通过验证
    public ArrayList<DirectedGraphNode> topSort2(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Integer> map = buildIndex(graph);
        Queue<DirectedGraphNode> starts = new LinkedList<>();

        // 有可能有多个点是入度为0（森林）
        for (Map.Entry<DirectedGraphNode, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 0) {
                starts.add(entry.getKey());
            }
        }
        // 宽度优选遍历
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        while (!starts.isEmpty()) {
            DirectedGraphNode node = starts.poll();
            ans.add(node);
            if (node.neighbors != null && !node.neighbors.isEmpty()) {
                for (DirectedGraphNode neighbor : node.neighbors) {
                    if (map.get(neighbor) == 1) {
                        starts.offer(neighbor);
                    }
                    map.put(neighbor, map.get(neighbor) - 1);
                }
            }
        }
        return ans;
    }

    // 所有点以及其入度是多少对应关系
    public HashMap<DirectedGraphNode, Integer> buildIndex(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Integer> map = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            if (!map.containsKey(node)) {
                map.put(node, 0);
            }
            for (DirectedGraphNode neighbor : node.neighbors) {
                if (map.containsKey(neighbor)) {
                    map.put(neighbor, map.get(neighbor) + 1);
                } else {
                    map.put(neighbor, 1);
                }
            }
        }
        return map;
    }

    public static class Record {
        public DirectedGraphNode node;
        public long out;

        public Record(DirectedGraphNode n, long o) {
            node = n;
            out = o;
        }
    }

    public static class MyComparator implements Comparator<Record> {

        @Override
        public int compare(Record o1, Record o2) {
            if (o2.out > o1.out) {
                return 1;
            } else if (o1.out > o2.out) {
                return -1;
            } else {
                return 0;
            }
        }
    }

}
