package 练习题.lintcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// 笔记：https://www.cnblogs.com/greyzeng/p/16785660.html
// https://www.lintcode.com/problem/topological-sorting/description
// DFS方式和BFS方式
// DFS中，有如下两种评价标准
// 
// 1. 用点次来判断拓扑序列
// 
// 2. 用某个点能延申的最大深度来判断拓扑序
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
    // 考察出度从大到小
    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        if (null == graph) {
            return new ArrayList<>();
        }
        Map<DirectedGraphNode, Info> map = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            f(node, map);
        }
        List<Info> list = new ArrayList<>(map.values());
        list.sort((o1, o2) -> {
            if (o2.out > o1.out) {
                return 1;
            } else if (o2.out < o1.out) {
                return -1;
            }
            return 0;
        });
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (Info info : list) {
            ans.add(info.node);
        }
        return ans;
    }

    // 当前遍历的节点是node，之前遍历的节点和出度关系存map中
    public static Info f(DirectedGraphNode node, Map<DirectedGraphNode, Info> map) {
        if (map.containsKey(node)) {
            return map.get(node);
        }
        long numOfNodes = 0;
        for (DirectedGraphNode neighbor : node.neighbors) {
            numOfNodes += f(neighbor, map).out;
        }
        Info info = new Info(node, numOfNodes + 1);
        map.put(node, info);
        return info;
    }

    public static class Info {
        public DirectedGraphNode node;
        public long out;

        public Info(DirectedGraphNode node, long out) {
            this.node = node;
            this.out = out;
        }
    }

    // DFS方式
    // 考察深度
    public static ArrayList<DirectedGraphNode> topSort2(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        for (DirectedGraphNode cur : graph) {
            f2(cur, order);
        }
        ArrayList<Record> recordArr = new ArrayList<>(order.values());
        recordArr.sort((o1, o2) -> (o2.deep - o1.deep));
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (Record r : recordArr) {
            ans.add(r.node);
        }
        return ans;
    }

    public static Record f2(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
        if (order.containsKey(cur)) {
            return order.get(cur);
        }
        int follow = 0;
        for (DirectedGraphNode next : cur.neighbors) {
            // 所有邻居的最大深度的最大值
            follow = Math.max(follow, f2(next, order).deep);
        }
        // 当前节点的最大深度是所有邻居节点的最大深度的最大值+1
        Record ans = new Record(cur, follow + 1);
        order.put(cur, ans);
        return ans;
    }

    public static class Record {
        public DirectedGraphNode node;
        public int deep;

        public Record(DirectedGraphNode n, int o) {
            node = n;
            deep = o;
        }
    }

    // 使用BFS实现，
    // 入度为0
    // 已通过验证
    public ArrayList<DirectedGraphNode> topSort3(ArrayList<DirectedGraphNode> graph) {
        if (null == graph || graph.isEmpty()) {
            return new ArrayList<>();
        }
        // 遍历所有点，找到每个点的入度数据
        Map<DirectedGraphNode, Integer> map = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            map.putIfAbsent(node, 0);
            ArrayList<DirectedGraphNode> neighbors = node.neighbors;
            for (DirectedGraphNode n : neighbors) {
                if (!map.containsKey(n)) {
                    map.put(n, 1);
                } else {
                    map.put(n, map.get(n) + 1);
                }
            }
        }
        // 入度为0的点加入队列
        Queue<DirectedGraphNode> starts = new LinkedList<>();
        for (Map.Entry<DirectedGraphNode, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 0) {
                starts.add(entry.getKey());
            }
        }
        // 宽度优先遍历
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        while (!starts.isEmpty()) {
            DirectedGraphNode poll = starts.poll();
            ans.add(poll);
            // map.remove(poll);
            if (poll.neighbors != null && !poll.neighbors.isEmpty()) {
                for (DirectedGraphNode nb : poll.neighbors) {
                    if (map.get(nb) == 1) {
                        // map.remove(nb);
                        starts.offer(nb);
                    }
                    map.put(nb, map.get(nb) - 1);
                }
            }
        }
        return ans;
    }
}
