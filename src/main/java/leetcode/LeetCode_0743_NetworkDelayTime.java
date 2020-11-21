//There are N network nodes, labelled 1 to N.
//
//        Given times, a list of travel times as directed edges times[i] = (u, v, w),
//        where u is the source node, v is the target node,
//        and w is the time it takes for a signal to travel from source to target.
//
//        Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal?
//        If it is impossible, return -1.
package leetcode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LeetCode_0743_NetworkDelayTime {
    public static void main(String[] args) {
        int[][] times = new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 1}};
        int N = 3;
        int K = 2;
        System.out.println(networkDelayTime(times, N, K));

    }

    public static int networkDelayTime(int[][] times, int N, int K) {
        Graph graph = generate(times);
        Node from = null;
        for (Node n : graph.nodes.values()) {
            if (n.value == K) {
                from = n;
            }
        }
        HashMap<Node, Integer> map = dijkstra(from);
        int sum = -1;

        for (Map.Entry<Node, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 0) {
                N--;
                continue;
            }
            N--;
            if (entry.getValue() == Integer.MAX_VALUE) {
                return -1;
            } else {
                sum = Math.max(entry.getValue(), sum);
            }
        }
        // 防止出现环的形状
        //   int[][] times = new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 1}};
        //        int N = 3;
        //        int K = 2;
        if (N != 0) {
            return -1;
        }
        return sum;
    }

    public static Graph generate(int[][] times) {
        Graph graph = new Graph();
        for (int[] time : times) {
            int from = time[0];
            int to = time[1];
            int weight = time[2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge fromToEdge = new Edge(weight, fromNode, toNode);
            //Edge toFromEdge = new Edge(weight, toNode, fromNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            //fromNode.in++;
            //toNode.out++;
            toNode.in++;
            fromNode.edges.add(fromToEdge);
            //toNode.edges.add(toFromEdge);
            graph.edges.add(fromToEdge);
            //graph.edges.add(toFromEdge);
        }

        return graph;
    }

    public static class Graph {
        public HashMap<Integer, Node> nodes;
        public HashSet<Edge> edges;

        public Graph() {
            nodes = new HashMap<>();
            edges = new HashSet<>();
        }
    }

    public static class Edge {
        public int weight;
        public Node from;
        public Node to;

        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    public static class Node {
        public int value;
        public int in;
        public int out;
        public ArrayList<Node> nexts;
        public ArrayList<Edge> edges;

        public Node(int value) {
            this.value = value;
            in = 0;
            out = 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }

    public static HashMap<Node, Integer> dijkstra(Node from) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);
        HashSet<Node> selectedNodes = new HashSet<>();
        Node minNode = getMinNode(distanceMap, selectedNodes);
        while (minNode != null) {
            int distance = distanceMap.get(minNode);
            for (Edge neighbor : minNode.edges) {
                Node to = neighbor.to;
                if (!distanceMap.containsKey(to)) {
                    distanceMap.put(to, distance + neighbor.weight);
                } else {
                    distanceMap.put(to, Math.min(distance + neighbor.weight, distanceMap.get(to)));
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    public static Node getMinNode(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNodes) {
        int minDistance = Integer.MAX_VALUE;
        Node minNode = null;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node n = entry.getKey();
            int distance = entry.getValue();
            if (!selectedNodes.contains(n) && distance < minDistance) {
                minDistance = distance;
                minNode = n;
            }
        }
        return minNode;
    }


}
