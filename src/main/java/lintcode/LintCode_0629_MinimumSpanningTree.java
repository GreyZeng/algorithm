//给出一些Connections，即Connections类，找到一些能够将所有城市都连接起来并且花费最小的边。
//如果说可以将所有城市都连接起来，则返回这个连接方法；不然的话返回一个空列表。
//
//样例
//样例 1:
//
//输入:
//["Acity","Bcity",1]
//["Acity","Ccity",2]
//["Bcity","Ccity",3]
//输出:
//["Acity","Bcity",1]
//["Acity","Ccity",2]
//样例 2:
//
//输入:
//["Acity","Bcity",2]
//["Bcity","Dcity",5]
//["Acity","Dcity",4]
//["Ccity","Ecity",1]
//输出:
//[]
//
//解释:
//没有办法连通
//注意事项
//返回按cost排序的连接方法，如果cost相同就按照city1进行排序，如果city1也相同那么就按照city2进行排序。
package lintcode;

import java.util.List;

// TODO
public class LintCode_0629_MinimumSpanningTree {
	public class Connection {
		public String city1, city2;
		public int cost;

		public Connection(String city1, String city2, int cost) {
			this.city1 = city1;
			this.city2 = city2;
			this.cost = cost;
		}
	}

	public List<Connection> lowestCost(List<Connection> connections) {
		return null;
	}

}
