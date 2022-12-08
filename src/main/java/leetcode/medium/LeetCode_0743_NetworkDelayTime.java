// 有 n 个网络节点，标记为 1 到 n。
// 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi
// 是一个信号从源节点传递到目标节点的时间。
// 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
// https://leetcode.cn/problems/network-delay-time/
// 笔记：
public class LeetCode_0743_NetworkDelayTime {

  public static int networkDelayTime(int[][] times, int N, int K) {
    // 转换成图结构
    Graph graph = generate(times);
    Node from = null;
    // 找到开始节点
    for (Node n : graph.nodes.values()) {
      if (n.value == K) {
        from = n;
      }
    }
    HashMap<Node, Integer> map = dijkstra2(from, N);
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
  // 二维数组转换成自己熟悉的图结构
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

  public static class NodeRecord {
    public Node node;
    public int distance;

    public NodeRecord(Node node, int distance) {
      this.node = node;
      this.distance = distance;
    }
  }

  public static class NodeHeap {
    private Node[] nodes; // 实际的堆结构
    // key 某一个node， value 上面堆中的位置
    private HashMap<Node, Integer> heapIndexMap;
    // key 某一个节点， value 从源节点出发到该节点的目前最小距离
    private HashMap<Node, Integer> distanceMap;
    private int size; // 堆上有多少个点

    public NodeHeap(int size) {
      nodes = new Node[size];
      heapIndexMap = new HashMap<>();
      distanceMap = new HashMap<>();
    }

    public boolean isEmpty() {
      return size == 0;
    }

    // 有一个点叫node，现在发现了一个从源节点出发到达node的距离为distance
    // 判断要不要更新，如果需要的话，就更新
    public void addOrUpdateOrIgnore(Node node, int distance) {
      if (inHeap(node)) {
        distanceMap.put(node, Math.min(distanceMap.get(node), distance));
        insertHeapify(node, heapIndexMap.get(node));
      }
      if (!isEntered(node)) {
        nodes[size] = node;
        heapIndexMap.put(node, size);
        distanceMap.put(node, distance);
        insertHeapify(node, size++);
      }
    }

    public NodeRecord pop() {
      NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
      swap(0, size - 1);
      heapIndexMap.put(nodes[size - 1], -1);
      distanceMap.remove(nodes[size - 1]);
      // free C++同学还要把原本堆顶节点析构，对java同学不必
      nodes[size - 1] = null;
      heapify(0, --size);
      return nodeRecord;
    }

    private void insertHeapify(Node node, int index) {
      while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
        swap(index, (index - 1) / 2);
        index = (index - 1) / 2;
      }
    }

    private void heapify(int index, int size) {
      int left = index * 2 + 1;
      while (left < size) {
        int smallest =
            left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
                ? left + 1
                : left;
        smallest =
            distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
        if (smallest == index) {
          break;
        }
        swap(smallest, index);
        index = smallest;
        left = index * 2 + 1;
      }
    }

    private boolean isEntered(Node node) {
      return heapIndexMap.containsKey(node);
    }

    private boolean inHeap(Node node) {
      return isEntered(node) && heapIndexMap.get(node) != -1;
    }

    private void swap(int index1, int index2) {
      heapIndexMap.put(nodes[index1], index2);
      heapIndexMap.put(nodes[index2], index1);
      Node tmp = nodes[index1];
      nodes[index1] = nodes[index2];
      nodes[index2] = tmp;
    }
  }

  // 改进后的dijkstra算法
  // 从head出发，所有head能到达的节点，生成到达每个节点的最小路径记录并返回
  public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
    NodeHeap nodeHeap = new NodeHeap(size);
    nodeHeap.addOrUpdateOrIgnore(head, 0);
    HashMap<Node, Integer> result = new HashMap<>();
    while (!nodeHeap.isEmpty()) {
      NodeRecord record = nodeHeap.pop();
      Node cur = record.node;
      int distance = record.distance;
      for (Edge edge : cur.edges) {
        nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
      }
      result.put(cur, distance);
    }
    return result;
  }
}
