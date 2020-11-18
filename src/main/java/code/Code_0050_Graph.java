package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Code_0050_Graph {
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

    public static class Graph {
        public HashMap<Integer, Node> nodes;
        public HashSet<Edge> edges;

        public Graph() {
            nodes = new HashMap<>();
            edges = new HashSet<>();
        }
    }


    public static class GraphGenerator {

        // matrix 所有的边
        // N*3 的矩阵
        // [weight, from节点上面的值，to节点上面的值]
        public static Graph createGraph(Integer[][] matrix) {
            Graph graph = new Graph();
            for (int i = 0; i < matrix.length; i++) {
                // matrix[0][0], matrix[0][1]  matrix[0][2]
                Integer weight = matrix[i][0];
                Integer from = matrix[i][1];
                Integer to = matrix[i][2];
                if (!graph.nodes.containsKey(from)) {
                    graph.nodes.put(from, new Node(from));
                }
                if (!graph.nodes.containsKey(to)) {
                    graph.nodes.put(to, new Node(to));
                }
                Node fromNode = graph.nodes.get(from);
                Node toNode = graph.nodes.get(to);
                Edge newEdge = new Edge(weight, fromNode, toNode);
                fromNode.nexts.add(toNode);
                fromNode.out++;
                toNode.in++;
                fromNode.edges.add(newEdge);
                graph.edges.add(newEdge);
            }
            return graph;
        }

    }
}
