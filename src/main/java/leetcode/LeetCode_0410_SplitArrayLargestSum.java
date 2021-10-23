package leetcode;

//给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
//
//        设计一个算法使得这 m 个子数组各自和的最大值最小。
//
//         
//
//        示例 1：
//
//        输入：nums = [7,2,5,10,8], m = 2
//        输出：18
//        解释：
//        一共有四种方法将 nums 分割为 2 个子数组。 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
//        因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
//        示例 2：
//
//        输入：nums = [1,2,3,4,5], m = 2
//        输出：9
//        示例 3：
//
//        输入：nums = [1,4,4], m = 3
//        输出：4
//         
//
//        提示：
//
//        1 <= nums.length <= 1000
//        0 <= nums[i] <= 106
//        1 <= m <= min(50, nums.length)
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode-cn.com/problems/split-array-largest-sum
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
//tips:
//        从左往右的尝试模型，按最后一个画家负责的情况枚举情况
//        k块
//        min(max(s1,s2...sk))
//        四边形不等式不是最优解
//        O(N * K)
//
//        目标定，至少需要几个画家？
//        累加和--》二分--》几个画家
//
//        有一个最优解
//
//        1. 先定一个目标（比如定成累加和 为 0 ~ sum 之间的一个值） 看下需要划分几块
//        2. 二分 达标位置（取最左的位置）
//        O(N*log2Sum)
public class LeetCode_0410_SplitArrayLargestSum {
    // 求原数组arr[L...R]的累加和
	public static int sum(int[] sum, int L, int R) {
		return sum[R + 1] - sum[L];
	}

	// 不优化枚举的动态规划方法，O(N^2 * K)
	public static int splitArray1(int[] nums, int K) {
		int N = nums.length;
		int[] sum = new int[N + 1];
		for (int i = 0; i < N; i++) {
			sum[i + 1] = sum[i] + nums[i];
		}
		int[][] dp = new int[N][K + 1];
		for (int j = 1; j <= K; j++) {
			dp[0][j] = nums[0];
		}
		for (int i = 1; i < N; i++) {
			dp[i][1] = sum(sum, 0, i);
		}
		// 每一行从上往下
		// 每一列从左往右
		// 根本不去凑优化位置对儿！
		for (int i = 1; i < N; i++) {
			for (int j = 2; j <= K; j++) {
				int ans = Integer.MAX_VALUE;
				// 枚举是完全不优化的！
				for (int leftEnd = 0; leftEnd <= i; leftEnd++) {
					int leftCost = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
					int rightCost = leftEnd == i ? 0 : sum(sum, leftEnd + 1, i);
					int cur = Math.max(leftCost, rightCost);
					if (cur < ans) {
						ans = cur;
					}
				}
				dp[i][j] = ans;
			}
		}
		return dp[N - 1][K];
	}

	// 课上现场写的方法，用了枚举优化，O(N * K)
	public static int splitArray2(int[] nums, int K) {
		int N = nums.length;
		int[] sum = new int[N + 1];
		for (int i = 0; i < N; i++) {
			sum[i + 1] = sum[i] + nums[i];
		}
		int[][] dp = new int[N][K + 1];
		int[][] best = new int[N][K + 1];
		for (int j = 1; j <= K; j++) {
			dp[0][j] = nums[0];
			best[0][j] = -1;
		}
		for (int i = 1; i < N; i++) {
			dp[i][1] = sum(sum, 0, i);
			best[i][1] = -1;
		}
		// 从第2列开始，从左往右
		// 每一列，从下往上
		// 为什么这样的顺序？因为要去凑（左，下）优化位置对儿！
		for (int j = 2; j <= K; j++) {
			for (int i = N - 1; i >= 1; i--) {
				int down = best[i][j - 1];
				// 如果i==N-1，则不优化上限
				int up = i == N - 1 ? N - 1 : best[i + 1][j];
				int ans = Integer.MAX_VALUE;
				int bestChoose = -1;
				for (int leftEnd = down; leftEnd <= up; leftEnd++) {
					int leftCost = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
					int rightCost = leftEnd == i ? 0 : sum(sum, leftEnd + 1, i);
					int cur = Math.max(leftCost, rightCost);
					// 注意下面的if一定是 < 课上的错误就是此处！当时写的 <= ！
					// 也就是说，只有取得明显的好处才移动！
					// 举个例子来说明，比如[2,6,4,4]，3个画匠时候，如下两种方案都是最优:
					// (2,6) (4) 两个画匠负责 | (4) 最后一个画匠负责
					// (2,6) (4,4)两个画匠负责 | 最后一个画匠什么也不负责
					// 第一种方案划分为，[0~2] [3~3]
					// 第二种方案划分为，[0~3] [无]
					// 两种方案的答案都是8，但是划分点位置一定不要移动!
					// 只有明显取得好处时(<)，划分点位置才移动!
					// 也就是说后面的方案如果==前面的最优，不要移动！只有优于前面的最优，才移动
					// 比如上面的两个方案，如果你移动到了方案二，你会得到:
					// [2,6,4,4] 三个画匠时，最优为[0~3](前两个画家) [无](最后一个画家)，
					// 最优划分点为3位置(best[3][3])
					// 那么当4个画匠时，也就是求解dp[3][4]时
					// 因为best[3][3] = 3，这个值提供了dp[3][4]的下限
					// 而事实上dp[3][4]的最优划分为:
					// [0~2]（三个画家处理） [3~3] (一个画家处理)，此时最优解为6
					// 所以，你就得不到dp[3][4]的最优解了，因为划分点已经越过2了
					// 提供了对数器验证，你可以改成<=，对数器和leetcode都过不了
					// 这里是<，对数器和leetcode都能通过
					// 这里面会让同学们感到困惑的点：
					// 为啥==的时候，不移动，只有<的时候，才移动呢？例子懂了，但是道理何在？
					// 哈哈哈哈哈，看了邮局选址问题，你更懵，请看42节！
					if (cur < ans) {
						ans = cur;
						bestChoose = leftEnd;
					}
				}
				dp[i][j] = ans;
				best[i][j] = bestChoose;
			}
		}
		return dp[N - 1][K];
	}

	public static int splitArray3(int[] nums, int M) {
		long sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		long l = 0;
		long r = sum;
		long ans = 0;
		while (l <= r) {
			long mid = (l + r) / 2;
			long cur = getNeedParts(nums, mid);
			if (cur <= M) {
				ans = mid;
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return (int) ans;
	}

	public static int getNeedParts(int[] arr, long aim) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > aim) {
				return Integer.MAX_VALUE;
			}
		}
		int parts = 1;
		int all = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (all + arr[i] > aim) {
				parts++;
				all = arr[i];
			} else {
				all += arr[i];
			}
		}
		return parts;
	}

	public static int[] randomArray(int len, int maxValue) {
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * maxValue);
		}
		return arr;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int N = 100;
		int maxValue = 100;
		int testTime = 10000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * N) + 1;
			int M = (int) (Math.random() * N) + 1;
			int[] arr = randomArray(len, maxValue);
			int ans1 = splitArray1(arr, M);
			int ans2 = splitArray2(arr, M);
			int ans3 = splitArray3(arr, M);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.print("arr : ");
				printArray(arr);
				System.out.println("M : " + M);
				System.out.println("ans1 : " + ans1);
				System.out.println("ans2 : " + ans2);
				System.out.println("ans3 : " + ans3);
				System.out.println("Oops!");
				break;
			}
		}
		System.out.println("测试结束");
	}
}
