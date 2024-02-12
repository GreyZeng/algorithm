package class_2023_03_1_week;

// 主席树详解
// 测试链接 : https://www.luogu.com.cn/problem/P3834
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下所有代码，把主类名改成Main，可以通过
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Code04_ChairmanTree {

	public static int MAXN = 200010;

	// 输入数据相关
	// 数组 : {100, 35, 4,  800}
	// 排序 : {4 , 35, 100, 800}
	//        1    2    3    4
	// 1  2   3   4
	// 1 ~ 800
	// 1 ~ 4
	public static int[] origin = new int[MAXN];
	public static int[] sorted = new int[MAXN];
	
	
	// 每个版本的线段树头部
	// 线段树不是基于下标的，基于值，基于值转化之后的rank
	// root[i] : i位置的数，加入之后，线段树的头部节点编号
	public static int[] root = new int[MAXN];

	// 建树相关，当你的数组个数是n，一般来讲开32 * n
	public static int[] left = new int[MAXN << 5];
	public static int[] right = new int[MAXN << 5];
	public static int[] sum = new int[MAXN << 5];

	public static int cnt, n, m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			cnt = 0;
			n = (int) in.nval;
			in.nextToken();
			m = (int) in.nval;
			for (int i = 1; i <= n; i++) {
				in.nextToken();
				origin[i] = (int) in.nval;
				sorted[i] = origin[i];
			}
			Arrays.sort(sorted, 1, n + 1);
			root[0] = build(1, n);
			for (int i = 1; i <= n; i++) {
				int x = rank(origin[i]);
				root[i] = insert(root[i - 1], 1, n, x);
			}
			for (int i = 1; i <= m; i++) {
				in.nextToken();
				int L = (int) in.nval;
				in.nextToken();
				int R = (int) in.nval;
				in.nextToken();
				int K = (int) in.nval;
				// L..R K
				// R - (L-1)
				int ansIndex = query(root[L - 1], root[R], K, 1, n);
				out.println(sorted[ansIndex]);
				out.flush();
			}
		}
	}

	// 0版本来说，值的范围l~r, rt, 0个
	public static int build(int l, int r) {
		int rt = ++cnt;
		sum[rt] = 0;
		if (l < r) {
			int mid = (l + r) / 2;
			left[rt] = build(l, mid);
			right[rt] = build(mid + 1, r);
		}
		return rt;
	}

	// 当前范围是 l ~ r
	// pre：这个范围在上一个版本的编号
	// 当前版本，在l~r上加数，加x
	// 当前版本，l~r范围，编号返回
	public static int insert(int pre, int l, int r, int x) {
		int rt = ++cnt;
		left[rt] = left[pre];
		right[rt] = right[pre];
		sum[rt] = sum[pre] + 1;
		if (l < r) {
			int mid = (l + r) / 2;
			if (x <= mid) {
				left[rt] = insert(left[pre], l, mid, x);
			} else {
				right[rt] = insert(right[pre], mid + 1, r, x);
			}
		}
		return rt;
	}

	// 请查询  下标！在 : 32 ~ 54 范围上，排名第9的数字！
	// 54版本 - 31版本的线段树，请加工出来！排名第9的数字！
	// u : 31版本
	// v : 54版本
	// l ~ r : 值的范围，在线段树上!
	public static int query(int u, int v, int k, int l, int r) {
		if (l == r) {
			return l;
		}
		int leftSize = sum[left[v]] - sum[left[u]];
		int mid = (l + r) / 2;
		if (leftSize >= k) {
			return query(left[u], left[v], k, l, mid);
		} else {
			return query(right[u], right[v], k - leftSize, mid + 1, r);
		}
	}

	public static int rank(int v) {
		int l = 1;
		int r = n;
		int m = 0;
		int ans = 0;
		while (l <= r) {
			m = (l + r) / 2;
			if (sorted[m] <= v) {
				ans = m;
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return ans;
	}

}
