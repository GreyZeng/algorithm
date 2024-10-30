package grey.algorithm;

//归并排序
//1）整体是递归，左边排好序+右边排好序+merge让整体有序
//2）让其整体有序的过程里用了排外序方法
//3）利用master公式来求解时间复杂度
//T(N) = 2*T(N/2) + O(N^1)
//根据master可知时间复杂度为O(N*logN)
//merge过程需要辅助数组，所以额外空间复杂度为O(N)
//归并排序的实质是把比较行为变成了有序信息并传递，比O(N^2)的排序快
//笔记：https://www.cnblogs.com/greyzeng/p/16653063.html
//测评链接：https://www.luogu.com.cn/problem/P1177
//acm测评风格，注意输入输出的处理
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Code_0023_Luogu_P1177_MergeSort {
	public static final int MAXN = 100001;
	public static final int[] arr = new int[MAXN];
	public static final int[] help = new int[MAXN];
	public static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		in.nextToken();
		n = (int) in.nval;
		for (int i = 0; i < n; i++) {
			in.nextToken();
			arr[i] = (int) in.nval;
		}
		// mergeSort1(0, n - 1);
		mergeSort2(0, n - 1);
		for (int i = 0; i < n - 1; i++) {
			out.print(arr[i] + " ");
		}
		out.println(arr[n - 1]);
		out.flush();
		out.close();
		br.close();
	}

	// 递归方法实现merge sort
	// 在 l ... r 实现排序
	public static void mergeSort1(int l, int r) {
		if (l >= r) {
			return;
		}
		int mid = l + ((r - l) >> 1);
		mergeSort1(l, mid);
		mergeSort1(mid + 1, r);
		merge(l, mid, r);
	}

	public static void merge(int l, int m, int r) {
		int i = l;
		int s = l;
		int e = m + 1;
		while (s <= m && e <= r) {
			if (arr[s] <= arr[e]) {
				help[i++] = arr[s++];
			} else {
				help[i++] = arr[e++];
			}
		}
		while (s <= m) {
			help[i++] = arr[s++];
		}
		while (e <= r) {
			help[i++] = arr[e++];
		}
		for (i = l; i <= r; i++) {
			arr[i] = help[i];
		}
	}

	// 迭代版本的merge sort
	public static void mergeSort2(int l, int r) {
		int len = r - l + 1;
		for (int s, e, m, step = 1; step < len; step <<= 1) {
			s = 0;
			while (s < len) {
				// 存在左组
				m = s + step - 1; // 中间位置
				if (m + 1 >= len) {
					// 没有右组了
					// 说明已经排好了
					break;
				}
				// 右组的结束位置
				e = Math.min(len - 1, s + (step << 1) - 1);
				merge(s, m, e);
				s = e + 1;
			}
		}
	}
}
