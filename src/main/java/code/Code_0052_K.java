package code;

import java.util.HashSet;
import java.util.Set;

import code.graph.*;

//K算法 （边权值排序，并查集）（如果无向图会少一侧的情况，按情况补充即可）
//最小生成树算法之Kruskal
// 1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
// 2）当前的边要么进入最小生成树的集合，要么丢弃
// 3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
// 4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
// 5）考察完所有边之后，最小生成树的集合也得到了
public class Code_0052_K {
	public static Set<Node> k(Graph graph) {
		HashSet<Edge> edges = graph.edges;
		// 1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
		Edge minum = null;
		for (Edge edge : edges) {
			minum = minum == null || edge.weight >= minum.weight ? minum : edge;
		}
		// 2）当前的边要么进入最小生成树的集合，要么丢弃
		HashSet<Edge> result = new HashSet<>();
		result.add(minum);
		// 3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
		Node from = minum.from;
		Node to = minum.to;
		// TODO
		// 4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
		// 5）考察完所有边之后，最小生成树的集合也得到了
	}
}
