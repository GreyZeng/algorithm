package snippet;
// 把Code_0065_BestSplitForAll中提到的，min{左部分累加和，右部分累加和}，定义为S(N-1)，也就是说：

// `S(N-1)`表示在`arr[0…N-1]`范围上，做最优划分所得到的`min{左部分累加和，右部分累加和}的最大值`, 
// 现在要求返回一个长度为N的s数组，
// s[i]表示在`arr[0…i]`范围上，做最优划分所得到的min{左部分累加和，右部分累加和}的最大值, 得到整个s数组的过程，做到时间复杂度O(N)

// tips:
// 1. sum和范围有单调性
// 2. 范式遵从 差(好(S左，S右))
//    都有O(N)的优化
// 3. 前缀和加速（前缀和数组多加一位，不需要判断边界）
public class Code_0070_BestSplitForEveryPosition {

	public static int[] bestSplit1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return new int[0];
		}
		int N = arr.length;
		int[] ans = new int[N];
		ans[0] = 0;
		for (int range = 1; range < N; range++) {
			for (int s = 0; s < range; s++) {
				int sumL = 0;
				for (int L = 0; L <= s; L++) {
					sumL += arr[L];
				}
				int sumR = 0;
				for (int R = s + 1; R <= range; R++) {
					sumR += arr[R];
				}
				ans[range] = Math.max(ans[range], Math.min(sumL, sumR));
			}
		}
		return ans;
	}

	// 求原来的数组arr中，arr[L...R]的累加和
	public static int sum(int[] sum, int L, int R) {
		return sum[R + 1] - sum[L];
	}

	public static int[] bestSplit2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return new int[0];
		}
		int N = arr.length;
		int[] ans = new int[N];
		ans[0] = 0;
		int[] sum = new int[N + 1];
		for (int i = 0; i < N; i++) {
			sum[i + 1] = sum[i] + arr[i];
		}
		for (int range = 1; range < N; range++) {
			for (int s = 0; s < range; s++) {
				int sumL = sum(sum, 0, s);
				int sumR = sum(sum, s + 1, range);
				ans[range] = Math.max(ans[range], Math.min(sumL, sumR));
			}
		}
		return ans;
	}

	// 四边形不等式优化
	public static int[] bestSplit3(int[] arr) {
		if (arr == null || arr.length == 0) {
			return new int[0];
		}
		int n = arr.length;
		if (n == 1) {
			return new int[] { 0 };
		}
		int[] sum = new int[n + 1];
		for (int i = 0; i < n; i++) {
			sum[i + 1] = sum[i] + arr[i];
		}
		// s[0] = 0
		int[] s = new int[n];
		s[1] = Math.min(arr[0], arr[1]);
		int bestSplitPosition = 1;
		int begin = 0;
		for (int i = 2; i < n; i++) {
			// 分割点从1开始，不回退
			int ans = s[i - 1];
			for (begin = bestSplitPosition; begin <= i; begin++) {
				int right = sum(sum, begin, i);
				int left = sum(sum, 0, begin - 1);
				if (Math.min(right, left) >= ans) {
					bestSplitPosition = begin;
					ans = Math.min(right, left);
				} else {
					break;
				}
			}
			s[i] = ans;
		}
		return s;

	}

	public static int[] randomArray(int len, int max) {
		int[] ans = new int[len];
		for (int i = 0; i < len; i++) {
			ans[i] = (int) (Math.random() * max);
		}
		return ans;
	}

	public static boolean isSameArray(int[] arr1, int[] arr2) {
		if (arr1.length != arr2.length) {
			return false;
		}
		int N = arr1.length;
		for (int i = 0; i < N; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int N = 20;
		int max = 30;
		int testTime = 1000000;
		System.out.println("测试开始");
		int[] arr = null;
		int[] ans1 = null;
		int[] ans2 = null;
		int[] ans3 = null;
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * N);
			arr = randomArray(len, max);
			ans1 = bestSplit1(arr);
			ans2 = bestSplit2(arr);
			ans3 = bestSplit3(arr);
			if (!isSameArray(ans1, ans2) || !isSameArray(ans1, ans3)) {
				System.out.println("Oops!");
				break;
			}
		}
		System.out.println("测试结束"); 
	}
}
