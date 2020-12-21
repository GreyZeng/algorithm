//链接：https://www.nowcoder.com/questionTerminal/c23eab7bb39748b6b224a8a3afbe396b
//来源：牛客网
//
//一个有n户人家的村庄，有m条路连接着。村里现在要修路，每条路都有一个代价，现在请你帮忙计算下，最少需要花费多少的代价，就能让这n户人家连接起来。
//
//
//输入描述:
//输入第一行，两个整数n,m;
//接下来m行，每行三个整数a,b,c，表示有路连接编号为a和b的人家，修路要花费的代价为c。
//数据保证能将每户人家都连接起来。
//注意重边的情况。n≤10000, m≤100,000，边权0<c≤10000。
//
//
//输出描述:
//输出最小的花费代价使得这n户人家连接起来。
//示例1
//输入
//3 3
//1 3 3
//1 2 1
//2 3 1
//输出
//2
package nowcoder;

import java.util.*;

// https://www.nowcoder.com/questionTerminal/c23eab7bb39748b6b224a8a3afbe396b
// 最小生成树 K算法，P算法
public class NowCoder_MinimumSpanningTree {

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
        PriorityQueue<Edge> queue = new PriorityQueue<>(new MyComparator());
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

    //  P算法
//    1）可以从任意节点出发来寻找最小生成树
//    2）某个点加入到被选取的点中后，解锁这个点出发的所有新的边
//    3）在所有解锁的边中选最小的边，然后看看这个边会不会形成环
//    4）如果会，不要当前边，继续考察剩下解锁的边中最小的边，重复3）
//    5）如果不会，要当前边，将该边的指向点加入到被选取的点中，重复2）
//    6）当所有点都被选取，最小生成树就得到了
    public static Set<Edge> P(Graph graph) {
        // 解锁的边进入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new MyComparator());

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
            break;
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

    public static class MyComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    // 输入第一行，两个整数n,m;
    // 接下来m行，每行三个整数a,b,c，表示有路连接编号为a和b的人家，修路要花费的代价为c。
    // 数据保证能将每户人家都连接起来。
    // 注意重边的情况。n≤10000, m≤100,000，边权0<c≤10000。
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Graph graph = new Graph(n);
        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            int weight = in.nextInt();
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge fromToEdge = new Edge(weight, fromNode, toNode);
            Edge toFromEdge = new Edge(weight, toNode, fromNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            fromNode.in++;
            toNode.out++;
            toNode.in++;
            fromNode.edges.add(fromToEdge);
            toNode.edges.add(toFromEdge);
            graph.edges.add(fromToEdge);
            graph.edges.add(toFromEdge);
        }
        //Set<Edge> result = K(graph);
        Set<Edge> result = P(graph);

        int sum = 0;
        for (Edge edge : result) {
            sum += edge.weight;
        }
        System.out.println(sum);
        in.close();
    }
}
