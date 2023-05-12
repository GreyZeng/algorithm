package dp;

// arr是货币数组，其中的值都是正数。再给定一个正数aim, 每个值都认为是一张货币，即便是值相同的货币也认为每一张都是不同的，返回组成aim的方法数
// 例如：arr = {1,1,1}，aim = 2 , 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2 , 一共就3种方法，所以返回3
public class Code_CoinsWayEveryPaperDifferent {
    public static int ways(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return aim == 0 ? 1 : 0;
        }
        return p(arr, 0, aim);
    }

    // 从i开始及其往后所有的货币组成aim的方法数是多少
    public static int p(int[] arr, int i, int rest) {
        if (rest == 0) {
            return 1;
        }
        // rest != 0
        // 但是已经没有硬币了
        if (i == arr.length) {
            return 0;
        }
        int ways = p(arr, i + 1, rest);
        if (arr[i] <= rest) {
            ways += p(arr, i + 1, rest - arr[i]);
        }
        return ways;
    }

    public static int dp(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return aim == 0 ? 1 : 0;
        }
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 1;
        }
        // 第0列和最后一行已经填好了
        // 倒数第二行开始填
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j < aim + 1; j++) {
                int ways = dp[i + 1][j];
                if (arr[i] <= j) {
                    ways += dp[i + 1][j - arr[i]];
                }
                dp[i][j] = ways;
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
            int ans1 = ways(arr, aim);
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
