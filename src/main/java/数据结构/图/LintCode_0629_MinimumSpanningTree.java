// 给出一些Connections，即Connections类，找到一些能够将所有城市都连接起来并且花费最小的边。
// 如果说可以将所有城市都连接起来，则返回这个连接方法；不然的话返回一个空列表。
//
// 样例
// 样例 1:
//
// 输入:
// ["Acity","Bcity",1]
// ["Acity","Ccity",2]
// ["Bcity","Ccity",3]
// 输出:
// ["Acity","Bcity",1]
// ["Acity","Ccity",2]
// 样例 2:
//
// 输入:
// ["Acity","Bcity",2]
// ["Bcity","Dcity",5]
// ["Acity","Dcity",4]
// ["Ccity","Ecity",1]
// 输出:
// []
//
// 解释:
// 没有办法连通
// 注意事项
// 返回按cost排序的连接方法，如果cost相同就按照city1进行排序，如果city1也相同那么就按照city2进行排序。
package 数据结构.图;

import java.util.*;


// TODO P算法还有问题
public class LintCode_0629_MinimumSpanningTree {
  public static class Connection {
    public String city1, city2;
    public int cost;

    public Connection(String city1, String city2, int cost) {
      this.city1 = city1;
      this.city2 = city2;
      this.cost = cost;
    }
  } 

  public static void main(String[] args) {
    List<Connection> connections = new ArrayList<>();
    // ["Acity","Bcity",1]
    // ["Acity","Ccity",2]
    // ["Bcity","Ccity",3]
    connections.add(new Connection("Acity", "Bcity", 1));
    connections.add(new Connection("Bcity", "Ccity", 2));
    connections.add(new Connection("Bcity", "Ccity", 3));
    List<Connection> connections1 = lowestCost(connections);
    System.out.println(connections1);
  }

  /**
   * @param connections given a list of connections include two cities and cost
   * @return a list of connections from results
   */
  public static List<Connection> lowestCost(List<Connection> connections) {

    Graph graph = new Graph();
    for (Connection connection : connections) {
      String from = connection.city1;
      String to = connection.city2;
      int weight = connection.cost;
      if (!graph.nodes.containsKey(from)) {
        graph.nodes.put(from, new Node(from));
      }
      if (!graph.nodes.containsKey(to)) {
        graph.nodes.put(to, new Node(to));
      }
      Node fromNode = graph.nodes.get(from);
      Node toNode = graph.nodes.get(to);
      Edge fromToEdge = new Edge(weight, fromNode, toNode);

      // Edge toFromEdge = new Edge(weight, toNode, fromNode);
      fromNode.nexts.add(toNode);
      fromNode.out++;
      // fromNode.in++;
      // toNode.out++;
      toNode.in++;
      fromNode.edges.add(fromToEdge);
      // toNode.edges.add(toFromEdge);
      graph.edges.add(fromToEdge);
      // graph.edges.add(toFromEdge);
    }

    PriorityQueue<Edge> result = K(graph);
    // PriorityQueue<Edge> result = P(graph);

    List<Connection> ans = new ArrayList<>();
    while (!result.isEmpty()) {
      Edge edge = result.poll();
      ans.add(new Connection(edge.from.value, edge.to.value, edge.weight));
    }

    return ans;

  }

  // K算法 （边权值排序，并查集）（如果无向图会少一侧的情况，按情况补充即可）
  // 最小生成树算法之Kruskal
  // 1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
  // 2）当前的边要么进入最小生成树的集合，要么丢弃
  // 3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
  // 4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
  // 5）考察完所有边之后，最小生成树的集合也得到了
  // 边存在小根堆里面，保证每次弹出的都是权重最小的值
  // 点存在并查集中，每次加入一个边，就把两个边的点union
  public static PriorityQueue<Edge> K(Graph graph) {
    PriorityQueue<Edge> ans = new PriorityQueue<>(new MyComparator());
    // 1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
    PriorityQueue<Edge> queue = new PriorityQueue<>(new MyComparator());
    for (Edge edge : graph.edges) {
      queue.offer(edge);
    }
    UnionFind uf = new UnionFind();
    uf.addSets(graph.nodes.values());
    // 2）当前的边要么进入最小生成树的集合，要么丢弃
    while (!queue.isEmpty()) {
      Edge edge = queue.poll();
      if (!uf.isSameSet(edge.from, edge.to)) {
        // 3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
        ans.offer(edge);
        uf.union(edge.from, edge.to);
      }
      // 4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
      // 5）考察完所有边之后，最小生成树的集合也得到了
    }
    // 防止出现森林的情况
    Node t = null;
    for (Node node : graph.nodes.values()) {
      if (t == null) {
        t = node;
      } else {
        if (!uf.isSameSet(t, node)) {
          return new PriorityQueue<>();
        }
      }
    }
    return ans;
  }


  public static class Graph {
    public HashMap<String, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
      nodes = new HashMap<>();
      edges = new HashSet<>();
    }
  }

  public static class Node {
    public String value;
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(String value) {
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
      if (o1.weight != o2.weight) {
        return o1.weight - o2.weight;
      } else if (o1.from != o2.from) {
        return o1.from.value.compareTo(o2.from.value);
      } else {
        return o1.to.value.compareTo(o2.to.value);
      }
    }
  }
}
