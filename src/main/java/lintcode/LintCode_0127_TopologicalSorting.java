package lintcode;


import java.util.*;

// https://www.lintcode.com/problem/topological-sorting/description
public class LintCode_0127_TopologicalSorting {

    class DirectedGraphNode {
        int label;
        ArrayList<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }

    public Graph covert(ArrayList<DirectedGraphNode> graph) {
        // TODO
        return null;
    }

    public ArrayList<DirectedGraphNode> covert(Graph graph) {
        // TODO
        return null;
    }

    // TODO BFS方式
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        return null;
    }

    // TODO DFS方式
    public ArrayList<DirectedGraphNode> topSort2(ArrayList<DirectedGraphNode> graph) {
        return null;
    }

    public class Graph {
        public HashMap<Integer, Node> nodes;
        public HashSet<Edge> edges;

        public Graph() {
            nodes = new HashMap<>();
            edges = new HashSet<>();
        }
    }

    public class Edge {
        public int weight;
        public Node from;
        public Node to;

        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    public class Node {
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

}
