package snippet;

// arr是货币数组，其中的值都是正数。再给定一个正数aim, 每个值都认为是一张货币，即便是值相同的货币也认为每一张都是不同的，返回组成aim的方法数
// 例如：arr = {1,1,1}，aim = 2 , 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2 , 一共就3种方法，所以返回3
public class Code_0004_CoinsWayEveryPaperDifferent {
    public static int coinWays(int[] arr, int aim) {
        return p(arr, 0, aim);
    }
    // i 开始到最后，能获取到的方法数是多少
    public static int p(int[] arr, int i, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        if (i == arr.length) {
            return 0;
        }
        int yes = p(arr, i + 1, rest - arr[i]);
        int no = p(arr, i + 1, rest);
        return yes + no;
    }

    public static int dp(int[] arr, int aim) {
		int len = arr.length;
        int[][] dp = new int[len + 1][aim + 1];
        for (int i = 0; i < len + 1; i++) {
            dp[i][0] = 1;
        } 
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 1; j < aim + 1; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j - arr[i] >= 0) {
                    dp[i][j] += dp[i + 1][j - arr[i]];
                }
            }
        }
        return dp[0][aim];
	}

	// 为了测试
	public static int[] randomArray(int maxLen, int maxValue) {
		int N = (int) (Math.random() * maxLen);
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = (int) (Math.random() * maxValue) + 1;
		}
		return arr;
	}

	// 为了测试
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 为了测试
	public static void main(String[] args) {
		int maxLen = 20;
		int maxValue = 30;
		int testTime = 1000000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[] arr = randomArray(maxLen, maxValue);
			int aim = (int) (Math.random() * maxValue);
			int ans1 = coinWays(arr, aim);
			int ans2 = dp(arr, aim);
			if (ans1 != ans2) {
				System.out.println("Oops!");
				printArray(arr);
				System.out.println(aim);
				System.out.println(ans1);
				System.out.println(ans2);
				break;
			}
		}
		System.out.println("测试结束");
	}
}
