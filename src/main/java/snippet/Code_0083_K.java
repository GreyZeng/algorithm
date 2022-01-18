package snippet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

// https://www.nowcoder.com/questionTerminal/c23eab7bb39748b6b224a8a3afbe396b
// 最小生成树 K算法
public class Code_0083_K {

    // K算法 （边权值排序，并查集）（如果无向图会少一侧的情况，按情况补充即可）
    // 最小生成树算法之Kruskal
    // 1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
    // 2）当前的边要么进入最小生成树的集合，要么丢弃
    // 3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
    // 4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
    // 5）考察完所有边之后，最小生成树的集合也得到了
    // 边存在小根堆里面，保证每次弹出的都是权重最小的值
    // 点存在并查集中，每次加入一个边，就把两个边的点union
    public static Set<Edge> K(Graph graph) {

        // 1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        for (Edge edge : graph.edges) {
            queue.offer(edge);
        }
        HashSet<Edge> ans = new HashSet<>();
        UnionFind uf = new UnionFind();
        uf.addSets(graph.nodes.values());
        // 2）当前的边要么进入最小生成树的集合，要么丢弃
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            if (!uf.isSameSet(edge.from, edge.to)) {
                // 3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
                ans.add(edge);
                uf.union(edge.from, edge.to);
            }
            // 4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
            // 5）考察完所有边之后，最小生成树的集合也得到了
        }
        Node t = null;
        for (Node node : graph.nodes.values()) {
            if (t == null) {
                t = node;
            } else {
                if (!uf.isSameSet(t, node)) {
                    return new HashSet<>();
                }
            }
        }
        return ans;
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

    public static class UnionFind {
        private HashMap<Node, Node> parentMap;
        private HashMap<Node, Integer> sizeMap;

        public UnionFind() {
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void addSets(Collection<Node> values) {
            for (Node node : values) {
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public boolean isSameSet(Node from, Node to) {
            return findFather(from) == findFather(to);
        }

        public void union(Node a, Node b) {
            Node f1 = findFather(a);
            Node f2 = findFather(b);
            if (f1 != f2) {
                int size1 = sizeMap.get(f1);
                int size2 = sizeMap.get(f2);
                Node small = size1 > size2 ? f2 : f1;
                Node large = small == f1 ? f2 : f1;
                parentMap.put(small, large);
                sizeMap.put(large, size1 + size2);
            }

        }

        public Node findFather(Node n) {
            Stack<Node> path = new Stack<>();
            while (n != parentMap.get(n)) {
                path.add(n);
                n = parentMap.get(n);
            }
            while (!path.isEmpty()) {
                parentMap.put(path.pop(), n);
            }
            return n;
        }
    }

}
