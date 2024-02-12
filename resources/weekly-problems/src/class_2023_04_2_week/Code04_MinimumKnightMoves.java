package class_2023_04_2_week;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

// 来自Indeed、谷歌、亚马逊、领英、英伟达
// 一个坐标可以从 -infinity 延伸到 +infinity 的 无限大的 棋盘上
// 你的 骑士 驻扎在坐标为 [0, 0] 的方格里。
// 骑士的走法和中国象棋中的马相似，走 “日” 字：
// 即先向左（或右）走 1 格，再向上（或下）走 2 格
// 或先向左（或右）走 2 格，再向上（或下）走 1 格
// 每次移动，他都可以像中国象棋中的马一样，选八个方向中的一个前进
// 返回 骑士前去征服坐标为 [x, y] 的部落所需的最小移动次数
// 本题确保答案是一定存在的。
// 测试链接 : https://leetcode.cn/problems/minimum-knight-moves/
public class Code04_MinimumKnightMoves {

	// A*算法的实现
	public static int minKnightMoves(int x, int y) {
		
		// int[] cur = { 
		// 0 : 出发点到当前位置跳了几次
		// 1 : 当前位置跳到目标位置，估计还要跳几次
		// 2 : 当前位置的行
		// 3 : 当前位置的列
		// f() : 出发点到当前位置跳了几次，谁次数少，谁先弹出
		// f() + g() : 
		PriorityQueue<int[]> heap = new PriorityQueue<>(
				(a, b) -> (a[0] + a[1]) - (b[0] + b[1]));
		// 从堆里弹出了什么位置，不要重复再考虑了
		HashMap<Integer, HashSet<Integer>> closed = new HashMap<>();
		heap.add(new int[] { 0, distance(0, 0, x, y), 0, 0 });
		int ans = Integer.MAX_VALUE;
		while (!heap.isEmpty()) {
			int[] cur = heap.poll();
			int cost = cur[0];
			int row = cur[2];
			int col = cur[3];
			if (isPoped(closed, row, col)) {
				continue;
			}
			if (row == x && col == y) {
				ans = cost;
				break;
			}
			close(closed, row, col);
			add(cost + 1, row + 2, col + 1, x, y, closed, heap);
			add(cost + 1, row + 1, col + 2, x, y, closed, heap);
			add(cost + 1, row - 1, col + 2, x, y, closed, heap);
			add(cost + 1, row - 2, col + 1, x, y, closed, heap);
			add(cost + 1, row - 2, col - 1, x, y, closed, heap);
			add(cost + 1, row - 1, col - 2, x, y, closed, heap);
			add(cost + 1, row + 1, col - 2, x, y, closed, heap);
			add(cost + 1, row + 2, col - 1, x, y, closed, heap);
		}
		return ans;
	}

	// 如果之间弹出过(r,c)点，返回true
	// 如果之间没弹出过(r,c)点，返回false
	public static boolean isPoped(HashMap<Integer, HashSet<Integer>> closed, int r, int c) {
		return closed.containsKey(r) && closed.get(r).contains(c);
	}

	// 把(r,c)点加入closed表
	public static void close(HashMap<Integer, HashSet<Integer>> closed, int r, int c) {
		if (!closed.containsKey(r)) {
			closed.put(r, new HashSet<>());
		}
		closed.get(r).add(c);
	}

	public static void add(int cost, int r, int c, int x, int y, HashMap<Integer, HashSet<Integer>> closed,
			PriorityQueue<int[]> heap) {
		if (!isPoped(closed, r, c)) {
			heap.add(new int[] { cost, distance(r, c, x, y), r, c });
		}
	}

	// 曼哈顿距离 / 3
	// 为什么要定成这个
	// 因为估计函数的估计代价 要小于等于 真实代价
	// 我们知道，走"日"字是一次蹦3个曼哈顿距离
	// 如果A点到B点的曼哈顿距离是3，不是任意的A和B都能通过走"日"的方式，一步达到的
	// 所以真实代价 >= 曼哈顿距离 / 3
	// 那就把 曼哈顿距离 / 3，定成估计函数
	public static int distance(int r, int c, int x, int y) {
		return (Math.abs(x - r) + Math.abs(y - c)) / 3;
	}

}
