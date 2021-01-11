package snippet;

import java.util.Arrays;

//洗咖啡杯问题
//        给定一个数组，代表每个人喝完咖啡准备刷杯子的时间
//        只有一台咖啡机，一次只能洗一个杯子，时间耗费wash，洗完才能洗下一杯
//        每个咖啡杯也可以自己挥发干净，时间耗费dry，咖啡杯可以并行挥发
//        返回让所有咖啡杯变干净的最早完成时间
//        三个参数：int[] drinks、int wash、int dry
public class Code_0055_WashCoffee {
	public static int wash(int[] drinks, int wash, int dry) {
		Arrays.sort(drinks); // 未排序的话先排序
		// 如果挥发的时间比洗咖啡的时间还快
		if (dry <= wash) {
			return drinks[drinks.length - 1] + dry;
		}
		return p(drinks, wash, dry, 0, 0);
	}

	// 0..i-1位置的杯子已经搞定了,从i开始搞定后续的杯子
	// washLine：i-1杯咖啡洗完后到的时间点
	// 返回最早完成时间
	public static int p(int[] drinks, int wash, int dry, int i, int washLine) {
		if (i == drinks.length - 1) {
			return Math.min(Math.max(drinks[i], washLine) + wash, drinks[i] + dry);
		}

		// 普遍位置
		// 第i杯选择挥发
		int iDry = drinks[i] + dry;
		int iDryNext = p(drinks, wash, dry, i + 1, washLine);
		int dryTo = Math.max(iDry, iDryNext);
		// 第i杯选择用洗咖啡机
		int iWash = Math.max(washLine, drinks[i]) + wash;
		int iWashNext = p(drinks, wash, dry, i + 1, iWash);
		int washTo = Math.max(iWash, iWashNext);
		return Math.min(dryTo, washTo);
	}

	public static int wash2(int[] drinks, int wash, int dry) {
		Arrays.sort(drinks); // 未排序的话先排序
		// 如果挥发的时间比洗咖啡的时间还快
		if (dry <= wash) {
			return drinks[drinks.length - 1] + dry;
		}
		int limit = 0;
		for (int drink : drinks) {
			limit = Math.max(limit, drink) + wash;
		}
		int M = drinks.length;
		int N = limit; // 所有杯子都通过咖啡机来洗的话，咖啡机洗碗所有杯子来到的时间点
		int[][] dp = new int[M][N + 1];
		for (int i = 0; i <= N; i++) {
			dp[M - 1][i] = Math.min(Math.max(drinks[M - 1], i) + wash, drinks[M - 1] + dry);
		}
		for (int i = M - 2; i >= 0; i--) {
			for (int washLine = 0; washLine <= N; washLine++) {
				int iWash = Math.max(washLine, drinks[i]) + wash;
				if (iWash <= N) {
					iWash = Math.max(iWash, dp[i + 1][iWash]);
				}
				int iDry = drinks[i] + dry;
				int iDryNext = dp[i + 1][washLine];
				int dryTo = Math.max(iDry, iDryNext);
				// 第i杯选择用洗咖啡机
				dp[i][washLine] = Math.min(dryTo, iWash);
			}
		}

		return dp[0][0];
	}

	public static void main(String[] args) {
		int[] arr = { 1, 1, 5, 5, 7, 1, 1, 12, 1, 3, 12, 1, 1, 1, 12 };
		int a = 2;
		int b = 7;
		System.out.println(wash(arr, a, b));
		System.out.println(wash2(arr, a, b));
	}
}
