package todo.week.class_2021_12_3_week;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 测试链接 : https://leetcode.com/problems/cut-off-trees-for-golf-event/
public class Code04_CutOffTreesForGolfEvent {

	public static int cutOffTree(List<List<Integer>> forest) {
		int n = forest.size();
		int m = forest.get(0).size();
		// [ [3,5,2], [1,9,4] , [2,6,10] ]
		// 低 中 高
		ArrayList<int[]> cells = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int val = forest.get(i).get(j);
				if (val > 1) {
					cells.add(new int[] { i, j, val });
				}
			}
		}
		cells.sort((a, b) -> a[2] - b[2]);
		int ans = 0, lastR = 0, lastC = 0;
		for (int[] cell : cells) {
			int step = bestWalk(forest, lastR, lastC, cell[0], cell[1]);
			if (step == -1) {
				return -1;
			}
			ans += step;
			lastR = cell[0];
			lastC = cell[1];
			forest.get(lastR).set(lastC, 1);
		}
		return ans;
	}

	public static int[] next = { -1, 0, 1, 0, -1 };

	// 0 1 2 3 4
	// i
	// 行 + next[i-1]
	// 列 + next[i]
	// i == 1 -> 上
	// i == 2 -> 右
	// i == 3 -> 下
	// i == 4 -> 左
	public static int bestWalk(List<List<Integer>> forest, int sr, int sc, int tr, int tc) {
		int n = forest.size();
		int m = forest.get(0).size();
		boolean[][] seen = new boolean[n][m];
		LinkedList<int[]> deque = new LinkedList<>();
		deque.offerFirst(new int[] { 0, sr, sc });
		while (!deque.isEmpty()) {
			int[] cur = deque.pollFirst();
			int step = cur[0], r = cur[1], c = cur[2];
			if (r == tr && c == tc) {
				return step;
			}
			seen[r][c] = true;
			for (int i = 1; i < 5; i++) { // (r,c) 上下左右，全试试！
				int nr = r + next[i - 1];
				int nc = c + next[i];
				if (nr >= 0 && nr < n && nc >= 0 && nc < m && !seen[nr][nc] && forest.get(nr).get(nc) > 0) {
					int[] move = { step + 1, nr, nc };
					// 更近的话
					if ((i == 1 && r > tr) || (i == 2 && c < tc) || (i == 3 && r < tr) || (i == 4 && c > tc)) {
						deque.offerFirst(move);
					} else { // 更远的话，放到尾部！
						deque.offerLast(move);
					}
				}
			}
		}
		return -1;
	}

}
