package class_2021_11_4_week;

import java.util.Arrays;

// 来自真实面试，同学给我的问题
// 限制：0 <= start <= end，0 <= target <= 64
// [start,end]范围上的数字，有多少数字二进制中1的个数等于target
public class Code03_StartToEndBinaryOneTarget {

	// 暴力方法
	// 为了验证
	public static long nums1(long start, long end, int target) {
		if (start < 0 || end < 0 || start > end || target < 0) {
			return -1;
		}
		long ans = 0;
		for (long i = start; i <= end; i++) {
			if (bitOne(i) == target) {
				ans++;
			}
		}
		return ans;
	}

	public static int bitOne(long num) {
		int bits = 0;
		for (int i = 63; i >= 0; i--) {
			if ((num & (1L << i)) != 0) {
				bits++;
			}
		}
		return bits;
	}

	// 正式方法
	// 递归展示其思路，但是不做动态规划的优化
	public static long nums2(long start, long end, int target) {
		if (start < 0 || end < 0 || start > end || target < 0) {
			return -1;
		}
		if (start == 0 && end == 0) {
			return target == 0 ? 1 : 0;
		}
		// 寻找end这数，最高位的1在哪？
		int ehigh = 62;
		while ((end & (1L << ehigh)) == 0) {
			ehigh--;
		}
		if (start == 0) {
			return process2(ehigh, 0, target, end);
		} else { // 170 ~ 3657 0 ~ 169 0 ~ 3657
			start--;
			int shigh = 62;
			while (shigh >= 0 && (start & (1L << shigh)) == 0) {
				shigh--;
			}
			return process2(ehigh, 0, target, end) - process2(shigh, 0, target, start);
		}
	}
	// 11 10 9 8 7 6 5 4 3 2 1 0
	// num : 0 1 1 0 0 1 1 0 0 1 0 1 0
	// 1.....
	// 0.....

	// 如果num最高位的1在i位，也就是说num[i...0]才有意义，比i再高的位置都是0
	// 那么从第i位开始做决定，依次往低位进行决定
	// index : 当前来到哪一位做决定，index在i~0之间，从高到低
	// less : 之间做的决定是不是已经比num小了
	// rest : 还剩几个1需要出现
	// long process(index, less, rest, num)含义 :
	// [i...index+1]上面已经做过决定了，接下来要在[index...0]上面做决定
	// 在[i...index+1]上面的决定是不是比num[i...index+1]小，
	// 如果是，less = 1
	// 如果还没有，一定说明之前的决定==num[i...index+1]，此时less = 0
	// 在[index...0]上面做决定的过程中一定要出现rest个1
	// 返回[index...0]上，有多少合法的决定
	// 这个方法可以改动态规划，因为：index范围62~0, less范围0或者1，rest范围0~target
	// 自己改动态规划吧
	// 只有index、less、rest这三个有效可变参数、num是固定参数
	// 所以可以改成三维动态规划
	//
	// num [h....index+1] 决定完了，需要保证之前做的决定，不能比num大，
	// 1) 之前做的决定 已经小于 num所对应的前缀状态了，
	// 2) 之前做的决定 等于 num所对应的前缀状态了，
	// index..... 去做决定吧！
	// less == 1 之前做的决定 已经小于 num所对应的前缀状态了
	// less == 0 之前做的决定 等于 num所对应的前缀状态
	// 剩余几个1，需要出现！
	// [index.....]
	// index -> 62~0 63种变化
	// less -> 0 1 2种变化
	// rest -> 0 64种变化
	// 63 * 2 * 64
	public static long process2(int index, int less, int rest, long num) {
		if (rest > index + 1) {
			return 0;
		}
		// rest <= index + 1
		if (rest == 0) {
			return 1L;
		}
		// 0 < rest <= index + 1
		// 还有1需要去消耗
		// 位数也够用
		if (less == 1) { // less == 1 之前做的决定 已经小于 num所对应的前缀状态了
			if (rest == index + 1) {
				return 1;
			} else {
				// 后面剩余的位数 > 需要消耗掉1的数量的！
				// 某些位置填1，某些位置填0
				// index 0 1
				// index 0
				// process2(index - 1, 1, rest, num );
				// index 1
				// process2(index - 1, 1, rest - 1, num);
				return process2(index - 1, 1, rest - 1, num) + process2(index - 1, 1, rest, num);
			}
		} else { // less == 0, 之前做的决定 等于 num所对应的前缀状态的
			if (rest == index + 1) { // 后面剩余的位数，必须都填1，才能消耗完
				// index
				// 1 1 1 1 1 1 1 1 1
				// num
				// 1
				// index 1
				//
				// num 111111111 1
				return (num & (1L << index)) == 0 ? 0 : process2(index - 1, 0, rest - 1, num);
			} else {
				// less == 0, 之前做的决定 等于 num所对应的前缀状态的
				// 后面剩余的位数 > 需要消耗掉1的数量的！
				// 某些位置填1，某些位置填0
				if ((num & (1L << index)) == 0) {
					return process2(index - 1, 0, rest, num);
				} else { // num 当前位置 1
					// index 1
					// index 0
					return process2(index - 1, 0, rest - 1, num) + process2(index - 1, 1, rest, num);
				}
			}
		}
	}

	// 最优解方法
	// 方法二的思路 + 动态规划
	public static long nums3(long start, long end, int target) {
		if (start < 0 || end < 0 || start > end || target < 0) {
			return -1;
		}
		if (start == 0 && end == 0) {
			return target == 0 ? 1 : 0;
		}
		int ehigh = 62;
		while ((end & (1L << ehigh)) == 0) {
			ehigh--;
		}
		long[][][] dpe = new long[ehigh + 1][2][target + 1];
		for (int i = 0; i <= ehigh; i++) {
			Arrays.fill(dpe[i][0], -1);
			Arrays.fill(dpe[i][1], -1);
		}
		long anse = process3(ehigh, 0, target, end, dpe);
		if (start == 0) {
			return anse;
		} else {
			start--;
			int shigh = 62;
			while (shigh >= 0 && (start & (1L << shigh)) == 0) {
				shigh--;
			}
			long[][][] dps = new long[shigh + 1][2][target + 1];
			for (int i = 0; i <= shigh; i++) {
				Arrays.fill(dps[i][0], -1);
				Arrays.fill(dps[i][1], -1);
			}
			long anss = process3(shigh, 0, target, start, dps);
			return anse - anss;
		}
	}

	// dp 傻缓存
	// dp 63 * 2 * 64
	// 一定能装下所有的解！
	// index+less+rest
	// dp[index][less][rest] 请直接拿数据
	// dp[index][less][rest] == -1 表示 没算过，去算！
	// dp[index][less][rest] != -1 表示 算过！结果就是dp[index][less][rest]
	public static long process3(int index, int less, int rest, long num, long[][][] dp) {
		if (rest > index + 1) {
			return 0;
		}
		if (rest == 0) {
			return 1L;
		}
		if (dp[index][less][rest] != -1) {
			return dp[index][less][rest];
		}
		// 没算过！
		long ans = 0;
		if (less == 1) {
			if (rest == index + 1) {
				ans = 1;
			} else {
				ans = process3(index - 1, 1, rest - 1, num, dp) + process3(index - 1, 1, rest, num, dp);
			}
		} else {
			if (rest == index + 1) {
				ans = (num & (1L << index)) == 0 ? 0 : process3(index - 1, 0, rest - 1, num, dp);
			} else {
				if ((num & (1L << index)) == 0) {
					ans = process3(index - 1, 0, rest, num, dp);
				} else {
					ans = process3(index - 1, 0, rest - 1, num, dp) + process3(index - 1, 1, rest, num, dp);
				}
			}
		}
		dp[index][less][rest] = ans;
		return ans;
	}

	// 利用排列组合的方法
	public static long nums4(long start, long end, int target) {
		if (start < 0 || end < 0 || start > end || target < 0) {
			return -1;
		}
		long anse = process4(63, target, end);
		if (start == 0) {
			return anse;
		} else {
			long anss = process4(63, target, start - 1);
			return anse - anss;
		}
	}

	public static long process4(int index, int rest, long num) {
		if (rest > index + 1) {
			return 0;
		}
		if (rest == 0) {
			return 1;
		}
		if ((num & (1L << index)) == 0) {
			return process4(index - 1, rest, num);
		} else {
			return c(index, rest) + process4(index - 1, rest - 1, num);
		}
	}

	// 求C(N,A)的解
	// N! / (A! * (N - A)!)
	// 即 : (A+1 * A+2 * ... * N) / (2 * 3 * 4 * (N-A))
	// 为了不溢出，每一步求一个最大公约数，然后消掉
	public static long c(long n, long a) {
		if (n < a) {
			return 0L;
		}
		long up = 1L;
		long down = 1L;
		for (long i = a + 1, j = 2; i <= n || j <= n - a;) {
			if (i <= n) {
				up *= i++;
			}
			if (j <= n - a) {
				down *= j++;
			}
			long gcd = gcd(up, down);
			up /= gcd;
			down /= gcd;
		}
		return up / down;
	}

	// 求m和n的最大公约数
	public static long gcd(long m, long n) {
		return n == 0 ? m : gcd(n, m % n);
	}

	public static void main(String[] args) {
		long range = 600L;
		System.out.println("功能测试开始");
		for (long start = 0L; start < range; start++) {
			for (long end = start; end < range; end++) {
				int target = (int) (Math.random() * 10);
				long ans1 = nums1(start, end, target);
				long ans2 = nums2(start, end, target);
				long ans3 = nums3(start, end, target);
				long ans4 = nums4(start, end, target);
				if (ans1 != ans2 || ans1 != ans3 || ans1 != ans4) {
					System.out.println("出错了！");
				}
			}
		}
		System.out.println("功能测试结束");

		long start = 33281731L;
		long end = 204356810L;
		int target = 17;
		long startTime;
		long endTime;
		long ans1;
		long ans2;
		long ans3;
		long ans4;

		System.out.println("大范围性能测试，开始");
		startTime = System.currentTimeMillis();
		ans1 = nums1(start, end, target);
		endTime = System.currentTimeMillis();
		System.out.println("方法一答案：" + ans1 + ", 运行时间(毫秒) : " + (endTime - startTime));
		startTime = System.currentTimeMillis();
		ans2 = nums2(start, end, target);
		endTime = System.currentTimeMillis();
		System.out.println("方法二答案：" + ans2 + ", 运行时间(毫秒) : " + (endTime - startTime));
		startTime = System.currentTimeMillis();
		ans3 = nums3(start, end, target);
		endTime = System.currentTimeMillis();
		System.out.println("方法三答案：" + ans3 + ", 运行时间(毫秒) : " + (endTime - startTime));
		ans4 = nums4(start, end, target);
		endTime = System.currentTimeMillis();
		System.out.println("方法四答案：" + ans4 + ", 运行时间(毫秒) : " + (endTime - startTime));
		System.out.println("大范围性能测试，结束");

		System.out.println("超大范围性能测试，开始");
		start = 88193819381L;
		end = 92371283713182371L;
		target = 30;
		startTime = System.currentTimeMillis();
		ans3 = nums3(start, end, target);
		endTime = System.currentTimeMillis();
		System.out.println("方法三答案：" + ans3 + ", 运行时间(毫秒) : " + (endTime - startTime));
		startTime = System.currentTimeMillis();
		ans4 = nums4(start, end, target);
		endTime = System.currentTimeMillis();
		System.out.println("方法四答案：" + ans4 + ", 运行时间(毫秒) : " + (endTime - startTime));
		System.out.println("超大范围性能测试，结束");
	}

}
