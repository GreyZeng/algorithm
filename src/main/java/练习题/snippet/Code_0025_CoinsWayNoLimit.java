package 练习题.snippet;

// arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim，每个值都认为是一种面值，且认为张数是无限的。返回组成aim的方法数
// 例如：arr = {1,2}，aim = 4 方法如下：1+1+1+1、1+1+2、2+2 一共就3种方法，所以返回3
// https://www.nowcoder.com/questionTerminal/4e05294fc5aa4d4fa8eacef2e606e5a8
public class Code_0025_CoinsWayNoLimit {
    public static int coinWays(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return p(arr, 0, aim);
    }

    // i..... to aim
    public static int p(int[] arr, int i, int rest) {
        if (i == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        // rest not null and i not to end
        int ways = 0;
        for (int zhangs = 0; rest - arr[i] * zhangs >= 0; zhangs++) {
            ways += p(arr, i + 1, rest - arr[i] * zhangs);
        }
        return ways;
    }

    public static int coinWaysDp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        // 0 col finished
        // last row finished
        dp[arr.length][0] = 1;
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                int ways = 0;
                for (int zhangs = 0; j - arr[i] * zhangs >= 0; zhangs++) {
                    ways += dp[i + 1][j - arr[i] * zhangs];
                }
                dp[i][j] = ways;
            }
        }
        return dp[0][aim];
    }

    public static int coinWaysDp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        // 0 col finished
        // last row finished
        dp[arr.length][0] = 1;
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j - arr[i] >= 0) {
                    dp[i][j] += dp[i][j - arr[i]];
                }
            }
        }
        return dp[0][aim];
    }
    
    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinWays(arr, aim);
            int ans2 = coinWaysDp1(arr, aim);
            int ans3 = coinWaysDp2(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
