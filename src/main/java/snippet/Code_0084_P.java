package snippet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

// https://www.nowcoder.com/questionTerminal/c23eab7bb39748b6b224a8a3afbe396b
// 最小生成树 P算法
public class Code_0084_P {


    //  P算法
//    1）可以从任意节点出发来寻找最小生成树
//    2）某个点加入到被选取的点中后，解锁这个点出发的所有新的边
//    3）在所有解锁的边中选最小的边，然后看看这个边会不会形成环
//    4）如果会，不要当前边，继续考察剩下解锁的边中最小的边，重复3）
//    5）如果不会，要当前边，将该边的指向点加入到被选取的点中，重复2）
//    6）当所有点都被选取，最小生成树就得到了
    public static Set<Edge> P(Graph graph) {
        // 解锁的边进入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));

        // 哪些点被解锁出来了
        HashSet<Node> nodeSet = new HashSet<>();
        Set<Edge> result = new HashSet<>(); // 依次挑选的的边在result里
        for (Node node : graph.nodes.values()) { // 随便挑了一个点
            // node 是开始点
            if (!nodeSet.contains(node)) {
                nodeSet.add(node);
                for (Edge edge : node.edges) { // 由一个点，解锁所有相连的边
                    priorityQueue.add(edge);
                }
                while (!priorityQueue.isEmpty()) {
                    Edge edge = priorityQueue.poll(); // 弹出解锁的边中，最小的边
                    Node toNode = edge.to; // 可能的一个新的点
                    if (!nodeSet.contains(toNode)) { // 不含有的时候，就是新的点
                        nodeSet.add(toNode);
                        result.add(edge);
                        for (Edge nextEdge : toNode.edges) {
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
            // 如果有森林，就不能break，如果没有森林，就可以break
            //break;
        }
        return result;
    }

    public static class Graph {
        public HashMap<Integer, Node> nodes;
        public HashSet<Edge> edges;

        public Graph(int n) {
            nodes = new HashMap<>();
            edges = new HashSet<>(n);
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
}
