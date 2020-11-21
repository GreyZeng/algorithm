package code;

import code.graph.Edge;
import code.graph.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

//
//  1）Dijkstra算法必须指定一个源点,每个边的权值均为非负数,求这个点到其他所有点的最短距离，到不了则为正无穷
//          2）生成一个源点到各个点的最小距离表，一开始只有一条记录，即原点到自己的最小距离为0，
//          源点到其他所有点的最小距离都为正无穷大
//          3）从距离表中拿出没拿过记录里的最小记录，通过这个点发出的边，更新源 点到各个点的最小距离表，不断重复这一步
//          4）源点到所有的点记录如果都被拿过一遍，过程停止，最小距离表得到了
//          给定出发点，出发点到所有点的距离之和最小是多少？
// 非负数的边
public class Code_0052_Dijkstra {
    // HashMap版本
    public static HashMap<Node, Integer> dijkstra(Node from) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);
        HashSet<Node> selectedNodes = new HashSet<>();
        Node minNode = getMinNode(distanceMap, selectedNodes);
        while (minNode != null) {
            int distance = distanceMap.get(minNode);
            for (Edge neighbor : minNode.edges) {
                Node to = neighbor.to;
                if (distanceMap.containsKey(to)) {
                    distanceMap.put(to, Math.min(distanceMap.get(to), neighbor.weight + distance));
                } else {
                    distanceMap.put(to, distance + neighbor.weight);
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    private static Node getMinNode(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNodes) {
        Node result = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            if (!selectedNodes.contains(entry.getKey()) && entry.getValue() < minDistance) {
                result = entry.getKey();
                minDistance = entry.getValue();
            }
        }
        return result;
    }
}



