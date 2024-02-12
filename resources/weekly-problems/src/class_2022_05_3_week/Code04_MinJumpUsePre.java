package class_2022_05_3_week;

import java.util.Arrays;

// 来自学员问题
// 为了给刷题的同学一些奖励，力扣团队引入了一个弹簧游戏机
// 游戏机由 N 个特殊弹簧排成一排，编号为 0 到 N-1
// 初始有一个小球在编号 0 的弹簧处。若小球在编号为 i 的弹簧处
// 通过按动弹簧，可以选择把小球向右弹射 jump[i] 的距离，或者向左弹射到任意左侧弹簧的位置
// 也就是说，在编号为 i 弹簧处按动弹簧，
// 小球可以弹向 0 到 i-1 中任意弹簧或者 i+jump[i] 的弹簧（若 i+jump[i]>=N ，则表示小球弹出了机器）
// 小球位于编号 0 处的弹簧时不能再向左弹。
// 为了获得奖励，你需要将小球弹出机器。
// 请求出最少需要按动多少次弹簧，可以将小球从编号 0 弹簧弹出整个机器，即向右越过编号 N-1 的弹簧。
// 测试链接 : https://leetcode-cn.com/problems/zui-xiao-tiao-yue-ci-shu/
public class Code04_MinJumpUsePre {

	// 宽度优先遍历
	// N*logN
	public int minJump(int[] jump) {
		int n = jump.length;
		int[] queue = new int[n];
		int l = 0;
		int r = 0;
		queue[r++] = 0;
		IndexTree it = new IndexTree(n);
		// 1...n初始化的时候 每个位置填上1
		for (int i = 1; i < n; i++) {
			it.add(i, 1);
		}
		int step = 0;
		while (l != r) { // 队列里面还有东西
			// tmp记录了当前层的终止位置！
			int tmp = r;
			// 当前层的所有节点，都去遍历!
			for (; l < tmp; l++) {
				int cur = queue[l];
				int forward = cur + jump[cur];
				if (forward >= n) {
					return step + 1;
				}
				if (it.value(forward) != 0) {
					queue[r++] = forward;
					it.add(forward, -1);
				}
				// cur
				// 1....cur-1 cur
				while (it.sum(cur - 1) != 0) {
					int find = find(it, cur - 1);
					it.add(find, -1);
					queue[r++] = find;
				}
			}
			step++;
		}
		return -1;
	}

	public static int find(IndexTree it, int right) {
		int left = 0;
		int mid = 0;
		int find = 0;
		while (left <= right) {
			mid = (left + right) / 2;
			if (it.sum(mid) > 0) {
				find = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return find;
	}

	public static class IndexTree {

		private int[] tree;
		private int N;

		public IndexTree(int size) {
			N = size;
			tree = new int[N + 1];
		}

		public int value(int index) {
			if (index == 0) {
				return sum(0);
			} else {
				return sum(index) - sum(index - 1);
			}
		}

		public int sum(int i) {
			int index = i + 1;
			int ret = 0;
			while (index > 0) {
				ret += tree[index];
				index -= index & -index;
			}
			return ret;
		}

		public void add(int i, int d) {
			int index = i + 1;
			while (index <= N) {
				tree[index] += d;
				index += index & -index;
			}
		}

	}

	// 感谢黄汀同学
	// 弄出了时间复杂度O(N)的过程
	// 和大厂刷题班，第10节，jump game类似
	public int minJump2(int[] jump) {
		int N = jump.length;
		int ans = N;
		int next = jump[0];
		if (next >= N) {
			return 1;
		}
		if (next + jump[next] >= N) {
			return 2;
		}
		// dp[i] : 来到i位置，最少跳几步？
		int[] dp = new int[N + 1];
		Arrays.fill(dp, N);
		// dis[i] : <= i步的情况下，最远能跳到哪？
		int[] dis = new int[N];
		// 如果从0开始向前跳，<=1步的情况下，最远当然能到next
		dis[1] = next;
		// 如果从0开始向前跳，<=2步的情况下，最远可能比next + jump[next]要远，
		// 这里先设置，以后可能更新
		dis[2] = next + jump[next];
		dp[next + jump[next]] = 2;
		int step = 1;
		for (int i = 1; i < N; i++) {
			if (i > dis[step]) {
				step++;
			}
			dp[i] = Math.min(dp[i], step + 1);
			next = i + jump[i];
			if (next >= N) {
				ans = Math.min(ans, dp[i] + 1);
			} else if (dp[next] > dp[i] + 1) {
				dp[next] = dp[i] + 1;
				dis[dp[next]] = Math.max(dis[dp[next]], next);
			}
		}
		return ans;
	}

}
