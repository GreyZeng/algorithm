/*链接：https://www.nowcoder.com/questionTerminal/b2d552cd60b7415fad2612a32e799812
        来源：牛客网

        有一个int数组arr其中只含有1、2和3，分别代表所有圆盘目前的状态，1代表左柱，2代表中柱，3代表右柱，arr[i]的值代表第i+1个圆盘的位置。
        比如，arr=[3,3,2,1]，代表第1个圆盘在右柱上、第2个圆盘在右柱上、第3个圆盘在中柱上、第4个圆盘在左柱上。
        如果arr代表的状态是最优移动轨迹过程中出现的状态，返回arr这种状态是最优移动轨迹中的第几个状态。如果arr代表的状态不是最优移动轨迹过程中出现的状态，则返回-1。

        给定一个int数组arr及数组的大小n，含义如题所述，请返回一个int，代表所求的结果。

        测试样例：
        [3,3]
        返回：3*/
package nowcoder;

public class NowCoder_HanotaII {
	// O(N)
	public static int chkStep(int[] arr, int N) {
		return f(arr, N - 1, 1, 3, 2);
	}
	// n 表示0...n个圆盘 n+1
	// from 从哪个位置
	// to 到哪个位置
	// other 其他位置
	// 返回，这个是最优解的第几步
	// eg: f(arr, 8,1,3,2): arr中表示9个圆盘目前的状态，从左边移动到右边，是最优解的第几步

	// 三层汉诺塔问题
	// 0~i-1 from -> other
	// i from -> to
	// 0~i-1 other -> to
	// 所以i位置没有必要去other位置
	// 如果某一步 arr[i] = other 直接返回-1
	// 如果arr[i] = from 说明 第一步没走完
	// 调用 f(arr, i-1, from ,other, to)
	// 如果arr[i] = to 说明在第三步
	// 因为N层汉诺塔问题的步数是 2^N - 1
	// 第一步走了 2^(i-1) - 1
	// 第二步走了 1步
	// 所以第一步+ 第二步 = 2^(i-1)
	// 剩下第三步走了 2^(i-1) + f(arr, i-1,other,to,from)
	public static int f(int[] arr, int N, int from, int to, int other) {
		if (N == -1) {
			return 0;
		}
		if (arr[N] == other) {
			return -1;
		}
		if (arr[N] == from) {
			return f(arr, N - 1, from, other, to);
		} else {
			int rest = f(arr, N - 1, other, to, from);
			if (rest == -1) {
				return -1;
			}
			return (1 << N) + rest;
		}
	}
}
